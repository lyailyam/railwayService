package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.SessionFactoryListener;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List result = null;

        Session session = SessionFactoryListener.getSession();
        session.beginTransaction();

        result = session.createQuery("from UserEntity ").list();

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(result).build();
    }
}
