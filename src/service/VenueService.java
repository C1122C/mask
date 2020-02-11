package service;

import java.util.ArrayList;

import model.Activity;
import model.App;
import model.Hall;
import model.Venue;
import model.VenueStatistic;

public interface VenueService {
	public Venue getByID(int id);
	
	/*
	 * date-price获取活动统计信息
	 */
	public ArrayList<String> getAct(int aid);
	/*
	 * 获取场馆统计信息
	 */
	public VenueStatistic getStatistic(String start,String end,int vid);
	/*
	 * 获取场馆活动信息
	 */
	public ArrayList<Activity> getAll(int offset,int vid);
	/*
	 * 删除展厅
	 */
	public String deleteHall(int hid);
	/*
	 * 新增展厅
	 */
	public String saveAHall(Hall hall);
	/*
	 * 新增场馆
	 */
	public int addVenue(String name,String pwd,String phone,String pay_id,String pro,String city,String dis,String det);
	
	/*
	 * 修改场馆，如果为“”则表示不改
	 */
	public String modVenue(String name,String pwd,String phone,String pay_id,String pro,String city,String dis,String det,int vid);
	/*
	 * 管理员查看申请
	 */
	public ArrayList<App> getApplication(int offset);
	/*
	 * 获取申请详情
	 */
	public App getAppByID(int aid);
	/*
	 * 管理员审批申请
	 */
	public String modApplication(int id,String state);
	/*
	 * 获取场馆申请
	 */
	public ArrayList<App> getVenueApp(int vid,int offset);
	
	
	
	public Hall findByID(int hid);
}
