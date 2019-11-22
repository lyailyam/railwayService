package kz.edu.nu.cs.se.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "railwaysdb", catalog = "")
@IdClass(EmployeeEntityPK.class)
public class EmployeeEntity {
    private int id;
    private String email;
    private String firstname;
    private String surname;
    private Double salary;
    private int roleId;
    private int stationId;
    @Temporal(TemporalType.DATE) private String employedDate;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 225)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 255)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 255)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "salary", nullable = true, precision = 0)
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "station_id", nullable = false)
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "employed_date", nullable = false)
    public String getEmployedDate() {
        return employedDate;
    }

    public void setEmployedDate(String employedDate) {
        this.employedDate = employedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return id == that.id &&
                roleId == that.roleId &&
                stationId == that.stationId &&
                email.equals(that.email) &&
                firstname.equals(that.firstname) &&
                surname.equals(that.surname) &&
                salary.equals(that.salary) &&
                employedDate.equals(that.employedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstname, surname, salary, roleId, stationId, employedDate);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", roleId=" + roleId +
                ", stationId=" + stationId +
                ", employedDate='" + employedDate + '\'' +
                '}';
    }
}
