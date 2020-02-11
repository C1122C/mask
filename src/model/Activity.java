package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Activity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String des;
	private String type;
	private ArrayList<Hall> halls;
	private int id;
	private int vid;
	private String name;
	private String vname;
	private String hname;
	private ArrayList<String> time;
	private String city;
	private String path;
	private boolean isSelect;
	private double price;
	private String first;
	private String last;
	private String state;
	private double total;//总收益
	private String apath;
	
	
	
	public String getApath() {
		return apath;
	}
	public void setApath(String apath) {
		this.apath = apath;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	
	
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public ArrayList<String> getTime() {
		return time;
	}
	public void setTime(ArrayList<String> time) {
		this.time = time;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Hall> getHalls() {
		return halls;
	}
	public void setHalls(ArrayList<Hall> halls) {
		this.halls = halls;
	}
	public boolean isSelect() {
		return isSelect;
	}
	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
