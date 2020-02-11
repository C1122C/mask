<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<jsp:useBean id="city" type="business.CityBean" scope="session"></jsp:useBean>
<jsp:useBean id="user" type="business.UserInfoBean" scope="session"></jsp:useBean>
<jsp:useBean id="orderlist" type="business.OrderListBean" scope="session"></jsp:useBean>
<jsp:useBean id="username" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_login.tld" prefix="MyTag" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/user.js"></script>
		<script src="JS/act.js"></script>
		<script src="JS/coupon.js"></script>
		<script src="JS/order.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
		        refresh();
	        	$("body").on('click','.city_bt',function(){
	        		var city=$(this).html();
	        		$("#location").html(city);
	        		var t=$("#act_type").html();
	        		$("#loading").fadeIn(500);
	        		change_select(city,t);
	        		$("#loading").fadeOut(500);
	            });
	        	$("body").on('click','.xl-r',function(){
	        		var t=$(this).html();
	        		$("#act_type").html(t);
	        		var city=$("#location").html();
	        		$("#loading").fadeIn(500);
	        		change_select(city,t);
	        		$("#loading").fadeOut(500);
	            });	
	        	
	        	
	        	$("body").on('mouseover','.over_show',function(){
		        	//alert("in");
	                $(this).next().show();
	            });	
	        	$("body").on('mouseleave','.bye',function(){
	        		//alert("in");
	                $(this).hide();
	            });
	        	$("body").on('click','#search_icon',function(){
	                var key=document.getElementById("search_input").value;
	                if(key==null||key==""){
	                    alert("请输入关键字");
	                }
	                else{
	                	$("#location").html("全国");
	                	$("#act_type").html("全部演出");
	                    //alert(key);
	                    $("#loading").fadeIn(500);
	                    select_by_input(key);
	                    $("#loading").fadeOut(500);
	                }
	            });
	        		
	        	$("body").on('click','#logout',function(){
	        		$("#loading").fadeIn(500);
	        		logout();
	        		$("#loading").fadeOut(500);
	            });

	        	$("body").on('click','#delete',function(){
	        		$("#loading").fadeIn(500);
	        		delete_account();
	        		$("#loading").fadeOut(500);
	            });	
	        	

	        	$("body").on('click','.detail',function(){
	        		var id=$(this).attr("name");
	        		$("#loading").fadeIn(500);
	        		a_detail(id);
	        		$("#loading").fadeOut(500);
	            });	
	        	
	        	$("body").on('click','.Quxiao',function(){
	        		var id=$(this).attr("name");
	        		$("#loading").fadeIn(500);
	        		cancle_order(id);
	        		$(this).parent().hide();
	        		$("#loading").fadeOut(500);
	            });

	        	$("body").on('click','.pay1',function(){
	        		var id=$(this).attr("name");
	        		$("#loading").fadeIn(500);
	        		pay_order(id);
	        		$("#loading").fadeOut(500);
	            });
	        	
	        	$("body").on('click','.type',function(){
	                $(this).addClass("LI-on");
	                $(this).siblings().removeClass("LI-on");
	                var t=$(this).html();
	               	$("#loading").fadeIn(500);
	                select_order(t);
	                $("#loading").fadeOut(500);
	            });
	        	
	        	$(window).scroll(function(){
	                if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
	                    $("#loading").fadeIn(500);
	                    more_order();
	                    $("#loading").fadeOut(500);
	                }
	            });
	        	$(window).bind('beforeunload',function(){
	        		reset("order_select_offset");
	        		order_reset();
	        	});
	        	
	        });
    	</script>
	</head>
	<body>
		
	<MyTag:checkSession />
	<header class="hed hed1">
        <div class="hed w">
            <a href="./index.jsp"><span class="logo" style="margin-bottom: -15px;"></span></a>
            <div class="l hed-cshi">    
                <a class="over_show" id="location">全国</a>
                
                <ul class="chengshi bye" style="display:none;">
	                <%for(int i=0;i<city.getCity().size();i++){ %>
	                      <li><a class="city_bt"><%=city.getCity().get(i) %></a ></li>
	                <%} %>                      
                </ul>
            </div>
            <ul class="l hed-ul">
                <li><a class="older hed-on" href="./user_index.jsp">首页</a></li>
                <li class="mar-r dh-fl">
                    <a class="older over_show" id="act_type">全部演出</a>
                                       
                    <span class="dh-xl xl-xs xl-xs-1 bye" style="display:none;">
                    	<%for(int i=0;i<user.getInterest().size();i++){ %>
                    		<a>
	                            <span class="dh-xl-xl">
	                                <span class="xl-r"><%=user.getInterest().get(i) %></span>
	                            </span>
	                         </a>
	                	<%} %>       
                    </span>
                </li>
                
            </ul>
            
            <div class="r hed-su" onkeydown="keyLogin3(event)">
                <form id="key-search" action="/list/" method="get" role="form">                
                	<input type="text" name="keyword" placeholder="输入演出、场馆名称..." class="l su-ipt" style="margin-top:10px;" id="search_input">
                	<i class="iconfont su-tb" style="height:71px;width:40px;background-image: url(./image/search.png);background-repeat: no-repeat;background-position:0 0;margin-top:12px;" id="search_icon"></i>
                </form>                
                <div class="wode-dongxi">
                    <a class="older hed-on" href="./mine.jsp" style="margin-top:-10px;">个人中心</a>
                </div>
            </div>
        </div>

    </header>
	<div style="height: 150px;"></div>
	<a id="o_t" name="全部订单-0"></a>
	<div class="ol-older">
        <div class="older-w w">
            
            <ul class="older-l l">
                <a><li class="li-co3">个人空间</li></a>
                <a href="./order_scan.jsp"><li class="coF old-li old-li-on"><i class="i-ico">我的订单</i></li></a>
                <a href="./mine.jsp"><li class="old-li"><i class="i-ico">个人信息</i></li></a>
                <a href="./user_statistic.jsp"><li class="old-li"><i class="i-ico">个人统计信息</i></li></a>
                <a href="./coupon.jsp"><li class="old-li"><i class="i-ico">优惠券</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
                <a><li class="old-li"><i class="i-ico" id="delete">删除账号</i></li></a>
            </ul>

            <div class="My-you">
            	<div class="div-xxk">
            		
            		<div class="older-lieb r">
            			<div class="lieb-top">
            				<ul class="top-xuan">
            					<li class="LI-on type">全部订单</li>
            					<li class="LI-on type">进行中</li>
            					<li class="LI-on type">已完成</li>
            					<li class="LI-on type">已取消</li>
            				</ul>
            			</div>
            			<div class="DINGdan">
	            			<div class="lieb-lieb" id="order_box">
	            			<%for(int i=0;i<orderlist.getOrderList().size();i++){%>
                            	<div class="lieb-liebiao">
	            					<div class="liebiao-top">
	            						<span>订单号：</span>
	            						<span><%=orderlist.getOrderList().get(i).getId() %></span>		
	            						<span class="coF"><%=orderlist.getOrderList().get(i).getState() %></span>					
	            						<span><%=orderlist.getOrderList().get(i).getCreateDate() %></span>			
	            					</div>
	            					<div class="liebiao-con">
	            						<div class="con1">
                                            <a class="detail" name=<%=orderlist.getOrderList().get(i).getActid() %>>
	            							<img src=<%=orderlist.getOrderList().get(i).getPath() %> alt=<%=orderlist.getOrderList().get(i).getActName() %> title=<%=orderlist.getOrderList().get(i).getActName() %>/>
                                            </a>
                                            <a>
                                            	<p class="slh con1-tit detail" name=<%=orderlist.getOrderList().get(i).getActid() %>><%=orderlist.getOrderList().get(i).getActName() %></p>
                                            </a>
	            							<p class="con1-sc slh2">时间：<span><%=orderlist.getOrderList().get(i).getActTime() %></span></p>
	            							<p class="con1-sc slh2">场馆：<span><%=orderlist.getOrderList().get(i).getVenueName() %></span></p>
	            						</div>
	            						<div class="con2">
	            							<p class="coF">共计：¥<%=orderlist.getOrderList().get(i).getSum() %></p>
	            						</div>
	            						<%if(orderlist.getOrderList().get(i).getJudgeState()==1){ %>
                                        <div class="con2 bor-r">
                                            <a class="pay1" name=<%=orderlist.getOrderList().get(i).getId() %>>支付</a>
                                        </div>
                                        <%}else if(orderlist.getOrderList().get(i).getJudgeState()==2||orderlist.getOrderList().get(i).getJudgeState()==3){ %>
                                        <div class="con2 bor-r">
                                            <a class="Quxiao" name=<%=orderlist.getOrderList().get(i).getId() %>>取消</a>
                                        </div>
                                        <%} %>
                                    </div>
	            				</div>
                              <%} %>      	            				
	            				
                            </div>
	            		</div>

            		</div>

        		</div>
    		</div>
        </div>
	</div>
	<div class="modal-container" style="display:none;" id="little_board">
		<div class="modal">
		    <span class="icon"></span>
		
		    <p class="tip" id="message">已为您取消订单</p>
		
		</div>
    </div>
	
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>