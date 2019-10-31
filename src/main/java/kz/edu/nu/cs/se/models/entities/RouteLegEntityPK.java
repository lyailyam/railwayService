package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RouteLegEntityPK implements Serializable {
    private int routeId;
    private int legNum;

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

        RouteLegEntityPK that = (RouteLegEntityPK) o;

        if (routeId != that.routeId) return false;
        if (legNum != that.legNum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId;
        result = 31 * result + legNum;
        return result;
    }
}
