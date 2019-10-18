package kz.edu.nu.cs.se.web;

import com.auth0.AuthenticationController;
import kz.edu.nu.cs.se.security.Auth0AuthenticationConfig;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Redirects to Auth0 to initiate the authentication flow when user makes a login request.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Inject private Auth0AuthenticationConfig config;
    @Inject private AuthenticationController authenticationController;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(buildAuthUrl(request));
    }

    private String buildAuthUrl(HttpServletRequest request) {
        String redirectUrl = String.format(
                "%s://%s:%s/railway_service_war/callback",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort()
        );

        return authenticationController.buildAuthorizeUrl(request, redirectUrl)
                .withAudience("https://" + config.getDomain() + "/userinfo")
                .withScope(config.getScope())
                .build();
    }
}
