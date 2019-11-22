package kz.edu.nu.cs.se.models;

import java.util.List;

public class RouteInstance {

    private String date;
    private long route_id;
    private List<LegInstance> legs;
    private long train_id;
    private boolean fuckPassengers;

    public RouteInstance(){}

    public RouteInstance(String date, long route_id, List<LegInstance> legs, long train_id) {
        this.date = date;
        this.route_id = route_id;
        this.legs = legs;
        this.train_id = train_id;
        fuckPassengers = false;
    }

    public String getDate() {
        return date;
    }

    public long getRouteId() {
        return route_id;
    }

    public List<LegInstance> getLegs() {
        return legs;
    }

    public long getTrainId() {
        return train_id;
    }

    public boolean getFuckPassengers() {
        return fuckPassengers;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setRoute_id(long route_id) {
        this.route_id = route_id;
    }

    public void setLegs(List<LegInstance> legs) {
        this.legs = legs;
    }

    public void setTrain_id(long train_id) {
        this.train_id = train_id;
    }

    public void setFuckPassengers(boolean fuckPassengers) {
        this.fuckPassengers = fuckPassengers;
    }
}



