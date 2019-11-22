package kz.edu.nu.cs.se.services;


import com.google.gson.Gson;
import kz.edu.nu.cs.se.DBConnector;
import kz.edu.nu.cs.se.models.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Path("/advisories")
public class AdvisoryService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdvisories() {

        String advisoriesSql = "select * from advisory order by date_created, time_created ";

        Gson gson = new Gson();
        List<AdvisoryMessage> messagesList = new CopyOnWriteArrayList<>();

        try {
            Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(advisoriesSql);

            while(rs.next()) {

                AdvisoryMessage adv = new AdvisoryMessage();

                adv.setManagerId(rs.getInt("manager_id"));
                adv.setMessage(rs.getString("adv_message"));
                adv.setDateCreated(rs.getDate("date_created").toString());
                adv.setTimeCreated(rs.getTime("time_created").toString());

                messagesList.add(adv);

            }


            conn.close();
            return Response.ok(gson.toJson(messagesList)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }


    }

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdvisoryMessage(AdvisoryMessage advisory) {
        String sql = "INSERT INTO advisory (manager_id, adv_message, date_created, time_created) "
                + String.format("VALUES (%s,'%s','%s','%s')", advisory.getManagerId(), advisory.getMessage(),
                ticket.getSeatId(), ticket.getTripId(), ticket.getStatus());

        try(Connection conn = DBConnector.getDatabaseConnection();
//            Statement stmt = conn.createStatement()) {
//            int rs = stmt.executeUpdate(sql);
//            return Response.status(rs).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // TODO: Return a proper return with proper error status and message
//        return Response.ok("test").build();


    }*/




}
