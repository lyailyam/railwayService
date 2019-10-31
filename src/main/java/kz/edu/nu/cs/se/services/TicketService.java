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


// TODO: No longer works with updates db
@Path("/tickets_")
public class TicketService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets(@QueryParam("user_id") Integer userId,
                               @DefaultValue("5") @QueryParam("limit") Integer limit,
                               @DefaultValue("0") @QueryParam("offset") Integer offset) {

        String sql;
        if (userId != null) {
            sql = "SELECT ticket.id, ticket.price, user.id, user.firstname, user.surname, ticket.status, "
                    + "date(trip.departure_time), time(trip.departure_time), date(trip.arrival_time), time(trip.arrival_time), "
                    + "trip.status, st1.name, st2.name, railcar.train_id, railcar.id, seat.number, seat.location "
                    + "FROM ticket INNER JOIN trip ON trip.id = ticket.trip_id "
                    + "INNER JOIN station st1 on st1.id = trip.first_station_id "
                    + "INNER JOIN station st2 ON st2.id = trip.last_station_id "
                    + "INNER JOIN seat ON seat.id = ticket.seat_id "
                    + "INNER JOIN railcar ON railcar.id = seat.railcar_id "
                    + "INNER JOIN user ON user.id = ticket.user_id "
                    + "WHERE ticket.user_id = " + userId + " ORDER BY trip.departure_time ";
        } else {
            sql = "SELECT ticket.id, ticket.price, user.id, user.firstname, user.surname, ticket.status, "
                    + "date(trip.departure_time), time(trip.departure_time), date(trip.arrival_time), time(trip.arrival_time), "
                    + "trip.status, st1.name, st2.name, railcar.train_id, railcar.id, seat.number, seat.location "
                    + "FROM ticket INNER JOIN trip ON trip.id = ticket.trip_id "
                    + "INNER JOIN station st1 on st1.id = trip.first_station_id "
                    + "INNER JOIN station st2 ON st2.id = trip.last_station_id "
                    + "INNER JOIN seat ON seat.id = ticket.seat_id "
                    + "INNER JOIN railcar ON railcar.id = seat.railcar_id "
                    + "INNER JOIN user ON user.id = ticket.user_id "
                    + "ORDER BY trip.departure_time ";
        }

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            List<TicketInfo> ticketList = new CopyOnWriteArrayList<TicketInfo>();

            while (rs.next()) {
                TicketInfo t = new TicketInfo();

                t.setTicketId(rs.getInt("ticket.id"));
                t.setPrice(rs.getDouble("ticket.price"));
                t.setTicketStatus(rs.getString("ticket.status"));

                t.setUserId(rs.getInt("user.id"));
                t.setUserFirstName(rs.getString("user.firstname"));
                t.setUserLastName(rs.getString("user.surname"));

                t.setDepDate(rs.getString("date(trip.departure_time)"));
                t.setDepTime(rs.getString("time(trip.departure_time)"));
                t.setArrDate(rs.getString("date(trip.arrival_time)"));
                t.setArrTime(rs.getString("time(trip.arrival_time)"));

                t.setTripStatus(rs.getString("trip.status"));

                t.setFirstStatName(rs.getString("st1.name"));
                t.setLastStatName(rs.getString("st2.name"));

                t.setTrainId(rs.getInt("railcar.train_id"));
                t.setRailcarId(rs.getInt("railcar.id"));
                t.setSeatNum(rs.getString("seat.number"));
                t.setSeatLocation(rs.getString("seat.location"));

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
        String sql = "INSERT INTO ticket (user_id, price, seat_id, trip_id, status) "
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
        String sql = "SELECT ticket.id, ticket.price, user.id, user.firstname, user.surname, ticket.status, "
                + "date(trip.departure_time), time(trip.departure_time), date(trip.arrival_time), time(trip.arrival_time), "
                + "trip.status, st1.name, st2.name, railcar.train_id, railcar.id, seat.number, seat.location "
                + "FROM ticket INNER JOIN trip ON trip.id = ticket.trip_id "
                + "INNER JOIN station st1 on st1.id = trip.first_station_id "
                + "INNER JOIN station st2 ON st2.id = trip.last_station_id "
                + "INNER JOIN seat ON seat.id = ticket.seat_id "
                + "INNER JOIN railcar ON railcar.id = seat.railcar_id "
                + "INNER JOIN user ON user.id = ticket.user_id "
                + "WHERE ticket.id = " + ticketId;

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            rs.next();

            // TODO: Consider using constructor?
            TicketInfo t = new TicketInfo();

            t.setTicketId(rs.getInt("ticket.id"));
            t.setPrice(rs.getDouble("ticket.price"));
            t.setTicketStatus(rs.getString("ticket.status"));

            t.setUserId(rs.getInt("user.id"));
            t.setUserFirstName(rs.getString("user.firstname"));
            t.setUserLastName(rs.getString("user.surname"));

            t.setDepDate(rs.getString("date(trip.departure_time)"));
            t.setDepTime(rs.getString("time(trip.departure_time)"));
            t.setArrDate(rs.getString("date(trip.arrival_time)"));
            t.setArrTime(rs.getString("time(trip.arrival_time)"));

            t.setTripStatus(rs.getString("trip.status"));

            t.setFirstStatName(rs.getString("st1.name"));
            t.setLastStatName(rs.getString("st2.name"));

            t.setTrainId(rs.getInt("railcar.train_id"));
            t.setRailcarId(rs.getInt("railcar.id"));
            t.setSeatNum(rs.getString("seat.number"));
            t.setSeatLocation(rs.getString("seat.location"));



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
