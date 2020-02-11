package service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;

import dao.AccountDao;
import dao.VenueDao;
import factory.DaoFactory;
import factory.ServiceFactory;
import model.Activity;
import model.Address;
import model.App;
import model.Hall;
import model.Venue;
import model.VenueStatistic;
import util.APP_STATE;
import util.APP_TYPE;

public class VenueServiceImpl implements VenueService {

	private VenueDao venueDao;
	private AccountDao accountDao;
	private ActService actService;
	private DaoFactory factory;
	private ServiceFactory fact;
	private static VenueServiceImpl venueService;
	
	private VenueServiceImpl() {
		factory=DaoFactory.getInstance();
		fact=ServiceFactory.getInstance();
		venueDao=factory.getVenueDao();
		accountDao=factory.getAccountDao();
		actService=fact.getActService();
	}
	
	public static VenueServiceImpl getInstance() {
		if(venueService==null) {
			venueService=new VenueServiceImpl();
		}
		return venueService;
	}
	
	/**
	 * checked
	 */
	@Override
	public ArrayList<String> getAct(int aid) {
		// TODO Auto-generated method stub
		return accountDao.getAct(aid);
	}

	/**
	 * checked
	 */
	@Override
	public ArrayList<Activity> getAll(int offset, int vid) {
		return actService.selectByVid(vid, offset);
	}

	/**
	 * checked
	 */
	@Override
	public int addVenue(String name,String pwd,String phone,String pay_id,String pro,String city,String dis,String det) {
		// TODO Auto-generated method stub
		int id=venueDao.getVID();
		Venue venue=new Venue();
		Address addr=new Address();
		addr.setProvince(pro);
		addr.setCity(city);
		addr.setDistrict(dis);
		addr.setDetail(det);
		venue.setAddr(addr);
		venue.setId(id);
		venue.setName(name);
		venue.setPayid(pay_id);
		venue.setPhone(phone);
		venue.setPwd(this.encode(pwd));
		venueDao.addV(id, this.encode(pwd));
		App app=new App();
		app.setDate(new Date());
		app.setId(venueDao.getAppID());
		app.setState(APP_STATE.WITHOUT_APPROVAL.getName());
		app.setType(APP_TYPE.REG_VENUE.getName());
		app.setVenue(venue);
		app.setOrg(0);
		String res=venueDao.addVenueApp(app);
		if(res.equals("FAIL")) {
			return -1;
		}
		return id;
	}

	/**
	 * checked
	 */
	@Override
	public String modVenue(String name,String pwd,String phone,String pay_id,String pro,String city,String dis,String det,int vid) {
		// TODO Auto-generated method stub
		System.out.println("--------------------------"+pwd+"---------------------------");
		Venue venue=venueDao.getByID(vid);
		if(!(name==null||name.equals(""))) {
			venue.setName(name);
		}
		if(!(pwd==null||pwd.equals(""))) {
			System.out.println("--------------------------ADD---------------------------");
			venue.setPwd(this.encode(pwd));
		}
		if(!(phone==null||phone.equals(""))) {
			venue.setPhone(phone);
		}
		if(!(pay_id==null||pay_id.equals(""))) {
			venue.setPayid(pay_id);
		}
		Address addr=venue.getAddr();
		if(!(pro==null||pro.equals(""))) {
			addr.setProvince(pro);
		}
		if(!(city==null||city.equals(""))) {
			addr.setCity(city);
		}
		if(!(dis==null||dis.equals(""))) {
			addr.setDistrict(dis);
		}
		if(!(det==null||det.equals(""))) {
			addr.setDetail(det);
		}
		venue.setAddr(addr);
		App app=new App();
		int aid=venueDao.getAppID();
		int id=venueDao.getVID();
		app.setOrg(venue.getId());
		venue.setId(id);
		app.setDate(new Date());
		app.setId(aid);
		app.setState(APP_STATE.WITHOUT_APPROVAL.getName());
		app.setType(APP_TYPE.MOD_VENUE.getName());
		app.setVenue(venue);
		return venueDao.addVenueApp(app);
		
	}

	/**
	 * checked
	 */
	@Override
	public ArrayList<App> getApplication(int offset) {
		// TODO Auto-generated method stub
		return venueDao.getApplication(offset);
	}

	/**
	 * checked
	 */
	@Override
	public String modApplication(int aid,String state) {
		// TODO Auto-generated method stub
		return venueDao.modAppState(aid, state);
	}

	/**
	 * checked
	 */
	@Override
	public ArrayList<App> getVenueApp(int vid, int offset) {
		// TODO Auto-generated method stub
		return venueDao.getVenueApp(vid, offset);
	}

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

	/**
	 * checked
	 */
	@Override
	public VenueStatistic getStatistic(String start, String end, int vid) {
		// TODO Auto-generated method stub
		VenueStatistic vs=new VenueStatistic();
		ArrayList<String> d_m=accountDao.getVen(start, end, vid);
		ArrayList<String> d=new ArrayList<String>();
		ArrayList<Double> m=new ArrayList<Double>();
		for(int i=0;i<d_m.size();i++) {
			String s[]=d_m.get(i).split("=");
			d.add(s[0]);
			m.add(Double.parseDouble(s[1]));
		}
		vs.setConsum(m);
		vs.setDate(d);
		
		ArrayList<String> kind=new ArrayList<String>();
		ArrayList<Integer> num=new ArrayList<Integer>();
		ArrayList<String> org=accountDao.OrderNum(start, end, vid);
		for(int i=0;i<org.size();i++) {
			String s[]=org.get(i).split("=");
			kind.add(s[0]);
			num.add(Integer.parseInt(s[1]));
		}
		vs.setOrder_kind(kind);
		vs.setOrderNum(num);
		
		vs.setTop_ten(accountDao.topVenueTenAct(start, end, vid));
		return vs;
	}

	/**
	 * checked
	 */
	@Override
	public String deleteHall(int hid) {
		// TODO Auto-generated method stub
		Hall hall=this.findByID(hid);
		App app=new App();
		app.setDate(new Date());
		app.setHall(hall);
		int id=venueDao.getAppID();
		app.setId(id);
		app.setState(APP_STATE.WITHOUT_APPROVAL.getName());
		app.setType(APP_TYPE.DELETE_HALL.getName());
		app.setOrg(hall.getVid());
		return venueDao.addHallApp(app);
	}

	/**
	 * checked
	 */
	@Override
	public String saveAHall(Hall hall) {
		App app=new App();
		app.setDate(new Date());
		int i=venueDao.getHallID();
		hall.setId(i);
		app.setHall(hall);
		
		int id=venueDao.getAppID();
		app.setId(id);
		app.setState(APP_STATE.WITHOUT_APPROVAL.getName());
		app.setType(APP_TYPE.ADD_HALL.getName());
		app.setOrg(hall.getVid());
		return venueDao.addHallApp(app);
	}

	/**
	 * checked
	 */
	@Override
	public App getAppByID(int aid) {
		// TODO Auto-generated method stub
		return venueDao.getAppByID(aid);
	}

	

	@Override
	public Hall findByID(int hid) {
		// TODO Auto-generated method stub
		return venueDao.findByID(hid);
	}

	/**
	 * checked
	 */
	@Override
	public Venue getByID(int id) {
		return venueDao.getByID(id);
	}

}
