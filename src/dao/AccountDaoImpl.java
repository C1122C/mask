package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ACCOUNT_STATE;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class AccountDaoImpl implements AccountDao {
	
	private static AccountDaoImpl AccountDao;
	private SQLHelper helper;
	
	private AccountDaoImpl() {
		helper=SQLHelper.getInstance();
	}
	
	public static AccountDaoImpl getInstance()
	{
		if(AccountDao==null) {
			AccountDao=new AccountDaoImpl();
		}
		return AccountDao;
	}

	/**
	 * checked
	 */
	@Override
	public ArrayList<String> topTenVen(String start, String end) {
		String sql="select VID,VNAME,sum(TOTAL) as t from ORD where PAYDATE between \""+start+"\" and \""+end+"\" group by VID,VNAME order by t desc limit 0,10;";
		System.out.println("GET TOP VENUE FROM DB SQL IS "+sql);
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
				String s="";
				s=s+result.getString("VNAME")+"="+result.getDouble("t");
				res.add(s);
				System.out.println(s);
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
	public ArrayList<String> topTenAct(String start, String end) {
		String sql="select AID,ANAME,sum(TOTAL) as t from ORD where PAYDATE between \""+start+"\" and \""+end+"\" group by AID,ANAME order by t desc limit 0,10;";
		System.out.println("GET TOP ACT FROM DB SQL IS "+sql);
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
				String s="";
				s=s+result.getString("ANAME")+"="+result.getDouble("t");
				res.add(s);
				System.out.println(s);
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
	public ArrayList<String> getVen(String start, String end, int vid) {
		String sql="select PAYDATE,sum(TOTAL) as t from ORD where VID="+vid+" and PAYDATE between \""+start+"\" and \""+end+"\" group by PAYDATE order by PAYDATE;";
		System.out.println("GET VENUE CURVE FROM DB SQL IS:"+sql);
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
				System.out.println("NOT AN EMPTY SET");
				String s="";
				String ss=result.getString("PAYDATE");
				ss=ss.substring(0, ss.length()-2);
				s=ss+"="+result.getDouble("t");
				res.add(s);
				System.out.println(s);
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
	public ArrayList<String> getAct(int aid) {
		String sql="select PAYDATE,sum(TOTAL) as t from ORD where AID="+aid+" group by PAYDATE order by PAYDATE;";
		System.out.println("GET ACT CURVE FROM DB SQL IS:"+sql);
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
				System.out.println("NOT AN EMPTY SET");
				String s="";
				s=s+result.getString("PAYDATE")+"="+result.getDouble("t");
				res.add(s);
				System.out.println(s);
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
	public ArrayList<String> getPlatCurve(String start, String end) {
		double sum=this.getsum(start, end);
		String sql="select PAYDATE,sum(TOTAL) as t from ORD where PAYDATE between \""+start+"\" and \""+end+"\" group by PAYDATE order by PAYDATE;";
		System.out.println("GET PLAT CURVE FROM DB SQL IS:"+sql);
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
				String s="";
				s=s+result.getString("PAYDATE")+"="+result.getDouble("t")+"="+sum;
				res.add(s);
				System.out.println(s);
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
	public double getsum(String start, String end) {
		String sql="select sum(TOTAL) as t from ORD where PAYDATE between \""+start+"\" and \""+end+"\";";
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
	public ArrayList<String> OrderNum(String start, String end, int vid) {
		String sql="select STATE,count(*) as num from ORD where C_DATE between \""+start+"\" and \""+end+"\" and VID="+vid+" group by STATE;";
		System.out.println("GET ORDER SCALE FROM DB SQL IS:"+sql);
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
				System.out.println("NOT AN EMPTY SET");
				String s=result.getString("STATE")+"="+result.getInt("num");
				System.out.println(s);
				res.add(s);
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
	public ArrayList<String> topVenueTenAct(String start, String end, int vid) {
		String sql="select AID,ANAME,sum(TOTAL) as t from ORD where VID="+vid+" and PAYDATE between \""+start+"\" and \""+end+"\" group by AID,ANAME order by t desc limit 0,10;";
		System.out.println("GET TOP TEN ACT OF VENUE FROM DB SQL IS:"+sql);
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
				System.out.println("NOT AN EMPTY SET");
				String s="";
				s=s+result.getString("ANAME")+"="+result.getDouble("t");
				res.add(s);
				System.out.println(s);
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
	public int getUserSum() {
		String sql="select count(*) as t from LOGIN where STATUS=\"u\" and STATE=\""+ACCOUNT_STATE.CAN_USE.getName()+"\";";
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
				res=result.getInt("t");
				
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
