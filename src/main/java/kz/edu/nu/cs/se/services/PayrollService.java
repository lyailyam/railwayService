package kz.edu.nu.cs.se.services;

import kz.edu.nu.cs.se.SessionFactoryListener;
import kz.edu.nu.cs.se.models.entities.PayrollEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Date;

@Path("/payroll")
public class PayrollService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayroll(@QueryParam("employeeId") Integer employeeId,
                               @QueryParam("paymentAmount") Double paymentAmount,
                               @QueryParam("paymentDate") String paymentDate){
        List result;

        Session session = SessionFactoryListener.getSession();
        session.beginTransaction();

        try {
            session.beginTransaction();

            Query query = session.createQuery("from PayrollEntity where" +
                    "(:employeeId is null or employeeId = :employeeId) and " +
                    "(:paymentAmount is null or paymentAmount = :paymentAmount) and " +
                    "(:paymentDate is null or paymentDate = :paymentDate)");

            query.setParameter("employeeId", employeeId);
            query.setParameter("paymentAmount", paymentAmount);
            query.setParameter("paymentDate", paymentDate);

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
    public Response makePayment(PayrollEntity paycheck) {
        Session session = SessionFactoryListener.getSession();

        try {
            session.beginTransaction();
            session.save(paycheck);
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

}
