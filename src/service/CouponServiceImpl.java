package service;

import java.util.ArrayList;

import dao.CouponDao;
import factory.DaoFactory;
import factory.ServiceFactory;
import model.Coupon;

public class CouponServiceImpl implements CouponService {

	private CouponDao couponDao;
	private UserService userService;
	private DaoFactory factory;
	private ServiceFactory fact;
	private static CouponServiceImpl couponservice;
	
	private CouponServiceImpl() {
		factory=DaoFactory.getInstance();
		fact=ServiceFactory.getInstance();
		couponDao=factory.getCouponDao();
		userService=fact.getUserService();
	}
	
	public static CouponServiceImpl getInstance() {
		if(couponservice==null) {
			couponservice=new CouponServiceImpl();
		}
		return couponservice;
	}
	/*
	 * checked
	 * @see service.CouponService#getAllCoupon(java.lang.String, int)
	 */
	@Override
	public ArrayList<Coupon> getAllCoupon(String userid, int offset) {
		// TODO Auto-generated method stub
		return couponDao.getAllCoupon(userid, offset);
	}

	/*
	 * checked
	 * @see service.CouponService#getUseableCoupon(java.lang.String, double)
	 */
	@Override
	public ArrayList<Coupon> getUseableCoupon(String userid, double sum) {
		// TODO Auto-generated method stub
		return couponDao.getUseableCoupon(userid, sum);
	}

	/*
	 * checked
	 * @see service.CouponService#exchange(java.lang.String, int)
	 */
	@Override
	public String exchange(String userid,int coupon_tid) {
		Coupon coupon=couponDao.getByTypeId(coupon_tid);
		String res=couponDao.addCoupon(userid, coupon);
		if(res.startsWith("FAIL")) {
			return res;
		}
		return userService.changeBonus(userid,-coupon.getChange());
	}

	/*
	 * checked
	 * @see service.CouponService#use(int)
	 */
	@Override
	public String use(int id) {
		return couponDao.deleteCoupon(id);
	}

	/*
	 * checked
	 * @see service.CouponService#getCanChange(int, int)
	 */
	@Override
	public ArrayList<Coupon> getCanChange(int bon, int offset) {
		// TODO Auto-generated method stub
		return couponDao.getCanChange(bon, offset);
	}

}
