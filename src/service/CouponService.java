package service;

import java.util.ArrayList;

import model.Coupon;

public interface CouponService {
	/*
	 * 获取用户拥有的所有优惠券
	 */
	public ArrayList<Coupon> getAllCoupon(String userid,int offset);
	/*
	 * 获取用户可用的优惠券
	 */
	public ArrayList<Coupon> getUseableCoupon(String userid,double sum);
	/*
	 * 获取用户能够兑换的优惠券类型
	 */
	public ArrayList<Coupon> getCanChange(int bon,int offset);
	/*
	 * 兑换某种类型的优惠券
	 */
	public String exchange(String userid,int coupon_tid);
	/*
	 * 使用优惠券
	 */
	public String use(int id);
}
