<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="vname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginv.tld" prefix="MyTagv" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/act.js"></script>
		<script src="JS/jquery-3.2.1.js"></script>
	    <script src="JS/jquery.min.js"></script>
    	<script src="JS/jquery-ui.min.js"></script>
    	<script src="JS/echarts.min.js"></script>
	    <script type="text/javascript">
		   
	        window.onload = function () {
	            getData();
	        };

	        function getData() {
	            $("#loading").fadeIn(500);
	            drawActCharts();
	            $("#loading").fadeOut(500);
	        }
	        
	        
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
           <div class="My-you">
            	<div class="div-xxk">
            		
					<div id="seri" style="width:100%;min-height:300px;margin-top:30px;"></div>
						
            	</div>

        	</div>
    	</div>
     </div>
	
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>