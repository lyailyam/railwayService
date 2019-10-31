package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

public class TicketRouteEntityPK implements Serializable {
    private int routeId;
    private int legNum;
    private Date date;
    private int ticketId;

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

    @Column(name = "date", nullable = false)
    @Id
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "ticket_id", nullable = false)
    @Id
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

        TicketRouteEntityPK that = (TicketRouteEntityPK) o;

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
