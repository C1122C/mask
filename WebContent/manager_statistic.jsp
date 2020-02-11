<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginm.tld" prefix="MyTagm" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/act.js"></script>
		<script src="JS/user.js"></script>
		<script src="JS/jquery-3.2.1.js"></script>
	    <script src="JS/jquery.min.js"></script>
    	<script src="JS/jquery-ui.min.js"></script>
    	<script src="JS/echarts.min.js"></script>
	    <script type="text/javascript">
		    $(document).ready(function(){
	        	
	        	$("body").on('click','#logout',function(){
	        		logout();
	            });

	        	$("body").on('click','#search_statis',function(){
		        	$("#top_ven").empty();
		        	$("#top_act").empty();
		        	$("#plat").empty();
		        	$("#user").empty();
		        	getData();
	            });
		    });
		    $(function () {
	            $("#startDatePicker").datepicker({
	                dateFormat: "yy-mm-dd",
	                maxDate:new Date(2018,6,1),
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
	            drawPlatCharts(SD,ED);
	            $("#loading").fadeOut(500);
	        }
	        
	        
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
                <a href="./manager_statistic.jsp"><li class="coF old-li old-li-on"><i class="i-ico">统计信息</i></li></a>
                <a href="./manager_app.jsp"><li class="old-li"><i class="i-ico">申请列表</i></li></a>
                <a href="./manager_account.jsp"><li class="old-li"><i class="i-ico">活动结算</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
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
						<div id="top_ven" style="width:100%;min-height:300px;margin-top:30px;"></div>
						<div id="top_act" style="width:100%;min-height:300px;margin-top:30px;"></div>
						<div id="plat" style="width:100%;min-height:300px;margin-top:30px;"></div>
						<div id="user" style="width:100%;min-height:300px;margin-top:30px;"></div>
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