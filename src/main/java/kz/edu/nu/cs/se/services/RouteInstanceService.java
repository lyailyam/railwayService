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

@Path("/route_instances")
public class RouteInstanceService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRouteInstances() {

        String sql = "select le.*, rl.depart_station_name, rl.arrival_station_name, rl.depart_station_id, rl.arrival_station_id\n" +
                "from leg_instance le\n" +
                "join \n" +
                "(select route_leg.*, dep_st.name as depart_station_name, arr_st.name as arrival_station_name from route_leg\n" +
                "join station dep_st on dep_st.id = route_leg.depart_station_id\n" +
                "join station arr_st on arr_st.id = route_leg.arrival_station_id) rl \n" +
                "on rl.route_id = le.route_id and rl.leg_num = le.leg_num\n" +
                "order by date,route_id,leg_num;";

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            List<RouteInstance> routeInstances = new CopyOnWriteArrayList<>();
            String date = "";
            long route_id = 0;
            List<LegInstance> legs = new CopyOnWriteArrayList<>();
            long train_id;
            while (rs.next()) {
                if (!date.equals(rs.getString("date")) || route_id != rs.getLong("route_id")) {
                    date = rs.getString("date");
                    route_id = rs.getLong("route_id");
                    train_id = rs.getLong("train_id");
                    legs = new CopyOnWriteArrayList<>();
                    RouteInstance ri = new RouteInstance(date,route_id,legs,train_id);
                    routeInstances.add(ri);
                }
                LegInstance leg = new LegInstance(rs.getInt("leg_num"), rs.getString("depart_actual_time"),
                        rs.getString("arrival_actual_time"), rs.getInt("available_seats"),
                        rs.getString("depart_station_name"),rs.getString("arrival_station_name"),
                        rs.getLong("depart_station_id"), rs.getLong("arrival_station_id"));
                legs.add(leg);
            }

            return Response.ok(gson.toJson(routeInstances)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

//[
//        {
//            "date": "2019-12-06",
//            "route_id": 3,
//            "legs":[
//                    {
//                        "leg_num":1,
//                            "depart_actual_time":"09:00:00",
//                            "arrival_actual_time":"10:00:00",
//                            "available_seats":10,
//                            "depart_station_name":"Nur-Sultan",
//                            "arrival_station_name":"Kokshetau",
//                            "depart_station_id":1,
//                            "arrival_station_id":8
//                    },
//                    {
//                        "leg_num":2,
//                            "depart_actual_time":"10:00:00",
//                            "arrival_actual_time":"11:00:00",
//                            "available_seats":10,
//                            "depart_station_name":"Kokshetau",
//                            "arrival_station_name":"Petropavl",
//                            "depart_station_id":8,
//                            "arrival_station_id":7
//                    }
//            ],
//            "train_id":1
//        }
//]

    @Path("/{route_id: [0-9]+}/{date: \\d{4}-\\d{2}-\\d{2}}")
    @DELETE
    public Response deleteRouteInstance(@PathParam("route_id") Integer routeId,
                                   @PathParam("date") String date) {

        String sql = "delete from leg_instance where date = '" + date + "' and route_id = " + routeId;

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement())
        {
            int rs = stmt.executeUpdate(sql);
            return Response.ok(rs).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTrip(RouteInstance routeInstance) {
        String date = routeInstance.getDate();
        long route_id = routeInstance.getRouteId();
        List<LegInstance> legInstances = routeInstance.getLegs();
        long train_id = routeInstance.getTrainId();
        boolean fuckPassengers = routeInstance.getFuckPassengers();
        String sql;
        Connection conn = DBConnector.getDatabaseConnection();
        try
        {
            int rs;
            Statement stmt = conn.createStatement();
            if (fuckPassengers) {
                sql = "delete from ticket_route where route_id = "+route_id+"  and gdate = '"+date+"';";
                rs = stmt.executeUpdate(sql);
            }
            for (LegInstance leg : legInstances) {
                sql = "update leg_instance " +
                        "set depart_actual_time = '"+leg.getDepart_actual_time()+
                        "', arrival_actual_time = '"+leg.getArrival_actual_time()+
                        "', train_id =  " + train_id +
                        " where date = '"+date+"' and route_id = "+route_id+" and leg_num = " + leg.getLegnum();
                rs = stmt.executeUpdate(sql);
            }
            return Response.ok().build();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
            return Response.status(404).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertTrip(RouteInstance routeInstance) {
        long route_id = routeInstance.getRouteId();
        String date = routeInstance.getDate();
        long train_id = routeInstance.getTrainId();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement())
        {
            for (LegInstance leg : routeInstance.getLegs()) {
                String sql = "INSERT INTO leg_instance\n" + "VALUES ('" +
                        date + "', " +
                        route_id + ", "+
                        leg.getLegnum()+ ", '" +
                        leg.getDepart_actual_time() + "', '" +
                        leg.getArrival_actual_time() + "', " +
                        leg.getAvailable_seats() + ", "+
                        train_id + ");";
                int rs = stmt.executeUpdate(sql);
            }
            return Response.ok().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

}



