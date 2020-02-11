package service;

import java.util.ArrayList;

import dao.OrderDao;
import dao.PayDao;
import factory.DaoFactory;
import factory.ServiceFactory;
import model.Activity;
import model.Hall;
import model.Order;
import util.ORDER_STATE;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	private PayDao payDao;
	private DaoFactory factory;
	private static OrderServiceImpl orderservice;
	private ActService actService;
	private UserService userService;
	private ServiceFactory fact;
	
	private OrderServiceImpl() {
		factory=DaoFactory.getInstance();
		orderDao=factory.getOrderDao();
		fact=ServiceFactory.getInstance();
		payDao=factory.getPayDao();
		actService=fact.getActService();
		userService=fact.getUserService();
	}
	
	public static OrderServiceImpl getInstance() {
		if(orderservice==null) {
			orderservice=new OrderServiceImpl();
		}
		return orderservice;
	}

	/*
	 * checked
	 * @see service.OrderService#getOrder(int, java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<Order> getOrder(int offset, String userid, String type) {
		// TODO Auto-generated method stub
		return orderDao.getOrder(offset, userid, type);
	}

	/**
	 * cheked
	 */
	@Override
	public String checkIn(int id,int aid) {
		// TODO Auto-generated method stub
		Order order=orderDao.getOrderByID(id);
		if(order==null||order.getId()==0) {
			return "FAIL";
		}
		if(order.getActid()!=aid) {
			return "FAIL";
		}
		Activity act=actService.getByID(order.getActid());
		Hall hall=new Hall();
		for(int i=0;i<act.getHalls().size();i++) {
			System.out.println("IN CHECK_IN ORDER HALL IS"+order.getHallname()+" HALL IS"+act.getHalls().get(i).getName());
			if(act.getHalls().get(i).getName().equals(order.getHallname())) {
				hall=act.getHalls().get(i);
				System.out.println("INT CHECK_IN GET THE HALL WE WANT!");
				break;
			}
		}
		String rr=hall.check_in(order);
		if(rr.equals("FAIL")) {
			return rr;
		}
		String res=actService.changeSeat(order.getActid(), order.getActTime(), hall);
		if(res.startsWith("FAIL")) {
			return res;
		}
		return orderDao.modState(id, ORDER_STATE.USED.getName(),ORDER_STATE.USED.getIndex());
	}

	/*
	 * checked
	 * @see service.OrderService#cancleOrder(int)
	 */
	@Override
	public String cancleOrder(int id) {
		// TODO Auto-generated method stub
		Order order=orderDao.getOrderByID(id);
		Activity act=actService.getByID(order.getActid());
		Hall hall=new Hall();
		for(int i=0;i<act.getHalls().size();i++) {
			System.out.println("ORDER:"+order.getActTime()+" HTIME:"+act.getHalls().get(i).getTimetosee());
			if(act.getHalls().get(i).getTimetosee().equals(order.getActTime())) {
				hall=act.getHalls().get(i);
				System.out.println("DURING CANCLE ORDER GET A HALL");
				break;
			}
		}
		hall.cancle_order(order);
		String res=actService.changeSeat(order.getActid(), order.getActTime(), hall);
		if(res.startsWith("FAIL")) {
			return res;
		}
		double back=order.getSum()*0.7;
		String account=order.getPay_id();
		if(order.getState().equals(ORDER_STATE.UNUSE.getName())) {
			res=payDao.transfer("10001","123456",order.getPay_id(),order.getSum()*0.7);
			if(res.startsWith("FAIL")) {
				return res;
			}
			res=userService.addConsumption(order.getUserid(),-order.getSum());
			if(res.startsWith("FAIL")) {
				return res;
			}
		}
		res=orderDao.modState(id, ORDER_STATE.CANCLE.getName(),ORDER_STATE.CANCLE.getIndex());
		if(res.startsWith("FAIL")) {
			return res;
		}
		return "已为您取消订单，退还"+back+"元到您的账户:"+account;
	}

	/*
	 * checked
	 * @see service.OrderService#user_pay(java.lang.String, java.lang.String, double, int, int, int)
	 */
	@Override
	public String userPay(String user_pay_id,String user_pwd,double sum,int oid,int min,int sec) {
		Order order=orderDao.getOrderByID(oid);
		String res=payDao.transfer(user_pay_id,user_pwd,"10001",order.getSum());
		if(res.startsWith("FAIL")) {
			return res;
		}
		res=userService.addConsumption(order.getUserid(),order.getSum());
		if(res.startsWith("FAIL")) {
			return res;
		}
		return orderDao.modPayDate(oid,user_pay_id);
	}

	@Override
	public Order getByID(int id) {
		// TODO Auto-generated method stub
		return orderDao.getOrderByID(id);
	}

	/*checked*/
	@Override
	public void checkLate() {
		orderDao.checkLate();
	}

	

}
