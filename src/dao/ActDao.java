package dao;

import java.util.ArrayList;

import model.Activity;
import model.Hall;

public interface ActDao {
	/*
	 * 改变某一场演出的票务信息
	 */
	public String changeSeat(int aid,String showtime,Hall hall);
	/*
	 * 获取新的AID
	 */
	public int getNewAid();
	/*
	 * 获取数据库中所有的city
	 */
	public ArrayList<String> getAllCity();
	/*
	 * 获取广告活动
	 */
	public ArrayList<Activity> getAd();
	/*
	 * 通过城市、演出类型查找
	 */
	public ArrayList<Activity> selectByCond(String city, String type, int offset);
	/*
	 * 通过用户输入查找
	 */
	public ArrayList<Activity> selectByNameOrVen(String input, int offset);
	/*
	 * 查找更多
	 */
	public ArrayList<Activity> selectMore(String city, String type, int offset, String input);
	
	/*
	 * 获取已经结束还未结算的活动
	 */
	public ArrayList<Activity> getPassed(int offset);
	/*
	 * 发布活动
	 */
	public String addAct(Activity act);
	/*
	 * 通过ID得到活动
	 */
	public Activity getByID(int id);
	/*
	 * 得到活动应结算的金额
	 */
	public double getSum(int aid);
	/*
	 * 把活动状态置为已结算
	 */
	public String modSettle(int aid);
	/*
	 * 查看场馆活动
	 */
	public ArrayList<Activity> selectByVid(int vid,int offset);
}
