package kz.edu.nu.cs.se;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Path("/db")
public class TestDB {
    // TODO : Just a temporary class. Need to create a proper DB connection class
    private static Connection getDatabaseConnection() {
        Connection conn = null;

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ds-week8");
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    // Just testing connection to db
    @GET
    public Response getId() {
        Gson gson = new Gson();

        try {
            Connection conn = getDatabaseConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();

            String msg = rs.getString("firstname") + " " + rs.getString("email");

            rs.close();
            stmt.close();
            conn.close();

            return Response.ok(gson.toJson(msg)).build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok(gson.toJson(new String("error"))).build();
    }
}

