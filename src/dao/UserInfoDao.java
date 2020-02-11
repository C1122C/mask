package dao;


import java.util.ArrayList;

import model.User;
import model.UserStatistic;

public interface UserInfoDao {
	/*
	 * 获取用户基本信息
	 */
	public User getUserInfo(String userid);
	/*
	 * 修改用户信息
	 */
	public String modUser(User user);
	/*
	 * 获取用户比例
	 */
	public ArrayList<String> getUserProportion();
	/*
	 * 获取某段时间内增加的用户数
	 */
	public int getAdd(String start,String end);
	/*
	 * 获取某段时间内流失的用户数
	 */
	public int getLose(String start,String end);
	/*
	 * 获取用户统计信息
	 */
	public UserStatistic getStatistic(String id,String start,String end);
	
	
}
