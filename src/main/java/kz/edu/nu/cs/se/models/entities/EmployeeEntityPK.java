package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class EmployeeEntityPK implements Serializable {
    private int id;
    private int stationId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "station_id", nullable = false)
    @Id
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntityPK that = (EmployeeEntityPK) o;

        if (id != that.id) return false;
        if (stationId != that.stationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + stationId;
        return result;
    }
}
