<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="vactlist" type="business.ActListBean" scope="session"></jsp:useBean>  
<jsp:useBean id="vname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginv.tld" prefix="MyTagv" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/act.js"></script>
		<script src="JS/user.js"></script>
		<script src="JS/venue.js"></script>
		<script src="JS/coupon.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
		    
	        $(document).ready(function(){
	        	$("body").on('click','#logout',function(){
	        		logout();
	            });
	            
	        	$("body").on('click','.xianchang',function(){
	        		var aid=$(this).attr("name");
	        		$("#loading").fadeIn(500);
	        		get_scene(aid);
	        		$("#loading").fadeOut(500);
	            });

	        	$("body").on('click','.chakan',function(){
	        		var aid=$(this).attr("name");
	        		$("#loading").fadeIn(500);
	        		get_a_statistic(aid);
	        		$("#loading").fadeOut(500);
	            });
	        	
	        	$(window).bind('beforeunload',function(){
	        		reset("venue_act_offset");
	        	});

	        	$(window).scroll(function(){
	                if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
	                    $("#loading").fadeIn(500);
	                    more_venue_act();
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
                <a><li class="li-co3"><%=vactlist.getActList().get(0).getVname() %></li></a>
                <a href="./venue_statistic.jsp"><li class="old-li"><i class="i-ico">统计信息</i></li></a>
                <a href="./venue_act.jsp"><li class="coF old-li old-li-on"><i class="i-ico">本馆演出</i></li></a>
                <a href="./act_add.jsp"><li class="old-li"><i class="i-ico">发布活动</i></li></a>
                <a href="./venue_mod.jsp"><li class="old-li"><i class="i-ico">信息修改</i></li></a>
                <a href="./venue_app.jsp"><li class="old-li"><i class="i-ico">本馆申请</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
            </ul>

            <div class="My-you">
            	<div class="div-xxk">
            		
            		<div class="older-lieb r" style="min-height:500px;">
            		<ul class="a_list_res" id="type_box">
            		<%for(int i=0;i<vactlist.getActList().size();i++){%>
            		
                	<div class="a_list_res_item" style="width:790px;">
				        <a class="cc-title">
				        	<img class="list_post" src=<%=vactlist.getActList().get(i).getPath() %> alt=<%=vactlist.getActList().get(i).getName() %> title=<%=vactlist.getActList().get(i).getName() %> style="display:block;">
				        </a>
        				<div class="list_detail">
				            <a class="cc-title">
				                <%=vactlist.getActList().get(i).getName() %>       
				            </a>
				            <span class="list_time"><%=vactlist.getActList().get(i).getFirst() %>~<%=vactlist.getActList().get(i).getLast() %></span>
				            <span class="list_place"><%=vactlist.getActList().get(i).getState() %></span>
                    	</div>
        				<div class="item_right">
                        	<%if(vactlist.getActList().get(i).getState().equals("已结束")){ %>
                        	<a class="blc chakan" name=<%=vactlist.getActList().get(i).getId() %>>详情查看</a>
                        	<%}else{ %>
                			<a class="blc xianchang" name=<%=vactlist.getActList().get(i).getId() %>>现场</a>
                			<%} %>
                    	</div>
    				</div>
    				<%} %>
    				
     			</ul>
        		</div>
    		</div>
        </div>
	</div>
	<div class="modal-container" style="display:none;" id="little_board">
		<div class="modal">
		    <span class="icon"></span>
		
		    <p class="tip">没有更多了哦</p>
		
		</div>
    </div>
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</div>
	</body>
</html>