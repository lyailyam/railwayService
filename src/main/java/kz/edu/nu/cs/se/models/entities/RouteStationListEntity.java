package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "route_station_list", schema = "railways-test", catalog = "")
@IdClass(RouteStationListEntityPK.class)
public class RouteStationListEntity {
    private int routeId;
    private int stationId;
    private int order;

    @Id
    @Column(name = "route_id", nullable = false)
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Id
    @Column(name = "station_id", nullable = false)
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "order", nullable = false)
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteStationListEntity that = (RouteStationListEntity) o;

        if (routeId != that.routeId) return false;
        if (stationId != that.stationId) return false;
        if (order != that.order) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId;
        result = 31 * result + stationId;
        result = 31 * result + order;
        return result;
    }
}
