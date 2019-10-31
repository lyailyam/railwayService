package kz.edu.nu.cs.se;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Properties;

@Path("/items")
public class TestService {
    @GET
    public Response getList() {
        Gson gson = new Gson();

        final Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.getProperty("version"));
        System.out.println(properties.getProperty("artifactId"));

        return Response.ok(gson.toJson(new String("test"))).build();
    }
}