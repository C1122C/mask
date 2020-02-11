<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<jsp:useBean id="city" type="business.CityBean" scope="session"></jsp:useBean>
<jsp:useBean id="user" type="business.UserInfoBean" scope="session"></jsp:useBean>
<jsp:useBean id="pay" type="business.PayBean" scope="session"></jsp:useBean>
<jsp:useBean id="username" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_login.tld" prefix="MyTag" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>MASK</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
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
        		
            
        	
        	$("body").on('click','#pay',function(){
        		var oid=$(this).attr("data-oid");
        		var v1=$("#counter");
        		var v2=document.getElementById("account").value;
        		var v3=document.getElementById("pwd").value
        		var info=$("#info").attr("name");
        		if(info==null||info==""){
            		info="no";
            	}
        		var sum=$("#total").html();
        		var can=true;
        		if(v2==null||v2=="") {
        			alert("请输入账号");
	                can=false;
                }
        		else{
        			if(v3==null||v3=="") {
            			alert("请输入密码");
    	                can=false;
                    }
            	}
        		if(can){
        			var sec=parseInt(v1.attr("data-sec"));
                    var min=parseInt(v1.attr("data-min"));
                    var state=parseInt(v1.attr("data-state"));
            		var par=oid+":";
            		if(state==0){
            			par=par+v2+":"+v3+":"+min+":"+sec+":"+info+":"+sum;
            			//alert(par);
    	                pay_money(par);
    	        		
            		}
            		
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
	<div style="height: 15px;"></div>
	<a data-sec=<%=pay.getSec() %> data-min=<%=pay.getMin() %> data-oid=<%=pay.getOid() %> data-state="0" id="counter"></a>
	<a id="info" name=<%=pay.getInfo() %>></a>
	<div class="pay_id_h">
		<div class="h_container">
			<div class="h_title">支付平台</div>
		</div>
	</div>
	<div class="pay_id_container">
		
		<div class="d1">
			<div class="d2">
				<div class="d3">
					<div class="d4">
						<div class="d5">
							<span class="s2">订单号：<%=pay.getOid() %></span>
						</div>
						<span class="s4">
							<strong class="s5" id="total"><%=pay.getSum() %></strong>元
						</span>
					</div>
				</div>
			</div>
			<div class="d6">
				<div class="d7">
					<div class="d8">
						<form>
							<div class="d9">
								<div class="d10">
									<div style="width:100%">
										<div class="d11">
											<p class="p1">登录支付平台付账</p>
										</div>
									</div>
								</div>
								<div style="padding: 0;">
									<div>
										<div class="d12">
											<label class="d15">帐户名：</label>
											<%if(user.getUser().getPayID()==null){ %>
											<input class="i1" id="account">
											<%}else{ %>
											<input class="i1" id="account" value=<%=user.getUser().getPayID() %>>
											<%} %>
										</div>
										<div class="d13">
											<label class="d15">支付密码：</label>
											<span class="s6">
												<input type="password" class="i2" id="pwd">
											</span>
										</div>
										<div class="d14">
											<div class="d16">
												<a class="a1">
													<span class="s7" data-oid=<%=pay.getOid() %> id="pay">支付</span>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
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