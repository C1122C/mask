package service;

import java.util.ArrayList;
import java.util.Date;

import business.UserIndexBean;
import dao.ActDao;
import dao.OrderDao;
import dao.PayDao;
import dao.VenueDao;
import factory.DaoFactory;
import factory.ServiceFactory;
import model.Activity;
import model.Hall;
import model.Order;
import model.User;
import model.Venue;
import util.ACT_STATE;
import util.ORDER_STATE;

public class ActServiceImpl implements ActService {

	private ActDao actDao;
	private PayDao payDao;
	private OrderDao orderDao;
	private VenueDao venueDao;
	private UserService userService;
	private DaoFactory factory;
	private ServiceFactory fact;
	private static ActServiceImpl actservice;
	
	private ActServiceImpl() {
		factory=DaoFactory.getInstance();
		fact=ServiceFactory.getInstance();
		actDao=factory.getActDao();
		payDao=factory.getPayDao();
		orderDao=factory.getOrderDao();
		venueDao=factory.getVenueDao();
		userService=fact.getUserService();
	}
	
	public static ActServiceImpl getInstance() {
		if(actservice==null) {
			actservice=new ActServiceImpl();
		}
		return actservice;
	}
	/*
	 * checked
	 * @see service.ActService#get_act_type()
	 */
	public ArrayList<String> getActType(){
		ArrayList<String> sorts=new ArrayList<String>();
		sorts.add("全部演出");
        sorts.add("演唱会");
        sorts.add("音乐会");
        sorts.add("话剧舞台剧");
        sorts.add("舞蹈芭蕾");
        sorts.add("儿童亲子");
        sorts.add("戏曲综艺");
        sorts.add("休闲娱乐");
        sorts.add("体育赛事");
        return sorts;
	}
	
	

	/*
	 * checked
	 * @see service.ActService#buyTicket(int, boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public int buyTicket(int aid, boolean isSelect, String info, String username) {
		//返回订单号，此时订单并未支付 //showtime 单价 数量 总价vote//aid:showTime:rc-rr-sc-sr-price:sum select
		String showtime="";
		double v_price=0;
		int v_num=0;
		double sum=0;
		String s[]=info.split(":");
		sum=Double.parseDouble(s[s.length-1]);
		if(isSelect) {
			System.out.println("IN BUY SELECT");
			System.out.println("SUM:"+sum);
			showtime=s[1];
			System.out.println("SHOWTIME:"+showtime);
			int oid=orderDao.getAOid();
			System.out.println("OID:"+oid);
			Activity act=this.getByID(aid);
			Hall hall=new Hall();
			for(int i=0;i<act.getHalls().size();i++) {
				System.out.println("PARE:"+showtime+" SHOWTIME:"+act.getHalls().get(i).getTimetosee());
				if(act.getHalls().get(i).getShowTime().equals(showtime)) {
					hall=act.getHalls().get(i);
					break;
				}
			}
			Order order=new Order();
			order.setActid(aid);
			order.setActName(act.getName());
			System.out.println("SET ORDER ANAME:"+act.getName());
			order.setActTime(hall.getTimetosee());
			order.setCreateDate(new Date());
			order.setId(oid);
			order.setJudgeState(ORDER_STATE.UNPAY.getIndex());
			order.setPath(act.getPath());
			order.setRoom(hall.getName());
			System.out.println("SET ORDER HNAME:"+hall.getName());
			order.setStart(System.currentTimeMillis());
			order.setState(ORDER_STATE.UNPAY.getName());
			order.setSum(sum);
			order.setUserid(username);
			order.setVenueCity(act.getCity());
			order.setVenueid(act.getVid());
			order.setVenueName(act.getVname());
			System.out.println("SET ORDER VNAME:"+act.getVname());
			order.setHallname(hall.getName());
			order.setActType(act.getType());
			ArrayList<String> s_r=new ArrayList<String>();
			ArrayList<String> s_c=new ArrayList<String>();
			ArrayList<Integer> r_r=new ArrayList<Integer>();
			ArrayList<Integer> r_c=new ArrayList<Integer>();
			for(int i=2;i<s.length-1;i++) {
				System.out.println("DEAL SEAT:"+s[i]);
				String seat[]=s[i].split("-");
				hall.sell(Integer.parseInt(seat[1]), Integer.parseInt(seat[0]), oid);
				order.setPrice(Double.parseDouble(seat[4]));
				s_r.add(seat[3]);
				s_c.add(seat[2]);
				r_r.add(Integer.parseInt(seat[1]));
				r_c.add(Integer.parseInt(seat[0]));
			}
			System.out.println("AFTER SELL HALL IS:"+hall.to_save_form());
			order.setReslX(r_r);
			order.setReslY(r_c);
			order.setSeatX(s_r);
			order.setSeatY(s_c);
			System.out.println("SHOULD BE :"+r_r.size()+" BUT IS "+order.getReslX().size());
			System.out.println("AFTER CREATE ORDER IS:"+order.save_realX());
			orderDao.addOrder(order);
			actDao.changeSeat(aid, hall.getTimetosee(), hall);
			return oid;
		}
		else {
			showtime=s[0];
			v_price=Double.parseDouble(s[1]);
			v_num=Integer.parseInt(s[2]);
			return this.vote(aid, showtime, v_price, v_num,sum,username);
		}
		
	}
	/*
	 * checked
	 */
	public double sell(String par) {//123456789@qq.com:19:2018-04-30:8-0-7-1-1000.0:9-0-8-1-1000.0
		String p[]=par.split(":");
		String user=p[0];
		System.out.println("IN SELL USER IS"+user);
		int aid=Integer.parseInt(p[1]);
		System.out.println("IN SELL AID IS"+aid);
		String showtime=p[2];
		System.out.println("IN SELL SHOWTIME:"+showtime);
		double sum=0;
		int oid=orderDao.getAOid();
		System.out.println("IN SELL OID IS"+oid);
		Activity act=this.getByID(aid);
		Hall hall=new Hall();
		for(int i=0;i<act.getHalls().size();i++) {
			System.out.println("PARE:"+showtime+" SHOWTIME:"+act.getHalls().get(i).getTimetosee());
			if(act.getHalls().get(i).getShowTime().equals(showtime)) {
				hall=act.getHalls().get(i);
				break;
			}
		}
		User u=new User();
		if(!user.equals("0")) {
			u=userService.getUserInfo(user);
			System.out.println("IN SELL GET USER NAME"+u.getName());
		}
		
		Order order=new Order();
		order.setActid(aid);
		order.setActName(act.getName());
		System.out.println("SET ORDER ANAME:"+act.getName());
		order.setActTime(hall.getTimetosee());
		order.setCreateDate(new Date());
		order.setId(oid);
		order.setJudgeState(ORDER_STATE.UNUSE.getIndex());
		order.setPath(act.getPath());
		order.setRoom(hall.getName());
		order.setStart(System.currentTimeMillis());
		order.setState(ORDER_STATE.UNUSE.getName());
		
		order.setUserid(user);
		order.setVenueCity(act.getCity());
		order.setVenueid(act.getVid());
		order.setVenueName(act.getVname());
		order.setHallname(hall.getName());
		order.setActType(act.getType());
		ArrayList<String> s_r=new ArrayList<String>();
		ArrayList<String> s_c=new ArrayList<String>();
		ArrayList<Integer> r_r=new ArrayList<Integer>();
		ArrayList<Integer> r_c=new ArrayList<Integer>();
		/*
		 * user(0表示非会员）:aid:time:realc-realr-seatc-seatr-price:返回应付钱数
		 */
		String bef=hall.to_save_form();
		for(int i=3;i<p.length;i++) {
			System.out.println("DEAL SEAT:"+p[i]);
			String seat[]=p[i].split("-");
			hall.sell(Integer.parseInt(seat[1]), Integer.parseInt(seat[0]), oid);
			order.setPrice(Double.parseDouble(seat[4]));
			s_r.add(seat[3]);
			s_c.add(seat[2]);
			r_r.add(Integer.parseInt(seat[1]));
			r_c.add(Integer.parseInt(seat[0]));
			sum=sum+Double.parseDouble(seat[4]);
		}
		System.out.println("AFTER SELL HALL IS:"+hall.to_save_form());
		if(!user.equals("0")) {
			sum=sum*u.getCut();
		}
		order.setSum(sum);
		order.setReslX(r_r);
		order.setReslY(r_c);
		order.setSeatX(s_r);
		order.setSeatY(s_c);
		System.out.println("SHOULD BE :"+r_r.size()+" BUT IS "+order.getReslX().size());
		System.out.println("AFTER CREATE ORDER IS:"+order.save_realX());
		orderDao.addOrder(order);
		actDao.changeSeat(aid, hall.getTimetosee(), hall);
		System.out.println("BEFORE SELL IS"+bef);
		System.out.println("AFTER SELL IS"+hall.to_save_form());
		return sum;
		
	}
	/*
	 * checked
	 */
	@Override
	public int vote(int aid,String showtime,double ticket,int num,double sum,String username){
		int oid=orderDao.getAOid();
		Activity act=this.getByID(aid);
		Hall hall=new Hall();
		for(int i=0;i<act.getHalls().size();i++) {
			if(act.getHalls().get(i).getShowTime().equals(showtime)) {
				hall=act.getHalls().get(i);
				System.out.println("GET THE HALL");
				break;
			}
		}
		Order order=new Order();
		order.setActid(aid);
		order.setActName(act.getName());
		order.setActTime(hall.getTimetosee());
		order.setCreateDate(new Date());
		order.setId(oid);
		order.setJudgeState(ORDER_STATE.UNPAY.getIndex());
		order.setPath(act.getPath());
		order.setRoom(hall.getName());
		order.setStart(System.currentTimeMillis());
		order.setState(ORDER_STATE.UNPAY.getName());
		order.setSum(sum);
		order.setUserid(username);
		order.setVenueCity(act.getCity());
		order.setVenueid(act.getVid());
		order.setHallname(hall.getName());
		order.setVenueName(act.getVname());
		order.setActType(act.getType());
		ArrayList<String> s_r=new ArrayList<String>();
		ArrayList<String> s_c=new ArrayList<String>();
		ArrayList<Integer> r_r=new ArrayList<Integer>();
		ArrayList<Integer> r_c=new ArrayList<Integer>();
		order.setPrice(ticket);
		boolean success=true;
		for(int i=0;i<num;i++) {
			String seat=hall.sell_vote(ticket, oid);
			System.out.println("SELL!"+seat);
			if(seat.equals("")) {
				success=false;
				break;
			}
			else {
				String ss[]=seat.split("-");
				s_r.add(ss[2]);
				s_c.add(ss[3]);
				r_r.add(Integer.parseInt(ss[0]));
				r_c.add(Integer.parseInt(ss[1]));
			}
			
		}
		order.setReslX(r_r);
		order.setReslY(r_c);
		order.setSeatX(s_r);
		order.setSeatY(s_c);
		if(success) {
			orderDao.addOrder(order);
			actDao.changeSeat(aid, hall.getTimetosee(), hall);
			return oid;
		}
		else {
			return -1;
		}
	}

	/**
	 * checked
	 */
	@Override
	public String settle(int aid) {
		double v=actDao.getSum(aid);
		Activity a=actDao.getByID(aid);
		Venue ven=venueDao.getByID(a.getVid());
		String vpay=ven.getPayid();
		String res=payDao.transfer("10001","123456",vpay,v);
		if(res.startsWith("FAIL")) {
			return res;
		}
		res=actDao.modSettle(aid);
		return res;
	}

	@Override
	public ArrayList<Activity> getPassed(int offset) {
		return actDao.getPassed(offset);
	}

	/*checked*/
	@Override
	public Activity getByID(int id) {
		// TODO Auto-generated method stub
		return actDao.getByID(id);
	}

	/**
	 * checked
	 */
	@Override
	public String publish(int aid, String name, String hall, String type, String s_year, String s_month, String s_day,
			String e_year, String e_month, String e_day, String sale_type, String des,String path,
			String priceInfo,int vid,String time) {
		// TODO Auto-generated method stub
		Venue venue=venueDao.getByID(vid);
		Activity act=new Activity();
		int sM=Integer.parseInt(s_month);
		if(sM<10) {
			s_month="0"+s_month;
		}
		int sD=Integer.parseInt(s_day);
		if(sD<10) {
			s_day="0"+s_day;
		}
		int eM=Integer.parseInt(e_month);
		if(eM<10) {
			e_month="0"+e_month;
		}
		int eD=Integer.parseInt(e_day);
		if(eD<10) {
			e_day="0"+e_day;
		}
		act.setCity(venue.getAddr().getCity());
		act.setDes(des);
		act.setFirst(s_year+"-"+s_month+"-"+s_day);
		act.setId(aid);
		act.setLast(e_year+"-"+e_month+"-"+e_day);
		act.setName(name);
		act.setPath(path);
		if(sale_type.equals("选座购票")) {
			act.setSelect(true);
		}
		else {
			act.setSelect(false);
		}
		act.setState(ACT_STATE.UNDERWAY.getName());
		act.setType(type);
		act.setVid(vid);
		act.setVname(venue.getName());
		act.setHname(hall);
		Hall h=new Hall();
		ArrayList<Hall> hs=venue.getHall();
		for(int i=0;i<hs.size();i++) {
			System.out.println("VENUE HALL IS"+hs.get(i).getName()+" AND HALL IS"+hall);
			if(hs.get(i).getName().equals(hall)) {
				System.out.println("GET");
				h=hs.get(i);
				break;
			}
		}
		ArrayList<Hall> act_hall=new ArrayList<Hall>();
		ArrayList<String> a_time=new ArrayList<String>();
		for(int i=sD;i<=eD;i++) {
			System.out.println("IN ADD SEAT SD IS"+sD+" ED IS "+eD+" I IS "+i);
			String s=i+"";
			if(i<10) {
				s="0"+s;
			}
			a_time.add(s_year+"-"+s_month+"-"+s+" "+time);
			System.out.println("SET"+s_year+"-"+s_month+"-"+s+" "+time);
			Hall h1=new Hall();
			h1.setAid(h.getAid());
			h1.setId(h.getId());
			h1.setName(h.getName());
			h1.setSeatNum(h.getSeatNum());
			h1.setVid(h.getVid());
			h1.setMatrix(priceInfo);
			h1.setSelect(act.isSelect());
			h1.setShowTime(s_year+"-"+s_month+"-"+s+" "+time);
			h1.setTimetosee(s_year+"-"+s_month+"-"+s+" "+time);
			act_hall.add(h1);
		}
		
		for(int i=0;i<act_hall.size();i++) {
			System.out.println("AFTER SET HALL TIME IS"+act_hall.get(i).getTimetosee());
		}
		act.setHalls(act_hall);
		act.setTime(a_time);
		act.setPrice(act_hall.get(0).getStart());
		return actDao.addAct(act);
	}

	
	/**
	 * checked
	 */
	@Override
	public int getNewAid() {
		// TODO Auto-generated method stub
		return actDao.getNewAid();
	}

	/*
	 * checked
	 * @see service.ActService#get_all_city()
	 */
	@Override
	public ArrayList<String> getAllCity() {
		// TODO Auto-generated method stub
		return actDao.getAllCity();
	}

	/*
	 * checked
	 * @see service.ActService#getIndex(java.lang.String)
	 */
	@Override
	public UserIndexBean getIndex() {
		// TODO Auto-generated method stub
		UserIndexBean res=new UserIndexBean();
		res.setAd(actDao.getAd());
		res.setBallet(this.selectByCond("全国", "舞蹈芭蕾", 0));
		res.setConcert(this.selectByCond("全国", "演唱会", 0));
		res.setDrama(this.selectByCond("全国", "话剧舞台剧", 0));
		res.setEntertainment(this.selectByCond("全国", "休闲娱乐", 0));
		res.setMusicale(this.selectByCond("全国", "音乐会", 0));
		res.setOpera(this.selectByCond("全国", "戏曲综艺", 0));
		res.setParent_child(this.selectByCond("全国", "儿童亲子", 0));
		res.setSports(this.selectByCond("全国", "体育赛事", 0));
		return res;
	}

	/*
	 * checked
	 * @see service.ActService#select_by_cond(java.lang.String, java.lang.String, int)
	 */
	@Override
	public ArrayList<Activity> selectByCond(String city, String type, int offset) {
		// TODO Auto-generated method stub
		return actDao.selectByCond(city, type, offset);
	}

	/*
	 * checked
	 * @see service.ActService#select_by_name_or_ven(java.lang.String, int)
	 */
	@Override
	public ArrayList<Activity> selectByNameOrVen(String input, int offset) {
		// TODO Auto-generated method stub
		return actDao.selectByNameOrVen(input, offset);
	}

	/*checked*/
	@Override
	public ArrayList<Activity> selectMore(String city, String type, int offset, String input) {
		// TODO Auto-generated method stub
		return actDao.selectMore(city, type, offset, input);
	}
	
	/*
	 * checked
	 * @see service.ActService#changeSeat(int, java.lang.String, model.Hall)
	 */
	public String changeSeat(int aid,String showtime,Hall hall) {
		return actDao.changeSeat(aid, showtime, hall);
	}

	@Override
	public ArrayList<Activity> selectByVid(int vid, int offset) {
		// TODO Auto-generated method stub
		return actDao.selectByVid(vid, offset);
	}

	

}
