package dao;

import java.util.ArrayList;

public interface AccountDao {
	/*
	 * 得到一段时间内平台销量前十的场馆vname=total
	 */
	public ArrayList<String> topTenVen(String start,String end);
	/*
	 * 得到一段时间内平台销量前十的活动aname=total
	 */
	public ArrayList<String> topTenAct(String start,String end);
	/*
	 * 得到每一种订单的数目name=num
	 */
	public ArrayList<String> OrderNum(String start,String end,int vid);
	/*
	 * 得到某一场馆一段时间内的销量前十活动aname=total
	 */
	public ArrayList<String> topVenueTenAct(String start,String end,int vid);
	/*
	 * 得到场馆某一段时间内的平台销售曲线date=money
	 */
	public ArrayList<String> getVen(String start,String end,int vid);
	/*
	 * 得到某个活动自开始销售以来的平台销售曲线date=money
	 */
	public ArrayList<String> getAct(int aid);
	
	/*
	 * 得到平台某一段时间内的销售曲线date=money
	 */
	public ArrayList<String> getPlatCurve(String start,String end);
	/*
	 * 得到平台某一段时间内的销售总额
	 */
	public double getsum(String start,String end);
	/*
	 * 得到平台某一段时间内的注册用户总数
	 */
	public int getUserSum();
	
	
}
