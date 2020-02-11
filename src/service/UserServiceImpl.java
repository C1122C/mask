package service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.UUID;

import dao.LogInfoDao;
import dao.UserInfoDao;
import factory.DaoFactory;
import model.User;
import model.UserStatistic;

public class UserServiceImpl implements UserService {

	private UserInfoDao userDao;
	private LogInfoDao logDao;
	private DaoFactory factory;
	private static UserServiceImpl userService;
	
	private UserServiceImpl() {
		factory=DaoFactory.getInstance();
		userDao=factory.getUserInfoDao();
		logDao=factory.getLogInfoDao();
	}
	
	public static UserServiceImpl getInstance() {
		if(userService==null) {
			userService=new UserServiceImpl();
		}
		return userService;
	}
	
	/*
	 * checked
	 * @see service.UserService#logIn(java.lang.String, java.lang.String)
	 */
	@Override
	public String logIn(String userid, String password) {
		String s=logDao.getPassword(userid);
		String p=this.encode(password);
		if(!s.equals(p)) {
			return "FAIL";
		}
		return logDao.getStatus(userid);
	}

	/*
	 * checked
	 * @see service.UserService#encode(java.lang.String)
	 */
	@Override
	public String encode(String orginal) {
		BigInteger bigInteger=null;
		String KEY_MD5 = "MD5";
        try {
        	MessageDigest md = MessageDigest.getInstance(KEY_MD5);   
        	byte[] inputData = orginal.getBytes(); 
        	md.update(inputData);   
        	bigInteger = new BigInteger(md.digest());   
        } catch (Exception e) {e.printStackTrace();}
          
        return bigInteger.toString(16);
	}

	/*
	 * checked
	 * @see service.UserService#stop(java.lang.String)
	 */
	@Override
	public String stop(String userid) {
		// TODO Auto-generated method stub
		return logDao.stopUser(userid);
	}

	/*
	 * checked
	 * @see service.UserService#getUserInfo(java.lang.String)
	 */
	@Override
	public User getUserInfo(String userid) {
		// TODO Auto-generated method stub
		return userDao.getUserInfo(userid);
	}

	/*
	 * checked
	 * @see service.UserService#modUser(model.User)
	 */
	@Override
	public String modUser(User user) {
		// TODO Auto-generated method stub
		if(!(user.getPwd()==null)) {
			if(!user.getPwd().equals("")) {
				String res=logDao.changePassword(user.getId(),this.encode(user.getPwd()));
				if(res.startsWith("FAIL")) {
					return res;
				}
			}
			
		}
		return userDao.modUser(user);
	}

	/*
	 * checked
	 * @see service.UserService#addConsumption(java.lang.String, double)
	 */
	@Override
	public String addConsumption(String id,double value) {
		// TODO Auto-generated method stub
		User user=this.getUserInfo(id);
		double con=user.getConsumption()+value;
		user.setConsumption(con);
		int bon=user.getBonus()+(int)value;
		user.setBonus(bon);
		user.setRankFromCon(con);
		return this.modUser(user);
	}

	/*
	 * checked
	 * @see service.UserService#getStatistic(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public UserStatistic getStatistic(String id,String start, String end) {
		// TODO Auto-generated method stub
		return userDao.getStatistic(id, start, end);
	}

	/*
	 * checked
	 * @see service.UserService#mail_check(java.lang.String)
	 */
	@Override
	public String mailCheck(String mail) {
		// TODO Auto-generated method stub
		ArrayList<String> mails=logDao.getAllMail();
		if(mails.indexOf(mail)>=0) {
			return "FAIL";
		}
		if(!mail.matches("^\\w+@(\\w+\\.)+\\w+$")){
	        return "FAIL";
	    }
	    //生成激活码
	    String code=UUID.randomUUID().toString().replaceAll("-", "");
	    new Thread(new MailUtil(mail,code)).start();
	    
	    return logDao.saveMail(mail, code);
	}

	/*
	 * checked
	 * @see service.UserService#check_vcode(java.lang.String)
	 */
	@Override
	public String checkVcode(String vcode) {
		// TODO Auto-generated method stub
		return logDao.checkVcode(vcode);
	}

	/*
	 * checked
	 * @see service.UserService#changeBonus(java.lang.String, int)
	 */
	@Override
	public String changeBonus(String userid, int val) {
		User user=this.getUserInfo(userid);
		int bon=user.getBonus()+val;
		user.setBonus(bon);
		return this.modUser(user);
	}


}
