<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<jsp:useBean id="city" type="business.CityBean" scope="session"></jsp:useBean>
<jsp:useBean id="user" type="business.UserInfoBean" scope="session"></jsp:useBean>
<jsp:useBean id="act" type="business.ActBean" scope="session"></jsp:useBean>
<jsp:useBean id="username" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_login.tld" prefix="MyTag" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>MASK</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/act.js"></script>
		<script src="JS/user.js"></script>
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
	        		
	        	$("body").on('click','.gettime',function(){
	                var t=$(this).attr("name");
	                $(this).siblings().removeClass("changci-on");
	                $(this).addClass("changci-on");
	                var node1=document.getElementById("t_time");
                    node1.setAttribute("name",t);
	            });	
	        	$("body").on('click','.c_class',function(){
	        		var t=$(this).attr("name");
	                $(this).siblings().removeClass("changci-on");
	                $(this).addClass("changci-on");
	                var node1=document.getElementById("ticket");
	                var node2=document.getElementById("t_num");
	                var offset1=parseInt(node2.name);
	                var offset2=parseInt(t);
	                var res=offset1*offset2;
	                var v=document.getElementById("sum");
	                v.innerHTML=res+""; 
                    node1.setAttribute("name",t);
	            });
	        	$("body").on('click','.c_num',function(){
	        		var t=$(this).html();
	                $(this).siblings().removeClass("changci-on");
	                $(this).addClass("changci-on");
	                var node1=document.getElementById("t_num");
	                var node2=document.getElementById("ticket");
	                var offset1=parseInt(node2.name);
	                var offset2=parseInt(t);
	                var res=offset1*offset2;
	                var v=document.getElementById("sum");
	                v.innerHTML=res+""; 
                    node1.setAttribute("name",t);
	            });

	        	$("body").on('click','.choosewindows',function(){
	        		var t=$("#sum").html();
	                var node1=document.getElementById("t_num");
	                var node2=document.getElementById("ticket");
	                var node3=document.getElementById("t_time");
	                var node4=document.getElementById("aid");
	                $("#loading").fadeIn(500);
	                select_vote(node4.name,node3.name,node2.name,node1.name,t);
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
                <li><a class="older hed-on">首页</a></li>
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
	<a id="t_time" name=<%=act.getAct().getHalls().get(0).getShowTime() %>></a>
	<a id="ticket" name=<%=act.getAct().getHalls().get(0).getPrices().get(0) %>></a>
	<a id="t_num" name="1"></a>
	<a id="aid" name=<%=act.getAct().getId() %>></a>
	<div style="height: 15px;"></div>
	
	<!--  -->
	<div class="details-top">
    	<div class="top-w">
	        <img src=<%=act.getAct().getPath() %> alt=<%=act.getAct().getName() %> title=<%=act.getAct().getName() %>>
	        <div class="con_info">  
	            <p class="cc-title"><%=act.getAct().getName() %></p>
	            <p style="font-size: 14px;color: #FFFFFF;">时间：<span class="cc-time"><%=act.getAct().getFirst() %>-<%=act.getAct().getLast()%></span></p>
	            <p style="font-size: 14px;color: #FFFFFF;margin-bottom: 28px;">场馆：<span class="cc-place"><%=act.getAct().getVname() %></span></p>
	            <p style="font-size: 14px;color: #FFFFFF;margin-bottom: 28px;"><span class="cc-place"><%=act.getAct().getDes()%></span></p>
	        </div>
    	</div>
	</div>
	<div class="s_container">
		<div class="order-progress-bar">
		    	<div class="step first done">
		    		<span class="step-num">1</span>
		    		<div class="bar"></div>
		    		<span class="step-text">选择活动</span>
		  		</div>
	            <div class="step done">
	    			<span class="step-num">2</span>
	    			<div class="bar"></div>    
	    			<span class="step-text">购票</span>    
	  			</div>
	            <div class="step ">
				    <span class="step-num">3</span>
				    <div class="bar"></div>    
				    <span class="step-text">15分钟内付款</span>    
	            </div>
	            <div class="step last ">
				    <span class="step-num">4</span>
				    <div class="bar"></div>    
				    <span class="step-text">现场检票</span>    
	            </div>
		</div>
	</div>
	<div class="ticket_info">
    	<div class="details_main">
        
        	<div class="t_sort">
            	<div>
                	<span class="t_sort_time">选择场次:</span>

                	<ul class="t_sort_tinfo">
                	<%for(int i=0;i<act.getAct().getHalls().size();i++){ 
                		if(i==0){%>         
                    	<li class="gettime changci-on" name=<%=act.getAct().getHalls().get(i).getShowTime() %>><%=act.getAct().getHalls().get(i).getTimetosee() %></li>
                        <%}else{ %>
                        <li class="gettime" name=<%=act.getAct().getHalls().get(i).getShowTime() %>><%=act.getAct().getHalls().get(i).getTimetosee() %></li>
                        <%} %>
                    <%}%>
                   </ul>
	                
            	</div>
	            <div class='rapid'>
	                <span class="fenlei-xz">选择票档:</span>
	                <ul class="xz-piaojia">
	                <%for(int i=0;i<act.getAct().getHalls().get(0).getPrices().size();i++){ 
	                	if(i==0){%>         
                    	<li class="c_class changci-on" name=<%=act.getAct().getHalls().get(0).getPrices().get(i) %>>
                    		<%=act.getAct().getHalls().get(0).getTip().get(i) %>
                        </li>
                    	<%} else{%>
                    	<li class="c_class" name=<%=act.getAct().getHalls().get(0).getPrices().get(i) %>>
                    		<%=act.getAct().getHalls().get(0).getTip().get(i) %>
                        </li>
                        <%} %>
                     <%} %>
	               </ul>
	            </div>
	            <div class='rapid'>
	                <span class="fenlei-xz">选择数量:</span>
	                <ul class="xz-quantity" >
	                    <li class="c_num changci-on">1</li>
	                    <li class="c_num">2</li>
	                    <li class="c_num">3</li>
	                    <li class="c_num">4</li>
	                    <li class="c_num">5</li>
	                    <li class="c_num">6</li>
	                    <li class="c_num">7</li>
	                    <li class="c_num">8</li>
	                    <li class="c_num">9</li>
	                    <li class="c_num">10</li>
	                    <li class="c_num">11</li>
	                    <li class="c_num">12</li>
	                    <li class="c_num">13</li>
	                    <li class="c_num">14</li>
	                    <li class="c_num">15</li>
	                    <li class="c_num">16</li>
	                    <li class="c_num">17</li>
	                    <li class="c_num">18</li>
	                    <li class="c_num">19</li>
	                    <li class="c_num">20</li>
	                </ul>
	            </div>
	            <div class='rapid'>
	                <span class="fenlei-xz">合计:</span>
	                <div class="xz-priceInfo money">
	                	<span class="total" id="sum"><%=act.getAct().getHalls().get(0).getPrices().get(0) %></span>
	                	<span class="unit">元</span>
	                </div>
	            </div>
	            <div class='rapid'>
	                <div class="xz-button">
	                	<span class="choosewindows">立即购买</span>
	                </div>
	            </div>

        	</div>
        
        </div>
    </div>
    <div class="modal-container" style="display:none;" id="little_board">
		<div class="modal">
		    <span class="icon"></span>
		
		    <p class="tip">配票失败，请选择其他场次</p>
		
		</div>
    </div>
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>