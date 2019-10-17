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
    public Response getTickets(@QueryParam("user_id") Integer userId,
                                   @DefaultValue("5") @QueryParam("limit") Integer limit,
                                   @DefaultValue("0") @QueryParam("offset") Integer offset) {

        String sql;
        if (userId != null) {
            sql = "SELECT * FROM ticket, trip, station st1, station st2, train WHERE trip.id = ticket.trip_id and "
                    + " st1.id = trip.first_station_id and st2.id = trip.last_station_id and train.id = trip.train_id and "
                    + "ticket.user_id = " + userId + "ORDER BY trip.departure_time "
                    + "LIMIT " + limit + " OFFSET " + offset;
        } else {
            sql = "SELECT * FROM ticket LIMIT " + limit + " OFFSET " + offset;
        }

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            List<Ticket> ticketList = new CopyOnWriteArrayList<Ticket>();
            List<Trip> tripList = new CopyOnWriteArrayList<Trip>();
            List<Station> stat1List = new CopyOnWriteArrayList<Station>();
            List<Station> stat2List = new CopyOnWriteArrayList<Station>();
            List<Train> trainList = new CopyOnWriteArrayList<Train>();

            while (rs.next()) {
                Ticket t = new Ticket();
                Trip trip = new Trip();
                Station st1 = new Station();
                Station st2 = new Station();
                Train train = new Train();

                t.setId(rs.getInt(1));
                t.setUserId(rs.getInt("user_id"));
                t.setTripId(rs.getInt("trip_id"));
                t.setSeatId(rs.getInt("seat_id"));
                t.setPrice(rs.getDouble("price"));
                t.setStatus(rs.getString(7));

                trip.setId(rs.getInt(8));
                trip.setDep_datetime(rs.getString("departure_time"));
                trip.setArr_datetime(rs.getString("arrival_time"));
                trip.setStatus(rs.getString(11));
                trip.setFirst_stat_id(rs.getInt("first_station_id"));
                trip.setLast_stat_id(rs.getInt("last_statiom_id"));
                trip.setRoute_id(rs.getInt("route_id"));
                trip.setTrain_id(rs.getInt("train_id"));

                st1.setId(rs.getInt(16));
                st1.setName(rs.getString(17));
                st1.setLongitude(rs.getDouble(18));
                st1.setLatitude(rs.getDouble(19));

                st2.setId(rs.getInt(20));
                st2.setName(rs.getString(21));
                st2.setLongitude(rs.getDouble(22));
                st2.setLatitude(rs.getDouble(23));

                train.setId(rs.getInt(24));


                ticketList.add(t);
                tripList.add(trip);
                stat1List.add(st1);
                stat2List.add(st2);
                trainList.add(train);
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
        String sql = "SELECT * FROM ticket, trip, station st1, station st2, train WHERE trip.id = ticket.trip_id and "
                + " st1.id = trip.first_station_id and st2.id = trip.last_station_id and train.id = trip.train_id and "
                + "ticket.id = " + ticketId;

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            rs.next();

            // TODO: Consider using constructor?
            Ticket ticket = new Ticket();
            Trip trip = new Trip();
            Station st1 = new Station();
            Station st2 = new Station();
            Train train = new Train();

            ticket.setId(rs.getInt(1));
            ticket.setUserId(rs.getInt("user_id"));
            ticket.setTripId(rs.getInt("trip_id"));
            ticket.setSeatId(rs.getInt("seat_id"));
            ticket.setPrice(rs.getDouble("price"));
            ticket.setStatus(rs.getString(7));

            trip.setId(rs.getInt(8));
            trip.setDep_datetime(rs.getString("departure_time"));
            trip.setArr_datetime(rs.getString("arrival_time"));
            trip.setStatus(rs.getString(11));
            trip.setFirst_stat_id(rs.getInt("first_station_id"));
            trip.setLast_stat_id(rs.getInt("last_statiom_id"));
            trip.setRoute_id(rs.getInt("route_id"));
            trip.setTrain_id(rs.getInt("train_id"));

            st1.setId(rs.getInt(16));
            st1.setName(rs.getString(17));
            st1.setLongitude(rs.getDouble(18));
            st1.setLatitude(rs.getDouble(19));

            st2.setId(rs.getInt(20));
            st2.setName(rs.getString(21));
            st2.setLongitude(rs.getDouble(22));
            st2.setLatitude(rs.getDouble(23));

            train.setId(rs.getInt(24));


            return Response.ok(gson.toJson(ticket)).build();
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

