package model;

import java.io.Serializable;
import java.util.Date;


public class App implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Venue venue;
	private Hall hall;
	private int id;
	private Date date;
	private String state;
	private String type;//申请注册 修改场馆基本信息 修改场馆展厅信息
	private int org;
	
	public int getOrg() {
		return org;
	}
	public void setOrg(int org) {
		this.org = org;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
