package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import kz.edu.nu.cs.se.models.Ticket;
import kz.edu.nu.cs.se.models.entities.StationEntity;
import kz.edu.nu.cs.se.models.entities.TicketEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tickets")
public class TicketService_ {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets(@QueryParam("userId") Integer userId,
                               @QueryParam("status") String status,
                               @QueryParam("name") String name,
                               @QueryParam("surname") String surname,
                               @QueryParam("nationalId") String nationalId){
        List result = null;

        Session session = ConfiguredSessionFactory.getSession();
        session.beginTransaction();

        Query query = session.createQuery("from TicketEntity where" +
                "(:userId is null or userId = :userId) and " +
                "(:status is null or status = :status) and " +
                "(:name is null or name = :name) and " +
                "(:surname is null or surname = :surname) and " +
                "(:nationalId is null or nationalId = :nationalId)");

        query.setParameter("userId", userId);
        query.setParameter("status", status);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("nationalId", nationalId);

        result = query.list();

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTicket(TicketEntity ticket) {
        Session session = ConfiguredSessionFactory.getSession();

        try {
            session.beginTransaction();

            session.save(ticket);

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
    public Response putTicket(TicketEntity ticket) {
        Session session = ConfiguredSessionFactory.getSession();

        try {
            session.beginTransaction();

            session.saveOrUpdate(ticket);

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

    @Path("/{ticket_id: [0-9]+}")
    @GET
    public Response getTicket(@PathParam("ticket_id") Integer ticketId) {
        TicketEntity result = null;

        Session session = ConfiguredSessionFactory.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from TicketEntity where id = :ticketId");
            query.setParameter("ticketId", ticketId);

            result = (TicketEntity) query.uniqueResult();

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

    @Path("/{ticket_id: [0-9]+}")
    @DELETE
    public Response deleteTicket(@PathParam("ticket_id") Integer ticketId) {
        Session session = ConfiguredSessionFactory.getSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from TicketEntity where id = :ticketId");
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
