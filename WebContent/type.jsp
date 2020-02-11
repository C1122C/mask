<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<jsp:useBean id="city" type="business.CityBean" scope="session"></jsp:useBean>
<jsp:useBean id="user" type="business.UserInfoBean" scope="session"></jsp:useBean> 
<jsp:useBean id="actlist" type="business.ActListBean" scope="session"></jsp:useBean>
<jsp:useBean id="item" class="model.Activity" scope="page"></jsp:useBean> 
<jsp:useBean id="username" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_login.tld" prefix="MyTag" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>MASK</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/act.js"></script>
		<script src="JS/user.js"></script>
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

	        	
	        		
	        	$("body").on('click','.cc-title',function(){
	                var t=$(this).attr("name");
	                $("#loading").fadeIn(500);
	                a_detail(t);
	                $("#loading").fadeOut(500);
	            });	
	        	$("body").on('click','.blc',function(){
	                var t=$(this).attr("name");
	                $("#loading").fadeIn(500);
	                a_detail(t);
	                $("#loading").fadeOut(500);
	            });
	        	

	        	$(window).scroll(function(){
	                if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
	                    $("#loading").fadeIn(500);
	                    more_list();
	                    $("#loading").fadeOut(500);
	                }
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
	
	<div style="height: 15px;"></div>
	
	<!--  -->
	<div class="wall">
    	<div class="wall_main">
	        <div style="height: 15px;"></div>
        	<div class="a_list">
        		<ul class="a_list_res" id="type_box" style="margin-top:56px;">
        		<%
					for (int i = 0; i < actlist.getActList().size(); i++) {
						pageContext.setAttribute("item", actlist.getActList().get(i));
						
				%>
                	<div class="a_list_res_item">
				        <a class="cc-title" name=<%=actlist.getActList().get(i).getId() %>>
				        	<img class="list_post" src=<%=actlist.getActList().get(i).getPath() %> alt=<%=actlist.getActList().get(i).getName() %> title=<%=actlist.getActList().get(i).getName() %> style="display:block;">
				        </a>
        				<div class="list_detail">
				            <a class="cc-title" name=<%=actlist.getActList().get(i).getId() %>>
				                <jsp:getProperty name="item" property="name" />          
				            </a>
				            <span class="list_time"><jsp:getProperty name="item" property="first" />~<jsp:getProperty name="item" property="last" /></span>
				            <span class="list_place">[<%=actlist.getActList().get(i).getCity() %>]<jsp:getProperty name="item" property="vname" /></span>
                    	</div>
        				<div class="item_right">
                        	<span class="r_price"><span class="cc_price"><jsp:getProperty name="item" property="price" /></span>起</span>
                			<a class="blc" name=<%=actlist.getActList().get(i).getId() %>>立即购买</a>
                    	</div>
    				</div>
    				<%} %>
    				
     			</ul>
    
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
	</body>
</html>