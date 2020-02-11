package factory;

import dao.AccountDao;
import dao.AccountDaoImpl;
import dao.ActDao;
import dao.ActDaoImpl;
import dao.CouponDao;
import dao.CouponDaoImpl;
import dao.LogInfoDao;
import dao.LogInfoDaoImpl;
import dao.OrderDao;
import dao.OrderDaoImpl;
import dao.PayDao;
import dao.PayDaoImpl;
import dao.UserInfoDao;
import dao.UserInfoDaoImpl;
import dao.VenueDao;
import dao.VenueDaoImpl;

public class DaoFactory {
	private static DaoFactory daofactory;
	
	private DaoFactory() {}
	
	public static DaoFactory getInstance() {
		if(daofactory==null) {
			daofactory=new DaoFactory();
		}
		return daofactory;
	}
	
	public AccountDao getAccountDao() {
		return AccountDaoImpl.getInstance();
	}
	
	public ActDao getActDao() {
		return ActDaoImpl.getInstance();
	}
	
	public CouponDao getCouponDao() {
		return CouponDaoImpl.getInstance();
	}
	
	public LogInfoDao getLogInfoDao() {
		return LogInfoDaoImpl.getInstance();
	}
	
	public OrderDao getOrderDao() {
		return OrderDaoImpl.getInstance();
	}
	
	public PayDao getPayDao() {
		return PayDaoImpl.getInstance();
	}
	
	public UserInfoDao getUserInfoDao() {
		return UserInfoDaoImpl.getInstance();
	}
	
	public VenueDao getVenueDao() {
		return VenueDaoImpl.getInstance();
	}
	
}
