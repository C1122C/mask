package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Coupon;

public class CouponDaoImpl implements CouponDao {

	private static CouponDaoImpl couponDao;
	private SQLHelper helper;
	
	private CouponDaoImpl() {
		helper=SQLHelper.getInstance();
	}
	
	public static CouponDaoImpl getInstance()
	{
		if(couponDao==null) {
			couponDao=new CouponDaoImpl();
		}
		return couponDao;
	}
	
	/*
	 * checked
	 * @see dao.CouponDao#getAllCoupon(java.lang.String, int)
	 */
	@Override
	public ArrayList<Coupon> getAllCoupon(String userid, int offset) {
		String sql="select * from COUPON where DATEDIFF(VALI,CURDATE())>0 and ISUSED=0 and UID=\""+userid+"\" order by ID desc limit "+offset+",6;";
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
		ArrayList<Coupon> res=new ArrayList<Coupon>();
		try {
			while (result.next()) {
				Coupon c=new Coupon();
				c.setId(result.getInt("ID"));
				c.setType(result.getString("TYPE"));
				c.setType_id(result.getInt("TYPE_ID"));
				c.setCondition(result.getDouble("COND"));
				c.setValue(result.getDouble("VALU"));
				c.setValid(result.getDate("VALI"));
				c.setChange(result.getInt("CHA"));
				c.setName(result.getString("NAME"));
				c.setDes(result.getString("DES"));
				res.add(c);
				
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
	 * @see dao.CouponDao#getUseableCoupon(java.lang.String, double)
	 */
	@Override
	public ArrayList<Coupon> getUseableCoupon(String userid, double sum) {
		String sql="select * from COUPON where DATEDIFF(VALI,CURDATE())>0 and ISUSED=0 and UID=\""+userid+"\" and COND<="+sum+" order by ID desc;";
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
		ArrayList<Coupon> res=new ArrayList<Coupon>();
		try {
			while (result.next()) {
				Coupon c=new Coupon();
				c.setId(result.getInt("ID"));
				c.setType(result.getString("TYPE"));
				c.setType_id(result.getInt("TYPE_ID"));
				c.setCondition(result.getDouble("COND"));
				c.setValue(result.getDouble("VALU"));
				c.setValid(result.getDate("VALI"));
				c.setChange(result.getInt("CHA"));
				c.setName(result.getString("NAME"));
				System.out.println("GOT A COUPON NAME IS:"+result.getString("NAME"));
				c.setDes(result.getString("DES"));
				res.add(c);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		System.out.println("AT LAST COUPON LIST SIZE IS:"+res.size());
		return res;
	}

	/*
	 * checked
	 * @see dao.CouponDao#addCoupon(java.lang.String, model.Coupon)
	 */
	@Override
	public String addCoupon(String userid, Coupon coupon) {
		String sql="insert into COUPON (TYPE,TYPE_ID,COND,VALU,VALI,CHA,NAME,DES,UID,ISUSED) VALUES (\""+
				coupon.getType()+"\","+coupon.getType_id()+","+coupon.getCondition()
				+","+coupon.getValue()+",\""+coupon.getValid()+"\","+coupon.getChange()
				+",\""+coupon.getName()+"\",\""+coupon.getDes()+"\",\""+userid+"\",0);";
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
	 * @see dao.CouponDao#deleteCoupon(int)
	 */
	@Override
	public String deleteCoupon(int id) {
		String sql="update COUPON set ISUSED=1 where ID="+id+";";
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

	@Override
	public Coupon getByID(int id) {
		String sql="select * from COUPON where ID=+"+id+";";
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
		Coupon c=new Coupon();
		try {
			while (result.next()) {
				
				c.setId(result.getInt("ID"));
				c.setType(result.getString("TYPE"));
				c.setType_id(result.getInt("TYPE_ID"));
				c.setCondition(result.getDouble("COND"));
				c.setValue(result.getDouble("VALU"));
				c.setValid(result.getDate("VALI"));
				c.setChange(result.getInt("CHANGE"));
				c.setName(result.getString("NAME"));
				c.setDes(result.getString("DES"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return c;
	}

	/*checked*/
	@Override
	public Coupon getByTypeId(int id) {
		String sql="select * from COUPON_TYPE where ID=+"+id+";";
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
		Coupon c=new Coupon();
		try {
			while (result.next()) {
				
				c.setType(result.getString("TYPE"));
				c.setType_id(result.getInt("ID"));
				c.setCondition(result.getDouble("COND"));
				c.setValue(result.getDouble("VALU"));
				c.setValid(result.getDate("VALI"));
				c.setChange(result.getInt("CHA"));
				c.setName(result.getString("NAME"));
				c.setDes(result.getString("DES"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		helper.close(connection, stmt, result);
		return c;
	}

	/*
	 * checked
	 * @see dao.CouponDao#getCanChange(int, int)
	 */
	@Override
	public ArrayList<Coupon> getCanChange(int bon, int offset) {
		String sql="select * from COUPON_TYPE where DATEDIFF(VALI,CURDATE())>0 and CHA<="+bon+" order by ID desc limit "+offset+",6;";
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
		ArrayList<Coupon> res=new ArrayList<Coupon>();
		try {
			while (result.next()) {
				Coupon c=new Coupon();
				c.setType(result.getString("TYPE"));
				c.setType_id(result.getInt("ID"));
				c.setCondition(result.getDouble("COND"));
				c.setValue(result.getDouble("VALU"));
				c.setValid(result.getDate("VALI"));
				c.setChange(result.getInt("CHA"));
				c.setName(result.getString("NAME"));
				c.setDes(result.getString("DES"));
				res.add(c);
				
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
