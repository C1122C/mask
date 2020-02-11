package model;

import java.io.Serializable;


public class Seat implements Serializable{
	private static final long serialVersionUID = 1L;
	private String state;//empty sold add1-10 selected selectable
	private String orgState;
	private int realX;
	private int realY;
	private String posRow;
	private String posColum;
	private double price;
	private int oid;
	
	public String getOrgState() {
		return orgState;
	}
	public void setOrgState(String orgState) {
		this.orgState = orgState;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getRealX() {
		return realX;
	}
	public void setRealX(int realX) {
		this.realX = realX;
	}
	public int getRealY() {
		return realY;
	}
	public void setRealY(int realY) {
		this.realY = realY;
	}
	
	public String getPosRow() {
		return posRow;
	}
	public void setPosRow(String posRow) {
		this.posRow = posRow;
	}
	public String getPosColum() {
		return posColum;
	}
	public void setPosColum(String posColum) {
		this.posColum = posColum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	
}
