package model;

import java.util.ArrayList;

public class VenueStatistic {
	//name-money
	private ArrayList<String> top_ten;
	private ArrayList<String> order_kind;
	private ArrayList<Integer> orderNum;
	private ArrayList<String> date;
	private ArrayList<Double> consum;
	
	public ArrayList<String> getTop_ten() {
		return top_ten;
	}
	public void setTop_ten(ArrayList<String> top_ten) {
		this.top_ten = top_ten;
	}
	public ArrayList<String> getOrder_kind() {
		return order_kind;
	}
	public void setOrder_kind(ArrayList<String> order_kind) {
		this.order_kind = order_kind;
	}
	
	public ArrayList<Integer> getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(ArrayList<Integer> orderNum) {
		this.orderNum = orderNum;
	}
	
	public ArrayList<String> getDate() {
		return date;
	}
	public void setDate(ArrayList<String> date) {
		this.date = date;
	}
	public ArrayList<Double> getConsum() {
		return consum;
	}
	public void setConsum(ArrayList<Double> consum) {
		this.consum = consum;
	}
	
	
}
