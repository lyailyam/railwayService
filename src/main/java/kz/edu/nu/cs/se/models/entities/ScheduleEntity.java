package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "schedule", schema = "railwaysdb", catalog = "")
@IdClass(ScheduleEntityPK.class)
public class ScheduleEntity {
    private int employeeId;
    private int workingDay;
    private String startTime;
    private String endTime;

    @Id
    @Column(name = "employee_id", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "working_day", nullable = true)
    public int getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(int workingDay) {
        this.workingDay = workingDay;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleEntity that = (ScheduleEntity) o;
        return employeeId == that.employeeId &&
                workingDay == that.workingDay &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, workingDay, startTime, endTime);
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "employeeId=" + employeeId +
                ", workingDay=" + workingDay +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
