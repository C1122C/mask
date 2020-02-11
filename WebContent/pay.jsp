<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<jsp:useBean id="city" type="business.CityBean" scope="session"></jsp:useBean>
<jsp:useBean id="user" type="business.UserInfoBean" scope="session"></jsp:useBean>
<jsp:useBean id="order" type="business.CurOrderBean" scope="session"></jsp:useBean>
<jsp:useBean id="username" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_login.tld" prefix="MyTag" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>MASK</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/order.js"></script>
		<script src="JS/act.js"></script>
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
	        		
	        	$("body").on('click','.you-quan',function(){
	        		var oid=$(this).attr("data-oid");
	        		var cid=$(this).attr("data-cid");
	        		var mon=parseFloat($(this).attr("data-mon"));
	        		var v=document.getElementById("sum");
	        		var v1=document.getElementById("coupon_cut");
	        		var offset=parseFloat(v.innerHTML);
	        		var offset1=parseFloat(v1.innerHTML);
	        		var cut=$("#user_cut_num").val();
	                if($(this).hasClass("chosen")){
		                $(this).removeClass("chosen");
		                offset=(offset/cut+mon)*cut;
		                offset1=offset1-mon;
		            }
	                else{
	                	$(this).addClass("chosen");
	                	offset=(offset/cut-mon)*cut;
	                	offset1=offset1+mon;
		            }
	                v.innerHTML=offset+""; 
	                v1.innerHTML=offset1+"";
	            });	
	            
	        	
	        	$("body").on('click','.pay-btn',function(){
	        		var oid=$(this).attr("data-oid");
	        		var arrUl = jQuery(".you-quan");
	        		var v1=$("#counter");
	        		var sec=parseInt(v1.attr("data-sec"));
	                var min=parseInt(v1.attr("data-min"));
	                var state=parseInt(v1.attr("data-state"));
	        		var par=oid+":"+min+":"+sec+":";
	        		if(state==0){
		        		var l5=0;
	        			jQuery.each(arrUl, function(){
		        			if(jQuery(this).hasClass("chosen")){
		        				par=par+jQuery(this).attr("data-cid")+"-"+jQuery(this).attr("data-mon")+":";
		        				l5=1;
				            }
				           
			            });
			            if(l5==0){
				            par=par+"no:";
				        }
		        		var t=$("#sum").html();
		        		if(t==null||t==""){
		        			var tar1 = document.getElementById("sum");
		        			t=tar1.innerHTML;
			        	}
		        		par=par+t;
		        		$("#loading").fadeIn(500);
		        		//alert(par);
		                connect_pay(par);
		                $("#loading").fadeOut(500);
		        		
	        		}
	        		
	        	});

	        	$("body").on('click','#i_know',function(){
	        		window.location.href='order_scan.jsp';
	            });
	        	

	        });

	        setInterval(function () {
		        //alert("in");
        		var v1=$("#counter");
        		var sec=parseInt(v1.attr("data-sec"));
                var min=parseInt(v1.attr("data-min"));
                var oid=v1.attr("data-oid");
                var state=parseInt(v1.attr("data-state"));
        		//alert(min);
        		if(state==0){
        			if(sec==0){
        				if(min==0){
            				$("#counter").attr("data-state","1");
            				$("#loading").fadeIn(500);
        					time_out_cancle(oid);
        					$("#loading").fadeOut(500);
        				}
        				else{
        					min=min-1;
        					sec=59;
        				}
        			}
        			else{
        				sec=sec-1;
        			}
        			
        			v1.attr("data-sec",sec+"");
        			v1.attr("data-min",min+"");
        			
        			var v2=document.getElementById("m_place");
        	        v2.innerHTML=min+""; 
        	        
        	        var v3=document.getElementById("s_place");
        	        v3.innerHTML=sec+"";
        	    }
        		else{
        			clearInterval();
        		}
        	}, 1000);      
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
	
	<div style="height: 15px;margin-top:150px;"></div>
	
	<!--  -->
	
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
            <div class="step done">
			    <span class="step-num done">3</span>
			    <div class="bar"></div>    
			    <span class="step-text">15分钟内付款</span>    
            </div>
            <div class="step last ">
			    <span class="step-num">4</span>
			    <div class="bar"></div>    
			    <span class="step-text">现场检票</span>    
            </div>
		</div>
		
		<div class="count-down-wrapper">
		    <div class="count-down" data-sec="59" data-min="14" data-oid=<%=order.getOid() %> data-state="0" id="counter">
		      <p class="time-left">
		      	请在
		      	<span class="minute" id="m_place">14</span>
		      	分钟
		        <span class="second" id="s_place">59</span>
		                            秒内完成支付
		      </p>
		      <p class="tip">超时订单会自动取消，请尽快支付</p>
		    </div>
  		</div>

  		<p class="warning">请仔细核对场次信息，<span class="attention">出票后退票和改签将扣取一定费用</span></p>
  
    	<table class="order-table">
		    <thead>
		      <tr>
		        <th style="min-width:200px;">名称</th>
		        <th style="min-width:200px;">时间</th>
		        <th style="min-width:200px;">地点</th>
		        <th style="min-width:200px;">座位</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td class="movie-name"><%=order.getAct_name() %></td>
		        <td class="showtime"><%=order.getAct_time() %></td>
		        <td class="cinema-name"><%=order.getVname() %></td>
		        <td>
		          <span class="halll"><%=order.getHall_name() %></span>
		          <div class="seats">
		            <div>
		                <span class=""><%=order.getSeat() %></span>
		            </div>
		            <div>
		            </div>
		          </div>
		        </td>
		      </tr>
		    </tbody>
  		</table>

		<!-- 优惠券 -->
        <div class="my-youhuiquan">
            <div class="qingdan-top">
                <p class="top-tit">优惠券</p>
            </div>
            
            <div class="youhuiquan-you">
                      
                <div class="quanquan">
                <%for(int i=0;i<order.getCoupon().size();i++){%>  
                     <button class="you-quan" data-oid=<%=order.getOid() %> data-cid=<%=order.getCoupon().get(i).getId() %> data-mon=<%=order.getCoupon().get(i).getValue() %>>
                        <div class="quan-tit"><%=order.getCoupon().get(i).getName() %></div>
                        <div class="l quan-prive">￥<span class="quan-prive1"><%=order.getCoupon().get(i).getValue() %></span></div>
                        <div class="r qisi"><span class="quan-qian r">满<span class="xiane"><%=order.getCoupon().get(i).getCondition() %></span>元可用</span><br>有效期至：<span class="quan-time"><%=order.getCoupon().get(i).getValid() %></span></div>
                     </button>
                <%} %>    
                </div>
            
            </div>
        </div>
        <input id="user_cut_num" value=<%=user.getUser().getCut() %> style="display:none;" />
	    <div class="right">
		    <div class="price-wrapper">
		      <span>实际支付 :</span>
		      <span class="price" id="sum"><%=order.getSum()*user.getUser().getCut() %></span>
		      <span>(用户等级折扣:</span>
		      <span id="user_cut"><%=user.getUser().getCutToShow() %></span>
		      <span>;优惠券折扣:</span>
		      <span id="coupon_cut">0</span>
		      <span>)</span>
		    </div>
		    <div><div class="pay-btn" data-oid=<%=order.getOid() %>>确认支付</div></div>
	    </div>    
    </div>
            
		

    <div class="modal-container" style="display:none;" id="little_board1">
		<div class="modal">
		    <span class="icon"></span>
		
		    <p class="tip">支付超时，该订单已失效，请重新购买</p>
		
		    <div class="ok-btn btn" id="i_know">我知道了</div>
		</div>
    </div>
	<div class="modal-container" style="display:none;" id="little_board">
		<div class="modal">
		    <span class="icon"></span>
		
		    <p class="tip">请重新尝试</p>
		
		</div>
    </div>	
    
	
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>