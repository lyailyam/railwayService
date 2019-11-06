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

@Path("/ticketsInfo")
public class TicketInfoService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets(@QueryParam("userId") Integer userId) {

        String sql;
        if (userId != null) {
            sql = "select ticket.id as ticket_id, ticket.price, ticket.status as ticket_status, " +
                    "ticket.name as traveler_name, ticket.surname as traveler_surname, " +
                    "ticket.national_id as traveler_nation_id, user.id as buyer_id, " +
                    "user.firstname as buyer_name, user.surname as buyer_surname, " +
                    "user.national_id as buyer_nation_id, user.activated as buyer_activate_status, user.email as buyer_email, " +
                    " route_legs_stations.route_id, route_legs_stations.first_stat_leg_num, route_legs_stations.first_stat_dep_sched_time, " +
                    "route_legs_stations.first_stat_arr_sched_time, route_legs_stations.last_stat_leg_num, " +
                    "route_legs_stations.last_stat_dep_sched_time, route_legs_stations.last_stat_arr_sched_time, " +
                    "route_legs_stations.first_stat_date, route_legs_stations.first_stat_dep_actual_time, " +
                    "route_legs_stations.first_stat_arr_actual_time, route_legs_stations.first_stat_leg_avail_seats, " +
                    "route_legs_stations.train_id, " +
                    "route_legs_stations.last_stat_date, route_legs_stations.last_stat_dep_actual_time, " +
                    "route_legs_stations.last_stat_arr_actual_time, route_legs_stations.last_stat_leg_avail_seats, " +
                    "route_legs_stations.first_station_id, route_legs_stations.first_station_name, route_legs_stations.first_station_city, " +
                    "route_legs_stations.first_station_longitude, route_legs_stations.first_station_latitude, " +
                    "route_legs_stations.last_station_id, route_legs_stations.last_station_name, route_legs_stations.last_station_city," +
                    "route_legs_stations.last_station_longitude, route_legs_stations.arrive_station_latitude, " +
                    "railcar.num as railcar_num, railcar.capacity as railcar_capacity, railcar.type as railcar_type, " +
                    "seat.num as seat_num, seat.location as seat_location " +
                    "from ticket inner join user on ticket.user_id = user.id " +
                    "inner join seat on seat.num = ticket.seat_num " +
                    "inner join railcar on railcar.num = seat.railcar_num " +
                    "inner join train on train.id = railcar.train_id " +
                    "inner join route_legs_stations on route_legs_stations.train_id = train.id " +
                    "WHERE user.id = " + userId + " ORDER BY first_stat_dep_sched_time ";
        } else {
            sql = "select ticket.id as ticket_id, ticket.price, ticket.status as ticket_status, " +
                    "ticket.name as traveler_name, ticket.surname as traveler_surname, " +
                    "ticket.national_id as traveler_nation_id, user.id as buyer_id, " +
                    "user.firstname as buyer_name, user.surname as buyer_surname, " +
                    "user.national_id as buyer_nation_id, user.activated as buyer_activate_status, user.email as buyer_email, " +
                    " route_legs_stations.route_id, route_legs_stations.first_stat_leg_num, route_legs_stations.first_stat_dep_sched_time, " +
                    "route_legs_stations.first_stat_arr_sched_time, route_legs_stations.last_stat_leg_num, " +
                    "route_legs_stations.last_stat_dep_sched_time, route_legs_stations.last_stat_arr_sched_time, " +
                    "route_legs_stations.first_stat_date, route_legs_stations.first_stat_dep_actual_time, " +
                    "route_legs_stations.first_stat_arr_actual_time, route_legs_stations.first_stat_leg_avail_seats, " +
                    "route_legs_stations.train_id, " +
                    "route_legs_stations.last_stat_date, route_legs_stations.last_stat_dep_actual_time, " +
                    "route_legs_stations.last_stat_arr_actual_time, route_legs_stations.last_stat_leg_avail_seats, " +
                    "route_legs_stations.first_station_id, route_legs_stations.first_station_name, route_legs_stations.first_station_city, " +
                    "route_legs_stations.first_station_longitude, route_legs_stations.first_station_latitude, " +
                    "route_legs_stations.last_station_id, route_legs_stations.last_station_name, route_legs_stations.last_station_city," +
                    "route_legs_stations.last_station_longitude, route_legs_stations.arrive_station_latitude, " +
                    "railcar.num as railcar_num, railcar.capacity as railcar_capacity, railcar.type as railcar_type, " +
                    "seat.num as seat_num, seat.location as seat_location " +
                    "from ticket inner join user on ticket.user_id = user.id " +
                    "inner join seat on seat.num = ticket.seat_num " +
                    "inner join railcar on railcar.num = seat.railcar_num " +
                    "inner join train on train.id = railcar.train_id " +
                    "inner join route_legs_stations on route_legs_stations.train_id = train.id " +
                    "ORDER BY first_stat_dep_sched_time ";
        }

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            // TODO : Check if id exists at the first place
            List<TicketInfo> ticketList = new CopyOnWriteArrayList<>();

            while (rs.next()) {
                TicketInfo t = new TicketInfo();

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
                t.setLastStatLatitude(rs.getDouble("arrive_station_latitude"));

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

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createUserTickets(Ticket ticket) {
//        String sql = "INSERT INTO ticket (user_id, price, seat_id, trip_id, status) "
//                + String.format("VALUES (%s,%s,%s,%s,'%s')", ticket.getUserId(), ticket.getPrice(),
//                ticket.getSeatId(), ticket.getTripId(), ticket.getStatus());
//
//        try(Connection conn = DBConnector.getDatabaseConnection();
//            Statement stmt = conn.createStatement()) {
//            int rs = stmt.executeUpdate(sql);
//            return Response.status(rs).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // TODO: Return a proper return with proper error status and message
//        return Response.ok("test").build();
//    }


//    @DELETE
//    @Path("/{ticket_id: [0-9]+}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteTicket(@PathParam("ticket_id") Integer ticketId) {
//        String sql = "DELETE FROM `railways-service`.`ticket` WHERE id = " + ticketId;
//
//        try(Connection conn = DBConnector.getDatabaseConnection();
//            Statement stmt = conn.createStatement()) {
//            int rs = stmt.executeUpdate(sql);
//
//            return Response.status(rs).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // TODO: Return a proper return with proper error status and message
//        return Response.ok("test").build();
//    }
}



