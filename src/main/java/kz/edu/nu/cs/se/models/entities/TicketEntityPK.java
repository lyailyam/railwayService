package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TicketEntityPK implements Serializable {
    private int id;
    private int seatId;
    private int tripId;
    private int userId;
    private String userEmail;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "seat_id", nullable = false)
    @Id
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    @Column(name = "trip_id", nullable = false)
    @Id
    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "user_email", nullable = false, length = 255)
    @Id
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketEntityPK that = (TicketEntityPK) o;

        if (id != that.id) return false;
        if (seatId != that.seatId) return false;
        if (tripId != that.tripId) return false;
        if (userId != that.userId) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + seatId;
        result = 31 * result + tripId;
        result = 31 * result + userId;
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        return result;
    }
}
