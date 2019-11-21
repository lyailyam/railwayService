package kz.edu.nu.cs.se;

import kz.edu.nu.cs.se.security.Auth0JwtPrincipal;
import org.apache.log4j.Logger;
import org.jboss.weld.context.http.Http;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.stream.Collectors;

@Logged
@Provider
public class ResponseLoggingFilter implements ContainerResponseFilter {

    static final Logger LOGGER = Logger.getLogger(ResponseLoggingFilter.class);

    @Context
    HttpServletRequest webRequest;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        final HttpSession session = webRequest.getSession();

//        Principal principal = webRequest.getUserPrincipal();
//
//        if (principal instanceof Auth0JwtPrincipal) {
//            Auth0JwtPrincipal auth0JwtPrincipal = (Auth0JwtPrincipal) principal;
//
//        }

        JSONObject obj =  (JSONObject) session.getAttribute("appMetadata");
        if(obj != null) {
            System.out.println("logged role="
                    + obj.getJSONObject("app_metadata").getJSONObject("authorization").getJSONArray("roles").get(0));
        }

        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println(userId);
        System.out.println(java.time.LocalTime.now());
        System.out.println(requestContext.getMethod());
        System.out.println(requestContext.getUriInfo().getRequestUri().toASCIIString());
//        String requestBody = new BufferedReader(new InputStreamReader(requestContext.getEntityStream()))
//                .lines()
//                .collect(Collectors.joining("\n"));
//
//        System.out.println(requestBody);

        LOGGER.info(" userId: " + userId + " time: " + java.time.LocalDateTime.now() + " method: " + requestContext.getMethod());

    }
}
