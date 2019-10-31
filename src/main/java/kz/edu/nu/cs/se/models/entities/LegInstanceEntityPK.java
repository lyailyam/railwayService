package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class LegInstanceEntityPK implements Serializable {
    private String date;
    private int routeId;
    private int legNum;

    @Column(name = "date", nullable = false)
    @Id
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "route_id", nullable = false)
    @Id
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Column(name = "leg_num", nullable = false)
    @Id
    public int getLegNum() {
        return legNum;
    }

    public void setLegNum(int legNum) {
        this.legNum = legNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegInstanceEntityPK that = (LegInstanceEntityPK) o;

        if (routeId != that.routeId) return false;
        if (legNum != that.legNum) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + routeId;
        result = 31 * result + legNum;
        return result;
    }
}
