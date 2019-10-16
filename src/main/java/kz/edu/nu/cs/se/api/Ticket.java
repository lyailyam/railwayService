package kz.edu.nu.cs.se.api;


public class Ticket {
    private int id;
    private int userId;
    private int tripId;
    private int seatId;
    private double price;
    private String status;

    public Ticket(int id, int userId, int tripId, int seatId, double price, String status) {
        this.id = id;
        this.userId = userId;
        this.tripId = tripId;
        this.seatId = seatId;
        this.price = price;
        this.status = status;
    }

    public Ticket() {

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTripId() {
        return tripId;
    }

    public int getSeatId() {
        return seatId;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }
}
