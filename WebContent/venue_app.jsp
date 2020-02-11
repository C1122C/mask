<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="vapplist" type="business.AppListBean" scope="session"></jsp:useBean> 
<jsp:useBean id="venue" type="business.VenueBean" scope="session"></jsp:useBean>  
<jsp:useBean id="vname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginv.tld" prefix="MyTagv" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/venue.js"></script>
		<script src="JS/coupon.js"></script>
		<script src="JS/user.js"></script>
		<script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
	        	$(window).bind('beforeunload',function(){
	        		reset("venue_app_offset");
	        	});
	        	$("body").on('click','#logout',function(){
	        		logout();
	            });
	            $(window).scroll(function(){
	                if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
	                    $("#loading").fadeIn(500);
	                    more_app();
	                    $("#loading").fadeOut(500);
	                }
	            });
	        	
	        });
    	</script>
	</head>
	<body>
	<MyTagv:checkSessionv />	
		<div class="v_headbar" style="margin-top: -15px;">
	    <a href="./index.jsp"><span class="logo" style="margin-bottom: -15px;"></span></a>
	    <div class="v_topbar" style="margin-bottom: -15px;">
	        <ul class="v_top_ul">
	            
	        </ul>
	    </div>
	</div>
	<div style="height: 15px;"></div>
	<div class="ol-older">
        <div class="older-w w">
            
            <ul class="older-l l">
                <a><li class="li-co3"><%=venue.getVenue().getName() %></li></a>
                <a href="./venue_statistic.jsp"><li class="old-li"><i class="i-ico">统计信息</i></li></a>
                <a href="./venue_act.jsp"><li class="old-li"><i class="i-ico">本馆演出</i></li></a>
                <a href="./act_add.jsp"><li class="old-li"><i class="i-ico">发布活动</i></li></a>
                <a href="./venue_mod.jsp"><li class="old-li"><i class="i-ico">信息修改</i></li></a>
                <a href="./venue_app.jsp"><li class="coF old-li old-li-on"><i class="i-ico">本馆申请</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
            </ul>

            <div class="conpun" style="margin-top:15px;">
            	
	            <div class="conpun-div" style="padding-bottom: 0;" id="type_box">
	                <div class="conpun-tit">申请</div>
	                <ul class="conpun-xinxi">
	                    <li style="width:255px;font-size:16px;">申请号</li>
	                    <li style="width:255px;font-size:16px;">申请日期</li>
	                    <li style="width:255px;font-size:16px;">结果</li>
	                </ul>
	                <%for(int i=0;i<vapplist.getAppList().size();i++){ %>
	                <ul class="conpun-xinxii">
	                	<li style="width:255px;font-size:14px;"><%=vapplist.getAppList().get(i).getId() %></li>
                        <li style="width:255px;font-size:14px;"><%=vapplist.getAppList().get(i).getDate() %></li>
                        <li style="width:255px;font-size:14px;"><%=vapplist.getAppList().get(i).getState() %></li>
                    </ul>
                    <%} %>
                </div>
        	</div>
            
        </div>
	</div>
	
	</body>
</html>