package model;

import java.io.Serializable;
import java.util.Date;

import util.COUPON_TYPE;

public class Coupon implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String type;
	private int type_id;
	private double condition;
	private double value;
	private Date valid;
	private int change;
	private String name;
	private String des;
	private int isMine;//1 for yes
	
	public double getCut(double sum) {
		if(type.equals(COUPON_TYPE.DISCOUNT.getName())) {
			return sum*value;
		}
		else {
			return sum-value;
		}
	}

	
	
	public int getType_id() {
		return type_id;
	}



	public void setType_id(int type_id) {
		this.type_id = type_id;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCondition() {
		return condition;
	}

	public void setCondition(double condition) {
		this.condition = condition;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getValid() {
		return valid;
	}

	public void setValid(Date valid) {
		this.valid = valid;
	}



	public int getChange() {
		return change;
	}



	public void setChange(int change) {
		this.change = change;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDes() {
		return des;
	}



	public void setDes(String des) {
		this.des = des;
	}



	public int getIsMine() {
		return isMine;
	}



	public void setIsMine(int isMine) {
		this.isMine = isMine;
	}
	
	
}
