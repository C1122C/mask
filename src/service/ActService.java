package service;

import java.util.ArrayList;

import business.UserIndexBean;
import model.Activity;
import model.Hall;

public interface ActService {
	/*
	 * 发布活动
	 */
	public String publish(int aid,String name,String hall,String type,String s_year,String s_month,String s_day,String e_year,String e_month,String e_day,String sale_type,String des,String path,String prices,int vid,String time);
	/*
	 * 购买  返回订单号
	 */
	public int buyTicket(int aid,boolean isSelect,String info,String username);
	/*
	 * user(0表示非会员）:aid:time:realc-realr-seatc-seatr-price:返回应付钱数
	 */
	public double sell(String par);
	/*
	 * 得到一个新的活动编号
	 */
	public int getNewAid();
	/*
	 * 配票 返回订单号
	 */
	public int vote(int aid,String showtime,double ticket,int num,double sum,String username);
	/*
	 * 得到已结束需要结算的活动
	 */
	public ArrayList<Activity> getPassed(int offset);
	/*
	 * 结算活动
	 */
	public String settle(int aid);
	/*
	 * 得到活动详细信息
	 */
	public Activity getByID(int id);
	/*
	 * 获取所有城市列表
	 */
	public ArrayList<String> getAllCity();
	
	/*
	 * 获取用户登录界面信息
	 */
	public UserIndexBean getIndex();
	/*
	 * 获取演出类型
	 */
	public ArrayList<String> getActType();
	/*
	 * city 是城市名或全国，sort是演出类型或全部演出
	 */
	public ArrayList<Activity> selectByCond(String city,String type,int offset);
	/*
	 * 根据用户输入的场馆名或者活动名搜索
	 */
	public ArrayList<Activity> selectByNameOrVen(String input,int offset);
	/*
	 * 根据场馆号查看
	 */
	public ArrayList<Activity> selectByVid(int vid,int offset);
	/*
	 * input可以是""
	 */
	public ArrayList<Activity> selectMore(String city,String type,int offset,String input);
	/*
	 * 订单取消时可以调用
	 */
	public String changeSeat(int aid,String showtime,Hall hall);
}
