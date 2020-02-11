<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mactlist" type="business.ActListBean" scope="session"></jsp:useBean>  
<jsp:useBean id="mname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginm.tld" prefix="MyTagm" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/act.js"></script>
		<script src="JS/user.js"></script>
		<script src="JS/coupon.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
	        	
	        	$("body").on('click','.blc',function(){
	        		var id=$(this).attr("name");
	        		close_act(id);
	            });

	        	$(window).scroll(function(){
	                if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
	                    more_m_act();
	                }
	            });

	        	$(window).bind('beforeunload',function(){
	        		reset("manager_act_offset");
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
                <a href="./manager_app.jsp"><li class="old-li"><i class="i-ico">申请列表</i></li></a>
                <a href="./manager_account.jsp"><li class="coF old-li old-li-on"><i class="i-ico">活动结算</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
            </ul>

            <div class="My-you">
            	<div class="div-xxk">
            		
            		<div class="older-lieb r" style="min-height:500px;">
            		<ul class="a_list_res" id="type_box">
                	<%for(int i=0;i<mactlist.getActList().size();i++){%>
            		
                	<div class="a_list_res_item" style="width:790px;">
				        <a class="cc-title">
				        	<img class="list_post" src=<%=mactlist.getActList().get(i).getPath() %> alt=<%=mactlist.getActList().get(i).getName() %> title=<%=mactlist.getActList().get(i).getName() %> style="display:block;">
				        </a>
        				<div class="list_detail">
				            <a class="cc-title">
				                <%=mactlist.getActList().get(i).getName() %>       
				            </a>
				            <span class="list_time">总金额：<%=mactlist.getActList().get(i).getTotal() %></span>
				            <span class="list_place"><%=mactlist.getActList().get(i).getState() %></span>
                    	</div>
                    	
        				<div class="item_right">
                        	<a class="blc" name=<%=mactlist.getActList().get(i).getId() %>>结算</a>
                        </div>
                        
    				</div>
    				<%} %>
    				
     			</ul>
        		</div>
    		</div>
        </div>
            
        </div>
	</div>
	
	</body>
</html>