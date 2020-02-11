package service;

import java.util.ArrayList;

import model.PUserStatistic;

public interface PlatStatisticService {
	/*
	 * name-money
	 */
	public ArrayList<String> topTenVen(String start,String end);
	/*
	 * name-money
	 */
	public ArrayList<String> topTenAct(String start,String end);
	/*
	 * date-money
	 */
	public ArrayList<String> getPlatCurve(String start,String end);
	
	public PUserStatistic getUserInfo(String start,String end);
	
}
