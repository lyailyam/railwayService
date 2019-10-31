package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "leg_instance", schema = "railwayway", catalog = "")
@IdClass(LegInstanceEntityPK.class)
public class LegInstanceEntity {
    private Date date;
    private int routeId;
    private int legNum;
    private Time departActualTime;
    private Time arrivalActualTime;
    private Integer availableSeats;
    private int trainId;

    @Id
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @Column(name = "route_id", nullable = false)
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Id
    @Column(name = "leg_num", nullable = false)
    public int getLegNum() {
        return legNum;
    }

    public void setLegNum(int legNum) {
        this.legNum = legNum;
    }

    @Basic
    @Column(name = "depart_actual_time", nullable = true)
    public Time getDepartActualTime() {
        return departActualTime;
    }

    public void setDepartActualTime(Time departActualTime) {
        this.departActualTime = departActualTime;
    }

    @Basic
    @Column(name = "arrival_actual_time", nullable = true)
    public Time getArrivalActualTime() {
        return arrivalActualTime;
    }

    public void setArrivalActualTime(Time arrivalActualTime) {
        this.arrivalActualTime = arrivalActualTime;
    }

    @Basic
    @Column(name = "available_seats", nullable = true)
    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
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

        LegInstanceEntity that = (LegInstanceEntity) o;

        if (routeId != that.routeId) return false;
        if (legNum != that.legNum) return false;
        if (trainId != that.trainId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (departActualTime != null ? !departActualTime.equals(that.departActualTime) : that.departActualTime != null)
            return false;
        if (arrivalActualTime != null ? !arrivalActualTime.equals(that.arrivalActualTime) : that.arrivalActualTime != null)
            return false;
        if (availableSeats != null ? !availableSeats.equals(that.availableSeats) : that.availableSeats != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + routeId;
        result = 31 * result + legNum;
        result = 31 * result + (departActualTime != null ? departActualTime.hashCode() : 0);
        result = 31 * result + (arrivalActualTime != null ? arrivalActualTime.hashCode() : 0);
        result = 31 * result + (availableSeats != null ? availableSeats.hashCode() : 0);
        result = 31 * result + trainId;
        return result;
    }
}
