package service;

import java.util.ArrayList;
import model.Order;

public interface OrderService {
	/*
	 * 获取用户订单 type可以是“”
	 */
	public ArrayList<Order> getOrder(int offset,String userid,String type);
	/*
	 * 获取详细订单信息
	 */
	public Order getByID(int id);
	/*
	 * 未支付直接取消，已支付扣除费用,失败返回FAIL，成功返回可以直接传达给用户的扣除信息
	 */
	public String cancleOrder(int id);
	
	/*
	 * 参数是oid
	 */
	public String checkIn(int id,int aid);
	
	/*
	 * 返回SUCCESS或可以直接传达给用户的话
	 */
	public String userPay(String user_pay_id,String user_pwd,double sum,int oid,int min,int sec);
	/*
	 * 支付、取消、检票
	 */
	/*
	 * 检查当前数据库中是否有时间超时的，自动取消
	 */
	public void checkLate();
}
