package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log_user", schema = "railwaysdb", catalog = "")
public class LogUserEntity {
    private int logId;
    private Integer userId;
    private Timestamp timestamp;
    private String activity;

    @Id
    @Column(name = "log_id", nullable = false)
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "timestamp", nullable = true)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "activity", nullable = true, length = 255)
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogUserEntity that = (LogUserEntity) o;

        if (logId != that.logId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (activity != null ? !activity.equals(that.activity) : that.activity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        return result;
    }
}
