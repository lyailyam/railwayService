package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.logging.Logged;
import kz.edu.nu.cs.se.SessionFactoryListener;
import kz.edu.nu.cs.se.models.entities.RouteLegEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/route_legs")
public class RouteLegService {

    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRouteLegs(@QueryParam("departStationId") Integer departStation,
                                 @QueryParam("arrivalStationId") Integer arriveStation,
                                 @QueryParam("arrivalScheduledTime") String arrivalScheduledTime,
                                 @QueryParam("departScheduledTime") String departScheduledTime) {
        List result = null;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from RouteLegEntity where " +
                    "(:departStation is null or departStationId = :departStation) and " +
                    "(:arriveStation is null or arrivalStationId = :arriveStation) and " +
                    "(:departScheduledTime is null or departScheduledTime = :departScheduledTime) and " +
                    "(:arrivalScheduledTime is null or arrivalScheduledTime = :arrivalScheduledTime)");

            query.setParameter("departStation", departStation);
            query.setParameter("arriveStation", arriveStation);
            query.setParameter("arrivalScheduledTime", arrivalScheduledTime);
            query.setParameter("departScheduledTime", departScheduledTime);

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
    public Response addRouteLeg(RouteLegEntity routeLeg) {
        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            session.save(routeLeg);

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
    public Response putRouteLeg(RouteLegEntity routeLeg) {
        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            session.saveOrUpdate(routeLeg);

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

    @Path("/{route_id: [0-9]+}/{leg_num: [0-9]+}")
    @GET
    @Logged
    public Response getRouteLeg(@PathParam("route_id") Integer routeId,
                               @PathParam("leg_num") Integer legNum) {
        RouteLegEntity result = null;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from RouteLegEntity where " +
                    "routeId = :routeId and legNum = :legNum ");

            query.setParameter("routeId", routeId);
            query.setParameter("legNum", legNum);

            result = (RouteLegEntity) query.uniqueResult();

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

    @Path("/{route_id: [0-9]+}/{leg_num: [0-9]+}")
    @DELETE
    @Logged
    public Response deleteRouteLeg(@PathParam("route_id") Integer routeId,
                                @PathParam("leg_num") Integer legNum) {
        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from RouteLegEntity where " +
                    "routeId = :routeId and legNum = :legNum ");

            query.setParameter("routeId", routeId);
            query.setParameter("legNum", legNum);

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
