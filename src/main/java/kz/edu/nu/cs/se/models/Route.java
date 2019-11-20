package kz.edu.nu.cs.se.models;

import java.util.List;

public class Route {
    private long route_id;
    private List<Leg> legs;

    public Route(long route_id, List<Leg> legs) {
        this.route_id = route_id;
        this.legs = legs;
    }
}
