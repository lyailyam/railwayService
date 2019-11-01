package kz.edu.nu.cs.se.models;

public class Schedule {
    private String depStationName;
    private String depStationCity;
    private String arrStationName;
    private String arrStationCity;
    private String depSchedTime;
    private String arrSchedTime;
    private String depDate;
    private String arrDate;

    public Schedule() {}

    public Schedule(String depStationName, String depStationCity, String arrStationName, String arrStationCity, String depSchedTime,
                    String arrSchedtime, String depDate, String arrDate) {
        this.depStationName = depStationName;
        this.depStationCity = depStationCity;
        this.arrStationName = arrStationName;
        this.arrStationCity = arrStationCity;
        this.depSchedTime = depSchedTime;
        this.arrSchedTime = arrSchedtime;
        this.depDate = depDate;
        this.arrDate = arrDate;
    }

    public String getDepStationName() {
        return depStationName;
    }

    public void setDepStationName(String depStationName) {
        this.depStationName = depStationName;
    }

    public String getDepStationCity() {
        return depStationCity;
    }

    public void setDepStationCity(String depStationCity) {
        this.depStationCity = depStationCity;
    }

    public String getArrStationName() {
        return arrStationName;
    }

    public void setArrStationName(String arrStationName) {
        this.arrStationName = arrStationName;
    }

    public String getArrStationCity() {
        return arrStationCity;
    }

    public void setArrStationCity(String arrStationCity) {
        this.arrStationCity = arrStationCity;
    }

    public String getDepSchedTime() {
        return depSchedTime;
    }

    public void setDepSchedTime(String depSchedTime) {
        this.depSchedTime = depSchedTime;
    }

    public String getArrSchedtime() {
        return arrSchedTime;
    }

    public void setArrSchedTime(String arrSchedtime) {
        this.arrSchedTime = arrSchedtime;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String toString() {
        return depStationName + " - " + arrStationName;
    }
}
