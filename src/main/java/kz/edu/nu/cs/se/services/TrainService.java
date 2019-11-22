package kz.edu.nu.cs.se.services;

import com.google.gson.Gson;
import kz.edu.nu.cs.se.DBConnector;

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

@Path("/trains")
public class TrainService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRouteInstances() {

        String sql = "select * from train";

        Gson gson = new Gson();

        try(Connection conn = DBConnector.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            List<Integer> trainIds = new CopyOnWriteArrayList<>();
            while (rs.next()) {
                trainIds.add(rs.getInt("id"));
            }

            return Response.ok(gson.toJson(trainIds)).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(404).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }
}
