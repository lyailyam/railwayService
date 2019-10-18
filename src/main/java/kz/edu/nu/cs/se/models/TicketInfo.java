package kz.edu.nu.cs.se.models;

public class TicketInfo {

    private int id;
    private double price;
    private String ticket_status;
    private String dep_time;
    private  String arr_time;
    private String trip_status;
    private String st1_name;
    private String st2_name;

    public TicketInfo() {}

    public TicketInfo(int id, double price, String ticket_status, String dep_time, String arr_time,
                      String trip_status, String st1_name, String st2_name) {
        this.id = id;
        this.price = price;
        this.ticket_status = ticket_status;
        this.dep_time = dep_time;
        this.arr_time = arr_time;
        this.trip_status = trip_status;
        this.st1_name = st1_name;
        this.st2_name = st2_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(String ticket_status) {
        this.ticket_status = ticket_status;
    }

    public String getDep_time() {
        return dep_time;
    }

    public void setDep_time(String dep_time) {
        this.dep_time = dep_time;
    }

    public String getArr_time() {
        return arr_time;
    }

    public void setArr_time(String arr_time) {
        this.arr_time = arr_time;
    }

    public String getTrip_status() {
        return trip_status;
    }

    public void setTrip_status(String trip_status) {
        this.trip_status = trip_status;
    }

    public String getSt1_name() {
        return st1_name;
    }

    public void setSt1_name(String st1_name) {
        this.st1_name = st1_name;
    }

    public String getSt2_name() {
        return st2_name;
    }

    public void setSt2_name(String st2_name) {
        this.st2_name = st2_name;
    }
}
