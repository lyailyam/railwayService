package kz.edu.nu.cs.se;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Logged
@Provider
public class ResponseLoggingFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        System.out.println(java.time.LocalTime.now());
        System.out.println(requestContext.getMethod());
        System.out.println(requestContext.getUriInfo().getAbsolutePath().toASCIIString());
        System.out.println(requestContext.getEntityStream().toString());
    }
}
