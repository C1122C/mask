package factory;

import service.ActService;
import service.ActServiceImpl;
import service.CouponService;
import service.CouponServiceImpl;
import service.OrderService;
import service.OrderServiceImpl;
import service.PlatStatisticService;
import service.PlatStatisticServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import service.VenueService;
import service.VenueServiceImpl;

public class ServiceFactory {
	private static ServiceFactory servicefactory=new ServiceFactory();
	
	private ServiceFactory() {}
	
	public static ServiceFactory getInstance() {
		return servicefactory;
	}
	public ActService getActService() {
		return ActServiceImpl.getInstance();
	}
	public CouponService getCouponService() {
		return CouponServiceImpl.getInstance();
	}
	public PlatStatisticService getPlatStatisticService() {
		return PlatStatisticServiceImpl.getInstance();
	}
	public UserService getUserService() {
		return UserServiceImpl.getInstance();
	}
	public OrderService getOrderService() {
		return OrderServiceImpl.getInstance();
	}
	public VenueService getVenueService() {
		return VenueServiceImpl.getInstance();
	}
}
