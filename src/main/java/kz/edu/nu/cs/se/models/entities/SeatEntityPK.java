package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SeatEntityPK implements Serializable {
    private int num;
    private int railcarNum;
    private int trainId;

    @Column(name = "num", nullable = false)
    @Id
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Column(name = "railcar_num", nullable = false)
    @Id
    public int getRailcarNum() {
        return railcarNum;
    }

    public void setRailcarNum(int railcarNum) {
        this.railcarNum = railcarNum;
    }

    @Column(name = "train_id", nullable = false)
    @Id
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

        SeatEntityPK that = (SeatEntityPK) o;

        if (num != that.num) return false;
        if (railcarNum != that.railcarNum) return false;
        if (trainId != that.trainId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + railcarNum;
        result = 31 * result + trainId;
        return result;
    }
}
