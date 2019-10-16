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

@Path("/tickets")
public class TicketAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets(@QueryParam("user_id") Integer userId,
                                   @DefaultValue("5") @QueryParam("limit") Integer limit,
                                   @DefaultValue("0") @QueryParam("offset") Integer offset) {

        String sql;
        if (userId != null) {
            sql = "SELECT * FROM `railways-service`.`ticket` WHERE user_id = " + userId + " LIMIT " + limit + " OFFSET " + offset;
        } else {
            sql = "SELECT * FROM `railways-service`.`ticket` LIMIT " + limit + " OFFSET " + offset;
        }

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

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

            return Response.ok(gson.toJson(ticketList)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: Return a proper return with proper error status and message
        return Response.ok("test").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserTickets(Ticket ticket) {
        String sql = "INSERT INTO `railways-service`.`ticket` (user_id, price, seat_id, trip_id, status) "
                + String.format("VALUES (%s,%s,%s,%s,'%s')", ticket.getUserId(), ticket.getPrice(),
                ticket.getSeatId(), ticket.getTripId(), ticket.getStatus());

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement()) {
            int rs = stmt.executeUpdate(sql);
            return Response.status(rs).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: Return a proper return with proper error status and message
        return Response.ok("test").build();
    }

    @GET
    @Path("/{ticket_id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicket(@PathParam("ticket_id") Integer ticketId) {
        String sql = "SELECT * FROM `railways-service`.`ticket` WHERE id = " + ticketId;

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            rs.next();

            // TODO: Consider using constructor?
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setUserId(rs.getInt("user_id"));
            ticket.setTripId(rs.getInt("trip_id"));
            ticket.setSeatId(rs.getInt("seat_id"));
            ticket.setPrice(rs.getDouble("price"));
            ticket.setStatus(rs.getString("status"));

            return Response.ok(gson.toJson(ticket)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: Return a proper return with proper error status and message
        return Response.ok("test").build();
    }

    @DELETE
    @Path("/{ticket_id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTicket(@PathParam("ticket_id") Integer ticketId) {
        String sql = "DELETE FROM `railways-service`.`ticket` WHERE id = " + ticketId;

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement()) {
            int rs = stmt.executeUpdate(sql);

            return Response.status(rs).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: Return a proper return with proper error status and message
        return Response.ok("test").build();
    }
}

