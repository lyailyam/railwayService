package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/stations")
public class StationService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStations() {
        List result = null;

        Session session = ConfiguredSessionFactory.getSession();
        session.beginTransaction();

        result = session.createQuery("from StationEntity").list();

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(result).build();
    }
}
