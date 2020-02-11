package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import business.AppBean;
import business.AppListBean;
import factory.ServiceFactory;
import model.Activity;
import model.App;
import model.PUserStatistic;
import service.ActService;
import service.PlatStatisticService;
import service.VenueService;
import util.APP_STATE;
import util.APP_TYPE;


/**
 * Servlet implementation class StockListServlet
 */
@WebServlet("/ManagerInfo")
public class ManagerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VenueService venueservice;
	private ActService actservice;
	private PlatStatisticService platservice;
	private ServiceFactory factory;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		factory=ServiceFactory.getInstance();
        venueservice=factory.getVenueService();
        actservice=factory.getActService();
        platservice=factory.getPlatStatisticService();
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
		String result="SUCCESS";
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		String type=request.getParameter("type");
		int t=Integer.parseInt(type);
		if(t<=4) {
			String SD = request.getParameter("SD");
			String ED = request.getParameter("ED");
		    
			
			String back="[";
			if(t==1) {
				//top ten ven
				ArrayList<String> topv=platservice.topTenVen(SD, ED);
				for(int i=0;i<topv.size();i++) {
					String s[]=topv.get(i).split("=");
					back=back+"{'name':'"+s[0]+"','money':'"+s[1]+"'},";
				}
				
			}
			else if(t==2) {
				//top ten act
				ArrayList<String> top=platservice.topTenAct(SD, ED);
				for(int i=0;i<top.size();i++) {
					String s[]=top.get(i).split("=");
					back=back+"{'name':'"+s[0]+"','money':'"+s[1]+"'},";
				}
				
			}
			else if(t==3) {
				//user
				PUserStatistic user=platservice.getUserInfo(SD, ED);
				ArrayList<String> sort=user.getType();
				ArrayList<Integer> num=user.getNum();
				for(int i=0;i<sort.size();i++) {
					back=back+"{'sum':'"+user.getSum()+"','add':'"+user.getAdd()+"','lose':'"+user.getLose()+"','sort':'"
							+sort.get(i)+"','num':'"+num.get(i)+"'},";
				}
				
			}
			else {//cur
				ArrayList<String> cur=platservice.getPlatCurve(SD, ED);
				for(int i=0;i<cur.size();i++) {
					String s[]=cur.get(i).split("=");
					back=back+"{'date':'"+s[0]+"','money':'"+s[1]+"','sum':'"+s[2]+"'},";
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
		else if(t==5){
			int o=0;
			if (null != cookies) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("manager_act_offset")) {
						o=Integer.parseInt(cookie.getValue());
						cookie.setValue(""+(o+6));
						cookie.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(cookie);
					}
				}
			}
			ArrayList<Activity> list1=actservice.getPassed(o);
			String back="[";
			for(int i=0;i<list1.size();i++) {
				Activity a=list1.get(i);
				back=back+"{'id':'"+a.getId()+"','name':'"+a.getName()+"','total':'"+a.getTotal()+
						"','path':'"+a.getPath()+"','state':'"+a.getState()+"'},";
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
		else if(t==10){
			int offset=0;
			if (null != cookies) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("manager_app_offset")) {
						offset=Integer.parseInt(cookie.getValue());
						cookie.setValue(""+(offset+6));
						cookie.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(cookie);
					}
				}
			}
			ArrayList<App> list1=venueservice.getApplication(offset);
			String back="[";
			for(int i=0;i<list1.size();i++) {
				App a=list1.get(i);
				back=back+"{'id':'"+a.getId()+"','type':'"+a.getType()+"','name':'"+a.getOrg()+"','res':'"+a.getState()+"'},";
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
			if(t==6) {
				int aid=Integer.parseInt(request.getParameter("id"));
				result=actservice.settle(aid);
				ArrayList<Activity> act=actservice.getPassed(0);
	        	ActListBean actlist=new ActListBean();
	        	actlist.setActList(act);
	        	System.out.println("UPDATE MANAGER ACT:"+actlist.getActList().size());
	        	session.setAttribute("mactlist", actlist);
			}
			else if(t==7) {
				int aid=Integer.parseInt(request.getParameter("aid"));
				App a=venueservice.getAppByID(aid);
				if(a.getType().equals(APP_TYPE.MOD_VENUE.getName())||a.getType().equals(APP_TYPE.REG_VENUE.getName())) {
					result="v";
				}
				else {
					result="h";
				}
				AppBean appbean=new AppBean();
				appbean.setApp(a);
				session.setAttribute("cur_app", appbean);
			}
			else if(t==8) {
				System.out.println("IN DOWN SETVLET-------------------------");
				int aid=Integer.parseInt(request.getParameter("aid"));
				result=venueservice.modApplication(aid, APP_STATE.DOWN.getName());
				ArrayList<App> app=venueservice.getApplication(0);
				AppListBean applist=new AppListBean();
				applist.setAppList(app);
				session.setAttribute("mapplist", applist);
				
			}
			else if(t==9) {
				System.out.println("IN PASS SETVLET-------------------------");
				int aid=Integer.parseInt(request.getParameter("aid"));
				result=venueservice.modApplication(aid, APP_STATE.PASS.getName());
				ArrayList<App> app=venueservice.getApplication(0);
				AppListBean applist=new AppListBean();
				applist.setAppList(app);
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
	
}
