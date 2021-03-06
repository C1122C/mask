<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<jsp:useBean id="city" type="business.CityBean" scope="session"></jsp:useBean>
<jsp:useBean id="user" type="business.UserInfoBean" scope="session"></jsp:useBean>
<jsp:useBean id="username" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_login.tld" prefix="MyTag" %>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/user.js"></script>
		<script src="JS/act.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script src="JS/jquery.min.js"></script>
    	<script src="JS/jquery-ui.min.js"></script>
    	<script src="JS/echarts.min.js"></script>
	    <script type="text/javascript">
		    $(function () {
	            $("#startDatePicker").datepicker({
	                dateFormat: "yy-mm-dd",
	                maxDate:new Date(2018,5,1),
	                minDate:new Date(2017,1,1),
	                changeMonth: true,
	                changeYear: true,
	                onClose:function (selectedDate) {
	                    $("#endDatePicker").datepicker("option","minDate",selectedDate);
	                }
	            });
	        });
	        $(function () {
	            $("#endDatePicker").datepicker({
	                dateFormat: "yy-mm-dd",
	                maxDate:new Date(2018,6,1),
	                minDate:new Date(2017,1,1),
	                changeMonth: true,
	                changeYear: true,
	                onClose:function (selectedDate) {
	                    $("#startDatePicker").datepicker("option","maxDate",selectedDate);
	                }
	            });
	        });
	        function p(s) {
	            return s < 10 ? '0' + s: s;
	        };
	        window.onload = function () {
	            document.getElementById("startDatePicker").value = "2017-12-01";
	            document.getElementById("endDatePicker").value = "2018-03-29";
	            getData();
	        };

	        function getData() {
	            $("#loading").fadeIn(500);
	            var SD = document.getElementById("startDatePicker").value;
	            var ED = document.getElementById("endDatePicker").value;
	            drawUserCharts(SD,ED);
	            $("#loading").fadeOut(500);
	        }
	        
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
	        	$("body").on('click','#search_statis',function(){
		        	$("#user_order_st").empty();
		        	$("#user_order_money").empty();
		        	$("#user_order_money1").empty();
		        	$("#user_order_time").empty();
	        		$("#loading").fadeIn(500);
		            var SD = document.getElementById("startDatePicker").value;
		            var ED = document.getElementById("endDatePicker").value;
		            drawUserCharts(SD,ED);
		            $("#loading").fadeOut(500);
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
                <a href="./mine.jsp"><li class="old-li"><i class="i-ico">个人信息</i></li></a>
                <a href="./user_statistic.jsp"><li class="coF old-li old-li-on"><i class="i-ico">个人统计信息</i></li></a>
                <a href="./coupon.jsp"><li class="old-li"><i class="i-ico">优惠券</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
                <a><li class="old-li"><i class="i-ico" id="delete">删除账号</i></li></a>
            </ul>

            <div class="My-you">
            	<div class="div-xxk">
            		
            		<div class="older-lieb r" style="min-height:500px;">
            		<div style="width:100%;height:30px;"></div>
            			<table cellpadding="10" style="margin-left: 86px">
						    <tr>
						        <th style="margin-left: 105px;font-size: 16px">开始日期</th>
						        <th><input type="text" id="startDatePicker" value="2005-03-15" style="font-size: 16px"/></th>
						        <th style="font-size: 16px">结束日期</th>
						        <th><input type="text" id="endDatePicker" value="2005-04-15" style="font-size: 16px"/></th>
						        <th><a class="conpun-btn l" style="margin-top:0px;" id="search_statis">搜索</a></th>
						    </tr>
						</table>
						<div id="user_order_st" style="width:100%;min-height:300px;margin-top:30px;"></div>
						<div id="user_order_money" style="width:100%;min-height:300px;margin-top:30px;"></div>
						<div id="user_order_money1" style="width:100%;min-height:300px;margin-top:30px;"></div>
						<div id="user_order_time" style="width:100%;min-height:300px;margin-top:30px;"></div>
						
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