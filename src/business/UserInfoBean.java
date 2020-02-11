package business;

import java.util.ArrayList;

import model.User;

public class UserInfoBean {
	private User user;
	private ArrayList<String> interest;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<String> getInterest() {
		return interest;
	}

	public void setInterest(ArrayList<String> interest) {
		this.interest = interest;
	}
	
}
