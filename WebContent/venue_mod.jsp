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
		<script src="JS/act.js"></script>
		<script src="JS/user.js"></script>
		<script src="JS/venue.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
		    
	        $(document).ready(function(){
	        	$("body").on('click','#logout',function(){
	        		logout();
	            });
	            
	        	$("body").on('click','#hall',function(){
	        		var name=$("#name").val();
	        		var pwd=$("#pwd").val();
	        		var phone=$("#phone").val();
	        		var pay_id=$("#pay_id").val();
	        		var pro=$("#pro").val();
	        		var city=$("#city").val();
	        		var dis=$("#dis").val();
	        		var det=$("#det").val();
	        		mod_venue_info(name,pwd,phone,pay_id,pro,city,dis,det);
	        		window.location.href='hall_mod.jsp';
	            });

	        	$("body").on('click','#sub',function(){
	        		var name=$("#name").val();
	        		var pwd=$("#pwd").val();
	        		var phone=$("#phone").val();
	        		var pay_id=$("#pay_id").val();
	        		var pro=$("#pro").val();
	        		var city=$("#city").val();
	        		var dis=$("#dis").val();
	        		var det=$("#det").val();
	        		mod_venue_info(name,pwd,phone,pay_id,pro,city,dis,det);
	        		window.location.href='venue_app.jsp';
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
                <a href="./venue_mod.jsp"><li class="coF old-li old-li-on"><i class="i-ico">信息修改</i></li></a>
                <a href="./venue_app.jsp"><li class="old-li"><i class="i-ico">本馆申请</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
            </ul>

            <div class="My-you">
            	<div class="div-xxk">
            		
            		<div class="older-lieb r" style="min-height:500px;">
            		<div class="self-info">
            				<div class="userexinfo-form-section">
            					<p>登录账号：</p>
            					<span id="account">
					              <%=venue.getVenue().getId() %>
					            </span>
          					</div>
            				<div class="userexinfo-form-section">
            					<p>场馆名称：</p>
					            <span>
					              <input type="text" id="name" class="ui-checkbox" value=<%=venue.getVenue().getName() %>>
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
            					<p>密码：</p>
					            <span>
					              <input type="password" id="pwd" class="ui-checkbox">
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
            					<p>联系电话：</p>
					            <span>
					              <input type="text" id="phone" class="ui-checkbox" value=<%=venue.getVenue().getPhone() %>>
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
            					<p>支付账号：</p>
					            <span>
					              <input type="text" id="pay_id" class="ui-checkbox" value=<%=venue.getVenue().getPayid() %>>
					            </span>
          					</div>
          					
          					<div class="date-picker userexinfo-form-section">

  								<p>地址：</p>
  								<span>
    								<div class="ui-select">
								      <input type="text" id="pro" class="ui-checkbox" value=<%=venue.getVenue().getAddr().getProvince() %>>
    								</div>
    								<span class="tip">省</span>
  								</span>
  								<span>
    								<div class="ui-select">
      									<input type="text" id="city" class="ui-checkbox" value=<%=venue.getVenue().getAddr().getCity() %>>
    								</div>
    								<span class="tip">市</span>
  								</span>
  								<span>
								    <div class="ui-select">
								      <input type="text" id="dis" class="ui-checkbox" value=<%=venue.getVenue().getAddr().getDistrict() %>>
								    </div>
								    <span class="tip">区</span>
								  </span>
								  <span>
								    <div class="ui-select">
								      <input type="text" id="dis" class="ui-checkbox" value=<%=venue.getVenue().getAddr().getDetail() %>>
								    </div>
								    <span class="tip">街道、门牌</span>
								  </span>
							</div>

          					
          					<input type="submit" class="form-save-btn" style="min-width:150px;float:left;margin-left:50px;margin-top:90px;margin-bottom:10px;"value="修改展厅信息" id="hall">
          					<input type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:50px;margin-top:90px;margin-bottom:10px;"value="提交" id="sub">
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