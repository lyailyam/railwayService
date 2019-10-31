package kz.edu.nu.cs.se.web;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import kz.edu.nu.cs.se.security.Auth0AuthenticationConfig;
import kz.edu.nu.cs.se.security.Auth0JwtPrincipal;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.inject.Inject;
import com.auth0.jwt.interfaces.Claim;
import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import kz.edu.nu.cs.se.models.entities.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

/**
 * Handles requests to the callback URI. Redirects to the home page or to the referring page if set.
 */
@WebServlet(urlPatterns = "/callback")
public class CallbackServlet extends HttpServlet {

    @Inject
    private Auth0AuthenticationConfig config;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();

        if (principal instanceof Auth0JwtPrincipal) {
            Auth0JwtPrincipal auth0JwtPrincipal = (Auth0JwtPrincipal) principal;
            Map<String, Claim> claims = auth0JwtPrincipal.getIdToken().getClaims();

            Integer userId = null;

            Session session = ConfiguredSessionFactory.getSession();

            Query query = session.createQuery("from UserEntity u where u.email = :email ");
            query.setParameter("email", claims.get("email").asString());

            UserEntity user = (UserEntity) query.uniqueResult();

            if (user == null) {
                try {
                    session.beginTransaction();

                    user = new UserEntity();

                    user.setEmail(claims.get("email").asString());
                    user.setFirstname(claims.get("given_name").asString());
                    user.setSurname(claims.get("family_name").asString());

                    userId = (Integer) session.save(user);

                    session.getTransaction().commit();
                } catch (HibernateException e) {
                    session.getTransaction().rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
            } else {
                userId = user.getId();
            }

            request.getSession().setAttribute("userId", userId);

            // Retrieve App data to retrieve Management API Token
            String clientId = config.getClientId();
            String clientSecret = config.getClientSecret();
            String domain = config.getDomain();

            // Retrieve Management API Token to access app metadata for a current user
            try {
                HttpResponse<String> managementApiResponse = Unirest.post(
                        "https://" + domain + "/oauth/token")
                        .header("content-type", "application/x-www-form-urlencoded")
                        .body("grant_type=client_credentials&client_id="
                                + clientId
                                + "&client_secret="
                                + clientSecret
                                + "&audience=https://" + domain + "/api/v2/")
                        .asString();
                String auth0ManagementAPIToken = new JSONObject(managementApiResponse.getBody())
                        .getString("access_token");

                // Get Auth0 User ID
                HttpResponse<String> userIdResponse = Unirest.get(
                        "https://"
                                + domain + "/api/v2/users-by-email?fields=user_id&include_fields=true&email="
                                + auth0JwtPrincipal.getIdToken().getClaim("email").asString())
                        .header("content-type", "application/json")
                        .header("authorization", "Bearer "
                                + auth0ManagementAPIToken)
                        .asString();
                JSONArray userIdJSONArray = new JSONArray(userIdResponse.getBody());
                String authUserId = userIdJSONArray.getJSONObject(0).getString("user_id");

                // Get app metadata with info on groups, roles, permissions
                HttpResponse<String> appMetadataResponse = Unirest.get("https://" + domain + "/api/v2/users/"
                        + authUserId
                        + "?fields=app_metadata&include_fields=true")
                        .header("content-type", "application/json")
                        .header("authorization", "Bearer "
                                + auth0ManagementAPIToken)
                        .asString();
                request.setAttribute("appMetadata", new JSONObject(appMetadataResponse.getBody()));

                JSONObject obj =  new JSONObject(appMetadataResponse.getBody());
                System.out.println("role="
                        + obj.getJSONObject("app_metadata").getJSONObject("authorization").getJSONArray("roles").get(0));
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }

        String referer = (String) request.getSession().getAttribute("Referer");
        String redirectTo = referer != null ? referer : "/";

        request.getRequestDispatcher(redirectTo).forward(request, response);
    }
}
