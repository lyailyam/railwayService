package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket_route", schema = "railwayway", catalog = "")
@IdClass(TicketRouteEntityPK.class)
public class TicketRouteEntity {
    private int routeId;
    private int legNum;
    private String date;
    private int ticketId;

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

    @Id
    @Column(name = "date", nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Id
    @Column(name = "ticket_id", nullable = false)
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketRouteEntity that = (TicketRouteEntity) o;

        if (routeId != that.routeId) return false;
        if (legNum != that.legNum) return false;
        if (ticketId != that.ticketId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId;
        result = 31 * result + legNum;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + ticketId;
        return result;
    }
}
