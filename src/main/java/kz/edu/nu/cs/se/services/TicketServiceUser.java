package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.ConfiguredSessionFactory;
import kz.edu.nu.cs.se.models.TicketServiceInfo;
import kz.edu.nu.cs.se.models.entities.StationEntity;
import kz.edu.nu.cs.se.models.entities.TicketEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/mytickets")
public class TicketServiceUser {

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    //need to get based on id

    public Response getTickets(//@QueryParam("seatNum") Integer seatNum,
                               //@QueryParam("railcarNum") Integer railcarNum,
                               //@QueryParam("trainNum") Integer trainNum,
                               @QueryParam("name") String name,
                               @QueryParam("surname") String surname,
                               @QueryParam("nationalId") String nationalId,
                               @QueryParam("status") String status,
                               @QueryParam("price") double price){
        List result = null;

        Session session = ConfiguredSessionFactory.getSession();
        session.beginTransaction();

        Query query = session.createQuery("from TicketEntity where" +
                //"(:seatNum is null or seatNum = :seatNum) and " +
                //"(:railcarNum is null or railcarNum = :railcarNum) and " +
                //"(:trainNum is null or trainNum = :trainNum) and " +
                "(:name is null or name = :name) and " +
                "(:surname is null or surname = :surname) and " +
                "(:nationalId is null or nationalId = :nationalId) and" +
                "(:status is null or status = :status) and " +
                "(:price is null or price = :price)");



        //query.setParameter("seatNum", seatNum);
        //query.setParameter("railcarNum", railcarNum);
        //query.setParameter("trainNum", trainNum);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("nationalId", nationalId);
        query.setParameter("status", status);
        query.setParameter("price", price);

        result = query.list();

        session.getTransaction().commit();
        session.close();

        return Response.ok().entity(result).build();
    }


}
