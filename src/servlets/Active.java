package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.CityBean;
import business.CouponListBean;
import business.OrderListBean;
import business.UserIndexBean;
import business.UserInfoBean;
import factory.ServiceFactory;
import model.Coupon;
import model.Order;
import model.User;
import service.ActService;
import service.CouponService;
import service.UserService;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Active")
public class Active extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userservice;
	private ActService actservice;
	private CouponService couponservice;
	private ServiceFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Active() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	factory=ServiceFactory.getInstance();
        userservice=factory.getUserService();
        actservice=factory.getActService();
        couponservice=factory.getCouponService();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Cookie cookie = null;
		String code=request.getParameter("code");
		String result=userservice.checkVcode(code);
		if(!result.equals("FAIL")){
			session = request.getSession(true);
			session.setAttribute("username", result);
			cookie = new Cookie("user", result);
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
        	
        	ArrayList<Order> or=new ArrayList<Order>();
        	OrderListBean orderlist=new OrderListBean();
        	orderlist.setOrderList(or);
        	
			
			ArrayList<String> c=actservice.getAllCity();
			UserIndexBean userbean=actservice.getIndex();
			
			
			CityBean cb=new CityBean();
			cb.setCity(c);
			
			User u=userservice.getUserInfo(result);
			UserInfoBean uib=new UserInfoBean();
			ArrayList<String> ins=actservice.getActType();
			uib.setUser(u);
			uib.setInterest(ins);
			
			ArrayList<Coupon> c_mine=couponservice.getAllCoupon(result, 0);
			ArrayList<Coupon> c_change=couponservice.getCanChange(u.getBonus(), 0);
			CouponListBean couponbean=new CouponListBean();
			couponbean.setAlreadyHave(c_mine);
			couponbean.setCanChange(c_change);
			
			session.setAttribute("list", userbean);
			session.setAttribute("city", cb);
			session.setAttribute("user", uib);
			session.setAttribute("orderlist",orderlist);
			session.setAttribute("couponlist",couponbean);
            request.getRequestDispatcher("/mine.jsp").forward(request, response);  
		}else{  
            request.getRequestDispatcher("/index.jsp").forward(request, response);  
		}  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
