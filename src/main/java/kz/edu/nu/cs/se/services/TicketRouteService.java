package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.logging.Logged;
import kz.edu.nu.cs.se.SessionFactoryListener;
import kz.edu.nu.cs.se.models.entities.TicketRouteEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ticket_route")
public class TicketRouteService {

    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketRoutes(@QueryParam("date") String date,
                                 @QueryParam("routeId") Integer routeId,
                                 @QueryParam("legNum") Integer legNum,
                                 @QueryParam("ticketId") Integer ticketId) {
        List result = null;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from TicketRouteEntity where " +
                    "(:date is null or date = :date) and " +
                    "(:routeId is null or routeId = :routeId) and " +
                    "(:legNum is null or legNum = :legNum) and " +
                    "(:ticketId is null or ticketId = :ticketId)");

            query.setParameter("date", date);
            query.setParameter("routeId", routeId);
            query.setParameter("legNum", legNum);
            query.setParameter("ticketId", ticketId);

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
    public Response addTicketRoute(TicketRouteEntity ticketRoute) {
        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            session.save(ticketRoute);

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
    public Response putTicketRoute(TicketRouteEntity ticketRoute) {
        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            session.saveOrUpdate(ticketRoute);

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

    @Path("/{route_id: [0-9]+}/{leg_num: [0-9]+}/{date}/{ticket_id}")
    @GET
    @Logged
    public Response getTicketRoute(@PathParam("route_id") Integer routeId,
                                   @PathParam("leg_num") Integer legNum,
                                   @PathParam("date") String date,
                                   @PathParam("ticket_id") Integer ticketId) {
        TicketRouteEntity result = null;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from TicketRouteEntity where " +
                    "routeId = :routeId and legNum = :legNum and date = :date and ticketId = :ticketId");

            query.setParameter("routeId", routeId);
            query.setParameter("legNum", legNum);
            query.setParameter("date", date);
            query.setParameter("ticketId", ticketId);

            result = (TicketRouteEntity) query.uniqueResult();

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

    @Path("/{route_id: [0-9]+}/{leg_num: [0-9]+}/{date}/{ticket_id}")
    @DELETE
    @Logged
    public Response deleteTicketRoute(@PathParam("route_id") Integer routeId,
                                      @PathParam("leg_num") Integer legNum,
                                      @PathParam("date") String date,
                                      @PathParam("ticket_id") Integer ticketId) {

        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from TicketRouteEntity where " +
                    "routeId = :routeId and legNum = :legNum and date = :date and ticketId = :ticketId");

            query.setParameter("routeId", routeId);
            query.setParameter("legNum", legNum);
            query.setParameter("date", date);
            query.setParameter("ticketId", ticketId);

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
