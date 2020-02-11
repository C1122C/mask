package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Hall implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int vid;
	private String name;
	private String showTime;
	private String timetosee;//use for page
	boolean isSelect;
	private Seat[][] seats;
	private int seatNum;
	private ArrayList<Double> prices;
	private ArrayList<String> b_pic;
	private ArrayList<String> tip;//for show
	private int aid;
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String prices_to_save_form() {
		String s="";
		for(int i=0;i<prices.size();i++) {
			s=s+prices.get(i);
			if(i!=prices.size()-1) {
				s=s+"=";
			}
		}
		return s;
	}
	public void get_prices_from_db(String s) {
		ArrayList<Double> pri=new ArrayList<Double>();
		String s1[]=s.split("=");
		for(int i=0;i<s1.length;i++) {
			pri.add(Double.parseDouble(s1[i]));
		}
		this.setPrices(pri);
	}
	public String bpic_to_save_form() {
		String s="";
		for(int i=0;i<b_pic.size();i++) {
			s=s+b_pic.get(i);
			if(i!=b_pic.size()-1) {
				s=s+"=";
			}
		}
		return s;
	}
	public void get_bpic_from_db(String s) {
		ArrayList<String> pri=new ArrayList<String>();
		String s1[]=s.split("=");
		for(int i=0;i<s1.length;i++) {
			pri.add(s1[i]);
		}
		this.setB_pic(pri);
	}
	public String tip_to_save_form() {
		String s="";
		for(int i=0;i<tip.size();i++) {
			s=s+tip.get(i);
			if(i!=tip.size()-1) {
				s=s+"=";
			}
		}
		return s;
	}
	public void get_tip_from_db(String s) {
		ArrayList<String> pri=new ArrayList<String>();
		String s1[]=s.split("=");
		for(int i=0;i<s1.length;i++) {
			pri.add(s1[i]);
		}
		this.setTip(pri);
	}
	public ArrayList<String> getB_pic() {
		return b_pic;
	}


	public void setB_pic(ArrayList<String> b_pic) {
		this.b_pic = b_pic;
	}
	//用点隔开
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

	public String getShowTime() {
		return showTime;
	}


	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}


	public String getTimetosee() {
		return timetosee;
	}


	public void setTimetosee(String timetosee) {
		this.timetosee = timetosee;
	}


	public boolean isSelect() {
		return isSelect;
	}


	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}


	public Seat[][] getSeats() {
		return seats;
	}


	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}


	public int getSeatNum() {
		return seatNum;
	}


	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	
	public ArrayList<Double> getPrices() {
		return prices;
	}
	public void setPrices(ArrayList<Double> price) {
		this.prices = price;
	}
	public ArrayList<String> getTip() {
		return tip;
	}


	public void setTip(ArrayList<String> tip) {
		this.tip = tip;
	}
	//
	
	//
    public void sell(int x,int y,int oid) {
    	Seat s=seats[x][y];
    	s.setOid(oid);
    	String ss=s.getState();
    	s.setOrgState(ss);
    	s.setState("sold");
    	seats[x][y]=s;
    }
    /*checked*/
    public String sell_vote(double price,int oid) {
    	System.out.println("BEFORE VOTE:---------------------------------------------");
    	System.out.println("BEFORE VOTE:"+this.to_save_form());
    	for(int i=0;i<seats.length;i++) {
    		for(int j=0;j<seats[i].length;j++) {
    			if((!seats[i][j].getState().equals("sold"))&&Math.abs(seats[i][j].getPrice()-price)<1) {
    				Seat s=seats[i][j];
    		    	s.setOid(oid);
    		    	String ss=s.getState();
    		    	s.setOrgState(ss);
    		    	s.setState("sold");
    		    	seats[i][j]=s;
    				return seats[i][j].getRealX()+"-"+seats[i][j].getRealY()+"-"+seats[i][j].getPosRow()+"-"+seats[i][j].getPosColum();
    			}
    		}
    	}
    	System.out.println("AFTER VOTE:"+this.to_save_form());
    	System.out.println("AFTER VOTE:-------------------------------------------------");
    	return "";
    }
	public String to_save_form() {
		String result="";
		int row=this.seats.length;
		int colum=this.seats[0].length;
		for(int i=0;i<row;i++) {
			for(int j=0;j<colum;j++) {
				result=result+seats[i][j].getState()+"="+seats[i][j].getOrgState()+"="+
						seats[i][j].getPosRow()+"="+seats[i][j].getPosColum()+"="+seats[i][j].getPrice()+"="+
						seats[i][j].getOid();
				if(j!=colum-1) {
					result=result+"-";
				}
			}
			if(i!=row-1) {
				result=result+":";
			}
		}
		return result;
	}
	public void from_save_form(String in) {
		String s1[]=in.split(":");
		Seat seats[][]=new Seat[s1.length][];
		for(int i=0;i<s1.length;i++) {
			String s2[]=s1[i].split("-");
			seats[i]=new Seat[s2.length];
			for(int j=0;j<s2.length;j++) {
				Seat seat=new Seat();
				//System.out.println(s2[j]);
				String s3[]=s2[j].split("=");
				seat.setOid(Integer.parseInt(s3[5]));
				seat.setOrgState(s3[1]);
				seat.setPosColum(s3[3]);
				seat.setPosRow(s3[2]);
				seat.setPrice(Double.parseDouble(s3[4]));
				seat.setRealX(i);
				seat.setRealY(j);
				seat.setState(s3[0]);
				seats[i][j]=seat;
				//System.out.println("CHECK SEAT:"+seat==null);
				//System.out.println("CHECK SEATS:"+seats==null);
				//System.out.println("CHECK SEATS:"+seats[i][j]==null);
				
			}
		}
		this.setSeats(seats);
	}
	public void setMatrix(String in) {
		this.b_pic=new ArrayList<String>();
		this.prices=new ArrayList<Double>();
		this.tip=new ArrayList<String>();
		
		int num=0;
		
		String input[]=in.split(":");
		Seat seats[][]=new Seat[input.length][];
		for(int i=0;i<input.length;i++) {
			
			String s[]=input[i].split("-");
			seats[i]=new Seat[s.length];
			for(int j=0;j<s.length;j++) {
				String ss[]=s[j].split("=");
				Seat seat=new Seat();
				if(ss[0].equals("empty")) {
					seat.setRealX(0);
					seat.setRealY(0);
					seat.setPosRow("0");
					seat.setPosColum("0");
					seat.setOid(0);
					seat.setOrgState("empty");
					seat.setPrice(0);
					seat.setState("empty");
					seats[i][j]=seat;
				}
				else {
					double price=Double.parseDouble(ss[3]);
					boolean find=false;
					for(int k=0;k<prices.size();k++) {
						if(Math.abs(prices.get(k)-price)<1) {
							find=true;
							num=k+2;
							break;
						}
					}
					if(!find) {
						prices.add(price);
						System.out.println("##################################WE ADD THE PRICE "+price+" WHEN "+s[j]+" AT "+input[i]+"##################################");
						tip.add(price+"");
						num=prices.size()+1;
						String pic="add-example"+num;
						b_pic.add(pic);
					}
					String state="add"+num;
					seat.setRealX(i);
					seat.setRealY(j);
					seat.setPosRow(ss[1]);
					seat.setPosColum(ss[2]);
					seat.setOid(0);
					seat.setOrgState(state);
					seat.setPrice(price);
					seat.setState(state);
					seats[i][j]=seat;
				}
				
			}
		}
		this.setSeats(seats);
	}
	/*checked*/
	public void cancle_order(Order order) {
		ArrayList<Integer> x=order.getReslX();
		ArrayList<Integer> y=order.getReslY();
		for(int i=0;i<x.size();i++) {
			seats[x.get(i)][y.get(i)].setOid(0);
			seats[x.get(i)][y.get(i)].setState(seats[x.get(i)][y.get(i)].getOrgState());
		}
	}
	public String check_in(Order order) {
		ArrayList<Integer> x=order.getReslX();
		ArrayList<Integer> y=order.getReslY();
		for(int i=0;i<x.size();i++) {
			String s1=seats[x.get(i)][y.get(i)].getState();
			if(s1.equals("add1")) {
				return "FAIL";
			}
			seats[x.get(i)][y.get(i)].setState("add1");
		}
		return "SUCCESS";
	}
	public double getStart() {
		double res=this.prices.get(0);
		for(int i=0;i<this.prices.size();i++) {
			if(res>this.prices.get(i)) {
				res=this.prices.get(i);
			}
		}
		return res;
	}
}
