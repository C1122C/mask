package business;

import java.util.List;

import model.Coupon;

public class CouponListBean {
	private List<Coupon> alreadyHave;
	private List<Coupon> canChange;
	public List<Coupon> getAlreadyHave() {
		return alreadyHave;
	}
	public void setAlreadyHave(List<Coupon> alreadyHave) {
		this.alreadyHave = alreadyHave;
	}
	public List<Coupon> getCanChange() {
		return canChange;
	}
	public void setCanChange(List<Coupon> canChange) {
		this.canChange = canChange;
	}

	
	
	
}
