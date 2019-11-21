package kz.edu.nu.cs.se.services;
import javax.naming.InitialContext;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sql.*;


@Path("/ticketsInfo")
public class TicketInfoService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets(@QueryParam("userId") Integer userId) {

        String legsTicketSql = null;
        String userTicketsSql = null;
        if (userId != null) {
            //limit 1 just for checking
            userTicketsSql = "select * from ticket where user_id = " + userId ;
        }


        Gson gson = new Gson();

        if(userId == null) return Response.status(404, "No data found").build();
        try
        {
            Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery(userTicketsSql);
            // TODO : Check if id exists at the first place
            List<TicketInfo> ticketList = new CopyOnWriteArrayList<>();
            //List<TicketInfo> ticketList = new ArrayList<TicketInfo>();

            int uniqTid = -1;
            while (rs3.next()) {
                TicketInfo ticket = new TicketInfo();

                if(uniqTid == rs3.getInt("id")) continue;
                uniqTid = rs3.getInt("id");
                ticket.setTicketId(uniqTid);
                ticket.setPrice(rs3.getDouble("price"));
                ticket.setTicketStatus(rs3.getString("status"));

                ticket.setTravelerName(rs3.getString("name"));
                ticket.setTravelerSurname(rs3.getString("surname"));
                ticket.setTravelerNationId(rs3.getString("national_id"));

                ticket.setBuyerId(rs3.getInt("user_id"));

                ticket.setTrainId(rs3.getInt("train_id"));
                ticket.setRailcarNum(rs3.getInt("railcar_num"));
                ticket.setSeatNum(rs3.getInt("seat_num"));

                ticketList.add(ticket);
            }

            //if(ticketList.isEmpty()) --> return "no tickets"
            int size = ticketList.size();
            for(int i=0; i<size; i++) {
                TicketInfo currTicket = ticketList.remove(0);
                int tid = currTicket.getTicketId();

                //all leg_instances associated with this ticket_id
                legsTicketSql = "select * from ticket inner join ticket_route on ticket.id = ticket_route.ticket_id " +
                        "inner join leg_instance on leg_instance.route_id = ticket_route.route_id and leg_instance.leg_num = ticket_route.leg_num " +
                        "and leg_instance.date = ticket_route.date " +
                        "inner join route_leg on route_leg.route_id = leg_instance.route_id and route_leg.leg_num = leg_instance.leg_num " +
                        "where ticket.user_id = " + userId + " and ticket.id = " + tid + " order by ticket_route.leg_num;";
                Statement stmt1 = conn.createStatement();
                ResultSet rs1 = stmt1.executeQuery(legsTicketSql);

                //first leg_instance
                rs1.next();

                currTicket.setRouteId(rs1.getInt("ticket_route.route_id"));
                currTicket.setFirstStatLegNum(rs1.getInt("ticket_route.leg_num"));
                currTicket.setFirstStatDate(rs1.getDate("ticket_route.date").toString());

                currTicket.setFirstStatDepActualTime(rs1.getTime("leg_instance.depart_actual_time").toString());
                currTicket.setFirstStatDepSchedTime(rs1.getTime("route_leg.depart_scheduled_time").toString());

                int firstStatId = rs1.getInt("route_leg.depart_station_id");
                currTicket.setFirstStatId(firstStatId);

                //last leg_instance
                if(!rs1.last()) {
                    rs1.beforeFirst();
                    rs1.next();
                }
                currTicket.setLastStatLegNum(rs1.getInt("ticket_route.leg_num"));
                currTicket.setLastStatDate(rs1.getDate("ticket_route.date").toString());

                currTicket.setLastStatArrActualTime(rs1.getTime("leg_instance.arrival_actual_time").toString());
                currTicket.setLastStatArrSchedTime(rs1.getTime("route_leg.arrival_scheduled_time").toString());

                int lastStatId = rs1.getInt("route_leg.arrival_station_id");
                currTicket.setLastStatId(lastStatId);


                //first station
                String firstStatSql = "select * from station where id = " + firstStatId;
                Statement stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery(firstStatSql);

                rs2.next();

                currTicket.setFirstStatName(rs2.getString("name"));
                currTicket.setFirstStatCity(rs2.getString("city"));
                currTicket.setFirstStatLongitude(rs2.getDouble("longitude"));
                currTicket.setFirstStatLatitude(rs2.getDouble("latitude"));

                //last station
                String lastStatSql = "select * from station where id = " + lastStatId;
                Statement stmt4 = conn.createStatement();
                ResultSet rs4 = stmt4.executeQuery(lastStatSql);

                rs4.next();

                currTicket.setLastStatName(rs4.getString("name"));
                currTicket.setLastStatCity(rs4.getString("city"));
                currTicket.setLastStatLongitude(rs4.getDouble("longitude"));
                currTicket.setLastStatLatitude(rs4.getDouble("latitude"));

                String railcarSql = "select * from seat inner join railcar on railcar.train_id = seat.train_id and railcar.num = seat.railcar_num " +
                        "where seat.num = " + currTicket.getSeatNum() + " and seat.train_id = " + currTicket.getTrainId() +
                        " and seat.railcar_num = " + currTicket.getRailcarNum();
                Statement stmt5 = conn.createStatement();
                ResultSet rs5 = stmt5.executeQuery(railcarSql);

                rs5.next();
                currTicket.setRailcarType(rs5.getString("type"));
                currTicket.setRailcarCapacity(rs5.getInt("capacity"));
                currTicket.setSeatLocation(rs5.getString("location"));

                ticketList.add(currTicket);

            }

            conn.close();
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



