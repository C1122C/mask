package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Venue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;//7
	private String name;
	private Address addr;
	private String phone;
	private ArrayList<Hall> hall;
	private String payid;
	private String pwd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddr() {
		return addr;
	}
	public void setAddr(Address addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public ArrayList<Hall> getHall() {
		return hall;
	}
	public void setHall(ArrayList<Hall> hall) {
		this.hall = hall;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
