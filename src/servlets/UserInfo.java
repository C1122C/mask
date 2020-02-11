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

import business.UserInfoBean;
import factory.ServiceFactory;
import model.User;
import model.UserStatistic;
import service.ActService;
import service.UserService;


/**
 * Servlet implementation class StockListServlet
 */
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userservice;
	private ActService actservice;
	private ServiceFactory factory;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		factory=ServiceFactory.getInstance();
        userservice=factory.getUserService();
        actservice=factory.getActService();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GET IN FOR TYPE "+request.getParameter("type"));
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		HttpSession session = request.getSession(false);
		String username="";
		String result="SUCCESS";
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		String type=request.getParameter("type");
		if (null != cookies) {
			
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("user")) {
					username=cookie.getValue();
					
				}
				
			}
		}
		int t=Integer.parseInt(type);
		if(t>=3) {/*用户统计数据*/
			
		    String SD = request.getParameter("SD")+" 00:00:00";
			String ED = request.getParameter("ED")+" 00:00:00";
			UserStatistic user=userservice.getStatistic(username, SD, ED);
			String back="[";
			if(t==3) {
				//order
				double sum=user.getSum();
				ArrayList<String> orderkind=user.getOrderKind();
				ArrayList<Integer> num=user.getOrderNum();
				for(int i=0;i<num.size();i++) {
					back=back+"{'sum':'"+sum+"','kind':'"+orderkind.get(i)+"','num':'"+num.get(i)+"'},";
				}
				
			}
			else if(t==4) {
				ArrayList<String> actkind=user.getActKind();
				ArrayList<Integer> num=user.getTimes();
				ArrayList<Double> money=user.getMoney();
				for(int i=0;i<num.size();i++) {
					back=back+"{'kind':'"+actkind.get(i)+"','num':'"+num.get(i)+"','money':'"+money.get(i)+"'},";
				}
				
			}
			else {
				ArrayList<String> date=user.getDate();
				ArrayList<Double> money=user.getConsum();
				for(int i=0;i<date.size();i++) {
					back=back+"{'date':'"+date.get(i)+"','money':'"+money.get(i)+"'},";
				}
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
				System.out.println("type "+type);
				
				if(t==1) {/*停用账号*/
					result=userservice.stop(username);
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
				else if(t==2){/*修改个人信息*/
					System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++IN+++++++++++++++++++++++++++++");
					String name=request.getParameter("name");
					System.out.println("GET NAME:"+name);
					String pwd=request.getParameter("pass");
					System.out.println("GET PWD:"+pwd);
					String gender=request.getParameter("gender");
					System.out.println("GET GENDER:"+gender);
					String year=request.getParameter("year");
					System.out.println("GET YEAR:"+year);
					String month=request.getParameter("month");
					System.out.println("GET MONTH:"+month);
					String day=request.getParameter("day");
					System.out.println("GET DAY:"+day);
					String pay_id=request.getParameter("payid");
					System.out.println("GET PAYID:"+pay_id);
					String ins[]=request.getParameter("interest").split(":");
					System.out.println("GETINS:"+ins.length);
					ArrayList<String> in=new ArrayList<String>();
					for(int i=0;i<ins.length;i++) {
						in.add(ins[i]);
					}
					User u=userservice.getUserInfo(username);
					u.setName(name);
					u.setPwd(pwd);
					u.setGender(gender);
					u.setB_year(Integer.parseInt(year));
					u.setB_month(Integer.parseInt(month));
					u.setB_day(Integer.parseInt(day));
					u.setPayID(pay_id);
					u.setInterest(in);
					result=userservice.modUser(u);
					if(result.equals("SUCCESS")) {
						User uu=userservice.getUserInfo(username);
						UserInfoBean uib=new UserInfoBean();
						ArrayList<String> inss=actservice.getActType();
						System.out.println("ADD NEW USERBEAN:"+name);
						uib.setUser(uu);
						uib.setInterest(inss);
						System.out.println("ADD NEW USERBEAN:"+uib.getUser().getName());
						session.setAttribute("user", uib);
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
	
}
