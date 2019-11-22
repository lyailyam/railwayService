package kz.edu.nu.cs.se.logging;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Logged
@Provider
public class ResponseLoggingFilter implements ContainerResponseFilter {
    static final Logger logger = Logger.getInstance();

    @Context
    HttpServletRequest webRequest;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        final HttpSession session = webRequest.getSession();

        logger.logApi(requestContext, session);
    }
}
