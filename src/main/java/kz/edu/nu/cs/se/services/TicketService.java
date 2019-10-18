package kz.edu.nu.cs.se.services;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import kz.edu.nu.cs.se.DBConnector;
import kz.edu.nu.cs.se.models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Path("/tickets")
public class TicketService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets(@DefaultValue("5") @QueryParam("user_id") Integer userId,
                                   @DefaultValue("5") @QueryParam("limit") Integer limit,
                                   @DefaultValue("0") @QueryParam("offset") Integer offset) {

        String sql;
        if (userId != null) {
            sql = "SELECT ticket.id, ticket.price, ticket.status, trip.departure_time, trip.arrival_time, trip.status, st1.name, st2.name "
                    + " FROM ticket INNER JOIN trip ON trip.id = ticket.trip_id "
                    + "INNER JOIN station st1 on st1.id = trip.first_station_id "
                    + "INNER JOIN station st2 ON st2.id = trip.last_station_id "
                    + "INNER JOIN train ON train.id = trip.train_id "
                    + "WHERE ticket.user_id = " + userId + " ORDER BY trip.departure_time "
                    + "LIMIT " + limit + " OFFSET " + offset;
        } else {
            sql = "SELECT ticket.id, ticket.price, ticket.status, trip.departure_time, trip.arrival_time, trip.status, st1.name, st2.name "
                    + " FROM ticket INNER JOIN trip ON trip.id = ticket.trip_id "
                    + "INNER JOIN station st1 on st1.id = trip.first_station_id "
                    + "INNER JOIN station st2 ON st2.id = trip.last_station_id "
                    + "INNER JOIN train ON train.id = trip.train_id "
                    + "ORDER BY trip.departure_time "
                    + "LIMIT " + limit + " OFFSET " + offset;
        }

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            List<TicketInfo> ticketList = new CopyOnWriteArrayList<TicketInfo>();

            while (rs.next()) {
                TicketInfo t = new TicketInfo();

                t.setId(rs.getInt("ticket.id"));
                t.setPrice(rs.getDouble("ticket.price"));
                t.setTicket_status(rs.getString("ticket.status"));

                t.setDep_time(rs.getString("trip.departure_time"));
                t.setArr_time(rs.getString("trip.arrival_time"));
                t.setTrip_status(rs.getString("trip.status"));

                t.setSt1_name(rs.getString("st1.name"));
                t.setSt2_name(rs.getString("st2.name"));


                ticketList.add(t);
            }

            return Response.ok(gson.toJson(ticketList)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
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
        String sql = "SELECT ticket.price, ticket.status, trip.departure_time, trip.arrival_time, trip.status, st1.name, st2.name "
                + "FROM ticket INNER JOIN trip ON trip.id = ticket.trip_id "
                + "INNER JOIN station st1 on st1.id = trip.first_station_id "
                + "INNER JOIN station st2 ON st2.id = trip.last_station_id "
                + "INNER JOIN train ON train.id = trip.train_id "
                + "WHERE ticket.id = " + ticketId;

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            rs.next();

            // TODO: Consider using constructor?
            TicketInfo t = new TicketInfo();

            t.setId(ticketId);
            t.setPrice(rs.getDouble("ticket.price"));
            t.setTicket_status(rs.getString("ticket.status"));

            t.setDep_time(rs.getString("trip.departure_time"));
            t.setArr_time(rs.getString("trip.arrival_time"));
            t.setTrip_status(rs.getString("trip.status"));

            t.setSt1_name(rs.getString("st1.name"));
            t.setSt2_name(rs.getString("st2.name"));


            return Response.ok(gson.toJson(t)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
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

