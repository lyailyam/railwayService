package kz.edu.nu.cs.se;

import org.jboss.weld.context.http.Http;

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
    @Context
    HttpServletRequest webRequest;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        final HttpSession session = webRequest.getSession();

        Integer userId = (Integer) session.getAttribute("userId");
        System.out.println(userId);
        System.out.println(java.time.LocalTime.now());
        System.out.println(requestContext.getMethod());
        System.out.println(requestContext.getUriInfo().getAbsolutePath().toASCIIString());
        System.out.println(requestContext.getEntityStream().toString());
    }
}
