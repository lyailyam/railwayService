package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import kz.edu.nu.cs.se.models.entities.SeatEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/seats")
public class SeatService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeats(@QueryParam("num") Integer num,
                                 @QueryParam("railcarNum") Integer railcarNum,
                                 @QueryParam("trainId") Integer trainId,
                                 @QueryParam("location") String location) {

        List result = null;

        Session session = ConfiguredSessionFactory.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from SeatEntity where " +
                    "(:num is null or num = :num) and " +
                    "(:railcarNum is null or railcarNum = :railcarNum) and " +
                    "(:trainId is null or trainId = :trainId) and " +
                    "(:location is null or location = :location)");

            query.setParameter("num", num);
            query.setParameter("railcarNum", railcarNum);
            query.setParameter("location", location);
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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSeat(SeatEntity seat) {
        Session session = ConfiguredSessionFactory.getSession();

        try {
            session.beginTransaction();

            session.save(seat);

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
    public Response putSeat(SeatEntity seat) {
        Session session = ConfiguredSessionFactory.getSession();

        try {
            session.beginTransaction();

            session.saveOrUpdate(seat);

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

    @Path("/{train_id: [0-9]+}/{railcar_num: [0-9]+}/{num}")
    @GET
    public Response getSeat(@PathParam("train_id") Integer trainId,
                                @PathParam("railcar_num") Integer railcarNum,
                                @PathParam("num") Integer num) {
        SeatEntity result = null;

        Session session = ConfiguredSessionFactory.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from SeatEntity where " +
                    "trainId = :trainId and railcarNum = :railcarNum and num = :num");

            query.setParameter("trainId", trainId);
            query.setParameter("railcarNum", railcarNum);
            query.setParameter("num", num);

            result = (SeatEntity) query.uniqueResult();

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

    @Path("/{train_id: [0-9]+}/{railcar_num: [0-9]+}/{num}")
    @DELETE
    public Response deleteSeat(@PathParam("train_id") Integer trainId,
                                   @PathParam("railcar_num") Integer railcarNum,
                                   @PathParam("num") Integer num) {

        Session session = ConfiguredSessionFactory.getSession();

        try {
            session.beginTransaction();

            Query query = session.createQuery("delete from SeatEntity where " +
                    "trainId = :trainId and railcarNum = :railcarNum and num = :num");

            query.setParameter("trainId", trainId);
            query.setParameter("railcarNum", railcarNum);
            query.setParameter("num", num);

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
