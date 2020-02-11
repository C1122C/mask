package model;

import java.util.ArrayList;

public class UserStatistic {
	
	private double sum;
	private ArrayList<String> orderKind;
	private ArrayList<Integer> orderNum;
	private ArrayList<String> actKind;
	private ArrayList<Integer> times;//concert,musicale,drama,ballet,child,opera,ent,sports
	private ArrayList<Double> money;
	private ArrayList<String> date;
	private ArrayList<Double> consum;
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public ArrayList<String> getOrderKind() {
		return orderKind;
	}
	public void setOrderKind(ArrayList<String> orderKind) {
		this.orderKind = orderKind;
	}
	public ArrayList<Integer> getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(ArrayList<Integer> orderNum) {
		this.orderNum = orderNum;
	}
	public ArrayList<String> getActKind() {
		return actKind;
	}
	public void setActKind(ArrayList<String> actKind) {
		this.actKind = actKind;
	}
	public ArrayList<Integer> getTimes() {
		return times;
	}
	public void setTimes(ArrayList<Integer> times) {
		this.times = times;
	}
	public ArrayList<Double> getMoney() {
		return money;
	}
	public void setMoney(ArrayList<Double> money) {
		this.money = money;
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
