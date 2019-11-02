package kz.edu.nu.cs.se.models;

public class Schedule {
    private int routeId;
    private String depStationName;
    private String depStationCity;
    private String arrStationName;
    private String arrStationCity;
    private String depSchedTime;
    private String arrSchedTime;
    private String depDate;
    private String arrDate;
    private int firstStatLegNum;
    private int lastStatLegNum;

    public Schedule() {}

    public Schedule(int routeId, String depStationName, String depStationCity, String arrStationName, String arrStationCity, String depSchedTime,
                    String arrSchedtime, String depDate, String arrDate, int firstStatLegNum, int lastStatLegNum) {
        this.routeId = routeId;
        this.depStationName = depStationName;
        this.depStationCity = depStationCity;
        this.arrStationName = arrStationName;
        this.arrStationCity = arrStationCity;
        this.depSchedTime = depSchedTime;
        this.arrSchedTime = arrSchedtime;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.firstStatLegNum = firstStatLegNum;
        this.lastStatLegNum = lastStatLegNum;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
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

    public String getArrSchedTime() {
        return arrSchedTime;
    }

    public int getFirstStatLegNum() {
        return firstStatLegNum;
    }

    public void setFirstStatLegNum(int firstStatLegNum) {
        this.firstStatLegNum = firstStatLegNum;
    }

    public int getLastStatLegNum() {
        return lastStatLegNum;
    }

    public void setLastStatLegNum(int lastStatLegNum) {
        this.lastStatLegNum = lastStatLegNum;
    }
}
