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

@Path("/routes")
public class RouteService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRoutes() {

        String sql =
                "select route_leg.*, dep_st.name as depart_station_name, arr_st.name as arrival_station_name from route_leg\n" +
                        "join station dep_st on dep_st.id = route_leg.depart_station_id\n" +
                        "join station arr_st on arr_st.id = route_leg.arrival_station_id\n" +
                        "order by route_id,leg_num;";

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            List<Route> routes = new CopyOnWriteArrayList<>();
            long route_id = 0;
            List<Leg> legs = new CopyOnWriteArrayList<>();
            while (rs.next()) {
                if (route_id != rs.getLong("route_id")) {
                    route_id = rs.getLong("route_id");
                    legs = new CopyOnWriteArrayList<>();
                    Route r = new Route(route_id,legs);
                    routes.add(r);
                }
                Leg leg = new Leg(rs.getInt("leg_num"), rs.getString("depart_scheduled_time"),
                        rs.getString("arrival_scheduled_time"),
                        rs.getString("depart_station_name"),rs.getString("arrival_station_name"),
                        rs.getLong("depart_station_id"), rs.getLong("arrival_station_id"));
                legs.add(leg);
            }


            return Response.ok(gson.toJson(routes)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

//[{
//    "route_id":3,
//    "legs":
//    [
//            {
//                "leg_num":1,
//                "depart_scheduled_time":"09:00:00",
//                "arrival_scheduled_time":"10:00:00",
//                "depart_station_name":"Nur-Sultan",
//                "arrival_station_name":"Kokshetau",
//                "depart_station_id":1,
//                "arrival_station_id":8
//            },
//            {
//                "leg_num":2,
//                "depart_scheduled_time":"10:00:00",
//                "arrival_scheduled_time":"11:00:00",
//                "depart_station_name":"Kokshetau",
//                "arrival_station_name":"Petropavl",
//                "depart_station_id":8,
//                "arrival_station_id":7
//            }
//    ]
//}]
}



