package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PayDaoImpl implements PayDao {

	private static PayDaoImpl payDao;
	private SQLHelper helper;
	
	private PayDaoImpl(){
		helper=SQLHelper.getInstance();
	}
		
	public static PayDaoImpl getInstance()
	{
		if(payDao==null) {
			payDao=new PayDaoImpl();
		}
		return payDao;
	}
	/*
	 * checked
	 * @see dao.PayDao#transfer(java.lang.String, java.lang.String, java.lang.String, double)
	 */
	@Override
	public String transfer(String from,String from_pwd,String to,double sum) {
		String sql="select * from PAY where ID=\""+from+"\";";
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
		double fm=0;
		double tm=0;
		boolean have=false;
		boolean wrong=false;
		try {
			while (result.next()) {
				have=true;
				fm=result.getDouble("BALANCE");
				if(!from_pwd.equals(result.getString("PWD"))){
					wrong=true;
				}
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!have) {
			return "FAIL:NOFROM";
		}
		if(wrong) {
			return "FAIL:WRONG PASSWORD";
		}
		if(fm<sum) {
			return "FAIL:NOTENOUGH";
		}
		have=false;
		sql="select * from PAY where ID=\""+to+"\";";
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (result.next()) {
				have=true;
				tm=result.getDouble("BALANCE");
			}
			result.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!have) {
			return "FAIL:NOTO";
		}
		sql="update PAY set BALANCE="+(fm-sum)+" where ID=\""+from+"\";";
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
		sql="update PAY set BALANCE="+(tm+sum)+" where ID=\""+to+"\";";
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
		return "SUCCESS";
	}

}
