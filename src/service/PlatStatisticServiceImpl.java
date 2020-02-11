package service;

import java.util.ArrayList;

import dao.AccountDao;
import dao.UserInfoDao;
import factory.DaoFactory;
import model.PUserStatistic;

public class PlatStatisticServiceImpl implements PlatStatisticService {

	private AccountDao accountDao;
	private UserInfoDao userDao;
	private DaoFactory factory;
	private static PlatStatisticServiceImpl platStatisticService;
	
	private PlatStatisticServiceImpl() {
		factory=DaoFactory.getInstance();
		accountDao=factory.getAccountDao();
		userDao=factory.getUserInfoDao();
	}
	
	public static PlatStatisticServiceImpl getInstance() {
		if(platStatisticService==null) {
			platStatisticService=new PlatStatisticServiceImpl();
		}
		return platStatisticService;
	}
	/**
	 * checked
	 */
	@Override
	public ArrayList<String> topTenVen(String start, String end) {
		// TODO Auto-generated method stub
		return accountDao.topTenVen(start, end);
	}

	/**
	 * checked
	 */
	@Override
	public ArrayList<String> topTenAct(String start, String end) {
		// TODO Auto-generated method stub
		return accountDao.topTenAct(start, end);
	}

	/**
	 * checked
	 */
	@Override
	public ArrayList<String> getPlatCurve(String start, String end) {
		// TODO Auto-generated method stub
		return accountDao.getPlatCurve(start, end);
	}

	/**
	 * checked
	 */
	@Override
	public PUserStatistic getUserInfo(String start, String end) {
		// TODO Auto-generated method stub
		PUserStatistic res=new PUserStatistic();
		res.setSum(accountDao.getUserSum());
		ArrayList<String> pro=userDao.getUserProportion();
		ArrayList<String> type=new ArrayList<String>();
		ArrayList<Integer> num=new ArrayList<Integer>();
		res.setAdd(userDao.getAdd(start, end));
		res.setLose(userDao.getLose(start, end));
		for(int i=0;i<pro.size();i++) {
			String s1[]=pro.get(i).split("=");
			type.add(s1[0]);
			num.add(Integer.parseInt(s1[1]));
		}
		res.setType(type);
		res.setNum(num);
		return res;
	}

}
