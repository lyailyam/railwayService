package kz.edu.nu.cs.se.models;

public class Ticket {
    private Integer id;
    private Integer userId;
    private Integer tripId;
    private Integer seatId;
    private Double price;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
