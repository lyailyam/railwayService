package kz.edu.nu.cs.se.web;

import com.auth0.json.mgmt.users.User;
import com.auth0.jwt.interfaces.Claim;
import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import kz.edu.nu.cs.se.models.entities.UserEntity;
import kz.edu.nu.cs.se.security.Auth0JwtPrincipal;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
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
        }

        String referer = (String) request.getSession().getAttribute("Referer");
        String redirectTo = referer != null ? referer : "/";

        request.getRequestDispatcher(redirectTo).forward(request, response);
    }
}
