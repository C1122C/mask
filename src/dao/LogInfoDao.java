package dao;

import java.util.ArrayList;

public interface LogInfoDao {
	/*
	 * 获取用户密码
	 */
	public String getPassword(String userid);
	/*
	 * 获取用户身份
	 */
	public String getStatus(String userid);
	/*
	 * 停用账号
	 */
	public String stopUser(String userid);
	/*
	 * 修改密码
	 */
	public String changePassword(String userid,String password);
	/*
	 * 获取目前注册的所有邮箱
	 */
	public ArrayList<String> getAllMail();
	/*
	 * 存储邮箱和验证码
	 */
	public String saveMail(String mail,String code);
	
	/*
	 * 成功返回用户名，否则返回FAIL,如果成功直接完成注册
	 */
	public String checkVcode(String vcode);
}
