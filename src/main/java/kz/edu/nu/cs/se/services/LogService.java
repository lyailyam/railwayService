package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.SessionFactoryListener;
import kz.edu.nu.cs.se.logging.Logged;
import kz.edu.nu.cs.se.logging.Logger;
import kz.edu.nu.cs.se.models.entities.SeatEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logs")
public class LogService {
    private Logger logger = Logger.getInstance();

    @Path("/api")
    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApiLogs() {

        List result = null;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from LogApiEntity");

            result = query.list();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(500).build();
        } finally {
            session.close();
        }

        return Response.ok().entity(result).build();
    }


    @Path("/users")
    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserLogs() {

        List result = null;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from LogUserEntity ");

            result = query.list();

            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(400).build();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return Response.status(500).build();
        } finally {
            session.close();
        }
        return Response.ok().entity(result).build();
    }

    @Path("/disable")
    @POST
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response disableLogs() {
        logger.setLevel(Logger.LoggerLevel.OFF);
        return Response.ok().build();
    }

    @Path("/enable")
    @POST
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response enableLogs() {
        logger.setLevel(Logger.LoggerLevel.ON);
        return Response.ok().build();
    }
}
