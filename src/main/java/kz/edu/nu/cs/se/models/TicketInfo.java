package kz.edu.nu.cs.se.models;

public class TicketInfo {

    private int ticketId;
    private double price;
    private String ticketStatus;
    private String travelerName;
    private String travelerSurname;
    private String travelerNationId;
    private int buyerId;
    private String buyerName;
    private String buyerSurname;
    private String buyerNationId;
    private int userActivateStatus;
    private String userEmail;
    private int routeId;
    private int firstStatLegNum;
    private String firstStatDepSchedTime;
    private String firstStatArrSchedTime;
    private int lastStatLegNum;
    private String lastStatDepSchedTime;
    private String lastStatArrSchedTime;
    private String firstStatDate;
    private String firstStatDepActualTime;
    private String firstStatArrActualTime;
    private int firstStatLegAvailSeats;
    private int trainId;
    private String lastStatDate;
    private String lastStatDepActualTime;
    private String lastStatArrActualTime;
    private int lastStatLegAvailSeats;
    private int firstStatId;
    private String firstStatName;
    private String firstStatCity;
    private double firstStatLongitude;
    private double firstStatLatitude;
    private int lastStatId;
    private String lastStatName;
    private String lastStatCity;
    private double lastStatLongitude;
    private double lastStatLatitude;
    private int railcarNum;
    private int railcarCapacity;
    private String railcarType;
    private int seatNum;
    private String seatLocation;


    public TicketInfo() {}

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getTravelerName() {
        return travelerName;
    }

    public void setTravelerName(String travelerName) {
        this.travelerName = travelerName;
    }

    public String getTravelerSurname() {
        return travelerSurname;
    }

    public void setTravelerSurname(String travelerSurname) {
        this.travelerSurname = travelerSurname;
    }

    public String getTravelerNationId() {
        return travelerNationId;
    }

    public void setTravelerNationId(String travelerNationId) {
        this.travelerNationId = travelerNationId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerSurname() {
        return buyerSurname;
    }

    public void setBuyerSurname(String buyerSurname) {
        this.buyerSurname = buyerSurname;
    }

    public String getBuyerNationId() {
        return buyerNationId;
    }

    public void setBuyerNationId(String buyerNationId) {
        this.buyerNationId = buyerNationId;
    }

    public int getUserActivateStatus() {
        return userActivateStatus;
    }

    public void setUserActivateStatus(int userActivateStatus) {
        this.userActivateStatus = userActivateStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getFirstStatLegNum() {
        return firstStatLegNum;
    }

    public void setFirstStatLegNum(int firstStatLegNum) {
        this.firstStatLegNum = firstStatLegNum;
    }

    public String getFirstStatDepSchedTime() {
        return firstStatDepSchedTime;
    }

    public void setFirstStatDepSchedTime(String firstStatDepSchedTime) {
        this.firstStatDepSchedTime = firstStatDepSchedTime;
    }

    public String getFirstStatArrSchedTime() {
        return firstStatArrSchedTime;
    }

    public void setFirstStatArrSchedTime(String firstStatArrSchedTime) {
        this.firstStatArrSchedTime = firstStatArrSchedTime;
    }

    public int getLastStatLegNum() {
        return lastStatLegNum;
    }

    public void setLastStatLegNum(int lastStatLegNum) {
        this.lastStatLegNum = lastStatLegNum;
    }

    public String getLastStatDepSchedTime() {
        return lastStatDepSchedTime;
    }

    public void setLastStatDepSchedTime(String lastStatDepSchedTime) {
        this.lastStatDepSchedTime = lastStatDepSchedTime;
    }

    public String getLastStatArrSchedTime() {
        return lastStatArrSchedTime;
    }

    public void setLastStatArrSchedTime(String lastStatArrSchedTime) {
        this.lastStatArrSchedTime = lastStatArrSchedTime;
    }

    public String getFirstStatDate() {
        return firstStatDate;
    }

    public void setFirstStatDate(String firstStatDate) {
        this.firstStatDate = firstStatDate;
    }

    public String getFirstStatDepActualTime() {
        return firstStatDepActualTime;
    }

    public void setFirstStatDepActualTime(String firstStatDepActualTime) {
        this.firstStatDepActualTime = firstStatDepActualTime;
    }

    public String getFirstStatArrActualTime() {
        return firstStatArrActualTime;
    }

    public void setFirstStatArrActualTime(String firstStatArrActualTime) {
        this.firstStatArrActualTime = firstStatArrActualTime;
    }

    public int getFirstStatLegAvailSeats() {
        return firstStatLegAvailSeats;
    }

    public void setFirstStatLegAvailSeats(int firstStatLegAvailSeats) {
        this.firstStatLegAvailSeats = firstStatLegAvailSeats;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getLastStatDate() {
        return lastStatDate;
    }

    public void setLastStatDate(String lastStatDate) {
        this.lastStatDate = lastStatDate;
    }

    public String getLastStatDepActualTime() {
        return lastStatDepActualTime;
    }

    public void setLastStatDepActualTime(String lastStatDepActualTime) {
        this.lastStatDepActualTime = lastStatDepActualTime;
    }

    public String getLastStatArrActualTime() {
        return lastStatArrActualTime;
    }

    public void setLastStatArrActualTime(String lastStatArrActualTime) {
        this.lastStatArrActualTime = lastStatArrActualTime;
    }

    public int getLastStatLegAvailSeats() {
        return lastStatLegAvailSeats;
    }

    public void setLastStatLegAvailSeats(int lastStatLegAvailSeats) {
        this.lastStatLegAvailSeats = lastStatLegAvailSeats;
    }

    public int getFirstStatId() {
        return firstStatId;
    }

    public void setFirstStatId(int firstStatId) {
        this.firstStatId = firstStatId;
    }

    public String getFirstStatName() {
        return firstStatName;
    }

    public void setFirstStatName(String firstStatName) {
        this.firstStatName = firstStatName;
    }

    public String getFirstStatCity() {
        return firstStatCity;
    }

    public void setFirstStatCity(String firstStatCity) {
        this.firstStatCity = firstStatCity;
    }

    public double getFirstStatLongitude() {
        return firstStatLongitude;
    }

    public void setFirstStatLongitude(double firstStatLongitude) {
        this.firstStatLongitude = firstStatLongitude;
    }

    public double getFirstStatLatitude() {
        return firstStatLatitude;
    }

    public void setFirstStatLatitude(double firstStatLatitude) {
        this.firstStatLatitude = firstStatLatitude;
    }

    public int getLastStatId() {
        return lastStatId;
    }

    public void setLastStatId(int lastStatId) {
        this.lastStatId = lastStatId;
    }

    public String getLastStatName() {
        return lastStatName;
    }

    public void setLastStatName(String lastStatName) {
        this.lastStatName = lastStatName;
    }

    public String getLastStatCity() {
        return lastStatCity;
    }

    public void setLastStatCity(String lastStatCity) {
        this.lastStatCity = lastStatCity;
    }

    public double getLastStatLongitude() {
        return lastStatLongitude;
    }

    public void setLastStatLongitude(double lastStatLongitude) {
        this.lastStatLongitude = lastStatLongitude;
    }

    public double getLastStatLatitude() {
        return lastStatLatitude;
    }

    public void setLastStatLatitude(double lastStatLatitude) {
        this.lastStatLatitude = lastStatLatitude;
    }

    public int getRailcarNum() {
        return railcarNum;
    }

    public void setRailcarNum(int railcarNum) {
        this.railcarNum = railcarNum;
    }

    public int getRailcarCapacity() {
        return railcarCapacity;
    }

    public void setRailcarCapacity(int railcarCapacity) {
        this.railcarCapacity = railcarCapacity;
    }

    public String getRailcarType() {
        return railcarType;
    }

    public void setRailcarType(String railcarType) {
        this.railcarType = railcarType;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public String getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(String seatLocation) {
        this.seatLocation = seatLocation;
    }

    public String getTripStatus() {
        return "default";
    }


}

