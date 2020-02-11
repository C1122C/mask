package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ACCOUNT_STATE;


public class LogInfoDaoImpl implements LogInfoDao {

	private static LogInfoDaoImpl logInfoDao;
	private SQLHelper helper;
	
	private LogInfoDaoImpl() {
		helper=SQLHelper.getInstance();
	}
	
	public static LogInfoDaoImpl getInstance()
	{
		if(logInfoDao==null) {
			logInfoDao=new LogInfoDaoImpl();
		}
		return logInfoDao;
	}
	
	/*
	 * checked
	 * @see dao.LogInfoDao#getPassword(java.lang.String)
	 */
	@Override
	public String getPassword(String userid) {
		String sql="select * from LOGIN where ID=\""+userid+"\" and STATE=\""+ACCOUNT_STATE.CAN_USE.getName()+"\";";
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
		String res="";
		try {
			while (result.next()) {
				res=result.getString("PASSWORD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		if(res==null||res.equals("")) {
			return "FAIL";
		}
		return res;
	}

	/*
	 * checked
	 * @see dao.LogInfoDao#stopUser(java.lang.String)
	 */
	@Override
	public String stopUser(String userid) {
		String sql="update LOGIN set STATE=\""+ACCOUNT_STATE.STOP.getName()+"\",STOPTIME=CURDATE() where ID=\""+userid+"\";";
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
	 * @see dao.LogInfoDao#changePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public String changePassword(String userid, String password) {
		String sql="update LOGIN set PASSWORD=\""+password+"\" where ID=\""+userid+"\";";
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
	 * @see dao.LogInfoDao#getStatus(java.lang.String)
	 */
	@Override
	public String getStatus(String userid) {
		String sql="select * from LOGIN where ID=\""+userid+"\" and STATE=\""+ACCOUNT_STATE.CAN_USE.getName()+"\";";
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
		String res="";
		try {
			while (result.next()) {
				res=result.getString("STATUS");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		if(res.equals("")) {
			return "FAIL";
		}
		return res;
	}

	/*
	 * checked
	 * @see dao.LogInfoDao#getAllMail()
	 */
	@Override
	public ArrayList<String> getAllMail() {
		String sql="select * from LOGIN;";
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
				res.add(result.getString("ID"));
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
	 * @see dao.LogInfoDao#save_mail(java.lang.String, java.lang.String)
	 */
	@Override
	public String saveMail(String mail, String code) {
		String sql="insert into LOGIN (ID,STATUS,STATE,CODE) values(\""+mail+"\",\"u\",\""+ACCOUNT_STATE.WAIT_CHECK.getName()+"\",\""+code+"\");";
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
	 * @see dao.LogInfoDao#check_vcode(java.lang.String)
	 */
	@Override
	public String checkVcode(String vcode) {
		String sql="select * from LOGIN where CODE=\""+vcode+"\" and STATE=\""+ACCOUNT_STATE.WAIT_CHECK.getName()+"\";";
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
		String res="";
		try {
			while (result.next()) {
				res=result.getString("ID");
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res.equals("")) {
			return "FAIL";
		}
		sql="update LOGIN set REGTIME=now(),STATE=\""+ACCOUNT_STATE.CAN_USE.getName()+"\" where CODE=\""+vcode+"\";";
		int r=0;
		try {
			stmt = connection.prepareStatement(sql);
			r = stmt.executeUpdate();
			stmt.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r==0) {
			return "FAIL";
		}
		
		sql="insert into USER (ID,CONSUM,RANK,BONUS,CUT,CUTTOSHOW) VALUES (\""+res+"\",0,\"普通会员\",0,1,\"无折扣\");";
		try {
			stmt = connection.prepareStatement(sql);
			r = stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helper.close(connection, stmt, result);
		if(r==0) {
			return "FAIL";
		}
		return res;
	}

}
