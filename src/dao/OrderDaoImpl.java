package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import factory.DaoFactory;
import model.Hall;
import model.Order;
import util.ORDER_STATE;

public class OrderDaoImpl implements OrderDao{
	private static OrderDaoImpl orderDao;
	private DaoFactory factory;
	private ActDao actDao;
	private SQLHelper helper;
	
	private OrderDaoImpl(){
		helper=SQLHelper.getInstance();
		factory=DaoFactory.getInstance();
		actDao=factory.getActDao();
	}
		
	public static OrderDaoImpl getInstance()
	{
		if(orderDao==null) {
			orderDao=new OrderDaoImpl();
		}
		return orderDao;
	}
	/*
	 * checked
	 * @see dao.OrderDao#getOrder(int, java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<Order> getOrder(int offset, String userid, String type) {
		String sql="update ORD set JUDGE=7, STATE=\""+ORDER_STATE.INVALID.getName()+"\" where DATEDIFF(ATIME,CURDATE())<0 and STATE=\""+ORDER_STATE.UNUSE.getName()+"\"";
		System.out.println("CHECK INVALID ORDER SQL IS "+sql);
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		int invalid=0;
		try {
			stmt = connection.prepareStatement(sql);
			invalid=stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CHECK INVALID ORDER RES IS "+invalid);
		System.out.println("SELECT ORDER FROM DB GET TYPE IS "+type);
		sql="";
		if(type.equals("")||type.equals("全部订单")) {
			sql="select * from ORD where UID=\""+userid+"\" order by ID desc limit "+offset+",6;";
		}
		else {
			if(type.equals("进行中")) {
				sql="select * from ORD where UID=\""+userid+"\" and JUDGE<=3 order by ID desc limit "+offset+",6;";
			}
			else if(type.equals("已完成")) {
				sql="select * from ORD where UID=\""+userid+"\" and JUDGE=4 order by ID desc limit "+offset+",6;";
			}
			else if(type.equals("已取消")) {
				sql="select * from ORD where UID=\""+userid+"\" and JUDGE>=5 order by ID desc limit "+offset+",6;";
			}
			else {
				sql="select * from ORD where UID=\""+userid+"\" order by ID desc limit "+offset+",6;";
			}
		}
		System.out.println("SELECT ORDER FROM DB SQL IS"+sql);
		
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Order> res=new ArrayList<Order>();
		ArrayList<Integer> toChange=new ArrayList<Integer>();
		long time=System.currentTimeMillis();
		try {
			while (result.next()) {
				System.out.println("NOT A EMPTY SET");
				Order o=new Order();
				o.setId(result.getInt("ID"));
				o.setUserid(result.getString("UID"));
				o.setActid(result.getInt("AID"));
				o.setActName(result.getString("ANAME"));
				o.setActTime(result.getTime("ATIME")+"");
				o.setJudgeState(result.getInt("JUDGE"));
				o.setPrice(result.getDouble("PRICE"));
				o.setRoom(result.getString("HNAME"));
				o.setVenueid(result.getInt("VID"));
				o.setVenueName(result.getString("VNAME"));
				o.setState(result.getString("STATE"));
				o.setSum(result.getDouble("TOTAL"));
				o.setPath(result.getString("PATH"));
				o.setStart(result.getLong("START"));
				o.setActType(result.getString("ATYPE"));
				if(time-result.getLong("START")>=300000&&result.getString("STATE").equals(ORDER_STATE.UNPAY.getName())) {
					o.setState(ORDER_STATE.CANCLE.getName());
					toChange.add(o.getId());
				}
				else {
					o.setState(result.getString("STATE"));
				}
				o.setPayDate(result.getDate("PAYDATE"));
				o.setPay_id(result.getString("PAY_ID"));
				o.setCreateDate(result.getTime("C_DATE"));
				o.unsave_realX(result.getString("R_X"));
				o.unsave_realY(result.getString("R_Y"));
				o.unsave_seatX(result.getString("S_X"));
				o.unsave_seatY(result.getString("S_Y"));
				res.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<toChange.size();i++) {
			sql="update ORD set STATE=\""+ORDER_STATE.CANCLE.getName()+"\",JUDGE=5 where ID="+toChange.get(i)+";";
			try {
				stmt = connection.prepareStatement(sql);
				stmt.executeUpdate();
				stmt.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		helper.close(connection, stmt, result);
		return res;
	}

	@Override
	public ArrayList<Order> getOrderByVen(int offset, int vid) {
		String sql="select * from ORD where VID="+vid+" order by ID desc limit "+offset+",6;";
		
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
		ArrayList<Order> res=new ArrayList<Order>();
		try {
			while (result.next()) {
				Order o=new Order();
				o.setId(result.getInt("ID"));
				o.setUserid(result.getString("UID"));
				o.setActid(result.getInt("AID"));
				o.setActName(result.getString("ANAME"));
				o.setActTime(result.getTime("ATIME")+"");
				o.setJudgeState(result.getInt("JUDGE"));
				o.setPrice(result.getDouble("PRICE"));
				o.setRoom(result.getString("HNAME"));
				o.setVenueid(result.getInt("VID"));
				o.setVenueName(result.getString("VNAME"));
				o.setState(result.getString("STATE"));
				o.setSum(result.getDouble("TOTAL"));
				o.setPath(result.getString("PATH"));
				o.setStart(result.getLong("START"));
				o.setState(result.getString("STATE"));
				o.setActType(result.getString("ATYPE"));
				o.setPayDate(result.getDate("PAYDATE"));
				o.setPay_id(result.getString("PAY_ID"));
				o.setCreateDate(result.getTime("C_DATE"));
				o.unsave_realX(result.getString("R_X"));
				o.unsave_realY(result.getString("R_Y"));
				o.unsave_seatX(result.getString("S_X"));
				o.unsave_seatY(result.getString("S_Y"));
				res.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		return res;
	}

	/*
	 * checked
	 * @see dao.OrderDao#getOrderByID(int)
	 */
	@Override
	public Order getOrderByID(int id) {
		String sql="select * from ORD where ID="+id+";";
		System.out.println("IN GET ORDER SQL IS"+sql);
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
		Order o=new Order();
		try {
			while (result.next()) {
				o.setId(result.getInt("ID"));
				o.setUserid(result.getString("UID"));
				o.setActid(result.getInt("AID"));
				o.setActName(result.getString("ANAME"));
				String time=result.getString("ATIME")+"";
				time=time.substring(0,time.length()-2);
				o.setActTime(time);
				o.setJudgeState(result.getInt("JUDGE"));
				o.setPrice(result.getDouble("PRICE"));
				o.setHallname(result.getString("HNAME"));
				o.setVenueid(result.getInt("VID"));
				o.setVenueName(result.getString("VNAME"));
				o.setActType(result.getString("ATYPE"));
				o.setState(result.getString("STATE"));
				o.setSum(result.getDouble("TOTAL"));
				o.setPath(result.getString("PATH"));
				o.setStart(result.getLong("START"));
				o.setState(result.getString("STATE"));
				o.setPayDate(result.getDate("PAYDATE"));
				o.setPay_id(result.getString("PAY_ID"));
				o.setCreateDate(result.getTime("C_DATE"));
				o.setActType(result.getString("ATYPE"));
				o.unsave_realX(result.getString("R_X"));
				o.unsave_realY(result.getString("R_Y"));
				o.unsave_seatX(result.getString("S_X"));
				o.unsave_seatY(result.getString("S_Y"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		return o;
	}

	/*
	 * checked
	 * */
	@Override
	public String addOrder(Order order) {
		String sql="insert into ORD (ID,UID,AID,VID,PATH,STATE,ANAME,ATIME,JUDGE,TOTAL,PRICE,VNAME,HNAME,S_X,S_Y,R_X,R_Y,START,C_DATE,ATYPE) VALUES ("+
				order.getId()+",\""+order.getUserid()+"\","+order.getActid()+","+order.getVenueid()+",\""+order.getPath()+"\",\""+
				order.getState()+"\",\""+order.getActName()+"\",\""+order.getActTime()+"\","+order.getJudgeState()+","+
				order.getSum()+","+order.getPrice()+",\""+order.getVenueName()+"\",\""+order.getHallname()+"\",\""+
				order.save_seatX()+"\",\""+order.save_seatY()+"\",\""+order.save_realX()+"\",\""+order.save_realY()+"\","+
				order.getStart()+",now(),\""+order.getActType()+"\");";
		System.out.println("ADD TO ORD SQL IS:"+sql);
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
		System.out.println("INSERT ORD IN DB RESULT IS:"+result);
		if(result==0) {
			return "FAIL";
		}
		return "SUCCESS";
		
	}

	/*
	 * checked
	 * @see dao.OrderDao#modState(int, java.lang.String, int)
	 */
	@Override
	public String modState(int id, String state,int i) {
		String sql="update ORD set STATE=\""+state+"\",JUDGE="+i+" where ID="+id+";";
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

	/*
	 *checked
	 * @see dao.OrderDao#modPayDate(int, java.lang.String)
	 */
	@Override
	public String modPayDate(int id,String state) {
		String sql="update ORD set STATE=\""+ORDER_STATE.UNUSE.getName()+"\",JUDGE=3,PAYDATE=now(),PAY_ID=\""+state+"\" where ID="+id+";";
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

	/*
	 * checked
	 * @see dao.OrderDao#get_a_oid()
	 */
	@Override
	public int getAOid() {
		String sql="select OID from IDS;";
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
				res=result.getInt("OID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="update IDS set OID="+(res+1)+";";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		return res;
	}

	/*checked*/
	@Override
	public void checkLate() {
		String sql="select * from ORD where STATE=\""+ORDER_STATE.UNPAY.getName()+"\";";
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
		ArrayList<Order> toChange=new ArrayList<Order>();
		
		long time=System.currentTimeMillis();
		try {
			while (result.next()) {
				if(time-result.getLong("START")>=900000) {
					Order order=new Order();
					order.setId(result.getInt("ID"));
					order.setActid(result.getInt("AID"));
					order.setActTime(result.getString("ATIME")+"");
					order.unsave_realX(result.getString("R_X"));
					order.unsave_realY(result.getString("R_Y"));
					toChange.add(order);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<toChange.size();i++) {
			sql="update ORD set STATE=\""+ORDER_STATE.CANCLE.getName()+"\",JUDGE=5 where ID="+toChange.get(i).getId()+";";
			try {
				stmt = connection.prepareStatement(sql);
				stmt.executeUpdate();
				stmt.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sql="select * from SEAT where AID="+toChange.get(i).getActid()+";";
			try {
				stmt = connection.prepareStatement(sql);
				result = stmt.executeQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				while (result.next()) {
					String st=result.getString("SHOWTIME")+"";
					System.out.println("****************************SEAT TIME IS "+st+" *****ORDER TIME IS "+toChange.get(i).getActTime()+"*****************************************");
					if(st.equals(toChange.get(i).getActTime())) {
						Hall hall=new Hall();
						hall.from_save_form(result.getString("SEATS"));
						hall.cancle_order(toChange.get(i));
						actDao.changeSeat(toChange.get(i).getActid(),st, hall);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		helper.close(connection, stmt, result);
	}

	
	
}
