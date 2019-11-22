package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ScheduleEntityPK implements Serializable {
    private int employeeId;
    private int workingDay;

    @Id
    @Column(name = "employee_id", nullable = false)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "working_day", nullable = false)
    public int getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(int workingDay) {
        this.workingDay = workingDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleEntityPK that = (ScheduleEntityPK) o;
        return employeeId == that.employeeId &&
                workingDay == that.workingDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, workingDay);
    }
}
