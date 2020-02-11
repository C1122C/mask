<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="user" type="business.UserInfoBean" scope="session"></jsp:useBean> 
<jsp:useBean id="city" type="business.CityBean" scope="session"></jsp:useBean> 
<jsp:useBean id="username" type="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_login.tld" prefix="MyTag" %>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/user.js"></script>
		<script src="JS/act.js"></script>
		<script src="JS/order.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
		        
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
	        		logout();
	            });

	        	$("body").on('click','#delete',function(){
	        		delete_account();
	            });	
	        	
	        	$("body").on('click','#save',function(){
	        		var name=document.getElementById("nickname").value;
	        		var pwd=document.getElementById("pwd").value;
	        		var gender=$('input:radio:checked').val();
	        		var year=document.getElementById("year").value;
	        		var month=document.getElementById("month").value;
	        		var day=document.getElementById("day").value;
	        		var pay_id=document.getElementById("pay_id").value;
	        		var arrUl = jQuery('input:checkbox');
	        		var interest="";
	        		jQuery.each(arrUl, function(){
	        			if(jQuery(this).is(":checked")){
	        				interest=interest+jQuery(this).val()+":";
			            }
		            });
		            interest=interest.substr(0,interest.length-1);
	        		save_person_info(name,pwd,gender,year,month,day,pay_id,interest);
	        	
	        		
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
	<div class="ol-older">
        <div class="older-w w">
            
            <ul class="older-l l">
                <a><li class="li-co3">个人空间</li></a>
                <a href="./order_scan.jsp"><li class="old-li"><i class="i-ico">我的订单</i></li></a>
                <a href="./mine.jsp"><li class="coF old-li old-li-on"><i class="i-ico">个人信息</i></li></a>
                <a href="./user_statistic.jsp"><li class="old-li"><i class="i-ico">个人统计信息</i></li></a>
                <a href="./coupon.jsp"><li class="old-li"><i class="i-ico">优惠券</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
                <a><li class="old-li"><i class="i-ico" id="delete">删除账号</i></li></a>
            </ul>

            <div class="My-you">
            	<div class="div-xxk">
            		
            		<div class="older-lieb r" style="min-height:500px;">
            			<div class="self-info">
            				<div class="userexinfo-form-section">
            					<p>账号：</p>
            					<span id="account">
					              <%=user.getUser().getId() %>
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
            					<p>会员级别：</p>
            					<span id="u_class">
					              <%=user.getUser().getRank() %>
					            </span>
          					</div>
            				<div class="userexinfo-form-section">
            					<p>昵称：</p>
					            <span><%if(user.getUser().getName()==null){ %>
					              <input type="text" name="nickName" id="nickname" class="ui-checkbox" placeholder="2-15个字，支持中英文、数字">
					              <%}else{ %>
					              <input type="text" name="nickName" id="nickname" class="ui-checkbox" placeholder="2-15个字，支持中英文、数字" value=<%=user.getUser().getName() %>>
					              <%} %>
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
            					<p>密码：</p>
					            <span>
					              <input type="password" name="nickName" id="pwd" class="ui-checkbox">
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
					            <p>性别：</p>
					            <%if(user.getUser().getGender()==null||user.getUser().getGender().equals("女")){ %>
					            <span>
					              <input type="radio" name="gender" value="男" id="userexinfo-form-gender-1">
					              <label for="userexinfo-form-gender-1">男</label>
					            </span>
					            <span>
					              <input type="radio" name="gender" value="女" checked id="userexinfo-form-gender-2">
					              <label for="userexinfo-form-gender-2">女</label>
					            </span>
					            <%} else{ %>
					             <span>
					              <input type="radio" name="gender" value="男" checked>
					              <label for="userexinfo-form-gender-1">男</label>
					            </span>
					            <span>
					              <input type="radio" name="gender" value="女">
					              <label for="userexinfo-form-gender-2">女</label>
					            </span><%} %>
          					</div>
          					<div class="date-picker userexinfo-form-section">

  								<p>生日：</p>
  								<span>
    								<div class="ui-select">
								      <select name="year" class="ui-select" id="year">
								        
								        <%if(user.getUser().getB_year()==0){ %>
								        <%for(int i=1935;i<2019;i++){ 
								        	if(i==1990){%>
								        <option value=<%=i %> selected><%=i %></option>
								        <%}else{ %>
								        <option value=<%=i %>><%=i %></option><%}} %>
								        <%}else{ %>
								        <%for(int i=1935;i<2019;i++){ 
								        	if(user.getUser().getB_year()==i){%>
								        <option value=<%=i %> selected><%=i %></option>
								        <%}else{ %>
								        <option value=<%=i %>><%=i %></option><%}} %>
								        <%} %>
								      </select>
    								</div>
    								<span class="tip">年</span>
  								</span>
  								<span>
								    <div class="ui-select">
								      <select name="month" class="ui-select" id="month">
								        <%if(user.getUser().getB_month()==0){ %>
								        <%for(int i=1;i<13;i++){ 
								        	if(i==1){%>
								        <option value=<%=i %> selected><%=i %></option>
								        <%}else{ %>
								        <option value=<%=i %>><%=i %></option><%}} %>
								        <%}else{ %>
								        <%for(int i=1;i<13;i++){ 
								        	if(user.getUser().getB_month()==i){%>
								        <option value=<%=i %> selected><%=i %></option>
								        <%}else{ %>
								        <option value=<%=i %>><%=i %></option><%}} %>
								        <%} %>
								      </select>
								    </div>
								    <span class="tip">月</span>
								  </span>
								  <span>
								    <div class="ui-select">
								      <select name="day" class="ui-select" id="day">
								        <%if(user.getUser().getB_day()==0){ %>
								        <%for(int i=1;i<=31;i++){ 
								        	if(i==1){%>
								        <option value=<%=i %> selected><%=i %></option>
								        <%}else{ %>
								        <option value=<%=i %>><%=i %></option><%}} %>
								        <%}else{ %>
								        <%for(int i=1;i<=31;i++){ 
								        	if(user.getUser().getB_day()==i){%>
								        <option value=<%=i %> selected><%=i %></option>
								        <%}else{ %>
								        <option value=<%=i %>><%=i %></option><%}} %>
								        <%} %>
								      </select>
								    </div>
								    <span class="tip">日</span>
								  </span>
							</div>

          					<div class="userexinfo-form-section">
            					<p>支付账号：</p>
					            <span>
					              <%if(user.getUser().getPayID()==null){ %>
					              <input type="text" class="ui-checkbox" id="pay_id">
					              <%}else{ %>
					              <input type="text" class="ui-checkbox" id="pay_id" value=<%=user.getUser().getPayID() %>>
					              <%} %>
					            </span>
          					</div>

				          	<div class="userexinfo-form-section expand-list">
					            <p>兴趣：</p>
					            <div class="interest-list">
					            <%if(user.getUser().getInterest()==null){ %>
					            <%for(int i=0;i<user.getInterest().size();i++){
					            	String s=user.getInterest().get(i);%>
					              <span>
					                <input type="checkbox" value=<%=s %> class="ui-checkbox/ interest" />
					                <label class="label_l"><%=s %></label>
					              </span>
					              <%} %>
					            <%}else{ %>
					            <%for(int i=0;i<user.getInterest().size();i++){
					            	String s=user.getInterest().get(i);
					            	if(user.getUser().getInterest().indexOf(s)>=0){%>
					              <span>
					                <input type="checkbox" value=<%=s %> class="ui-checkbox/ interest" checked />
					                <label class="label_l"><%=s %></label>
					              </span>
					              <%}else{ %>
					              <span>
					                <input type="checkbox" value=<%=s %> class="ui-checkbox/ interest" />
					                <label class="label_l"><%=s %></label>
					              </span>
					              <%}} %>
					             <%} %>
					            </div>
				          	</div>
          
          					<input type="submit" class="form-save-btn" style="margin-bottom:15px;" id="save" value="保存">
            			</div>
            		</div>

        		</div>
    		</div>
        </div>
	</div>
	<div class="modal-container" style="display:none;" id="little_board">
		<div class="modal">
		    <span class="icon"></span>
		
		    <p class="tip">已为您修改个人信息</p>
		
		</div>
    </div>
    <div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>