package kz.edu.nu.cs.se.services;


import kz.edu.nu.cs.se.SessionFactoryListener;
import kz.edu.nu.cs.se.models.entities.AdvisoryEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Path("/advisories")
public class AdvisoryService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdvisories() {
        List result = null;

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from AdvisoryEntity ");

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

    static void cancelRouteMessage(Long routeId, String date) {
        String message = "Route " + routeId + " on " + date + " was cancelled.";
        addMessage(message);
    }

    static void updateRouteMessage(Long routeId, String date) {
        String message = "Route " + routeId + " on " + date + " was updated. Please, check your ticket";
        addMessage(message);
    }

    public static void addMessage(String message) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        AdvisoryEntity entity = new AdvisoryEntity();
        entity.setDatetime(timestamp);

        Session session = SessionFactoryListener.getSession();

        entity.setMessage(message);
        try {
            session.beginTransaction();

            session.save(entity);

            session.getTransaction().commit();
        } catch (HibernateException e)
        {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
