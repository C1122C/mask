package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Address;
import model.App;
import model.Hall;
import model.Venue;
import util.ACCOUNT_STATE;
import util.APP_STATE;
import util.APP_TYPE;

public class VenueDaoImpl implements VenueDao {

	private static VenueDaoImpl venueDao;
	private SQLHelper helper;
	
	private VenueDaoImpl(){
		helper=SQLHelper.getInstance();
	}
		
	public static VenueDaoImpl getInstance()
	{
		if(venueDao==null) {
			venueDao=new VenueDaoImpl();
		}
		return venueDao;
	}
	
	/**
	 * checked
	 */
	@Override
	public ArrayList<App> getApplication(int offset) {
		String sql="select * from APP where ISVENUE=1 and STATE=\""+APP_STATE.WITHOUT_APPROVAL.getName()+"\" order by ID desc limit "+offset+",6;";
		System.out.println("GET APP FROM DB SQL IS "+sql);
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList<Integer> venueid=new ArrayList<Integer>();
		ArrayList<Integer> hallid=new ArrayList<Integer>();
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<App> res=new ArrayList<App>();
		try {
			while (result.next()) {
				App v=new App();
				v.setId(result.getInt("ID"));
				v.setDate(result.getTime("C_DATE"));
				v.setState(result.getString("STATE"));
				v.setType(result.getString("TYPE"));
				v.setOrg(result.getInt("ORG"));
				int vid=result.getInt("POINT");
				venueid.add(vid);
				res.add(v);
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=0;
		for(i=0;i<venueid.size();i++) {
			Venue venue=new Venue();
			venue=this.getByID(venueid.get(i));
			App a=res.get(i);
			a.setVenue(venue);
			res.set(i, a);
		}
		sql="select * from APP where ISVENUE=0 and STATE=\""+APP_STATE.WITHOUT_APPROVAL.getName()+"\" order by ID desc limit "+offset+",6;";
		System.out.println("GET APP FROM DB SQL IS "+sql);
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (result.next()) {
				App v=new App();
				v.setId(result.getInt("ID"));
				v.setDate(result.getTime("C_DATE"));
				v.setState(result.getString("STATE"));
				v.setType(result.getString("TYPE"));
				v.setOrg(result.getInt("ORG"));
				int hid=result.getInt("POINT");
				hallid.add(hid);
				res.add(v);
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int j=0;j<hallid.size();j++) {
			Hall hall=new Hall();
			hall=this.findByID(hallid.get(j));
			App a=res.get(i+j);
			a.setHall(hall);
			res.set(i+j, a);
		}
		helper.close(connection, stmt, result);
		return res;
	}

	/**
	 * checked
	 */
	@Override
	public String modAppState(int id,String state) {
		String sql="select * from APP where ID="+id+";";
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
		String type="";
		int point=0;
		int org=0;
		try {
			while (result.next()) {
				point=result.getInt("POINT");
				type=result.getString("TYPE");
				org=result.getInt("ORG");
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql="update APP set STATE=\""+state+"\" where ID="+id+";";
		try {
			stmt = connection.prepareStatement(sql);
			res = stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res==0) {
			return "FAIL";
		}
		String sql1="";
		boolean havetwo=false;
		if(state.equals(APP_STATE.DOWN.getName())) {
			if(type.equals(APP_TYPE.ADD_HALL.getName())) {
				sql="delete from HALL where ID="+point+";";
				
			}
			else if(type.equals(APP_TYPE.DELETE_HALL.getName())) {
				sql="update HALL set ISAPP=0 where ID="+point+";";
				
			}
			else if(type.equals(APP_TYPE.MOD_VENUE.getName())) {
				sql="delete from VENUE where ID="+point+";";
				
			}
			else {//注册场馆
				sql="delete from VENUE where ID="+point+";";
				sql1="delete from LOGIN where ID=\""+point+"\";";
				havetwo=true;
			}
		}
		else {
			if(type.equals(APP_TYPE.ADD_HALL.getName())) {
				sql="update HALL set ISAPP=0 where ID="+point+";";
				
			}
			else if(type.equals(APP_TYPE.DELETE_HALL.getName())) {
				sql="delete from HALL where ID="+point+";";
				
			}
			else if(type.equals(APP_TYPE.MOD_VENUE.getName())) {
				sql="delete from VENUE where ID="+org+";";
				sql1="update VENUE set ID="+org+",ISAPP=0 where ID="+point+";";
				havetwo=true;
			}
			else {//注册场馆
				sql="update VENUE set ISAPP=0 where ID="+point+";";
				sql1="update LOGIN set STATE=\""+ACCOUNT_STATE.CAN_USE.getName()+"\" where ID=\""+point+"\";";
				havetwo=true;
			}
		}
		try {
			stmt = connection.prepareStatement(sql);
			res = stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(havetwo) {
			try {
				stmt = connection.prepareStatement(sql1);
				res = stmt.executeUpdate();
				stmt.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("MOD APP RESULT IS "+res);
		helper.close(connection, stmt, result);
		return res==0?"FAIL":"SUCCESS";
	}
	
	/**
	 * checked
	 */
	@Override
	public ArrayList<App> getVenueApp(int vid, int offset) {
		String sql="select * from APP where ORG="+vid+" order by ID desc limit "+offset+",6;";
		System.out.println("GET APP OF VENUE FROM DB SQL IS:"+sql);
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
		ArrayList<App> res=new ArrayList<App>();
		try {
			while (result.next()) {
				System.out.println("NOT AN EMPTY SET");
				App v=new App();
				v.setId(result.getInt("ID"));
				v.setDate(result.getDate("C_DATE"));
				v.setState(result.getString("STATE"));
				v.setType(result.getString("TYPE"));
				res.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		return res;
	}

	/**
	 * checked
	 */
	@Override
	public Venue getByID(int id) {
		String sql="select * from VENUE where ID="+id+";";
		System.out.println("GET VENUE BY ID FROM DB SQL IS:"+sql);
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
		Venue v=new Venue();
		try {
			while (result.next()) {
				System.out.println("NOT AN EMPTY SET");
				v.setId(result.getInt("ID"));
				v.setName(result.getString("NAME"));
				Address a=new Address();
				a.reverse(result.getString("ADDR"));
				v.setAddr(a);
				v.setPhone(result.getString("PHONE"));
				v.setPayid(result.getString("PAY_ID"));
				System.out.println("GET IN HERE");	
			}
			System.out.println("BEFORE CLOSE");	
			result.close();
			stmt.close();
			System.out.println("AFTER CLOSE");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql="select * from HALL where VID="+v.getId()+" and ISAPP=0;";
		System.out.println("GET HALL OF VENUE FROM DB SQL IS:"+sql);
		ArrayList<Hall> hall=new ArrayList<Hall>();
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(result.next()) {
				System.out.println("NOT AN EMPTY SET");
				Hall h=new Hall();
				h.setId(result.getInt("ID"));
				h.setVid(result.getInt("VID"));
				h.setName(result.getString("NAME"));
				h.from_save_form(result.getString("SEATS"));
				h.setSeatNum(result.getInt("SNUM"));
				hall.add(h);
			}
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		v.setHall(hall);
		helper.close(connection, stmt, result);
		return v;
	}

	

	/**
	 * checked
	 */
	@Override
	public App getAppByID(int aid) {
		String sql="select * from APP where ID="+aid+";";
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
		App v=new App();
		try {
			while (result.next()) {
				
				v.setId(result.getInt("ID"));
				v.setDate(result.getTime("C_DATE"));
				v.setState(result.getString("STATE"));
				v.setType(result.getString("TYPE"));
				if(v.getType().equals(APP_TYPE.MOD_VENUE.getName())||v.getType().equals(APP_TYPE.REG_VENUE.getName())) {
					int vid=result.getInt("POINT");
					Venue venue=this.getByID(vid);
					v.setVenue(venue);
				}
				else {
					int hid=result.getInt("POINT");
					Hall hall=this.findByID(hid);
					v.setHall(hall);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		return v;
	}

	
	/**
	 * checked
	 */
	@Override
	public int getAppID() {
		String sql="select APPID from IDS;";
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
				res=result.getInt("APPID");
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="update IDS set APPID="+(res+1)+";";
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

	/**
	 * checked
	 */
	@Override
	public String addV(int id, String pwd) {
		String sql="insert into LOGIN (ID,PASSWORD,REGTIME,STATUS,STATE) values ("+id+",\""+pwd+"\",now(),\"v\",\""+ACCOUNT_STATE.WAIT_APPROVAL.getName()+"\");";
		System.out.println("INSERT VID INT LOGIN SQL IS:"+sql);
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
		if(result==0) {
			return "FAIL";
		}
		return "SUCCESS";
	}

	/**
	 * checked
	 */
	@Override
	public String addVenueApp(App app) {
		String sql="insert into APP (ID,C_DATE,STATE,TYPE,ISVENUE,POINT,ORG) values ("+app.getId()+",now(),\""+
			app.getState()+"\",\""+app.getType()+"\",1,"+app.getVenue().getId()+","+app.getOrg()+");";
		String sql1="insert into VENUE (ID,NAME,ADDR,PHONE,PAY_ID,ISAPP,ORIGINAL) VALUES ("+app.getVenue().getId()+",\""+
				app.getVenue().getName()+"\",\""+app.getVenue().getAddr().toString()+"\",\""+app.getVenue().getPhone()+"\",\""+
				app.getVenue().getPayid()+"\",1,"+app.getOrg()+");";
		System.out.println("INSERT APP TO DB SQL IS:"+sql);
		System.out.println("INSERT APP TO DB SQL IS:"+sql1);
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		int result=0;
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeUpdate();
			stmt.close();
			System.out.println("ADD APP RESULT:"+result);
			stmt = connection.prepareStatement(sql1);
			result = stmt.executeUpdate();
			stmt.close();
			System.out.println("ADD VENUE RESULT:"+result);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!(app.getVenue().getPwd()==null||app.getVenue().getPwd().equals(""))){
			System.out.println("----------------------------------IN ONE--------------------------------");
			if(app.getType().equals(APP_TYPE.MOD_VENUE.getName())) {
				sql="update LOGIN set PASSWORD=\""+app.getVenue().getPwd()+"\" where ID=\""+app.getOrg()+"\";";
				try {
					stmt = connection.prepareStatement(sql);
					result = stmt.executeUpdate();
					System.out.println("UPDATE PASS RESULT:"+result);
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("----------------------------------CHANGE PASS--------------------------------");
			}
		}
		helper.close1(connection, stmt);
		if(result==0) {
			return "FAIL";
		}
		return "SUCCESS";
	}

	/**
	 * checked
	 */
	@Override
	public String addHallApp(App app) {
		String sql="insert into APP (ID,C_DATE,STATE,TYPE,ISVENUE,POINT,ORG) values ("+app.getId()+",now(),\""+
				app.getState()+"\",\""+app.getType()+"\",0,"+app.getHall().getId()+","+app.getOrg()+");";
		
		String sql1="insert into HALL (ID,VID,NAME,SNUM,SEATS,ISAPP) VALUES ("+app.getHall().getId()+","+
					app.getHall().getVid()+",\""+app.getHall().getName()+"\","+app.getHall().getSeatNum()+",\""+
					app.getHall().to_save_form()+"\",1);";
		String sql2="update HALL set ISAPP=1 where id="+app.getHall().getId()+";";
		System.out.println("ADD HALL APP TO DB SQL IS:"+sql);
		System.out.println("ADD HALL APP TO DB SQL IS:"+sql1);
			Connection connection=helper.getConnection();
			PreparedStatement stmt=null;
			int result=0;
			try {
				stmt = connection.prepareStatement(sql);
				result = stmt.executeUpdate();
				stmt.close();
				if(app.getType().equals(APP_TYPE.ADD_HALL.getName())) {
					stmt = connection.prepareStatement(sql1);
					result = stmt.executeUpdate();
				}
				else {
					stmt = connection.prepareStatement(sql2);
					stmt.executeUpdate();
					
				}
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			helper.close1(connection, stmt);
			if(result==0) {
				return "FAIL";
			}
			return "SUCCESS";
	}

	/**
	 * checked
	 */
	@Override
	public Hall findByID(int hid) {
		String sql="select * from HALL where ID="+hid+";";
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		Hall h=new Hall();
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(result.next()) {
				System.out.println("NOT AN EMPTY SET");
				h.setId(result.getInt("ID"));
				h.setVid(result.getInt("VID"));
				h.setName(result.getString("NAME"));
				h.from_save_form(result.getString("SEATS"));
				h.setSeatNum(result.getInt("SNUM"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		return h;
	}

	/**
	 * checked
	 */
	@Override
	public int getHallID() {
		String sql="select HID from IDS;";
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
				res=result.getInt("HID");
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="update IDS set HID="+(res+1)+";";
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

	/**
	 * checked
	 */
	@Override
	public int getVID() {
		String sql="select VID from IDS;";
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
				res=result.getInt("VID");
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="update IDS set VID="+(res+1)+";";
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

	
	

}
