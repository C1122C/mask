<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="venue" type="business.VenueBean" scope="session"></jsp:useBean>  
<jsp:useBean id="vname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginv.tld" prefix="MyTagv" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/venue.js"></script>
		<script src="JS/user.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
	        	
	        	$("body").on('click','#logout',function(){
	        		logout();
	            });
	        	$("body").on('click','.delete',function(){
	        		var id=$(this).attr("name");
	        		delete_hall(id);
	        		$(this).parent().prev().hide();
	        		$(this).parent().hide();
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
                <a href="./venue_act.jsp"><li class="coF old-li old-li-on"><i class="i-ico">本馆演出</i></li></a>
                <a href="./act_add.jsp"><li class="old-li"><i class="i-ico">发布活动</i></li></a>
                <a href="./venue_mod.jsp"><li class="old-li"><i class="i-ico">信息修改</i></li></a>
                <a href="./venue_app.jsp"><li class="old-li"><i class="i-ico">本馆申请</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
            </ul>

            <div class="conpun">
            	<div style="width:800px;min-height:80px;"><a href="./hall_add.jsp" target="_blank" class="conpun-btn l" style="backgroung-color:#f069c8;height:35px;width:90px;font-size:18px;">添加展厅</a></div>
	            
	            <div class="conpun-div" style="padding-bottom: 0;">
	                <div class="conpun-tit">已有展厅</div>
	                <ul class="conpun-xinxi">
	                    <li style="width:380px;font-size:16px;">展厅名称</li>
	                    <li style="width:380px;font-size:16px;">操作</li>
	                </ul>
	                <ul class="conpun-xinxii">
	                <%for(int i=0;i<venue.getVenue().getHall().size();i++){ %>
	                    <li style="width:380px;font-size:16px;"><%=venue.getVenue().getHall().get(i).getName() %></li>
                        <li style="width:380px;font-size:16px;"><a class="conpun-btn l delete" style="margin-top: 0px;background-color:gray;height:35px;width:80px;color:#fff;margin-left:150px;" name=<%=venue.getVenue().getHall().get(i).getId() %>>删除</a></li>
                    <%} %>
                    </ul>
                </div>
        	</div>
            
        </div>
	</div>
	
	</body>
</html>