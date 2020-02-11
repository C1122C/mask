package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import business.CurOrderBean;
import business.OrderListBean;
import business.PayBean;
import factory.ServiceFactory;
import model.Activity;
import model.Coupon;
import model.Order;
import service.ActService;
import service.CouponService;
import service.OrderService;
import util.ORDER_STATE;



/**
 * Servlet implementation class Login
 */
@WebServlet("/UserOrder")
public class UserOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActService actservice;
	private CouponService couponservice;
	private OrderService orderservice;
	private ServiceFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOrder() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	factory=ServiceFactory.getInstance();
        actservice=factory.getActService();
        couponservice=factory.getCouponService();
        orderservice=factory.getOrderService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        HttpSession session = request.getSession(false);
		String username="";
		String result="SUCCESS";
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		
		if (null != cookies) {
			
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("user")) {
					username=cookie.getValue();
				}
				
			}
		}

			String type = request.getParameter("type");
			
	        if(type.equals("1")||type.equals("10")) {/*1 vote选择结束*/
	        	int aid=0;
	        	int oid=0;
	        	String time="";
	        	String sum="";
	        	String seat="";
	        	if(type.equals("1")) {
	        		aid=Integer.parseInt(request.getParameter("id"));
		        	time=request.getParameter("time");
		        	String tic=request.getParameter("tic");
		        	String num=request.getParameter("num");
		        	sum=request.getParameter("sum");
		        	oid=actservice.buyTicket(aid, false, time+":"+tic+":"+num+":"+sum,username);
		        	if(oid<0) {
		        		result="FAIL";
		        	}
		        	seat="请等待配票结果";
	        	}
	        	else {
	        		oid=Integer.parseInt(request.getParameter("id"));
	        		Order oo=orderservice.getByID(oid);
	        		aid=oo.getActid();
	        		time=oo.getActTime();
	        		sum=oo.getSum()+"";
	        		if(oo.getSeatX().size()==1) {
	        			seat=oo.getSeatX().get(0)+"排"+oo.getSeatY().get(0)+"座";
	        		}
	        		else {
	        			seat=oo.getSeatX().get(0)+"排"+oo.getSeatY().get(0)+"座"+"等";
	        		}
	        	}
	        	Activity a=actservice.getByID(aid);
	        	ArrayList<Coupon> coupons=couponservice.getUseableCoupon(username, Double.parseDouble(sum));
	        	CurOrderBean cb=new CurOrderBean();
	        	cb.setOid(oid);
	        	cb.setAid(aid);
	        	cb.setUid(username);
	        	cb.setState(ORDER_STATE.UNPAY);
	        	cb.setCreate_time(new Date());
	        	cb.setAct_name(a.getName());
	        	cb.setAct_time(time);
	        	cb.setVname(a.getVname());
	        	cb.setSum(Double.parseDouble(sum));
	        	cb.setCoupon(coupons);
	        	cb.setHall_name(a.getHname());
	        	cb.setSeat(seat);
	        	session.setAttribute("order",cb);
	        	
	        }
	        else if(type.equals("2")) {/*选座结束*/
	        	System.out.println("ORIGINAL PARA:"+request.getParameter("par"));
	        	String par[]=request.getParameter("par").split(":");
	        	String time=par[1];
	        	System.out.println("TIME:"+time);
	        	String sum=par[par.length-1];
	        	System.out.println("SUM:"+sum);
	        	String in[]=par[2].split("-");
	        	int aid=Integer.parseInt(par[0]);
	        	System.out.println("AID:"+aid);
	        	String info=request.getParameter("par");
	        	int oid=actservice.buyTicket(aid, true,info,username);
	        	Activity a=actservice.getByID(aid);
	        	
	        	ArrayList<Coupon> coupons=couponservice.getUseableCoupon(username, Double.parseDouble(sum));
	        	CurOrderBean cb=new CurOrderBean();
	        	cb.setOid(oid);
	        	System.out.println("OID:"+oid);
	        	cb.setAid(aid);
	        	cb.setUid(username);
	        	cb.setState(ORDER_STATE.UNPAY);
	        	cb.setCreate_time(new Date());
	        	cb.setAct_name(a.getName());
	        	cb.setAct_time(time);
	        	cb.setVname(a.getVname());
	        	cb.setSum(Double.parseDouble(sum));
	        	cb.setCoupon(coupons);
	        	cb.setHall_name(a.getHname());
	        	cb.setSeat(in[3]+"排"+in[2]+"座等"+(par.length-3)+"个座位");
	        	session.setAttribute("order",cb);
	        	
	        }
	        else if(type.equals("3")) {/*连接支付平台支付*/
	        	String in="";
	        	String par[]=request.getParameter("par").split(":");
	        	int oid=Integer.parseInt(par[0]);
	        	int min=Integer.parseInt(par[1]);
	        	int sec=Integer.parseInt(par[2]);
	        	double sum=Double.parseDouble(par[par.length-1]);
	        	ArrayList<Integer> cid=new ArrayList<Integer>();
	        	ArrayList<Double> mon=new ArrayList<Double>();
	        	System.out.println("PAR3"+par[3]+"like");
	        	if(par[3].length()>=3) {
	        		for(int i=3;i<par.length-1;i++) {
		        		String s[]=par[i].split("-");
		        		cid.add(Integer.parseInt(s[0]));
		        		in=in+s[0];
		        		if(i!=par.length-2) {
		        			in=in+"-";
		        		}
		        		mon.add(Double.parseDouble(s[1]));
		        	}
	        	}
	        	
	        	PayBean pb=new PayBean();
	        	pb.setCids(cid);
	        	pb.setMin(min);
	        	pb.setMons(mon);
	        	pb.setOid(oid);
	        	pb.setSec(sec);
	        	pb.setSum(sum);
	        	pb.setInfo(in);
	        	session.setAttribute("pay",pb);
	        	result="SUCCESS";
	        }
	        else if(type.equals("4")||type.equals("9")) {/*取消订单*/
	        	int oid=Integer.parseInt(request.getParameter("id"));
	        	result=orderservice.cancleOrder(oid);
	        	ArrayList<Order> o=orderservice.getOrder(0, username, "");
	        	OrderListBean orderlist=new OrderListBean();
	        	orderlist.setOrderList(o);
	        	session.setAttribute("orderlist",orderlist);
	        	
	        }
	        else if(type.equals("5")) {//订单号：账号：密码：分：秒：pay.getInfo：总计钱数/*用户付款*/
	        	String par[]=request.getParameter("par").split(":");
	        	System.out.println("PAY ORIGINAL PARA:"+request.getParameter("par"));
	        	int oid=Integer.parseInt(par[0]);
	        	String id=par[1];
	        	String pwd=par[2];
	        	int min=Integer.parseInt(par[3]);
	        	int sec=Integer.parseInt(par[4]);
	        	double sum=Double.parseDouble(par[par.length-1]);
	        	System.out.println("PAR5"+par[5]+"like");
	        	if(par[5].indexOf("no")<0) {
	        		String cids[]=par[5].split("-");
		        	for(int i=0;i<cids.length;i++) {
		        		int cid=Integer.parseInt(cids[i]);
		        		result=couponservice.use(cid);
		        	}
		        	ArrayList<Coupon> c_mine=couponservice.getAllCoupon(username, 0);
					CouponListBean couponbean=(CouponListBean) session.getAttribute("couponlist");
					couponbean.setAlreadyHave(c_mine);
					session.setAttribute("couponlist",couponbean);
	        	}
	        	
	        	result=orderservice.userPay(id, pwd, sum, oid,min,sec);
	        	if(result.equals("SUCCESS")) {
	        		ArrayList<Order> o=orderservice.getOrder(0, username, "");
		        	OrderListBean orderlist=new OrderListBean();
		        	orderlist.setOrderList(o);
		        	session.setAttribute("orderlist",orderlist);
	        	}
	        	
	        }
	        else if(type.equals("12")) {/*刷新*/
	        	ArrayList<Order> o=orderservice.getOrder(0, username, "");
		        OrderListBean orderlist=new OrderListBean();
		        orderlist.setOrderList(o);
		        session.setAttribute("orderlist",orderlist);
	        	
	        	
	        }
	        else if(type.equals("11")) {/*reset cookie*/
	        	if (null != cookies) {
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if (cookie.getName().equals("order_select_type")) {
							cookie.setValue("");
							cookie.setMaxAge(Integer.MAX_VALUE);
							response.addCookie(cookie);
						}
						
					}
				}
	        	
	        }
	        else if(type.equals("6")) {/*删除订单*/
	        	int oid=Integer.parseInt(request.getParameter("id"));
	        	result=orderservice.cancleOrder(oid);
	        	String t="全部订单";
	        	int o=0;
	        	boolean find1=false;
	        	boolean find2=false;
	        	if (null != cookies) {
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if (cookie.getName().equals("order_select_type")) {
							t=cookie.getValue();
							find1=true;
						}
						if (cookie.getName().equals("order_select_offset")) {
							o=Integer.parseInt(cookie.getValue());
							find2=true;
						}
						
					}
				}
	        	if(!find1) {
					Cookie c2 = new Cookie("order_select_type", "");
					c2.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(c2);
				}
				if(!find2) {
					Cookie c3 = new Cookie("order_select_offset", ""+6);
					c3.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(c3);
				}
	        	ArrayList<Order> or=orderservice.getOrder(o, username, t);
	        	OrderListBean orderlist=new OrderListBean();
	        	orderlist.setOrderList(or);
	        	session.setAttribute("orderlist",orderlist);
	        	
	        }
	        else if(type.equals("7")) {//汉字的种类 全部订单 进行中 已完成 已取消
	        	String t="全部订单";
	        	t=request.getParameter("t");
	        	System.out.println("GET TYPE "+t);
	        	boolean find1=false;
	        	boolean find2=false;
	        	if (null != cookies) {
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if (cookie.getName().equals("order_select_type")) {
							cookie.setValue(t);
							System.out.println("RESET COOKIE order_select_type TO "+t);
							cookie.setMaxAge(Integer.MAX_VALUE);
							response.addCookie(cookie);
							find1=true;
						}
						if (cookie.getName().equals("order_select_offset")) {
							cookie.setValue(6+"");
							System.out.println("RESET COOKIE order_select_offset TO 6");
							cookie.setMaxAge(Integer.MAX_VALUE);
							response.addCookie(cookie);
							find2=true;
						}
						
					}
				}
	        	if(!find1) {
					Cookie c2 = new Cookie("order_select_type", t);
					c2.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(c2);
					System.out.println("CREATE COOKIE order_select_type TO "+t);
				}
				if(!find2) {
					Cookie c3 = new Cookie("order_select_offset", ""+6);
					c3.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(c3);
					System.out.println("CREATE COOKIE order_select_offset TO 6");
				}
				System.out.println("ABOUT TO GET SELECTED ORDER");
	        	ArrayList<Order> or=orderservice.getOrder(0, username, t);
	        	OrderListBean orderlist=new OrderListBean();
	        	orderlist.setOrderList(or);
	        	System.out.println("UPDATE THE ORDERBEAN "+orderlist.getOrderList().size());
	        	session.setAttribute("orderlist",orderlist);
	        	
	        }
	        else if(type.equals("8")) {/*更多订单*/
	        	String t="";
	        	int o=6;
	        	if (null != cookies) {
					for (int i = 0; i < cookies.length; i++) {
						cookie = cookies[i];
						if (cookie.getName().equals("order_select_type")) {
							t=cookie.getValue();
						}
						if (cookie.getName().equals("order_select_offset")) {
							o=Integer.parseInt(cookie.getValue());
							cookie.setValue(o+6+"");
							cookie.setMaxAge(Integer.MAX_VALUE);
							response.addCookie(cookie);
						}
						
					}
				}
	        	
	        	ArrayList<Order> or=orderservice.getOrder(o, username, t);
	        	String back="[";
				for(int i=0;i<or.size();i++) {
					Order ord=or.get(i);
					back=back+"{'id':'"+ord.getId()+"','state':'"+ord.getState()+"','c_time':'"+ord.getCreateDate()+"','aid':'"+ord.getActid()+
							"','path':'"+ord.getPath()+"','a_name':'"+ord.getActName()+"','a_time':'"+ord.getActTime()+
							"','v_name':'"+ord.getVenueName()+"','sum':'"+ord.getSum()+"','judge':'"+ord.getJudgeState()+"'},";
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
		
        
        String back = "[{'answer':'"+result+"'}]";
        System.out.println(back);
        JSONArray json = new JSONArray(back);
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();
     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
