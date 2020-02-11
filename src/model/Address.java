package model;

import java.io.Serializable;

public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String province;
	private String city;
	private String district;
	private String detail;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String toString() {
		String res="";
		if(province!=null&&!province.equals("")){
			res=res+province;
		}
		if(city!=null&&!city.equals("")){
			res=res+"="+city;
		}
		if(district!=null&&!district.equals("")){
			res=res+"="+district;
		}
		if(detail!=null&&!detail.equals("")){
			res=res+"="+detail;
		}
		return res;
	}
	public void reverse(String s) {
		String s1[]=s.split("=");
		if(s1.length>0) {
			province=s1[0];
		}
		if(s1.length>1) {
			city=s1[1];
		}
		if(s1.length>2) {
			district=s1[2];
		}
		if(s1.length>3) {
			detail=s1[3];
		}
	}
	public String getShowString() {
		return this.province+"省"+this.city+"市"+this.district+this.detail;
	}
	
}
