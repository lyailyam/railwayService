package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.logging.Logged;
import kz.edu.nu.cs.se.SessionFactoryListener;
import kz.edu.nu.cs.se.models.entities.LegInstanceEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/leg_instances")
public class LegInstanceService {

    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLegInstances(@QueryParam("date") String date,
                                 @QueryParam("routeId") Integer routeId,
                                 @QueryParam("legNum") Integer legNum,
                                 @QueryParam("departActualTime") String departActualTime,
                                 @QueryParam("arrivalActualTime") String arrivalActualTime,
                                 @QueryParam("availableSeats") Integer availableSeats,
                                 @QueryParam("trainId") Integer trainId) {
        List result = null;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from LegInstanceEntity where " +
                    "(:date is null or date = :date) and " +
                    "(:routeId is null or routeId = :routeId) and " +
                    "(:legNum is null or legNum = :legNum) and " +
                    "(:departActualTime is null or departActualTime = :departActualTime) and " +
                    "(:arrivalActualTime is null or arrivalActualTime = :arrivalActualTime) and " +
                    "(:availableSeats is null or availableSeats = :availableSeats) and " +
                    "(:trainId is null or trainId = :trainId)");

            query.setParameter("date", date);
            query.setParameter("routeId", routeId);
            query.setParameter("legNum", legNum);
            query.setParameter("departActualTime", departActualTime);
            query.setParameter("arrivalActualTime", arrivalActualTime);
            query.setParameter("availableSeats", availableSeats);
            query.setParameter("trainId", trainId);

            result = query.list();

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

    @POST
    @Logged
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLegInstance(LegInstanceEntity legInstance) {
        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            session.save(legInstance);

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
    @Logged
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putLegInstance(LegInstanceEntity legInstance) {
        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            session.saveOrUpdate(legInstance);

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

    @Path("/{route_id: [0-9]+}/{leg_num: [0-9]+}/{date}")
    @GET
    @Logged
    public Response getLegInstance(@PathParam("route_id") Integer routeId,
                                @PathParam("leg_num") Integer legNum,
                                @PathParam("date") String date) {
        LegInstanceEntity result = null;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from LegInstanceEntity where " +
                    "routeId = :routeId and legNum = :legNum and date = :date");

            query.setParameter("routeId", routeId);
            query.setParameter("legNum", legNum);
            query.setParameter("date", date);

            result = (LegInstanceEntity) query.uniqueResult();

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

    @Path("/{route_id: [0-9]+}/{leg_num: [0-9]+}/{date}")
    @DELETE
    @Logged
    public Response deleteLegInstance(@PathParam("route_id") Integer routeId,
                                   @PathParam("leg_num") Integer legNum,
                                   @PathParam("date") String date) {

        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from LegInstanceEntity where " +
                    "routeId = :routeId and legNum = :legNum and date = :date");

            query.setParameter("routeId", routeId);
            query.setParameter("legNum", legNum);
            query.setParameter("date", date);

            query.executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } finally {
            session.close();
        }

       return Response.ok().build();
    }
}
