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


@Path("/tickets_")
public class TicketService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets(@QueryParam("user_id") Integer userId) {
                               //@DefaultValue("5") @QueryParam("limit") Integer limit,
                               //@DefaultValue("0") @QueryParam("offset") Integer offset) {

        String sql;
        if (userId != null) {
            sql = "SELECT ticket_id, price, ticket_status, traveler_name, traveler_surname, " +
                    "traveler_nation_id, buyer_id, buyer_name, buyer_surname, buyer_nation_id, " +
                    "first_stat_dep_sched_time, first_stat_arr_sched_time, last_stat_dep_sched_time, last_stat_arr_sched_time, " +
                    "first_stat_date, first_stat_dep_actual_time, first_stat_arr_actual_time, " +
                    "first_stat_leg_avail_seats, last_stat_date, last_stat_dep_actual_time, last_stat_arr_actual_time, " +
                    "last_stat_leg_avail_seats, train_id, first_station_id, first_station_city, first_station_name, " +
                    "first_station_longitude, first_station_latitude, last_station_id, last_station_city, last_station_name, " +
                    "last_station_longitude, last_station_latitude, railcar_num, railcar_capacity, railcar_type, " +
                    "seat_num, seat_location "
                    + "FROM ticket_seat_leg "
                    + "WHERE buyer_id = " + userId + " ORDER BY first_stat_dep_sched_time ";
        } else {
            sql = "SELECT ticket_id, price, ticket_status, traveler_name, traveler_surname, " +
                    "traveler_nation_id, buyer_id, buyer_name, buyer_surname, buyer_nation_id, " +
                    "first_stat_dep_sched_time, first_stat_arr_sched_time, last_stat_dep_sched_time, last_stat_arr_sched_time, " +
                    "first_stat_date, first_stat_dep_actual_time, first_stat_arr_actual_time, " +
                    "first_stat_leg_avail_seats, last_stat_date, last_stat_dep_actual_time, last_stat_arr_actual_time, " +
                    "last_stat_leg_avail_seats, train_id, first_station_id, first_station_city, first_station_name, " +
                    "first_station_longitude, first_station_latitude, last_station_id, last_station_city, last_station_name, " +
                    "last_station_longitude, last_station_latitude, railcar_num, railcar_capacity, railcar_type, " +
                    "seat_num, seat_location "
                    + "FROM ticket_seat_leg "
                    + "ORDER BY first_stat_dep_sched_time ";
        }

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            List<TicketServiceInfo> ticketList = new CopyOnWriteArrayList<TicketServiceInfo>();

            while (rs.next()) {
                TicketServiceInfo t = new TicketServiceInfo();

                t.setTicketId(rs.getInt("ticket_id"));
                t.setPrice(rs.getDouble("price"));
                t.setTicketStatus(rs.getString("ticket_status"));

                t.setTravelerName(rs.getString("traveler_name"));
                t.setTravelerSurname(rs.getString("traveler_surname"));
                t.setTravelerNationId(rs.getString("traveler_nation_id"));

                t.setBuyerId(rs.getInt("buyer_id"));
                t.setBuyerName(rs.getString("buyer_name"));
                t.setBuyerSurname(rs.getString("buyer_surname"));
                t.setBuyerNationId(rs.getString("buyer_nation_id"));

                t.setFirstStatDepSchedTime(rs.getString("first_stat_dep_sched_time"));
                t.setFirstStatArrSchedTime(rs.getString("first_stat_arr_sched_time"));
                t.setLastStatDepSchedTime(rs.getString("last_stat_dep_sched_time"));
                t.setLastStatArrSchedTime(rs.getString("last_stat_arr_sched_time"));

                t.setFirstStatDate(rs.getString("first_stat_date"));
                t.setFirstStatDepActualTime(rs.getString("first_stat_dep_actual_time"));
                t.setFirstStatArrActualTime(rs.getString("first_stat_arr_actual_time"));
                t.setFirstStatLegAvailSeats(rs.getInt("first_stat_leg_avail_seats"));

                t.setLastStatDate(rs.getString("last_stat_date"));
                t.setLastStatDepActualTime(rs.getString("last_stat_dep_actual_time"));
                t.setLastStatArrActualTime(rs.getString("last_stat_arr_actual_time"));
                t.setLastStatLegAvailSeats(rs.getInt("last_stat_leg_avail_seats"));

                t.setTrainId(rs.getInt("train_id"));

                t.setFirstStatId(rs.getInt("first_station_id"));
                t.setFirstStatName(rs.getString("first_station_name"));
                t.setFirstStatCity(rs.getString("first_station_city"));
                t.setFirstStatLongitude(rs.getDouble("first_station_longitude"));
                t.setFirstStatLatitude(rs.getDouble("first_station_latitude"));

                t.setLastStatId(rs.getInt("last_station_id"));
                t.setLastStatName(rs.getString("last_station_name"));
                t.setLastStatCity(rs.getString("last_station_city"));
                t.setLastStatLongitude(rs.getDouble("last_station_longitude"));
                t.setLastStatLatitude(rs.getDouble("last_station_latitude"));

                t.setRailcarNum(rs.getInt("railcar_num"));
                t.setRailcarCapacity(rs.getInt("railcar_capacity"));
                t.setRailcarType(rs.getString("railcar_type"));

                t.setSeatNum(rs.getInt("seat_num"));
                t.setSeatLocation(rs.getString("seat_location"));

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
        String sql = "SELECT ticket_id, price, ticket_status, traveler_name, traveler_surname, " +
                "traveler_nation_id, buyer_id, buyer_name, buyer_surname, buyer_nation_id, " +
                "first_stat_dep_sched_time, first_stat_arr_sched_time, last_stat_dep_sched_time, last_stat_arr_sched_time, " +
                "first_stat_date, first_stat_dep_actual_time, first_stat_arr_actual_time, " +
                "first_stat_leg_avail_seats, last_stat_date, last_stat_dep_actual_time, last_stat_arr_actual_time, " +
                "last_stat_leg_avail_seats, train_id, first_station_id, first_station_city, first_station_name, " +
                "first_station_longitude, first_station_latitude, last_station_id, last_station_city, last_station_name, " +
                "last_station_longitude, last_station_latitude, railcar_num, railcar_capacity, railcar_type, " +
                "seat_num, seat_location "
                + "FROM ticket_seat_leg "
                + "WHERE ticket_id = " + ticketId;

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place

            rs.next();

            // TODO: Consider using constructor?
            TicketServiceInfo t = new TicketServiceInfo();

            t.setTicketId(rs.getInt("ticket_id"));
            t.setPrice(rs.getDouble("price"));
            t.setTicketStatus(rs.getString("ticket_status"));

            t.setTravelerName(rs.getString("traveler_name"));
            t.setTravelerSurname(rs.getString("traveler_surname"));
            t.setTravelerNationId(rs.getString("traveler_nation_id"));

            t.setBuyerId(rs.getInt("buyer_id"));
            t.setBuyerName(rs.getString("buyer_name"));
            t.setBuyerSurname(rs.getString("buyer_surname"));
            t.setBuyerNationId(rs.getString("buyer_nation_id"));

            t.setFirstStatDepSchedTime(rs.getString("first_stat_dep_sched_time"));
            t.setFirstStatArrSchedTime(rs.getString("first_stat_arr_sched_time"));
            t.setLastStatDepSchedTime(rs.getString("last_stat_dep_sched_time"));
            t.setLastStatArrSchedTime(rs.getString("last_stat_arr_sched_time"));

            t.setFirstStatDate(rs.getString("first_stat_date"));
            t.setFirstStatDepActualTime(rs.getString("first_stat_dep_actual_time"));
            t.setFirstStatArrActualTime(rs.getString("first_stat_arr_actual_time"));
            t.setFirstStatLegAvailSeats(rs.getInt("first_stat_leg_avail_seats"));

            t.setLastStatDate(rs.getString("last_stat_date"));
            t.setLastStatDepActualTime(rs.getString("last_stat_dep_actual_time"));
            t.setLastStatArrActualTime(rs.getString("last_stat_arr_actual_time"));
            t.setLastStatLegAvailSeats(rs.getInt("last_stat_leg_avail_seats"));

            t.setTrainId(rs.getInt("train_id"));

            t.setFirstStatId(rs.getInt("first_station_id"));
            t.setFirstStatName(rs.getString("first_station_name"));
            t.setFirstStatCity(rs.getString("first_station_city"));
            t.setFirstStatLongitude(rs.getDouble("first_station_longitude"));
            t.setFirstStatLatitude(rs.getDouble("first_station_latitude"));

            t.setLastStatId(rs.getInt("last_station_id"));
            t.setLastStatName(rs.getString("last_station_name"));
            t.setLastStatCity(rs.getString("last_station_city"));
            t.setLastStatLongitude(rs.getDouble("last_station_longitude"));
            t.setLastStatLatitude(rs.getDouble("last_station_latitude"));

            t.setRailcarNum(rs.getInt("railcar_num"));
            t.setRailcarCapacity(rs.getInt("railcar_capacity"));
            t.setRailcarType(rs.getString("railcar_type"));

            t.setSeatNum(rs.getInt("seat_num"));
            t.setSeatLocation(rs.getString("seat_location"));


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
