package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "route_leg", schema = "railwaysdb", catalog = "")
@IdClass(RouteLegEntityPK.class)
public class RouteLegEntity {
    private int routeId;
    private int legNum;
    private int departStationId;
    private int arrivalStationId;
    private String departScheduledTime;
    private String arrivalScheduledTime;

    @ManyToOne
    @JoinColumn(name = "depart_station_id")
    private StationEntity departStation;

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
    @Column(name = "depart_station_id", nullable = false)
    public int getDepartStationId() {
        return departStationId;
    }

    public void setDepartStationId(int departStationId) {
        this.departStationId = departStationId;
    }

    @Basic
    @Column(name = "arrival_station_id", nullable = false)
    public int getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    @Basic
    @Column(name = "depart_scheduled_time", nullable = false)
    public String getDepartScheduledTime() {
        return departScheduledTime;
    }

    public void setDepartScheduledTime(String departScheduledTime) {
        this.departScheduledTime = departScheduledTime;
    }

    @Basic
    @Column(name = "arrival_scheduled_time", nullable = false)
    public String getArrivalScheduledTime() {
        return arrivalScheduledTime;
    }

    public void setArrivalScheduledTime(String arrivalScheduledTime) {
        this.arrivalScheduledTime = arrivalScheduledTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteLegEntity that = (RouteLegEntity) o;

        if (routeId != that.routeId) return false;
        if (legNum != that.legNum) return false;
        if (departStationId != that.departStationId) return false;
        if (arrivalStationId != that.arrivalStationId) return false;
        if (departScheduledTime != null ? !departScheduledTime.equals(that.departScheduledTime) : that.departScheduledTime != null)
            return false;
        if (arrivalScheduledTime != null ? !arrivalScheduledTime.equals(that.arrivalScheduledTime) : that.arrivalScheduledTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId;
        result = 31 * result + legNum;
        result = 31 * result + departStationId;
        result = 31 * result + arrivalStationId;
        result = 31 * result + (departScheduledTime != null ? departScheduledTime.hashCode() : 0);
        result = 31 * result + (arrivalScheduledTime != null ? arrivalScheduledTime.hashCode() : 0);
        return result;
    }
}
