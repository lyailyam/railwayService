package kz.edu.nu.cs.se.api;

public class Trip {
	
	private int id;
	private String dep_datetime;
	private String arr_datetime;
	private String status;
	private int first_stat_id;
	private int last_stat_id;
	private int route_id;
	private int train_id;
	
	public Trip(int id, String dep_datetime, String arr_datetime, String status,
			int first_stat_id, int last_stat_id, int route_id, int train_id) {
        this.id = id;
        this.dep_datetime = dep_datetime;
        this.arr_datetime = arr_datetime;
        this.status = status;
        this.first_stat_id = first_stat_id;
        this.last_stat_id = last_stat_id;
        this.route_id = route_id;
        this.train_id = train_id;
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDep_datetime() {
		return dep_datetime;
	}
	public void setDep_datetime(String dep_datetime) {
		this.dep_datetime = dep_datetime;
	}
	public String getArr_datetime() {
		return arr_datetime;
	}
	public void setArr_datetime(String arr_datetime) {
		this.arr_datetime = arr_datetime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFirst_stat_id() {
		return first_stat_id;
	}
	public void setFirst_stat_id(int first_stat_id) {
		this.first_stat_id = first_stat_id;
	}
	public int getLast_stat_id() {
		return last_stat_id;
	}
	public void setLast_stat_id(int last_stat_id) {
		this.last_stat_id = last_stat_id;
	}
	public int getRoute_id() {
		return route_id;
	}
	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	public int getTrain_id() {
		return train_id;
	}
	public void setTrain_id(int train_id) {
		this.train_id = train_id;
	}
	
	
	
	

}
