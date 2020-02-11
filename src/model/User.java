package model;

import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String pwd;
	private String gender;
	private Address address;
	private String payID;
	private int b_year;
	private int b_month;
	private int b_day;
	private ArrayList<String> interest;
	private double consumption;
	private String rank;
	private int bonus;
	private double cut;
	private String cutToShow;
	
	
	public double getSum(double sum) {
		return sum*cut;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPayID() {
		return payID;
	}

	public void setPayID(String payID) {
		this.payID = payID;
	}

	

	

	public int getB_year() {
		return b_year;
	}

	public void setB_year(int b_year) {
		this.b_year = b_year;
	}

	public int getB_month() {
		return b_month;
	}

	public void setB_month(int b_month) {
		this.b_month = b_month;
	}

	public int getB_day() {
		return b_day;
	}

	public void setB_day(int b_day) {
		this.b_day = b_day;
	}

	public ArrayList<String> getInterest() {
		return interest;
	}

	public void setInterest(ArrayList<String> interest) {
		this.interest = interest;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public double getCut() {
		return cut;
	}

	public void setCut(double cut) {
		this.cut = cut;
	}
	public void interest(String s) {
		if(s==null||s.length()<3) {
			return;
		}
		String s1[]=s.split("=");
		this.interest=new ArrayList<String>();
		for(int i=0;i<s1.length;i++) {
			interest.add(s1[i]);
		}
	}
	public String getinterest() {
		String res="";
		if(interest.size()==0) {
			return res;
		}
		if(interest.size()==1) {
			return interest.get(0);
		}
		for(int i=0;i<interest.size()-1;i++) {
			res=res+interest.get(i)+"=";
		}
		res=res+interest.get(interest.size()-1);
		return res;
	}

	public String getCutToShow() {
		return cutToShow;
	}

	public void setCutToShow(String cutToShow) {
		this.cutToShow = cutToShow;
	}
	/*
	 * checked
	 */
	public void setRankFromCon(double con) {
		if(con>=5000) {
			this.setCut(0.85);
			this.setCutToShow("八五折");
			this.setRank("皇冠会员");
			return;
		}
		if(con>=2000) {
			this.setCut(0.9);
			this.setCutToShow("九折");
			this.setRank("钻石会员");
			return;
		}
		if(con>=1000) {
			this.setCut(0.96);
			this.setCutToShow("九六折");
			this.setRank("黄金会员");
			return;
		}
		if(con>=500) {
			this.setCut(0.98);
			this.setCutToShow("九八折");
			this.setRank("白银会员");
			return;
		}
		this.setCut(1);
		this.setCutToShow("无折扣");
		this.setRank("普通会员");
		
		
	}
}
