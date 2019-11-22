package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log_api", schema = "railwaysdb", catalog = "")
public class LogApiEntity {
    private int logId;
    private Integer userId;
    private String role;
    private Timestamp timestamp;
    private String method;
    private String uri;

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
    @Column(name = "role", nullable = true, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
    @Column(name = "method", nullable = true, length = 255)
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "uri", nullable = true, length = 255)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogApiEntity that = (LogApiEntity) o;

        if (logId != that.logId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        return result;
    }
}
