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
                    $("#"+t).show();
                    $("#"+t).siblings().hide();
                    $("#sidebar").show();
                    $(this).parent().parent().show();
	            });	
	        	$("body").on('click','.selectable',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.add2',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.add3',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.add4',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.add5',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.add6',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.add7',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.add8',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.add9',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.add10',function(){
	        		if($("#ticket_box").children().length==6){
	        			alert("每单只能选择6张哦！");
		            }
	        		else{
	        			$(this).removeClass();
		                $(this).addClass("selected");
		                $(".no-ticket").hide();
		                $(".has-ticket").show();
		                var a1=$(this).attr("data-column-id");
		                var a2=$(this).attr("data-row-id");
		                var a3=$(this).attr("data-seat-col");
		                var a4=$(this).attr("data-seat-row");
		                var a5=$(this).attr("data-price");
		                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
		                var v1=document.createElement("div");
		                v1.setAttribute("class","ticket");
		                v1.setAttribute("id",ares);
		                v1.innerHTML=a4+"排"+a3+"座"; 
		                var node=document.getElementById("ticket_box");
		                node.appendChild(v1);
		                
		                var v=document.getElementById("sum");
		                var offset1=parseInt(a5);
		                var offset2=parseInt(v.innerHTML);
		                var res=offset1+offset2;
		                v.innerHTML=res+""; 
	                    
		        	}
	        		
	            });
	        	$("body").on('click','.selected',function(){
	        		$(this).removeClass("selected");
	        		var org=$(this).attr("data-org");
	                $(this).addClass(org);
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var el = document.getElementById(ares);
	                el.parentNode.removeChild(el);
	                $("#ticket_box").children(ares).remove();
	                if($("#ticket_box").children().length==0){
	                	$(".no-ticket").show();
		                $(".has-ticket").hide();
		            }
	                
	                var v=document.getElementById("sum");
	                var offset1=parseInt(a5);
	                var offset2=parseInt(v.innerHTML);
	                var res=offset2-offset1;
	                v.innerHTML=res+""; 
                    
	            });

	        	$("body").on('click','.confirm-btn',function(){
	        		if($("#ticket_box").children().length==0){
		        		alert("请先选择座位");
	        			
		            }
	        		else{
	        			var arrUl = jQuery(".ticket");
	        			var v=document.getElementById("aid");
		        		var par=v.name;
		        		var v1=document.getElementById("t_time");
		        		var par=par+":"+v1.name+":";
		        		jQuery.each(arrUl, function(){
			        		par=par+jQuery(this).attr("id")+":";
		        		});
		        		var t=$("#sum").html();
		        		par=par+t;
		                end_select(par);
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
    <a id="t_time" name=<%=act.getAct().getHalls().get(0).getShowTime() %>></a>
	<a id="aid" name=<%=act.getAct().getId() %>></a>
	<div style="height: 15px;"></div>
	
	<!--  -->
	<div class="details-top" style="margin-top:150px;">
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
         <div class="main clearfix"  >
        	<%for(int i=0;i<act.getAct().getHalls().size();i++){ 	
        		if(i==0){%>
        		<div class="hall" id=<%=act.getAct().getHalls().get(i).getShowTime() %> data-section-name=<%=act.getAct().getHalls().get(i).getName() %>>
        		<% }else{%>
        		<div class="hall" id=<%=act.getAct().getHalls().get(i).getShowTime() %> data-section-name=<%=act.getAct().getHalls().get(i).getName() %> style="display:none;">
        		<% }%>
        		<div class="seat-example">
        		<%for(int j=0;j<act.getAct().getHalls().get(i).getB_pic().size();j++){%>
		            <div class=<%=act.getAct().getHalls().get(i).getB_pic().get(j) %>>
		            	<span><%=act.getAct().getHalls().get(i).getPrices().get(j)+"元" %></span>
		            </div>
		        <%} %>
		        	<div class="sold-example example">
		            	<span>已售座位</span>
		            </div>
		            <div class="selected-example example">
		            	<span>已选座位</span>
		            </div>
		        </div>
        		
        
				<div class="seats-block" data-cols=<%=act.getAct().getHalls().get(i).getSeats()[0].length %> data-rows=<%=act.getAct().getHalls().get(i).getSeats().length %>>
			  		<div class="row-id-container">
			  		<%for(int j=0;j<act.getAct().getHalls().get(i).getSeats().length;j++){ %>
				        <span class="row-id"><%=j %></span>
				     <%} %>    
				    </div>

  					<div class="seats-container">
					    <div class="screen-container" style="left:245px;">
					      <div class="screen">银幕中央</div>
					      <div class="c-screen-line"></div>
					    </div>

					    <div class="seats-wrapper">
					    <%for(int j=0;j<act.getAct().getHalls().get(i).getSeats().length;j++){%>
					        <div class="row">
					        <%for(int k=0;k<act.getAct().getHalls().get(i).getSeats()[j].length;k++){%>
					            <span class=<%=act.getAct().getHalls().get(i).getSeats()[j][k].getState() %> 
					              data-column-id=<%=act.getAct().getHalls().get(i).getSeats()[j][k].getRealY() %>
					              data-row-id=<%=act.getAct().getHalls().get(i).getSeats()[j][k].getRealX() %>
					              data-seat-col=<%=act.getAct().getHalls().get(i).getSeats()[j][k].getPosColum() %>
					              data-seat-row=<%=act.getAct().getHalls().get(i).getSeats()[j][k].getPosRow() %>
					              data-oid=<%=act.getAct().getHalls().get(i).getSeats()[j][k].getOid() %>
					              data-price=<%=act.getAct().getHalls().get(i).getSeats()[j][k].getPrice() %>
					              data-org=<%=act.getAct().getHalls().get(i).getSeats()[j][k].getOrgState() %>
					            ></span>
					        <%} %>
					        </div>
					    <%} %>
					        
					    </div>
  					</div>

				</div>

        	</div>
			<%} %>
	      	<div class="side" id="sidebar">
		        <div class="movie-info clearfix">
			          <div class="poster">
			            	<img src=<%=act.getAct().getPath() %>>
			          </div>
			          <div class="content">
			          	<p class="name text-ellipsis"><%=act.getAct().getName() %></p>
			          </div>
		        </div>
		
		        <div class="show-info">
		          <div class="info-item">
		            <span>场馆:</span>
		            <span class="value text-ellipsis"><%=act.getAct().getName() %></span>
		          </div>
		          <div class="info-item">
		            <span>展厅 :</span>
		            <span class="value text-ellipsis"><%=act.getAct().getHalls().get(0).getName() %></span>
		          </div>
		          
		        </div>
		
		        <div class="ticket-info">
		          <div class="no-ticket">
		            <p class="buy-limit">座位：一次最多选6个座位</p>
		            <p class="no-selected">请<span>点击左侧</span>座位图选择座位</p>
		          </div>
		          <div class="has-ticket" style="display:none">
		            <span class="text">座位：</span>
		            <div class="ticket-container" data-limit="6" id="ticket_box">
		            	
		            </div>
		          </div>
		
		          <div class="total-price">
		            <span>总价 :</span>
		            <span class="price" id="sum">0</span>
		          </div>
		        </div>
		
		        <div class="confirm-order">
		            <div class="confirm-btn disable">确认购买</div>
	        	</div>
	      	</div>
        </div>
        
    </div>
	<div class="modal-container" style="display:none;" id="little_board">
		<div class="modal">
		    <span class="icon"></span>
		
		    <p class="tip" id="message">每单只能选择6张哦！</p>
		
		</div>
    </div>
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</div>
	</body>
</html>