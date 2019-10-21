package kz.edu.nu.cs.se.models;

public class TicketInfo {

    private int ticketId;
    private double price;
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String ticketStatus;
    private String depDate;
    private String depTime;
    private String arrDate;
    private String arrTime;
    private String tripStatus;
    private String firstStatName;
    private String lastStatName;
    private int trainId;
    private int railcarId;
    private String seatNum;
    private String seatLocation;

    public TicketInfo() {}

    public TicketInfo(int ticketId, double price, int userId, String userFirstName, String userLastName, String ticketStatus,
                      String depDate, String depTime, String arrDate, String arrTime, String tripStatus,
                      String firstStatName, String lastStatName, int trainId, int railcarId, String seatNum, String seatLocation) {
        this.ticketId = ticketId;
        this.price = price;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.ticketStatus = ticketStatus;
        this.depDate = depDate;
        this.depTime = depTime;
        this.arrDate = arrDate;
        this.arrTime = arrTime;
        this.tripStatus = tripStatus;
        this.firstStatName = firstStatName;
        this.lastStatName = lastStatName;
        this.trainId = trainId;
        this.railcarId = railcarId;
        this.seatNum = seatNum;
        this.seatLocation = seatLocation;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getFirstStatName() {
        return firstStatName;
    }

    public void setFirstStatName(String firstStatName) {
        this.firstStatName = firstStatName;
    }

    public String getLastStatName() {
        return lastStatName;
    }

    public void setLastStatName(String lastStatName) {
        this.lastStatName = lastStatName;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getRailcarId() {
        return railcarId;
    }

    public void setRailcarId(int railcarId) {
        this.railcarId = railcarId;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(String seatLocation) {
        this.seatLocation = seatLocation;
    }
}
