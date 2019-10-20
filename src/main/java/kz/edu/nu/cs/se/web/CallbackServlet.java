package kz.edu.nu.cs.se.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles requests to the callback URI. Redirects to the home page or to the referring page if set.
 */
@WebServlet(urlPatterns = "/callback")
public class CallbackServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String referer = (String) request.getSession().getAttribute("Referer");
        String redirectTo = referer != null ? referer : request.getContextPath();

        response.sendRedirect(redirectTo);
    }
}
