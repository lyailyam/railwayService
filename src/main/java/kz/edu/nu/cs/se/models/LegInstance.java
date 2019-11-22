package kz.edu.nu.cs.se.models;

public class LegInstance {
    private int leg_num;
    private String depart_actual_time;
    private String arrival_actual_time;
    private int available_seats;
    private String depart_station_name;
    private String arrival_station_name;
    private long depart_station_id;
    private long arrival_station_id;
    public LegInstance() {}
    public LegInstance(int leg_num, String depart_actual_time, String arrival_actual_time, int available_seats, String depart_station_name, String arrival_station_name, long depart_station_id, long arrival_station_id) {
        this.leg_num = leg_num;
        this.depart_actual_time = depart_actual_time;
        this.arrival_actual_time = arrival_actual_time;
        this.available_seats = available_seats;
        this.depart_station_name = depart_station_name;
        this.arrival_station_name = arrival_station_name;
        this.depart_station_id = depart_station_id;
        this.arrival_station_id = arrival_station_id;
    }

    public int getLegnum() {
        return leg_num;
    }

    public String getDepart_actual_time() {
        return depart_actual_time;
    }

    public String getArrival_actual_time() {
        return arrival_actual_time;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public String getDepart_station_name() {
        return depart_station_name;
    }

    public String getArrival_station_name() {
        return arrival_station_name;
    }

    public long getDepart_station_id() {
        return depart_station_id;
    }

    public long getArrival_station_id() {
        return arrival_station_id;
    }

    public void setLeg_num(int leg_num) {
        this.leg_num = leg_num;
    }

    public void setDepart_actual_time(String depart_actual_time) {
        this.depart_actual_time = depart_actual_time;
    }

    public void setArrival_actual_time(String arrival_actual_time) {
        this.arrival_actual_time = arrival_actual_time;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public void setDepart_station_name(String depart_station_name) {
        this.depart_station_name = depart_station_name;
    }

    public void setArrival_station_name(String arrival_station_name) {
        this.arrival_station_name = arrival_station_name;
    }

    public void setDepart_station_id(long depart_station_id) {
        this.depart_station_id = depart_station_id;
    }

    public void setArrival_station_id(long arrival_station_id) {
        this.arrival_station_id = arrival_station_id;
    }
}




