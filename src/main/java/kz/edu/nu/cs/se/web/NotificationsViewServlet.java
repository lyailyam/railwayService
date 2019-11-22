package kz.edu.nu.cs.se.web;

import kz.edu.nu.cs.se.models.Notification;
import kz.edu.nu.cs.se.security.Auth0JwtPrincipal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@WebServlet(urlPatterns = {"/notifications"})
public class NotificationsViewServlet extends HttpServlet {

    public NotificationsViewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Principal principal = request.getUserPrincipal();

        if (principal instanceof Auth0JwtPrincipal) {
            Auth0JwtPrincipal auth0JwtPrincipal = (Auth0JwtPrincipal) principal;
            request.setAttribute("profile", auth0JwtPrincipal.getIdToken().getClaims());
        }

        String id = request.getParameter("id");

        request.setAttribute("id", id);
        request.getRequestDispatcher("WEB-INF/jsp/notificationsTable.jsp").forward(request, response);
    }
}