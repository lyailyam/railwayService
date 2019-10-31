package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "trip", schema = "railways-test", catalog = "")
public class TripEntity {
    private int id;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private String status;
    private int firstStationId;
    private int lastStationId;
    private int routeId;
    private int trainId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "departure_time", nullable = false)
    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    @Basic
    @Column(name = "arrival_time", nullable = false)
    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "first_station_id", nullable = false)
    public int getFirstStationId() {
        return firstStationId;
    }

    public void setFirstStationId(int firstStationId) {
        this.firstStationId = firstStationId;
    }

    @Basic
    @Column(name = "last_station_id", nullable = false)
    public int getLastStationId() {
        return lastStationId;
    }

    public void setLastStationId(int lastStationId) {
        this.lastStationId = lastStationId;
    }

    @Basic
    @Column(name = "route_id", nullable = false)
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Basic
    @Column(name = "train_id", nullable = false)
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripEntity that = (TripEntity) o;

        if (id != that.id) return false;
        if (firstStationId != that.firstStationId) return false;
        if (lastStationId != that.lastStationId) return false;
        if (routeId != that.routeId) return false;
        if (trainId != that.trainId) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + firstStationId;
        result = 31 * result + lastStationId;
        result = 31 * result + routeId;
        result = 31 * result + trainId;
        return result;
    }
}
