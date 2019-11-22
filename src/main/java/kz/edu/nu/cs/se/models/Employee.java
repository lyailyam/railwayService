package kz.edu.nu.cs.se.models;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Employee {
    private int id;
    private String email;
    private String firstName;
    private String surname;
    private double salary;
    private int roleId;
    private int stationId;
    private String employedDate;
    private Map<Integer, List<String>> schedule;

    public Employee() {}

    public Employee(
            int id, String email, String firstName, String surname, double salary, int roleId, int stationId,
            String employedDate, Map<Integer, List<String>> schedule) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
        this.salary = salary;
        this.roleId = roleId;
        this.stationId = stationId;
        this.employedDate = employedDate;
        this.schedule = schedule;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getEmployedDate() {
        return employedDate;
    }

    public void setEmployedDate(String employedDate) {
        this.employedDate = employedDate;
    }

    public Map<Integer, List<String>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<Integer, List<String>> schedule) {
        this.schedule = schedule;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Double.compare(employee.salary, salary) == 0 &&
                roleId == employee.roleId &&
                stationId == employee.stationId &&
                email.equals(employee.email) &&
                firstName.equals(employee.firstName) &&
                surname.equals(employee.surname) &&
                employedDate.equals(employee.employedDate) &&
                schedule.equals(employee.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, surname, salary, roleId, stationId, employedDate, schedule);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", roleId=" + roleId +
                ", stationId=" + stationId +
                ", employedDate='" + employedDate + '\'' +
                ", schedule='" + schedule +
                '}';
    }
}