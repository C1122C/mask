package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;



public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String userid;
	private String path;
	private int actid;
	private int venueid;
	private String state;
	private double sum;
	private String actName;
	private String actTime;
	private int judgeState;
	//票面单价
	private double price;
	private String venueName;
	private String venueCity;
	private String room;
	private ArrayList<String> seatX;
	private ArrayList<String> seatY;
	private ArrayList<Integer> reslX;
	private ArrayList<Integer> reslY;
	private long start;
	private Date payDate;
	private Date createDate;
	private String pay_id;//付款方密码
	private String hallname;
	private String actType;
	
	public String getActType() {
		return actType;
	}
	public void setActType(String actType) {
		this.actType = actType;
	}
	public String save_seatX() {
		String res="";
		for(int i=0;i<seatX.size();i++) {
			res=res+seatX.get(i);
			if(i!=seatX.size()-1) {
				res=res+"=";
			}
		}
		return res;
	}
	public void unsave_seatX(String s) {
		String res[]=s.split("=");
		ArrayList<String> array=new ArrayList<String>();
		for(int i=0;i<res.length;i++) {
			array.add(res[i]);
		}
		this.setSeatX(array);
	}
	public String save_seatY() {
		String res="";
		for(int i=0;i<seatY.size();i++) {
			res=res+seatY.get(i);
			if(i!=seatY.size()-1) {
				res=res+"=";
			}
		}
		return res;
	}
	public void unsave_seatY(String s) {
		String res[]=s.split("=");
		ArrayList<String> array=new ArrayList<String>();
		for(int i=0;i<res.length;i++) {
			array.add(res[i]);
		}
		this.setSeatY(array);
	}
	public String save_realX() {
		String res="";
		for(int i=0;i<reslX.size();i++) {
			res=res+reslX.get(i);
			if(i!=reslX.size()-1) {
				res=res+"=";
			}
		}
		return res;
	}
	public void unsave_realX(String s) {
		String res[]=s.split("=");
		ArrayList<Integer> array=new ArrayList<Integer>();
		for(int i=0;i<res.length;i++) {
			array.add(Integer.parseInt(res[i]));
		}
		this.setReslX(array);
	}
	public String save_realY() {
		String res="";
		for(int i=0;i<reslY.size();i++) {
			res=res+reslY.get(i);
			if(i!=reslY.size()-1) {
				res=res+"=";
			}
		}
		return res;
	}
	public void unsave_realY(String s) {
		String res[]=s.split("=");
		ArrayList<Integer> array=new ArrayList<Integer>();
		for(int i=0;i<res.length;i++) {
			array.add(Integer.parseInt(res[i]));
		}
		this.setReslY(array);
	}
	public String getHallname() {
		return hallname;
	}

	public void setHallname(String hallname) {
		this.hallname = hallname;
	}

	public String getPay_id() {
		return pay_id;
	}

	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}

	public int getJudgeState() {
		return judgeState;
	}

	public void setJudgeState(int judgeState) {
		this.judgeState = judgeState;
	}

	public String getActTime() {
		return actTime;
	}

	public void setActTime(String actTime) {
		this.actTime = actTime;
	}

	
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Order() {
		start=System.currentTimeMillis();
	}
	
	public void pay() {
		payDate = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getActid() {
		return actid;
	}

	public void setActid(int actid) {
		this.actid = actid;
	}

	
	public int getVenueid() {
		return venueid;
	}

	public void setVenueid(int venueid) {
		this.venueid = venueid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	

	

	public String getVenueCity() {
		return venueCity;
	}

	public void setVenueCity(String venueCity) {
		this.venueCity = venueCity;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	
	public ArrayList<String> getSeatX() {
		return seatX;
	}

	public void setSeatX(ArrayList<String> seatX) {
		this.seatX = seatX;
	}

	public ArrayList<String> getSeatY() {
		return seatY;
	}

	public void setSeatY(ArrayList<String> seatY) {
		this.seatY = seatY;
	}

	public ArrayList<Integer> getReslX() {
		return reslX;
	}

	public void setReslX(ArrayList<Integer> reslX) {
		this.reslX = reslX;
	}

	public ArrayList<Integer> getReslY() {
		return reslY;
	}

	public void setReslY(ArrayList<Integer> reslY) {
		this.reslY = reslY;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	
	
	
}
