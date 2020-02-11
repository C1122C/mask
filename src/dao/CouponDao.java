package dao;

import java.util.ArrayList;

import model.Coupon;

public interface CouponDao {
	/*
	 * 获取用户拥有的所有优惠券
	 */
	public ArrayList<Coupon> getAllCoupon(String userid,int offset);
	/*
	 * 获取可用的优惠券
	 */
	public ArrayList<Coupon> getUseableCoupon(String userid,double sum);
	/*
	 * 新增一条用户优惠券记录
	 */
	public String addCoupon(String userid,Coupon coupon);
	/*
	 * 删除一条优惠券信息
	 */
	public String deleteCoupon(int id);
	/*
	 * 通过ID获得优惠券
	 */
	public Coupon getByID(int id);
	/*
	 * 通过ID获得一类优惠券
	 */
	public Coupon getByTypeId(int id);
	/*
	 * 获得可以兑换的优惠券
	 */
	public ArrayList<Coupon> getCanChange(int bon,int offset);
}
