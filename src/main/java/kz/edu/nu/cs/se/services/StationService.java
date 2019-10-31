package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import kz.edu.nu.cs.se.models.entities.StationEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.ws.rs.*;
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

        if (result.isEmpty()) {
            return Response.status(404).build();
        }

        return Response.ok().entity(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation(StationEntity station) {
        Session session = ConfiguredSessionFactory.getSession();

        try {
            session.beginTransaction();

            session.save(station);

            session.getTransaction().commit();
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } finally {
            session.close();
        }
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putStation(StationEntity station) {
        Session session = ConfiguredSessionFactory.getSession();

        try {
            session.beginTransaction();

            session.saveOrUpdate(station);

            session.getTransaction().commit();
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } finally {
            session.close();
        }
        return Response.ok().build();
    }
}
