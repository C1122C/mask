package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import business.ActListBean;
import business.AppListBean;
import business.CityBean;
import business.CouponListBean;
import business.HallBean;
import business.OrderListBean;
import business.UserIndexBean;
import business.UserInfoBean;
import business.VenueBean;
import factory.ServiceFactory;
import model.Activity;
import model.App;
import model.Coupon;
import model.Hall;
import model.Order;
import model.Seat;
import model.User;
import model.Venue;
import service.ActService;
import service.CouponService;
import service.OrderService;
import service.UserService;
import service.VenueService;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userservice;
	private ActService actservice;
	private OrderService orderservice;
	private CouponService couponservice;
	private VenueService venueservice;
	private ServiceFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	factory=ServiceFactory.getInstance();
        userservice=factory.getUserService();
        actservice=factory.getActService();
        couponservice=factory.getCouponService();
        orderservice=factory.getOrderService();
        venueservice=factory.getVenueService();
	}
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		HttpSession session = request.getSession(false);
		Cookie cookie = null;
		String type=request.getParameter("type");
		String result="";
		
		if (type.equals("4")) {/*退出登录*/
			Cookie cookie1 = new Cookie("user", null);
            cookie1.setMaxAge(0);
            cookie1.setPath("/"); 
            response.addCookie(cookie1);
			if (null != session) {
            	session.invalidate();
                session = null;
            }
            result="SUCCESS";
        }
		else {
			boolean log1=false;
			boolean log2=false;
			boolean log3=false;
			String u_i="";
			String v_i="";
			String m_i="";
			if (type.equals("1")) {/*登录*/
				String id=request.getParameter("id");
				String pwd=request.getParameter("pwd");
				result=userservice.logIn(id, pwd);
				if(result.equals("u")) {
					log1=true;
					u_i=id;
				} 
				else if(result.equals("v")) {
					log2=true;
					v_i=id;
				}
				else if(result.equals("m")) {
					log3=true;
					m_i=id;
				}
			}
			else if (type.equals("2")) {/*发送验证邮件*/
				String mail=request.getParameter("mail");
				result=userservice.mailCheck(mail);
			}
			
			if(log1) {
				session = request.getSession(true);
				session.setAttribute("username", u_i);
				cookie = new Cookie("user", u_i);
				cookie.setPath("/");
				cookie.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(cookie);
				Cookie c2 = new Cookie("order_select_type", "");
				c2.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(c2);
				Cookie c3 = new Cookie("order_select_offset", ""+6);
				c3.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(c3);
				Cookie c4 = new Cookie("coupon_mine_offset", ""+6);
				c4.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(c4);
				Cookie c5 = new Cookie("coupon_change_offset", ""+6);
				c5.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(c5);
				System.out.println("BEGIN TO GET INFOMATION UID IS"+u_i);
	        	ArrayList<Order> or=orderservice.getOrder(0, u_i, "");
	        	OrderListBean orderlist=new OrderListBean();
	        	orderlist.setOrderList(or);
	        	System.out.println("IN LOG! ORDER SIZE IS"+or.size());
				
				ArrayList<String> c=actservice.getAllCity();
				UserIndexBean userbean=actservice.getIndex();
				
				
				CityBean cb=new CityBean();
				cb.setCity(c);
				
				User u=userservice.getUserInfo(u_i);
				System.out.println("IN LOG! USER NAME IS"+u.getName()+" CUT IS"+u.getCut());
				UserInfoBean uib=new UserInfoBean();
				ArrayList<String> ins=actservice.getActType();
				uib.setUser(u);
				uib.setInterest(ins);
				
				ArrayList<Coupon> c_mine=couponservice.getAllCoupon(u_i, 0);
				ArrayList<Coupon> c_change=couponservice.getCanChange(u.getBonus(), 0);
				CouponListBean couponbean=new CouponListBean();
				couponbean.setAlreadyHave(c_mine);
				couponbean.setCanChange(c_change);
				System.out.println("IN LOG! COUPON SIZE IS"+couponbean.getAlreadyHave().size()+" CAN CHANGE IS"+couponbean.getCanChange().size());
				System.out.println("IN LOG! USER NAME IS"+uib.getUser().getName()+" CUT IS"+uib.getUser().getCut());
				session.setAttribute("list", userbean);
				session.setAttribute("city", cb);
				session.setAttribute("user", uib);
				session.setAttribute("orderlist",orderlist);
				session.setAttribute("couponlist",couponbean);
			}
			if(log2) {
				session = request.getSession(true);
				session.setAttribute("vname", v_i);
				cookie = new Cookie("user", v_i);
				cookie.setPath("/");
				cookie.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(cookie);
				Cookie c3 = new Cookie("venue_act_offset", ""+6);
				c3.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(c3);
				Cookie c4 = new Cookie("venue_app_offset", ""+6);
				c4.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(c4);
	        	ArrayList<Activity> act=venueservice.getAll(0, Integer.parseInt(v_i));
	        	ActListBean actlist=new ActListBean();
	        	actlist.setActList(act);
	        	System.out.println("IN LOG OF VENUE GET ACTLIST:"+actlist.getActList().size());
	        	
				Venue venue=venueservice.getByID(Integer.parseInt(v_i));
				VenueBean venuebean=new VenueBean();
				venuebean.setVenue(venue);
				System.out.println("IN LOG OF VENUE GET VENUE:"+venuebean.getVenue().toString());
				
				ArrayList<App> app=venueservice.getVenueApp(Integer.parseInt(v_i), 0);
				AppListBean applist=new AppListBean();
				applist.setAppList(app);
				System.out.println("IN LOG OF VENUE GET APP:"+applist.getAppList().size());
				
				Hall hall=new Hall();
				hall.setSeats(new Seat[0][0]);
				HallBean hallbean=new HallBean();
				hallbean.setHall(hall);
				hallbean.setOriginal("");
				
				session.setAttribute("vactlist", actlist);
				session.setAttribute("venue", venuebean);
				session.setAttribute("vapplist", applist);
				session.setAttribute("new_hall", hallbean);
			}
			if(log3) {
				session = request.getSession(true);
				session.setAttribute("mname", m_i);
				cookie = new Cookie("user", m_i);
				cookie.setPath("/");
				cookie.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(cookie);
				Cookie c3 = new Cookie("manager_act_offset", ""+6);
				c3.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(c3);
				Cookie c4 = new Cookie("manager_app_offset", ""+6);
				c4.setMaxAge(Integer.MAX_VALUE);
				response.addCookie(c4);
				
	        	ArrayList<Activity> act=actservice.getPassed(0);
	        	ActListBean actlist=new ActListBean();
	        	actlist.setActList(act);
	        	System.out.println("IN LOG OF MANAGER GET ACT:"+actlist.getActList().size());
	        	
				ArrayList<App> app=venueservice.getApplication(0);
				AppListBean applist=new AppListBean();
				applist.setAppList(app);
				System.out.println("IN LOG OF MANAGER GET APP:"+applist.getAppList().size());
				
				session.setAttribute("mactlist", actlist);
				session.setAttribute("mapplist", applist);
			}
			String back = "[{'answer':'"+result+"'}]";
	        System.out.println(back);
	        JSONArray json = new JSONArray(back);
	        PrintWriter writer = response.getWriter();
	        writer.print(json);
	        writer.flush();
	        writer.close();
	        
		}

     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
