package kz.edu.nu.cs.se.models;

public class Leg {
    private int leg_num;
    private String depart_scheduled_time;
    private String arrival_scheduled_time;
    private String depart_station_name;
    private String arrival_station_name;
    private long depart_station_id;
    private long arrival_station_id;
    public Leg(){}
    public Leg(int leg_num, String depart_scheduled_time, String arrival_scheduled_time, String depart_station_name, String arrival_station_name, long depart_station_id, long arrival_station_id) {
        this.leg_num = leg_num;
        this.depart_scheduled_time = depart_scheduled_time;
        this.arrival_scheduled_time = arrival_scheduled_time;
        this.depart_station_name = depart_station_name;
        this.arrival_station_name = arrival_station_name;
        this.depart_station_id = depart_station_id;
        this.arrival_station_id = arrival_station_id;
    }

    public int getLeg_num() {
        return leg_num;
    }

    public void setLeg_num(int leg_num) {
        this.leg_num = leg_num;
    }

    public String getDepart_scheduled_time() {
        return depart_scheduled_time;
    }

    public void setDepart_scheduled_time(String depart_scheduled_time) {
        this.depart_scheduled_time = depart_scheduled_time;
    }

    public String getArrival_scheduled_time() {
        return arrival_scheduled_time;
    }

    public void setArrival_scheduled_time(String arrival_scheduled_time) {
        this.arrival_scheduled_time = arrival_scheduled_time;
    }

    public String getDepart_station_name() {
        return depart_station_name;
    }

    public void setDepart_station_name(String depart_station_name) {
        this.depart_station_name = depart_station_name;
    }

    public String getArrival_station_name() {
        return arrival_station_name;
    }

    public void setArrival_station_name(String arrival_station_name) {
        this.arrival_station_name = arrival_station_name;
    }

    public long getDepart_station_id() {
        return depart_station_id;
    }

    public void setDepart_station_id(long depart_station_id) {
        this.depart_station_id = depart_station_id;
    }

    public long getArrival_station_id() {
        return arrival_station_id;
    }

    public void setArrival_station_id(long arrival_station_id) {
        this.arrival_station_id = arrival_station_id;
    }
}
