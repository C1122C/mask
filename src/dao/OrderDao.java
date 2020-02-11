package dao;

import java.util.ArrayList;

import model.Order;

public interface OrderDao {
	/*
	 * 根据类型选择订单
	 */
	public ArrayList<Order> getOrder(int offset,String userid,String type);
	/*
	 * 场馆获取订单
	 */
	public ArrayList<Order> getOrderByVen(int offset,int vid);
	/*
	 * 根据ID获取订单
	 */
	public Order getOrderByID(int id);
	/*
	 * 新增加一个订单
	 */
	public String addOrder(Order order);
	/*
	 * 修改订单状态
	 */
	public String modState(int id,String state,int i);
	/*
	 * 修改订单支付时间
	 */
	public String modPayDate(int id,String pay_id);
	
	/*
	 * 获取一个新的ID
	 */
	public int getAOid();
	/*
	 * 检查当前数据库中是否有时间超时的，自动取消,返回oid
	 */
	public void checkLate();
	
}
