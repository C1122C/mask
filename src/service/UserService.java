package service;

import model.UserStatistic;
import model.User;

public interface UserService {
	/*
	 * 用户登录，返回值：FAIL u v m
	 */
	public String logIn(String userid,String password);
	/*
	 * 邮箱验证，FAIL表示已注册过的邮箱 验证码存在数据库，否则发送验证码到邮箱
	 */
	public String mailCheck(String mail);
	
	/*
	 * 成功返回用户名，否则返回FAIL
	 */
	public String checkVcode(String vcode);
	/*
	 * 加密密码
	 */
	public String encode(String orginal);
	/*
	 * 用户终止
	 */
	public String stop(String userid);
	/*
	 * 获取用户个人信息
	 */
	public User getUserInfo(String userid);
	/*
	 * 
	 */
	public String changeBonus(String userid,int val);
	/*
	 * 修改用户个人信息
	 */
	public String modUser(User user);
	/*
	 * 改变消费累计，value可以为负，改变的同时改变积分，或许改变等级
	 */
	public String addConsumption(String id,double value);
	
	/*
	 * 获取用户统计信息
	 */
	public UserStatistic getStatistic(String id,String start,String end);
	
}
