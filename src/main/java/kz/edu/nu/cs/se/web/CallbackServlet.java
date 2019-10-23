package kz.edu.nu.cs.se.web;

import com.auth0.json.mgmt.users.User;
import com.auth0.jwt.interfaces.Claim;
import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import kz.edu.nu.cs.se.models.entities.UserEntity;
import kz.edu.nu.cs.se.security.Auth0JwtPrincipal;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Principal principal = request.getUserPrincipal();

        if (principal instanceof Auth0JwtPrincipal) {
            Auth0JwtPrincipal auth0JwtPrincipal = (Auth0JwtPrincipal) principal;
            Map<String, Claim> claims = auth0JwtPrincipal.getIdToken().getClaims();

            Session session = ConfiguredSessionFactory.getSession();

            try {
                session.beginTransaction();

                UserEntity user = new UserEntity();
                user.setEmail(claims.get("email").asString());
                user.setFirstname(claims.get("given_name").asString());
                user.setSurname(claims.get("family_name").asString());

                session.saveOrUpdate(user);

                session.getTransaction().commit();
            } catch (PersistenceException e) {
                // Ignore constraint violation
            } finally {
                session.close();
            }
        }

        String referer = (String) request.getSession().getAttribute("Referer");
        String redirectTo = referer != null ? referer : request.getContextPath();

        response.sendRedirect(redirectTo);
    }
}
