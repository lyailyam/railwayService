package kz.edu.nu.cs.se.services;


import com.google.gson.Gson;
import kz.edu.nu.cs.se.DBConnector;
import kz.edu.nu.cs.se.models.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Path("/notifications")
public class AdvisoryService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdvisories() {

        String notificationsSql = "select * from notification order by date_created, time_created ";

        Gson gson = new Gson();
        List<Notification> notifList = new CopyOnWriteArrayList<>();

        try {
            Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(notificationsSql);

            while(rs.next()) {

                Notification n = new Notification();

                n.setNotifId(rs.getInt("notif_id"));
                n.setManagerId(rs.getInt("manager_id"));
                n.setDateCreated(rs.getDate("date_created").toString());
                n.setTimeCreated(rs.getTime("time_created").toString());
                n.setOldRouteId(rs.getInt("route_id"));
                n.setOldLegNum(rs.getInt("leg_num"));
                n.setOldLegDate(rs.getDate("leg_date").toString());
                n.setNewRouteId(rs.getInt("new_route_id"));
                n.setNewLegNum(rs.getInt("new_leg_num"));
                n.setNewLegDate(rs.getDate("new_leg_date").toString());
                n.setMessage(rs.getString("message"));
                n.setOldDepActTime(rs.getTime("old_dep_act_time").toString());
                n.setOldArrActTime(rs.getTime("old_arr_act_time").toString());
                n.setNewDepActTime(rs.getTime("new_dep_act_time").toString());
                n.setNewArrActTime(rs.getTime("new_arr_act_time").toString());

                notifList.add(n);

            }


            conn.close();
            return Response.ok(gson.toJson(notifList)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }


    }


}
