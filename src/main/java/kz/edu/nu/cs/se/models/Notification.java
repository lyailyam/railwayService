package kz.edu.nu.cs.se.models;

public class Notification {

    //assumed to be independent of leg_instance, only relate to manager

    private int notifId;
    private int managerId;
    private String dateCreated;
    private String timeCreated;
    private int oldRouteId;
    private int oldLegNum;
    private String oldLegDate;
    private int newRouteId;
    private int newLegNum;
    private String newLegDate;
    private String message;
    private String oldDepActTime;
    private String oldArrActTime;
    private String newDepActTime;
    private String newArrActTime;

    public Notification() {}

    public int getNotifId() {
        return notifId;
    }

    public void setNotifId(int notifId) {
        this.notifId = notifId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getOldRouteId() {
        return oldRouteId;
    }

    public void setOldRouteId(int oldRouteId) {
        this.oldRouteId = oldRouteId;
    }

    public int getOldLegNum() {
        return oldLegNum;
    }

    public void setOldLegNum(int oldLegNum) {
        this.oldLegNum = oldLegNum;
    }

    public String getOldLegDate() {
        return oldLegDate;
    }

    public void setOldLegDate(String oldLegDate) {
        this.oldLegDate = oldLegDate;
    }

    public int getNewRouteId() {
        return newRouteId;
    }

    public void setNewRouteId(int newRouteId) {
        this.newRouteId = newRouteId;
    }

    public int getNewLegNum() {
        return newLegNum;
    }

    public void setNewLegNum(int newLegNum) {
        this.newLegNum = newLegNum;
    }

    public String getNewLegDate() {
        return newLegDate;
    }

    public void setNewLegDate(String newLegDate) {
        this.newLegDate = newLegDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOldDepActTime() {
        return oldDepActTime;
    }

    public void setOldDepActTime(String oldDepActTime) {
        this.oldDepActTime = oldDepActTime;
    }

    public String getOldArrActTime() {
        return oldArrActTime;
    }

    public void setOldArrActTime(String oldArrActTime) {
        this.oldArrActTime = oldArrActTime;
    }

    public String getNewDepActTime() {
        return newDepActTime;
    }

    public void setNewDepActTime(String newDepActTime) {
        this.newDepActTime = newDepActTime;
    }

    public String getNewArrActTime() {
        return newArrActTime;
    }

    public void setNewArrActTime(String newArrActTime) {
        this.newArrActTime = newArrActTime;
    }
}
