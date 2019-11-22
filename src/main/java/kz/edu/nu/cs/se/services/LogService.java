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
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/logs")
public class LogService {
    private Logger logger = Logger.getInstance();

    @Path("/api")
    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApiLogs() {

        List<Object[]> results = null;
        List data = new ArrayList<HashMap>();

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("select l.logId, l.method, l.timestamp, l.uri, l.role, l.userId," +
                    "u.nationalId, u.surname, u.email, u.firstname " +
                    "from LogApiEntity l left join UserEntity u on l.userId = u.id");

            results = query.getResultList();

            if(!results.isEmpty()) {
                for(Object[] result : results) {
                    HashMap<String, Object> resultMap = new HashMap();
                    resultMap.put("logId", result[0]);
                    resultMap.put("method", result[1]); // sql.Date is deprecated in JDBC
                    resultMap.put("timestamp", result[2]); // sql.Time is deprecated
                    resultMap.put("uri", result[3].toString()); // sql.Time is deprecated
                    resultMap.put("role", result[4]);
                    resultMap.put("userId", result[5]);
                    resultMap.put("nationalId", result[6]);
                    resultMap.put("surname", result[7]);
                    resultMap.put("email", result[8]);
                    resultMap.put("firstname", result[9]);
                    data.add(resultMap);
                }
            }

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

        return Response.ok().entity(data).build();
    }


    @Path("/users")
    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserLogs() {

        List<Object[]> results = null;
        List data = new ArrayList<HashMap>();

        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("select l.logId,  l.timestamp, l.activity, l.userId," +
                    "u.nationalId, u.surname, u.email, u.firstname " +
                    "from LogUserEntity l left join UserEntity u on l.userId = u.id");


            results = query.getResultList();

            if(!results.isEmpty()) {
                for(Object[] result : results) {
                    HashMap<String, Object> resultMap = new HashMap();
                    resultMap.put("logId", result[0]);
                    resultMap.put("timestamp", result[1]); // sql.Time is deprecated
                    resultMap.put("activity", result[2]); // sql.Time is deprecated
                    resultMap.put("userId", result[3]);
                    resultMap.put("nationalId", result[4]);
                    resultMap.put("surname", result[5]);
                    resultMap.put("email", result[6]);
                    resultMap.put("firstname", result[7]);
                    data.add(resultMap);
                }
            }

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
        return Response.ok().entity(data).build();
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

    @Path("/status")
    @GET
    @Logged
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus() {
        return Response.ok().entity(logger.getLevel()).build();
    }

    @Path("/users/clear")
    @DELETE
    @Logged
    public Response clearUserLogs() {
        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from LogUserEntity ");
            query.executeUpdate();

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
        return Response.ok().build();
    }

    @Path("/api/clear")
    @DELETE
    @Logged
    public Response clearApiLogs() {
        Session session = SessionFactoryListener.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from LogApiEntity ");
            query.executeUpdate();

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
        return Response.ok().build();
    }
}
