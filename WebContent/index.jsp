<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>MASK</title>
		<link href="CSS/Index.css" rel="stylesheet"  type="text/css">
		<script src="JS/index.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
	        	$(".log").click(function(){
	        		var type=$(this).attr("name");
		        	var id="";
	                var pwd="";
	                if(type=="l"){
		        		id=document.getElementById("account").value;
		                pwd=document.getElementById("password").value;
		        	}
		        	else{
		        		id=document.getElementById("mail").value;
		                
			        }
	                
	                
	               
	                if(id==null||id=="") {
		                if(type=="l"){
		                	alert("请输入用户名");
			            }
		                else{
		                	alert("请输入邮箱");
			            }
	                    
	                }
	                else if(pwd==null||pwd==""){
		                if(type=="l"){
		                	alert("请输入密码");
		                }
		                else{
		                	val_mail(id); 
			            }	
	                    
	                }
	                else{
		                //alert("JSP OK");
	                	login(id,pwd);
	                	
		            }
	                
	            });
	        	$("body").on('click','#ll',function(){
	        		document.getElementById("ll").setAttribute("style","color: #f44ebc;");
	        		document.getElementById("rr").setAttribute("style","color: #7f8c8d;");
	        		
	        		$(this).parent().next().show();
	        		$(this).parent().next().next().hide();
	            });
	        	$("body").on('click','#rr',function(){
	        		document.getElementById("rr").setAttribute("style","color: #f44ebc;");
	        		document.getElementById("ll").setAttribute("style","color: #7f8c8d;");
	        		
	        		$(this).parent().next().hide();
	        		$(this).parent().next().next().show();
	            });
	        	
	        });
	              
    </script>
	</head>
	<body>
		<img src="image/login-logo.png" style="margin-top:10px;margin-left:10px;">
		<div class="log_part" style="background-image: url(image/back_ground.jpg);">
            <div class="log-pan">
            	<ul class="logselect">
            		<li id="ll" style="color:#f44ebc;">登录</li>
            		<li id="rr">注册</li>
            		<li><a href="./venue_reg.jsp" target="_blank">场馆注册</a></li>
            	</ul>
            	<form method="post" action="/mask/Login">
                    <div style="height: 20px;"></div>
                    
                    <div class="log-input" style="margin-top: 50px;">
                        <span class="fon">账号</span>
                        <span><input id="account"  name="account" type="text"/> </span>
                    </div>
                    <div class="log-input">
                        <span class="fon">密码</span>
                        <span><input id="password" type="password"  name="pwd"/> </span>
                    </div>
                    <div class="log-button">
                        <span><a class="log" name="l"><input id="login-button" class="fon" value="登录" type="button"/></a></span>
                    </div>
            	</form>
            	<form method="post" action="/mask/Login"  style="display:none;">
                    <div style="height: 20px;"></div>
                    
                    <div class="log-input" style="margin-top: 50px;">
                        <span class="fon">邮箱</span>
                        <span><input id="mail"  name="mail" type="text"/> </span>
                    </div>
                    <div class="log-button">
                        <span><a class="log" name="r"><input id="reg-button" class="fon" value="验证邮箱" type="button"/></a></span>
                    </div>
                    
            	</form>
            </div>
        </div>
		
	</body>
</html>