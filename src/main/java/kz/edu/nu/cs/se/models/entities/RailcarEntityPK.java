package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RailcarEntityPK implements Serializable {
    private int num;
    private int trainId;

    @Column(name = "num", nullable = false)
    @Id
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

        RailcarEntityPK that = (RailcarEntityPK) o;

        if (num != that.num) return false;
        if (trainId != that.trainId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + trainId;
        return result;
    }
}
