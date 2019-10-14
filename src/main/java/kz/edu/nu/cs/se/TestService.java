package kz.edu.nu.cs.se;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/test")
public class TestService {
    @GET
    public Response getList() {
        Gson gson = new Gson();

        return Response.ok(gson.toJson(new String("test"))).build();
    }
}

