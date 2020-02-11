package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;

import business.ActBean;
import business.ActListBean;
import business.AppListBean;
import business.HallBean;
import factory.ServiceFactory;
import model.Activity;
import model.App;
import model.Hall;
import model.Seat;
import model.VenueStatistic;
import service.ActService;
import service.OrderService;
import service.VenueService;


/**
 * Servlet implementation class StockListServlet
 */
@WebServlet("/VenueInfo")
public class VenueInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VenueService venueservice;
	private ActService actservice;
	private OrderService orderservice;
	private ServiceFactory factory;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VenueInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		factory=ServiceFactory.getInstance();
        venueservice=factory.getVenueService();
        actservice=factory.getActService();
        orderservice=factory.getOrderService();
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
        System.out.println("IN SERVLET");
		HttpSession session = request.getSession(false);
		String username="";
		boolean cookieFound=false;
		String result="SUCCESS";
		String back="";
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
		System.out.println("===============================================cookie is"+username+"=============================");
		int t=0;
		if(type==null) {
			t=10;
		}
		else {
			t=Integer.parseInt(type);
		}
		
		System.out.println("GET T IS"+t);
		if(t<=3) {/*场馆统计信息*/
			
			String SD=request.getParameter("SD");
		    String ED=request.getParameter("ED");
			
		    VenueStatistic info=venueservice.getStatistic(SD, ED, Integer.parseInt(username));
			back="[";
			if(t==1) {
				//top ten
				ArrayList<String> top=info.getTop_ten();
				for(int i=0;i<top.size();i++) {
					String s[]=top.get(i).split("=");
					back=back+"{'name':'"+s[0]+"','money':'"+s[1]+"'},";
				}
				
			}
			else if(t==2) {
				//order
				ArrayList<String> kind=info.getOrder_kind();
				ArrayList<Integer> num=info.getOrderNum();
				for(int i=0;i<num.size();i++) {
					back=back+"{'kind':'"+kind.get(i)+"','num':'"+num.get(i)+"'},";
				}
				
			}
			else {
				ArrayList<String> date=info.getDate();
				ArrayList<Double> money=info.getConsum();
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
	        
			
		}
		else if(t==6){
			int o=0;
			if (null != cookies) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("venue_act_offset")) {
						o=Integer.parseInt(cookie.getValue());
						cookie.setValue(""+(o+6));
						cookie.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(cookie);
					}
				}
			}
			ArrayList<Activity> list1=venueservice.getAll(o, Integer.parseInt(username));
			back="[";
			for(int i=0;i<list1.size();i++) {
				Activity a=list1.get(i);
				back=back+"{'id':'"+a.getId()+"','name':'"+a.getName()+"','s_time':'"+a.getFirst()+"','e_time':'"+a.getLast()+
						"','vname':'"+a.getVname()+"','city':'"+a.getCity()+"','price':'"+a.getPrice()+
						"','path':'"+a.getPath()+"','state':'"+a.getState()+"'},";
			}
			if(back.length()>1) {
				back=back.substring(0, back.length()-1);
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(back);
				back = m.replaceAll("");
			}
			back=back+"]";
	        
		}
		else if(t==9){/*活动统计信息*/
			int aid=0;
			if (null != cookies) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("v_cur_aid")) {
						aid=Integer.parseInt(cookie.getValue());
						
					}
				}
			}
			ArrayList<String> list1=venueservice.getAct(aid);
			back="[";
			for(int i=0;i<list1.size();i++) {
				String s[]=list1.get(i).split("=");
				back=back+"{'date':'"+s[0]+"','consum':'"+s[1]+"'},";
			}
			if(back.length()>1) {
				back=back.substring(0, back.length()-1);
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(back);
				back = m.replaceAll("");
			}
			back=back+"]";
	        
		}
		else if(t==15){
			int offset=0;
			if (null != cookies) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("venue_app_offset")) {
						offset=Integer.parseInt(cookie.getValue());
						cookie.setValue(""+(offset+6));
						cookie.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(cookie);
					}
				}
			}
			ArrayList<App> list1=venueservice.getVenueApp(Integer.parseInt(username), offset);
			back="[";
			for(int i=0;i<list1.size();i++) {
				App a=list1.get(i);
				back=back+"{'id':'"+a.getId()+"','date':'"+a.getDate()+"','res':'"+a.getState()+"'},";
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
			if(t==4) {
				int aid=Integer.parseInt(request.getParameter("type"));
				cookieFound=false;
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("v_cur_aid")) {
						cookieFound = true;
						cookie.setValue(""+aid);
						cookie.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(cookie);
					}
					
				}
				if(!cookieFound) {
					Cookie c1 = new Cookie("v_cur_aid", aid+"");
					c1.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(c1);
				}
				result="SUCCESS";
			}
			else if(t==7) {/*现场售票*/
				//user(0表示非会员）:aid:time:realc-realr-seatc-seatr-price:返回应付钱数
				String s=request.getParameter("par");
				System.out.println("GET PARA:"+s);
				String par=s.substring(0, s.length()-1);
				result=actservice.sell(par)+"";
				String aid="";
				if (null != cookies) {
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if (cookie.getName().equals("cur_act_id")) {
							aid=cookie.getValue();
						}
						
					}
				}
				
				
				Activity act=actservice.getByID(Integer.parseInt(aid));
				System.out.println("ACT IS"+act==null);
				ActBean actbean=new ActBean();
				actbean.setAct(act);
				session.setAttribute("act", actbean);
				System.out.println("AFTER SELL UPDATE THE ACT:"+actbean.getAct().toString());
				System.out.println("THE ACT HAS HALL:"+actbean.getAct().getHalls().size());
				
			}
			else if(t==8) {
				String aid=request.getParameter("aid");
				String s=request.getParameter("par");
				System.out.println("GET OID"+s);
				result=orderservice.checkIn(Integer.parseInt(s),Integer.parseInt(aid));
				if(result.equals("SUCCESS")) {
					result="检票成功！";
				}
				else {
					result="检票失败";
				}
				Activity act=actservice.getByID(Integer.parseInt(aid));
				System.out.println("ACT IS"+act==null);
				ActBean actbean=new ActBean();
				actbean.setAct(act);
				session.setAttribute("act", actbean);
				System.out.println("AFTER SELL UPDATE THE ACT:"+actbean.getAct().toString());
				System.out.println("THE ACT HAS HALL:"+actbean.getAct().getHalls().size());
			}
			else if(t==10){//上传新活动信息
				
				String path="";
				int id=actservice.getNewAid();
				if (ServletFileUpload.isMultipartContent(request)) {  
					  
			        try {  
			        	DiskFileItemFactory factoy=new DiskFileItemFactory();
			        	ServletFileUpload sfu=new ServletFileUpload(factoy);
				        sfu.setSizeMax(10 * 1024 * 1024);// 以byte为单位 不能超过10M 1024byte =  
				        sfu.setHeaderEncoding("utf-8");  
				  
				        // 3.  
				        // 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。  
				        List<FileItem> fileItemList = sfu.parseRequest(request);  
				        Iterator<FileItem> fileItems = fileItemList.iterator();  
				  
				        while (fileItems.hasNext()) {  
				            FileItem fileItem = fileItems.next();  
				            // <input type="file">的上传文件的元素  
				            if (!fileItem.isFormField()) {  
					            String fileName = fileItem.getName();// 文件名称  
					            String suffix = fileName.substring(fileName.lastIndexOf('.'));  
					            fileName=id+suffix;
					  
					            File file = new File("E:/workspace/mask/WebContent/image/" + fileName); 
					            path="./image/"+fileName;
					            System.out.println(file.getAbsolutePath());  
					            fileItem.write(file);  
					  
					            // 6. 调用FileItem的delete()方法，删除临时文件  
					            fileItem.delete(); 
					            
				            }  
				        } 
				        String a_name=fileItemList.get(1).getString("utf-8");//活动名称
				        System.out.println("NAME IS"+a_name);
						String a_hall=fileItemList.get(2).getString("utf-8");//展厅名称
						System.out.println("HALL IS"+a_hall);
						String a_type=fileItemList.get(3).getString("utf-8");//演唱会、音乐会、话剧舞台剧，舞蹈芭蕾、儿童亲子、戏曲综艺、休闲娱乐、体育赛事
						System.out.println("TYPE IS"+a_type);
						String a_s_year=fileItemList.get(4).getString("utf-8");
						System.out.println("S YEAR IS "+a_s_year);
						String a_s_month=fileItemList.get(5).getString("utf-8");
						System.out.println("S MONTH IS"+a_s_month);
						String a_s_day=fileItemList.get(6).getString("utf-8");
						System.out.println("S DAY IS"+a_s_day);
						String a_e_year=fileItemList.get(7).getString("utf-8");
						System.out.println("E YEAR IS"+a_e_year);
						String a_e_month=fileItemList.get(8).getString("utf-8");
						System.out.println("E MONTH IS"+a_e_month);
						String a_e_day=fileItemList.get(9).getString("utf-8");
						System.out.println("E DAY IS"+a_e_day);
						String a_sale=fileItemList.get(11).getString("utf-8");//选座购票、系统配票
						System.out.println("SALE IS"+a_sale);
						String a_des=fileItemList.get(12).getString("utf-8");//活动描述
						System.out.println("DES IS"+a_des);
						String priceinfo=fileItemList.get(14).getString("utf-8");//j价位信息
						System.out.println("PRICEINFO IS"+priceinfo);
						String time=fileItemList.get(10).getString("utf-8");
						System.out.println("TIME IS"+time);
						
						result=actservice.publish(id, a_name, a_hall, a_type, a_s_year, a_s_month, a_s_day, a_e_year, a_e_month, a_e_day, a_sale, a_des,path,priceinfo,Integer.parseInt(username),time);
						ArrayList<Activity> act=venueservice.getAll(0, Integer.parseInt(username));
			        	ActListBean actlist=new ActListBean();
			        	actlist.setActList(act);
			        	System.out.println("AFTER PUBLISH UPDATE THE ACT LIST");
			        	session.setAttribute("vactlist", actlist);
			        } catch (FileUploadException e) {  
			        	e.printStackTrace();  
			        } catch (Exception e) {  
			        	e.printStackTrace();  
			        }  
			  
			    }  
				
	        	request.getRequestDispatcher("/venue_act.jsp").forward(request, response);  
			}
			else if(t==11) {
				String name=request.getParameter("name");
				String pwd=request.getParameter("pwd");
				String phone=request.getParameter("phone");
				String pay_id=request.getParameter("pay_id");
				String pro=request.getParameter("pro");
				String city=request.getParameter("city");
				String dis=request.getParameter("dis");
				String det=request.getParameter("det");
				result=venueservice.modVenue(name, pwd, phone, pay_id, pro, city, dis, det,Integer.parseInt(username));
				ArrayList<App> app=venueservice.getVenueApp(Integer.parseInt(username), 0);
				AppListBean applist=new AppListBean();
				applist.setAppList(app);
				System.out.println("UPDATE VENUE APP:"+applist.getAppList().size());
				session.setAttribute("vapplist", applist);
			}
			else if(t==12) {
				int id=Integer.parseInt(request.getParameter("id"));
				result=venueservice.deleteHall(id);
				ArrayList<App> app=venueservice.getVenueApp(Integer.parseInt(username), 0);
				AppListBean applist=new AppListBean();
				applist.setAppList(app);
				System.out.println("UPDATE VENUE APP:"+applist.getAppList().size());
				session.setAttribute("vapplist", applist);
			}
			else if(t==13) {
				System.out.println("IN DRAW METHOD");
				String input=request.getParameter("input");
				System.out.println("GET IN PUT"+input);
				String name=request.getParameter("name");
				System.out.println("GET NAME"+name);
				String num=request.getParameter("num");
				System.out.println("GET NUM"+num);
				Hall hall=new Hall();
				hall.from_save_form(input);
				System.out.println("SUCCESSFULLY SET THE SEAT"+hall.to_save_form());
				hall.setName(name);
				hall.setSeatNum(Integer.parseInt(num));
				if(!(username==null||username.equals(""))) {
					hall.setVid(Integer.parseInt(username));
					System.out.println("ADD VID"+hall.getVid());
				}
				
				HallBean hallbean=new HallBean();
				hallbean.setHall(hall);
				hallbean.setOriginal(input);
				session.setAttribute("new_hall", hallbean);
				System.out.println("UPDATE NEW_HALL INFO");
				result="SUCCESS";
			}
			else if(t==14) {
				System.out.println("IN DRAW METHOD");
				String input=request.getParameter("input");
				System.out.println("GET IN PUT"+input);
				String name=request.getParameter("name");
				System.out.println("GET NAME"+name);
				String num=request.getParameter("num");
				System.out.println("GET NUM"+num);
				Hall hall=new Hall();
				hall.from_save_form(input);
				System.out.println("SUCCESSFULLY SET THE SEAT"+hall.to_save_form());
				hall.setName(name);
				hall.setSeatNum(Integer.parseInt(num));
				if(!(username==null||username.equals(""))) {
					hall.setVid(Integer.parseInt(username));
					System.out.println("ADD VID"+hall.getVid());
				}
				else {
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if (cookie.getName().equals("v_cur_reg")) {
							hall.setVid(Integer.parseInt(cookie.getValue()));
						}
						
					}
				}
				HallBean hallbean=new HallBean();
				hallbean.setHall(hall);
				hallbean.setOriginal(input);
				session.setAttribute("new_hall", hallbean);
				System.out.println("UPDATE NEW_HALL INFO");
				result=venueservice.saveAHall(hall);
			}
			else if(t==16) {
				String name=request.getParameter("name");
				String pwd=request.getParameter("pwd");
				String phone=request.getParameter("phone");
				String pay_id=request.getParameter("pay_id");
				String pro=request.getParameter("pro");
				String city=request.getParameter("city");
				String dis=request.getParameter("dis");
				String det=request.getParameter("det");
				int vid=venueservice.addVenue(name, pwd, phone, pay_id, pro, city, dis, det);
				Hall hall=new Hall();
				hall.setSeats(new Seat[0][0]);
				HallBean hallbean=new HallBean();
				hallbean.setHall(hall);
				hallbean.setOriginal("");
				session.setAttribute("new_hall", hallbean);
				cookieFound=false;
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals("v_cur_reg")) {
						cookieFound = true;
						cookie.setValue(""+vid);
						cookie.setMaxAge(Integer.MAX_VALUE);
						response.addCookie(cookie);
					}
					
				}
				if(!cookieFound) {
					Cookie c1 = new Cookie("v_cur_reg", vid+"");
					c1.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(c1);
				}
			}
			back = "[{'answer':'"+result+"'}]";
	        
		}
		System.out.println(back);
        JSONArray json = new JSONArray(back);
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();	
	}
	
}
