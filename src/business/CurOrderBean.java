package business;

import java.util.ArrayList;
import java.util.Date;

import model.Coupon;
import util.ORDER_STATE;

public class CurOrderBean {
	private int oid;
	private int aid;
	private String uid;
	private String path;
	private ORDER_STATE state;
	private Date create_time;
	private String act_name;
	private String act_time;
	private String vname;
	private String hall_name;
	private String seat_row;
	private String seat_col;
	private String seat;
	private double sum;
	private ArrayList<Coupon> coupon;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ORDER_STATE getState() {
		return state;
	}
	public void setState(ORDER_STATE state) {
		this.state = state;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	
	public String getAct_time() {
		return act_time;
	}
	public void setAct_time(String act_time) {
		this.act_time = act_time;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getHall_name() {
		return hall_name;
	}
	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}
	public String getSeat_row() {
		return seat_row;
	}
	public void setSeat_row(String seat_row) {
		this.seat_row = seat_row;
	}
	public String getSeat_col() {
		return seat_col;
	}
	public void setSeat_col(String seat_col) {
		this.seat_col = seat_col;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public ArrayList<Coupon> getCoupon() {
		return coupon;
	}
	public void setCoupon(ArrayList<Coupon> coupon) {
		this.coupon = coupon;
	}
	
	
}
