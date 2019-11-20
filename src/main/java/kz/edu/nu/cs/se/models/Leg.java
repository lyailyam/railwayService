package kz.edu.nu.cs.se.models;

public class Leg {
    private int leg_num;
    private String depart_scheduled_time;
    private String arrival_scheduled_time;
    private String depart_station_name;
    private String arrival_station_name;
    private long depart_station_id;
    private long arrival_station_id;

    public Leg(int leg_num, String depart_scheduled_time, String arrival_scheduled_time, String depart_station_name, String arrival_station_name, long depart_station_id, long arrival_station_id) {
        this.leg_num = leg_num;
        this.depart_scheduled_time = depart_scheduled_time;
        this.arrival_scheduled_time = arrival_scheduled_time;
        this.depart_station_name = depart_station_name;
        this.arrival_station_name = arrival_station_name;
        this.depart_station_id = depart_station_id;
        this.arrival_station_id = arrival_station_id;
    }
}
