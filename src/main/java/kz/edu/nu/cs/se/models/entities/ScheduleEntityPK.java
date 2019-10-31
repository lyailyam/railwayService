package kz.edu.nu.cs.se.models.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ScheduleEntityPK implements Serializable {
    private int employeeId;
    private int workingDay;

    @Column(name = "employee_id", nullable = false)
    @Id
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "working_day", nullable = false)
    @Id
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

        if (employeeId != that.employeeId) return false;
        if (workingDay != that.workingDay) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + workingDay;
        return result;
    }
}
