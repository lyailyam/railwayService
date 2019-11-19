package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "seat", schema = "railwaysdb", catalog = "")
@IdClass(SeatEntityPK.class)
public class SeatEntity {
    private int num;
    private int railcarNum;
    private int trainId;
    private String location;

    @Id
    @Column(name = "num", nullable = false)
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Id
    @Column(name = "railcar_num", nullable = false)
    public int getRailcarNum() {
        return railcarNum;
    }

    public void setRailcarNum(int railcarNum) {
        this.railcarNum = railcarNum;
    }

    @Id
    @Column(name = "train_id", nullable = false)
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "location", nullable = true)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatEntity that = (SeatEntity) o;

        if (num != that.num) return false;
        if (railcarNum != that.railcarNum) return false;
        if (trainId != that.trainId) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + railcarNum;
        result = 31 * result + trainId;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
