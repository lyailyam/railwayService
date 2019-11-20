package kz.edu.nu.cs.se.models;

import java.util.List;

public class RouteInstance {

    private String date;
    private long route_id;
    private List<LegInstance> legs;
    private long train_id;

    public RouteInstance(String date, long route_id, List<LegInstance> legs, long train_id) {
        this.date = date;
        this.route_id = route_id;
        this.legs = legs;
        this.train_id = train_id;
    }
}



