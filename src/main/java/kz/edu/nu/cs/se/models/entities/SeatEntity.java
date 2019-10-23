package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "seat", schema = "railways-test", catalog = "")
public class SeatEntity {
    private int id;
    private int railcarId;
    private String number;
    private String location;
    private byte isOccupied;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "railcar_id", nullable = false)
    public int getRailcarId() {
        return railcarId;
    }

    public void setRailcarId(int railcarId) {
        this.railcarId = railcarId;
    }

    @Basic
    @Column(name = "number", nullable = false, length = 45)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "location", nullable = false)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "is_occupied", nullable = false)
    public byte getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(byte isOccupied) {
        this.isOccupied = isOccupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatEntity that = (SeatEntity) o;

        if (id != that.id) return false;
        if (railcarId != that.railcarId) return false;
        if (isOccupied != that.isOccupied) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + railcarId;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (int) isOccupied;
        return result;
    }
}
