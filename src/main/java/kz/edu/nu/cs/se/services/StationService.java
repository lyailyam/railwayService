package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import kz.edu.nu.cs.se.models.entities.StationEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/stations")
public class StationService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStations(@QueryParam("city") String city,
                                @QueryParam("name") String name) {
        List result = null;

        Session session = ConfiguredSessionFactory.getSession();
        session.beginTransaction();

        Query query = session.createQuery("from StationEntity where" +
                "(:city is null or city = :city) and " +
                "(:name is null or name = :name)");

        query.setParameter("city", city);
        query.setParameter("name", name);

        result = query.list();

        session.getTransaction().commit();
        session.close();

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

    @Path("/{station_id: [0-9]+}")
    @GET
    public Response getStation(@PathParam("station_id") Integer stationId) {
        StationEntity result = null;

        Session session = ConfiguredSessionFactory.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from StationEntity where id = :id");
            query.setParameter("id", stationId);

            result = (StationEntity) query.uniqueResult();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } finally {
            session.close();
        }

        return Response.ok().entity(result).build();
    }
}
