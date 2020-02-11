<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="cur_app" type="business.AppBean" scope="session"></jsp:useBean>
<jsp:useBean id="mname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginm.tld" prefix="MyTagm" %>

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
	        	$("body").on('click','#pass',function(){
	        		var id=$(this).attr("name");
	        		pass(id);
	        		alert("已通过");
	        		window.location.href='manager_app.jsp';
	            });
	        	$("body").on('click','#down',function(){
	        		var id=$(this).attr("name");
	        		turn_down(id);
	        		alert("已驳回");
	        		window.location.href='manager_app.jsp';
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

            <div class="My-you">
            	<div class="div-xxk">
            		
            		<div class="older-lieb r" style="min-height:500px;">
            		<div class="self-info">
            				<div class="userexinfo-form-section">
            					<p>场馆名称：</p>
					            <span>
					              <%=cur_app.getApp().getVenue().getName() %>
					            </span>
          					</div>
          					
          					<div class="userexinfo-form-section">
            					<p>联系电话：</p>
					            <span>
					              <%=cur_app.getApp().getVenue().getPhone() %>
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
            					<p>支付账号：</p>
					            <span>
					              <%=cur_app.getApp().getVenue().getPayid() %>
					            </span>
          					</div>
          					
          					<div class="date-picker userexinfo-form-section">

  								<p>地址：</p>
  								<span>
					              <%=cur_app.getApp().getVenue().getAddr().getShowString() %>
					            </span>
								
							</div>

          					
          					<input name=<%=cur_app.getApp().getId() %> type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:50px;margin-top:20px;margin-bottom:20px;"value="通过" id="pass">
							<input name=<%=cur_app.getApp().getId() %> type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:50px;background-color:gray;color:black;margin-top:20px;margin-bottom:20px;"value="驳回" id="down">
            			</div>
            		</div>

        		</div>
    		</div>
        </div>
	</div>
	
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>