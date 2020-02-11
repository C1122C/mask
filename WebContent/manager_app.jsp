<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mapplist" type="business.AppListBean" scope="session"></jsp:useBean> 
<jsp:useBean id="mname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginm.tld" prefix="MyTagm" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/venue.js"></script>
		<script src="JS/user.js"></script>
		<script src="JS/coupon.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
	        	$(window).bind('beforeunload',function(){
	        		reset("manager_app_offset");
	        	});
	        	$("body").on('click','.app_item',function(){
	        		var id=$(this).attr("data-id");
	        		get_a_app(id);
	            });
	        	
	        	$(window).scroll(function(){
	                if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
	                    more_m_app();
	                }
	            });
	        	$("body").on('click','#logout',function(){
	        		logout();
	            });
	        });
    	</script>
	</head>
	<body>
	<MyTagm:checkSessionm />
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
                <a><li class="li-co3">管理员</li></a>
                <a href="./manager_statistic.jsp"><li class="old-li"><i class="i-ico">统计信息</i></li></a>
                <a href="./manager_app.jsp"><li class="coF old-li old-li-on"><i class="i-ico">申请列表</i></li></a>
                <a href="./manager_account.jsp"><li class="old-li"><i class="i-ico">活动结算</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
            </ul>

            <div class="conpun" style="margin-top:15px;">
            	
	            <div class="conpun-div" style="padding-bottom: 0;" id="type_box">
	                <div class="conpun-tit">申请</div>
	                <ul class="conpun-xinxi">
	                    <li style="width:255px;font-size:16px;">类型</li>
	                    <li style="width:255px;font-size:16px;">来源</li>
	                    <li style="width:255px;font-size:16px;">状态</li>
	                </ul>
	                <%for(int i=0;i<mapplist.getAppList().size();i++){ %>
	                <ul class="conpun-xinxii app_item" data-id=<%=mapplist.getAppList().get(i).getId() %>>
	                	<li style="width:255px;font-size:14px;"><%=mapplist.getAppList().get(i).getType() %></li>
                        <li style="width:255px;font-size:14px;"><%=mapplist.getAppList().get(i).getOrg() %></li>
                        <li style="width:255px;font-size:14px;"><%=mapplist.getAppList().get(i).getState() %></li>
                    </ul>
                    <%} %>
	            </div>
        	</div>
            
        </div>
	</div>
	
	</body>
</html>