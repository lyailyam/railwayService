package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "railcar", schema = "railways-test", catalog = "")
public class RailcarEntity {
    private int id;
    private int trainId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "train_id", nullable = false)
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

        RailcarEntity that = (RailcarEntity) o;

        if (id != that.id) return false;
        if (trainId != that.trainId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + trainId;
        return result;
    }
}
