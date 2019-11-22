package kz.edu.nu.cs.se.models;

import java.util.List;

public class Route {
    private long route_id;
    private List<Leg> legs;

    public Route(){}

    public Route(long route_id, List<Leg> legs) {
        this.route_id = route_id;
        this.legs = legs;
    }

    public void setRoute_id(long route_id) {
        this.route_id = route_id;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public long getRoute_id() {
        return route_id;
    }

    public List<Leg> getLegs() {
        return legs;
    }
}
