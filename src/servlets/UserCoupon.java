package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import business.CouponListBean;
import factory.ServiceFactory;
import model.Coupon;
import model.User;
import service.CouponService;
import service.UserService;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Coupon")
public class UserCoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CouponService couponservice;
	private UserService userservice;
	private ServiceFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCoupon() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	factory=ServiceFactory.getInstance();
        couponservice=factory.getCouponService();
        userservice=factory.getUserService();
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
		String result="SUCCESS";
		Cookie[] cookies = request.getCookies();
		String username="";
		if (null != cookies) {
			
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("user")) {
					username=cookie.getValue();
				}
			}
		}
		if (type.equals("1")) {
			
			int mine_o=0;
			int change_o=0;
			
			ArrayList<Coupon> mine=new ArrayList<Coupon>();
			ArrayList<Coupon> change=new ArrayList<Coupon>();
			if (null != cookies) {
				
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if(cookie.getName().equals("coupon_mine_offset")) {
						mine_o=Integer.parseInt(cookie.getValue());
						mine=couponservice.getAllCoupon(username, mine_o);
						cookie.setValue(""+mine_o+6);
						cookie.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(cookie);
					}
					if(cookie.getName().equals("coupon_change_offset")) {
						change_o=Integer.parseInt(cookie.getValue());
						User u=userservice.getUserInfo(username);
						change=couponservice.getCanChange(u.getBonus(), change_o);
						cookie.setValue(""+change_o+6);
						cookie.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(cookie);
					}
					
				}
			}
			String back="[";
			for(int i=0;i<mine.size();i++) {
				Coupon c=mine.get(i);
				back=back+"{'valu':'"+c.getValue()+"','name':'"+c.getName()+"','type':'"+c.getType()+"','des':'"+c.getDes()+
						"','vali':'"+c.getValid()+"','tid':'"+c.getType_id()+"','change':'"+c.getChange()+
						"','ismine':'"+c.getIsMine()+"'},";
			}
			for(int i=0;i<change.size();i++) {
				Coupon c=change.get(i);
				back=back+"{'valu':'"+c.getValue()+"','name':'"+c.getName()+"','type':'"+c.getType()+"','des':'"+c.getDes()+
						"','vali':'"+c.getValid()+"','tid':'"+c.getType_id()+"','change':'"+c.getChange()+
						"','ismine':'"+c.getIsMine()+"'},";
			}
			if(back.length()>1) {
				back=back.substring(0, back.length()-1);
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(back);
				back = m.replaceAll("");
			}
			back=back+"]";
	        System.out.println(back);
	        JSONArray json = new JSONArray(back);
	        PrintWriter writer = response.getWriter();
	        writer.print(json);
	        writer.flush();
	        writer.close();	
			
        }
		else {
			if (type.equals("2")) {
				int id=Integer.parseInt(request.getParameter("id"));
				result=couponservice.exchange(username, id);
				ArrayList<Coupon> mine=new ArrayList<Coupon>();
				ArrayList<Coupon> change=new ArrayList<Coupon>();
				if (null != cookies) {
					
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if(cookie.getName().equals("coupon_mine_offset")) {
							mine=couponservice.getAllCoupon(username, 0);
							cookie.setValue(""+6);
							cookie.setMaxAge(Integer.MAX_VALUE);
							response.addCookie(cookie);
						}
						if(cookie.getName().equals("coupon_change_offset")) {
							User u=userservice.getUserInfo(username);
							change=couponservice.getCanChange(u.getBonus(), 0);
							cookie.setValue(""+6);
							cookie.setMaxAge(Integer.MAX_VALUE);
							response.addCookie(cookie);
						}
						
					}
				}
				CouponListBean couponbean=new CouponListBean();
				couponbean.setAlreadyHave(mine);
				couponbean.setCanChange(change);
				session.setAttribute("couponlist",couponbean);
			}
			if (type.equals("3")) {/*reset cookie*/
				String name=request.getParameter("name");
				if (null != cookies) {
					
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if(cookie.getName().equals(name)) {
							cookie.setValue(""+6);
							cookie.setMaxAge(Integer.MAX_VALUE);
							response.addCookie(cookie);
						}
						
					}
				}
				
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
