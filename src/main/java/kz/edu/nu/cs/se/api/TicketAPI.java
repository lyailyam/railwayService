package kz.edu.nu.cs.se.api;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import kz.edu.nu.cs.se.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Path("/ticket")
public class TicketAPI {

    @GET
    @Path("{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserTickets(@PathParam("id") String id) {
        Gson gson = new Gson();
        try {
            // TODO : Check if id exists at the first place
            Connection conn = DBConnector.getDatabaseConnection();
            String sql = "SELECT * FROM `railways-service`.`ticket`WHERE `user_id` = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Ticket> ticketList = new CopyOnWriteArrayList<Ticket>();

            while (rs.next()) {
                Ticket t = new Ticket();

                t.setId(rs.getInt("id"));
                t.setUserId(rs.getInt("user_id"));
                t.setTripId(rs.getInt("trip_id"));
                t.setSeatId(rs.getInt("seat_id"));
                t.setPrice(rs.getDouble("price"));
                t.setStatus(rs.getString("status"));

                ticketList.add(t);
            }

            rs.close();
            stmt.close();
            conn.close();

            return Response.ok(gson.toJson(ticketList)).build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok(gson.toJson(new String("error"))).build();
    }

    @POST
    @Path("{id: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserTickets(@PathParam("id") String id, String body) {
        Gson gson = new Gson();

        // Parsing JSON from POST request body
        Ticket t = gson.fromJson(body, Ticket.class);

        // TODO: upload the ticket data into the database

        System.out.println(t.getId());
        return Response.ok(gson.toJson(new String("test"))).build();
    }


}

