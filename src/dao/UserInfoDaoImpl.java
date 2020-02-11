package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Address;
import model.User;
import model.UserStatistic;

public class UserInfoDaoImpl implements UserInfoDao {

	private static UserInfoDaoImpl userInfoDao;
	private SQLHelper helper;
	
	private UserInfoDaoImpl(){
		helper=SQLHelper.getInstance();
	}
		
	public static UserInfoDaoImpl getInstance()
	{
		if(userInfoDao==null) {
			userInfoDao=new UserInfoDaoImpl();
		}
		return userInfoDao;
	}
	
	/*
	 * checked
	 * @see dao.UserInfoDao#getUserInfo(java.lang.String)
	 */
	@Override
	public User getUserInfo(String userid) {
		String sql="select * from USER where ID=\""+userid+"\";";
		System.out.println("SQL IS"+sql);
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
		User user=new User();
		try {
			while (result.next()) {
				System.out.println("NOT AN EMPTY SET");
				user.setId(result.getString("ID"));
				user.setName(result.getString("NAME"));
				user.setGender(result.getString("GENDER"));
				Address a=new Address();
				if(!(result.getString("ADDR")==null)) {
					a.reverse(result.getString("ADDR"));
				}
				
				user.setAddress(a);
				user.setPayID(result.getString("PAY_ID"));
				user.setB_year(result.getInt("B_YEAR"));
				user.setB_month(result.getInt("B_MONTH"));
				user.setB_day(result.getInt("B_DAY"));
				System.out.println("INTEREST FROM DB IS"+result.getString("INTEREST"));
				if(!(result.getString("INTEREST")==null)) {
					user.interest(result.getString("INTEREST"));
				}
				
				//System.out.println("INTEREST WE GOT IS"+user.getInterest().size());
				user.setConsumption(result.getDouble("CONSUM"));
				user.setRank(result.getString("RANK"));
				user.setBonus(result.getInt("BONUS"));
				user.setCut(result.getDouble("CUT"));
				user.setCutToShow(result.getString("CUTTOSHOW"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		return user;
	}

	/*
	 * checked
	 * @see dao.UserInfoDao#modUser(model.User)
	 */
	@Override
	public String modUser(User user) {
		String sql="update USER set NAME=\""+user.getName()+"\",GENDER=\""+user.getGender()+"\",ADDR=\""+user.getAddress().toString()+"\",PAY_ID=\""
				+user.getPayID()+"\",B_YEAR="+user.getB_year()+",B_MONTH="+user.getB_month()+",B_DAY="+user.getB_day()
				+",INTEREST=\""+user.getinterest()+"\",CONSUM="+user.getConsumption()+",RANK=\""
				+user.getRank()+"\",BONUS="+user.getBonus()+",CUT="+user.getCut()+",CUTTOSHOW=\""+user.getCutToShow()+"\" where ID=\""+user.getId()+"\";"; 
		Connection connection=helper.getConnection();
		PreparedStatement stmt=null;
		int r=0;
		try {
			stmt = connection.prepareStatement(sql);
			r = stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close1(connection, stmt);
		if(r==0) {
			return "FAIL";
		}
		return "SUCCESS";
	}

	/**
	 * checked
	 */
	@Override
	public ArrayList<String> getUserProportion() {
		String sql="select RANK,count(*) as t from USER group by RANK;";
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
				res.add(result.getString("RANK")+"="+result.getInt("t"));
				
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
	public int getAdd(String start, String end) {
		String sql="select count(*) as t from LOGIN where REGTIME between \""+start+"\" and \""+end+"\";";
		System.out.println("GET USER ADD FROM DB SQL IS "+sql);
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
		helper.close(connection, stmt, result);
		return res;
	}

	/**
	 * checked
	 */
	@Override
	public int getLose(String start, String end) {
		String sql="select count(*) as t from LOGIN where STOPTIME between \""+start+"\" and \""+end+"\";";
		System.out.println("GET USER LOSE FROM DB SQL IS "+sql);
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
		helper.close(connection, stmt, result);
		return res;
	}

	/*
	 * checked
	 * @see dao.UserInfoDao#getStatistic(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public UserStatistic getStatistic(String id, String start, String end) {
		UserStatistic res=new UserStatistic();
		String sql="select sum(TOTAL) as t from ORD where UID=\""+id+"\" and PAYDATE between \""+start+"\" and \""+end+"\";";
		System.out.println("GET USER STATISTIC FROM DB SQL IS:"+sql);
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
		try {
			while (result.next()) {
				res.setSum(result.getDouble("t"));
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="select STATE,count(*) as total from ORD where UID=\""+id+"\" and C_DATE between \""+start+"\" and \""+end+"\" group by STATE;";
		System.out.println("GET USER STATISTIC FROM DB SQL IS:"+sql);
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> kind=new ArrayList<String>();
		ArrayList<Integer> o_num=new ArrayList<Integer>();
		try {
			while (result.next()) {
				kind.add(result.getString("STATE"));
				o_num.add(result.getInt("total"));
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setOrderKind(kind);
		res.setOrderNum(o_num);
		sql="select ATYPE,count(*) as num,sum(TOTAL) as t from ORD where UID=\""+id+"\" and C_DATE between \""+start+"\" and \""+end+"\" group by ATYPE;";
		System.out.println("GET USER STATISTIC FROM DB SQL IS:"+sql);
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> atype=new ArrayList<String>();
		ArrayList<Integer> anum=new ArrayList<Integer>();
		ArrayList<Double> atotal=new ArrayList<Double>();
		try {
			while (result.next()) {
				atype.add(result.getString("ATYPE"));
				anum.add(result.getInt("num"));
				atotal.add(result.getDouble("t"));
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setActKind(atype);
		res.setTimes(anum);
		res.setMoney(atotal);
		sql="select PAYDATE,sum(TOTAL) as t from ORD where UID=\""+id+"\" and PAYDATE between \""+start+"\" and \""+end+"\" group by PAYDATE;";
		System.out.println("GET USER STATISTIC FROM DB SQL IS:"+sql);
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> date=new ArrayList<String>();
		ArrayList<Double> con=new ArrayList<Double>();
		try {
			while (result.next()) {
				date.add(result.getString("PAYDATE"));
				con.add(result.getDouble("t"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setDate(date);
		res.setConsum(con);
		helper.close(connection, stmt, result);
		return res;
	}

}
