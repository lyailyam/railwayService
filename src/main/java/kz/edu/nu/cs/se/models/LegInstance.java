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
}




