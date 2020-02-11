package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Activity;
import model.Hall;
import util.ACT_STATE;
import util.ORDER_STATE;

public class ActDaoImpl implements ActDao {

	private static ActDaoImpl ActDao;
	private SQLHelper helper;
	
	private ActDaoImpl() {
		helper=SQLHelper.getInstance();
	}
	
	public static ActDaoImpl getInstance()
	{
		if(ActDao==null) {
			ActDao=new ActDaoImpl();
		}
		return ActDao;
	}
	/**
	 * checked
	 */
	@Override
	public String addAct(Activity act) {
		int iss=0;
		if(act.isSelect()) {
			iss=1;
		}
		String sql="insert into ACT (ID,VID,NAME,VNAME,HNAME,TYPE,CITY,DES,PATH,ISSELECT,PRICE,FIRST,LAST,STATE,ISAD) VALUES ("+act.getId()+","+act.getVid()+",\""+act.getName()+"\",\""
				+act.getVname()+"\",\""+act.getHname()+"\",\""+act.getType()+"\",\""+act.getCity()+"\",\""+act.getDes()+"\",\""
				+act.getPath()+"\","+iss+","+act.getHalls().get(0).getStart()+",\""+act.getFirst()+"\",\""+act.getLast()+"\",\""+act.getState()+"\",0);";
		System.out.println("ADD ACT TO DB SQL IS:"+sql);
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		int result=0;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result==0) {
			return "FAIL";
		}
		
		ArrayList<Hall> halls=act.getHalls();
		for(int i=0;i<halls.size();i++) {
			Hall h=halls.get(i);
			sql="insert into SEAT (AID,HID,SHOWTIME,TIMETOSEE,SEATS,PRICES,B_PIC,TIP) VALUES ("+act.getId()+","+
					h.getId()+",\""+h.getShowTime()+"\",\""+h.getTimetosee()+"\",\""+h.to_save_form()+"\",\""+h.prices_to_save_form()+
					"\",\""+h.bpic_to_save_form()+"\",\""+h.tip_to_save_form()+"\");";
			try {
				stmt = connection.prepareStatement(sql);
				result = stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result==0) {
				return "FAIL";
			}
		}
		// TODO Auto-generated method stub
		helper.close1(connection, stmt);
		return "SUCCESS";
	}

	/*checked*/
	public Hall getHall(int hid) {
		String sql="select * from HALL where ID="+hid+";";
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hall hall=new Hall();
		try {
			while (result.next()) {
				hall.setId(result.getInt("ID"));
				hall.setVid(result.getInt("VID"));
				hall.setName(result.getString("NAME"));
				hall.setSeatNum(result.getInt("SNUM"));
				hall.from_save_form(result.getString("SEATS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		return hall;
	}
	
	/*checked*/
	@Override
	public Activity getByID(int id) {
		String sql="select * from ACT where ID="+id+";";
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DONE SELECT");
		Activity a=new Activity();
		try {
			while (result.next()) {
				System.out.println("WE HAVE AN ANSWER");
				a.setCity(result.getString("CITY"));
				a.setDes(result.getString("DES"));
				a.setFirst(result.getDate("FIRST")+"");
				a.setLast(result.getDate("LAST")+"");
				a.setHname(result.getString("HNAME"));
				a.setId(result.getInt("ID"));
				a.setName(result.getString("NAME"));
				a.setPath(result.getString("PATH"));
				a.setPrice(result.getDouble("PRICE"));
				int i=result.getInt("ISSELECT");
				if(i==1) {
					a.setSelect(true);
				}
				else {
					a.setSelect(false);
				}
				a.setState(result.getString("STATE"));
				a.setType(result.getString("TYPE"));
				a.setVid(result.getInt("VID"));
				a.setVname(result.getString("VNAME"));
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		sql="select * from SEAT where AID="+id+";";
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("DONE SEAT CHECK");
		ArrayList<Hall> halls=new ArrayList<Hall>();
		try {
			while (result.next()) {
				String st=result.getDate("SHOWTIME")+"";
				System.out.println("TIME:"+st);
				Hall hall=this.getHall(result.getInt("HID"));
				hall.setShowTime(st);
				hall.setTimetosee(result.getString("TIMETOSEE"));
				hall.from_save_form(result.getString("SEATS"));
				System.out.println(hall.to_save_form());
				hall.get_prices_from_db(result.getString("PRICES"));
				System.out.println("PRICE SET:"+hall.prices_to_save_form());
				hall.get_bpic_from_db(result.getString("B_PIC"));
				System.out.println("B_PIC SET:"+hall.bpic_to_save_form());
				hall.get_tip_from_db(result.getString("TIP"));
				halls.add(hall);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.setHalls(halls);
		helper.close(connection, stmt, result);
		return a;
	}

	/**
	 * checked
	 */
	@Override
	public ArrayList<Activity> getPassed(int offset) {
		String sql="select * from ACT where DATEDIFF(LAST,CURDATE())<=0 and STATE<>\""+ACT_STATE.CLOSED.getName()+"\" order by ID desc limit "+offset+",6;";
		System.out.println("GET PASSED FROM DB SQL IS "+sql);
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Activity> res=new ArrayList<Activity>();
		try {
			while (result.next()) {
				Activity a=new Activity();
				a.setCity(result.getString("CITY"));
				a.setDes(result.getString("DES"));
				a.setFirst(result.getDate("FIRST")+"");
				a.setLast(result.getDate("LAST")+"");
				a.setHname(result.getString("HNAME"));
				a.setId(result.getInt("ID"));
				a.setName(result.getString("NAME"));
				a.setPath(result.getString("PATH"));
				a.setPrice(result.getDouble("PRICE"));
				int i=result.getInt("ISSELECT");
				if(i==1) {
					a.setSelect(true);
				}
				else {
					a.setSelect(false);
				}
				a.setState(ACT_STATE.OVER.getName());
				a.setTotal(this.getSum(a.getId()));
				a.setType(result.getString("TYPE"));
				a.setVid(result.getInt("VID"));
				a.setVname(result.getString("VNAME"));
				res.add(a);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return res;
	}

	/**
	 * checked
	 */
	@Override
	public String modSettle(int aid) {
		String sql="update ACT set STATE=\""+ACT_STATE.CLOSED.getName()+"\" where ID="+aid+";";
		System.out.println("MOD SETTLE TO DB SQL IS "+sql);
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		int result=0;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close1(connection, stmt);
		return result==0?"FAIL":"SUCCESS";
	}

	/*checked*/
	@Override
	public String changeSeat(int aid, String showtime, Hall hall) {
		String sql="update SEAT set SEATS=\""+hall.to_save_form()+"\" where aid="+aid+" and SHOWTIME=\""+showtime+"\";";
		System.out.println("UPDATE TSEAT IN DB SQL IS"+sql);
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		int result=0;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close1(connection, stmt);
		System.out.println("UPDATE SEAT IN DB RESULTL IS:"+result);
		return result==0?"FAIL":"SUCCESS";
	}

	/**
	 * checked
	 */
	@Override
	public int getNewAid() {
		String sql="select AID from IDS;";
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int res=0;
		try {
			while (result.next()) {
				res=result.getInt("AID");
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="update IDS set AID="+(res+1)+";";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return res;
	}

	/*
	 * checked
	 * @see dao.ActDao#get_all_city()
	 */
	@Override
	public ArrayList<String> getAllCity() {
		String sql="select distinct(CITY) from ACT where STATE=\""+ACT_STATE.UNDERWAY.getName()+"\";";
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> res=new ArrayList<String>();
		try {
			while (result.next()) {
				res.add(result.getString("CITY"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return res;
	}

	/*
	 * checked
	 * @see dao.ActDao#get_ad()
	 */
	@Override
	public ArrayList<Activity> getAd() {
		String sql="select * from ACT where STATE=\""+ACT_STATE.UNDERWAY.getName()+"\" and ISAD=1 order by ID desc limit 0,6;";
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Activity> res=new ArrayList<Activity>();
		try {
			while (result.next()) {
				Activity a=new Activity();
				a.setCity(result.getString("CITY"));
				a.setDes(result.getString("DES"));
				a.setFirst(result.getDate("FIRST")+"");
				a.setLast(result.getDate("LAST")+"");
				a.setHname(result.getString("HNAME"));
				a.setId(result.getInt("ID"));
				a.setName(result.getString("NAME"));
				a.setPath(result.getString("PATH"));
				a.setPrice(result.getDouble("PRICE"));
				int i=result.getInt("ISSELECT");
				if(i==1) {
					a.setSelect(true);
				}
				else {
					a.setSelect(false);
				}
				a.setState(result.getString("STATE"));
				a.setType(result.getString("TYPE"));
				a.setVid(result.getInt("VID"));
				a.setVname(result.getString("VNAME"));
				a.setApath(result.getString("ADPATH"));
				res.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return res;
	}

	/*
	 * checked
	 * @see dao.ActDao#select_by_cond(java.lang.String, java.lang.String, int)
	 */
	@Override
	public ArrayList<Activity> selectByCond(String city, String type, int offset) {
		String sql="";
		if((city.equals("全国"))&&(type.equals("全部演出"))) {
			sql="select * from ACT where STATE=\""+ACT_STATE.UNDERWAY.getName()+"\" order by ID desc limit "+offset+",5;";
		}
		else if(!((city.equals("全国"))||(type.equals("全部演出")))){
			sql="select * from ACT where STATE=\""+ACT_STATE.UNDERWAY.getName()+"\" and TYPE=\""+type+"\" and CITY=\""+city+"\" order by ID desc limit "+offset+",5;";
		}
		else if(city.equals("全国")) {
			sql="select * from ACT where STATE=\""+ACT_STATE.UNDERWAY.getName()+"\" and TYPE=\""+type+"\" order by ID desc limit "+offset+",5;";
		}
		else if(type.equals("全部演出")) {
			sql="select * from ACT where STATE=\""+ACT_STATE.UNDERWAY.getName()+"\" and CITY=\""+city+"\" order by ID desc limit "+offset+",5;";
		}
		
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Activity> res=new ArrayList<Activity>();
		try {
			while (result.next()) {
				Activity a=new Activity();
				a.setCity(result.getString("CITY"));
				a.setDes(result.getString("DES"));
				a.setFirst(result.getDate("FIRST")+"");
				a.setLast(result.getDate("LAST")+"");
				a.setHname(result.getString("HNAME"));
				a.setId(result.getInt("ID"));
				a.setName(result.getString("NAME"));
				a.setPath(result.getString("PATH"));
				a.setPrice(result.getDouble("PRICE"));
				int i=result.getInt("ISSELECT");
				if(i==1) {
					a.setSelect(true);
				}
				else {
					a.setSelect(false);
				}
				a.setState(result.getString("STATE"));
				a.setType(result.getString("TYPE"));
				a.setVid(result.getInt("VID"));
				a.setVname(result.getString("VNAME"));
				res.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return res;
	}

	/*
	 * checked
	 * @see dao.ActDao#select_by_name_or_ven(java.lang.String, int)
	 */
	@Override
	public ArrayList<Activity> selectByNameOrVen(String input, int offset) {
		String sql="select * from ACT where STATE=\""+ACT_STATE.UNDERWAY.getName()+"\" and (NAME like \"%"+input+"%\" or VNAME like \"%"+input+"%\") order by ID desc limit "+offset+",5;";
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Activity> res=new ArrayList<Activity>();
		try {
			while (result.next()) {
				Activity a=new Activity();
				a.setCity(result.getString("CITY"));
				a.setDes(result.getString("DES"));
				a.setFirst(result.getDate("FIRST")+"");
				a.setLast(result.getDate("LAST")+"");
				a.setHname(result.getString("HNAME"));
				a.setId(result.getInt("ID"));
				a.setName(result.getString("NAME"));
				a.setPath(result.getString("PATH"));
				a.setPrice(result.getDouble("PRICE"));
				int i=result.getInt("ISSELECT");
				if(i==1) {
					a.setSelect(true);
				}
				else {
					a.setSelect(false);
				}
				a.setState(result.getString("STATE"));
				a.setType(result.getString("TYPE"));
				a.setVid(result.getInt("VID"));
				a.setVname(result.getString("VNAME"));
				res.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return res;
	}

	/*checked*/
	@Override
	public ArrayList<Activity> selectMore(String city, String type, int offset, String input) {
		if(input.equals("")||input==null) {
			return this.selectByCond(city, type, offset);
		}
		else {
			return this.selectByNameOrVen(input, offset);
		}
	}

	/**
	 * checked
	 */
	@Override
	public double getSum(int aid) {
		String sql="select sum(TOTAL) as t from ORD where AID="+aid+" and STATE<>\""+ORDER_STATE.CANCLE.getName()+"\" and STATE<>\""+ORDER_STATE.UNPAY.getName()+"\";";
		System.out.println("GET ACT SUM FROM DB SQL IS"+sql);
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double res=0;
		try {
			while (result.next()) {
				res=result.getDouble("t");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return res;
	}

	/**
	 * checked
	 */
	@Override
	public ArrayList<Activity> selectByVid(int vid, int offset) {
		String sql="select * from ACT where VID="+vid+" order by ID desc limit "+offset+",6;";
		System.out.println("GET ACT OF VENUE FROM DB SQL IS:"+sql);
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Activity> res=new ArrayList<Activity>();
		try {
			while (result.next()) {
				System.out.println("NOT AN EMPTY SET");
				Activity a=new Activity();
				a.setCity(result.getString("CITY"));
				a.setDes(result.getString("DES"));
				a.setFirst(result.getDate("FIRST")+"");
				a.setLast(result.getDate("LAST")+"");
				a.setHname(result.getString("HNAME"));
				a.setId(result.getInt("ID"));
				a.setName(result.getString("NAME"));
				a.setPath(result.getString("PATH"));
				a.setPrice(result.getDouble("PRICE"));
				int i=result.getInt("ISSELECT");
				if(i==1) {
					a.setSelect(true);
				}
				else {
					a.setSelect(false);
				}
				a.setState(result.getString("STATE"));
				a.setType(result.getString("TYPE"));
				a.setVid(result.getInt("VID"));
				a.setVname(result.getString("VNAME"));
				res.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return res;
	}

}
