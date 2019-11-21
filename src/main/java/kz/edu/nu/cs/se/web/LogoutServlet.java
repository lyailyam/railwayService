package kz.edu.nu.cs.se.web;

import kz.edu.nu.cs.se.security.Auth0AuthenticationConfig;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Logs the user out from the Auth0 authorization server and clears the session.
 */
@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    @Inject private Auth0AuthenticationConfig config;

    static final Logger LOGGER = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        // Logging
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));
        LOGGER.info(" userId: " + userId + " time: " + timestamp + " action: " + "logged out");

        clearSession(request);
        response.sendRedirect(getLogoutUrl(request));
    }

    private void clearSession(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
    }

    private String getLogoutUrl(HttpServletRequest request) {
        String returnUrl = String.format("%s://%s", request.getScheme(), request.getServerName());
        int port = request.getServerPort();
        String scheme = request.getScheme();

        if (("http".equals(scheme) && port != 80) ||
                ("https".equals(scheme) && port != 443)) {
            returnUrl += ":" + port;
        }

        returnUrl += request.getContextPath();

        String logoutUrl = String.format(
                "https://%s/v2/logout?client_id=%s&returnTo=%s",
                config.getDomain(),
                config.getClientId(),
                returnUrl
        );

        return logoutUrl;
    }
}
