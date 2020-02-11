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

import business.ActBean;
import business.ActListBean;
import factory.ServiceFactory;
import model.Activity;
import service.ActService;
import service.OrderService;


/**
 * Servlet implementation class StockListServlet
 */
@WebServlet("/ActSelectAndScan")
public class ActSelectAndScan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActService actservice;
	private OrderService orderservice;
	private ServiceFactory factory;
	private ArrayList<String> number;
	private ArrayList<String> sorts;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActSelectAndScan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		factory=ServiceFactory.getInstance();
        actservice=factory.getActService();
        orderservice=factory.getOrderService();
       number=new ArrayList<String>();
        sorts=actservice.getActType();
        for(int i=0;i<9;i++) {
        	number.add(i+"");
        }
        
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
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		HttpSession session = request.getSession(false);
		if(session==null) {
			System.out.println("SHUT-------------------");
		}
		System.out.println("CHECK SESSION:"+session.getAttribute("username"));
		String result="SUCCESS";
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		String back="";
		
		

		
		
			boolean find1=false;
			boolean find2=false;
			boolean find3=false;
			boolean find4=false;
			String type=request.getParameter("type");
			System.out.println("IN ActSelectAndScan :"+type);
			
			if(type.equals("5")) {/*更多列表*/
				
				String c="全国";
				String t="全部演出";
				int o=0;
				String in="";
				if (null != cookies) {
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if (cookie.getName().equals("act_select_type")) {
							t=cookie.getValue();
						}
						if (cookie.getName().equals("act_select_offset")) {
							o=Integer.parseInt(cookie.getValue());
							cookie.setValue(""+(o+5));
							cookie.setMaxAge(Integer.MAX_VALUE);
							response.addCookie(cookie);
						}
						if (cookie.getName().equals("act_select_place")) {
							c=cookie.getValue();
						}
						if (cookie.getName().equals("act_select_input")) {
							in=cookie.getValue();
						}
					}
				}
				ArrayList<Activity> list1=actservice.selectMore(c, t, o, in);
				back="[";
				for(int i=0;i<list1.size();i++) {
					Activity a=list1.get(i);
					back=back+"{'id':"+a.getId()+",'name':\""+a.getName()+"\",'s_time':\""+a.getFirst()+"\",'e_time':\""+a.getLast()+
							"\",'vname':\""+a.getVname()+"\",'city':\""+a.getCity()+"\",'price':"+a.getPrice()+
							",'path':\""+a.getPath()+"\"},";
				}
				if(back.length()>1) {
					back=back.substring(0, back.length()-1);
					Pattern p = Pattern.compile("\\s*|\t|\r|\n");
					Matcher m = p.matcher(back);
					back = m.replaceAll("");
				}
				
				back=back+"]";
		        
			}
			else {
				
				if(type.equals("4")) {/*活动详情*/
					String aid=request.getParameter("aid");
					if (null != cookies) {
						for (int i = 0; i < cookies.length; i++) {
							cookie = cookies[i];
							if (cookie.getName().equals("cur_act_id")) {
								cookie.setValue(aid);
								cookie.setMaxAge(Integer.MAX_VALUE);
								response.addCookie(cookie);
								find1=true;
							}
							
						}
					}
					if(!find1) {
						Cookie c1 = new Cookie("cur_act_id", aid);
						c1.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(c1);
					}
					orderservice.checkLate();
					System.out.println("AID IS"+Integer.parseInt(aid)+"like");
					Activity act=actservice.getByID(Integer.parseInt(aid));
					System.out.println("ACT IS"+act==null);
					ActBean actbean=new ActBean();
					actbean.setAct(act);
					session.setAttribute("act", actbean);
					if(act.isSelect()) {
						result="2";
					}
					else {
						result="1";
					}
					
				}
				else {
					String city="全国";
					String so="全部演出";
					String inp="";
					ArrayList<Activity> actlist=new ArrayList<Activity>();
					ActListBean actlistbean=new ActListBean();
					if(type.equals("1")) {/*选择城市和类型查找*/
						city=request.getParameter("city");
						so=request.getParameter("sort");
						actlist=actservice.selectByCond(city, so, 0);
						System.out.println(city+so+actlist.size());
					}
					else if(type.equals("2")) {/*根据场馆或活动名查找*/
						inp=request.getParameter("inp");
						actlist=actservice.selectByNameOrVen(inp, 0);
					}
					else if(type.equals("3")) {/*更多*/
						String num=request.getParameter("sort");
						int index=number.indexOf(num);
						so=sorts.get(index);
						actlist=actservice.selectByCond(city, so, 0);
					}
					else {
						result="FAIL";
					}
					actlistbean.setActList(actlist);
					session.setAttribute("actlist", actlistbean);
					if (null != cookies) {
						for (int i = 0; i < cookies.length; i++) {
							cookie = cookies[i];
							if (cookie.getName().equals("act_select_type")) {
								System.out.println("SO:"+so);
								cookie.setValue(so);
								cookie.setMaxAge(Integer.MAX_VALUE);
								response.addCookie(cookie);
								find1=true;
							}
							if (cookie.getName().equals("act_select_offset")) {
								cookie.setValue(""+5);
								cookie.setMaxAge(Integer.MAX_VALUE);
								response.addCookie(cookie);
								find2=true;
							}
							if (cookie.getName().equals("act_select_place")) {
								cookie.setValue(city);
								cookie.setMaxAge(Integer.MAX_VALUE);
								response.addCookie(cookie);
								find3=true;
							}
							if (cookie.getName().equals("act_select_input")) {
								cookie.setValue(inp);
								cookie.setMaxAge(Integer.MAX_VALUE);
								response.addCookie(cookie);
								find4=true;
							}
						}
					}
					if(!find1) {
						Cookie c2 = new Cookie("act_select_type", so);
						c2.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(c2);
					}
					if(!find2) {
						Cookie c3 = new Cookie("act_select_offset", ""+5);
						c3.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(c3);
					}
					if(!find3) {
						Cookie c4 = new Cookie("act_select_place", city);
						c4.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(c4);
					}
					if(!find4) {
						Cookie c5 = new Cookie("act_select_input", inp);
						c5.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(c5);
					}
				}
				
		        back = "[{'answer':'"+result+"'}]";
		        System.out.println(back);
		        JSONArray json = new JSONArray(back);
		        PrintWriter writer = response.getWriter();
		        writer.print(json);
		        writer.flush();
		        writer.close();
			}
			
			System.out.println(back);
	        JSONArray json = new JSONArray(back);
	        PrintWriter writer = response.getWriter();
	        writer.print(json);
	        writer.flush();
	        writer.close();		
		
	}
	
}
