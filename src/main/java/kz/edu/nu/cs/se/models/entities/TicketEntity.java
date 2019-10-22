package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket", schema = "railways-test", catalog = "")
@IdClass(TicketEntityPK.class)
public class TicketEntity {
    private int id;
    private Double price;
    private int seatId;
    private int tripId;
    private int userId;
    private String userEmail;
    private Object status;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Id
    @Column(name = "seat_id", nullable = false)
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    @Id
    @Column(name = "trip_id", nullable = false)
    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "user_email", nullable = false, length = 255)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketEntity that = (TicketEntity) o;

        if (id != that.id) return false;
        if (seatId != that.seatId) return false;
        if (tripId != that.tripId) return false;
        if (userId != that.userId) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + seatId;
        result = 31 * result + tripId;
        result = 31 * result + userId;
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
