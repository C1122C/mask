package business;

import java.util.ArrayList;

public class PayBean {
	private int oid;
	private int min;
	private int sec;
	private ArrayList<Integer> cids;
	private ArrayList<Double> mons;
	private double sum;
	private String info;//cid-cid
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getSec() {
		return sec;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	public ArrayList<Integer> getCids() {
		return cids;
	}
	public void setCids(ArrayList<Integer> cids) {
		this.cids = cids;
	}
	public ArrayList<Double> getMons() {
		return mons;
	}
	public void setMons(ArrayList<Double> mons) {
		this.mons = mons;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
