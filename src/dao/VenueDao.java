package dao;

import java.util.ArrayList;

import model.App;
import model.Hall;
import model.Venue;

public interface VenueDao {
	/*
	 * 管理员获取申请
	 */
	public ArrayList<App> getApplication(int offset);
	/*
	 * 管理员审批申请
	 */
	public String modAppState(int id,String state);
	/*
	 * 获得某个场馆的申请
	 */
	public ArrayList<App> getVenueApp(int vid,int offset);
	/*
	 * 根据ID获得场馆，包含展厅信息
	 */
	public Venue getByID(int id);
	/*
	 * 获取申请详情
	 */
	public App getAppByID(int aid);
	
	
	/*
	 * 获取新的场馆标识码
	 */
	public int getVID();
	/*
	 * 获取新的申请标识码
	 */
	public int getAppID();
	/*
	 * 获取新的申请标识码
	 */
	public int getHallID();
	/*
	 * 先把酒店的信息加入LOGIN 备注为未审核
	 */
	public String addV(int id,String pwd);
	/*
	 * 在VENUE中添加一条记录
	 */
	public String addVenueApp(App app);
	/*
	 * 在HALL中添加一条记录
	 */
	public String addHallApp(App app);
	/*
	 * 通过ID找到一个HALL
	 */
	public Hall findByID(int hid);
}
