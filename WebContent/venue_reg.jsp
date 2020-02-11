<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/venue.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
	        	
	        	$("body").on('click','#hall',function(){
		        	
	        		var name=$("#name").val();
	        		var pwd=$("#pwd").val();
	        		var phone=$("#phone").val();
	        		var pay_id=$("#pay_id").val();
	        		var pro=$("#pro").val();
	        		var city=$("#city").val();
	        		var dis=$("#dis").val();
	        		var det=$("#det").val();
	        		if(name==""||name==null||pwd==""||pwd==null||phone==""||phone==null||pay_id==""||pay_id==null||pro==""||pro==null||city==""||city==null||dis==""||dis==null||det==""||det==null){
		        		alert("请完整填写信息");
		        	}
	        		else{
	        			add_venue_info(name,pwd,phone,pay_id,pro,city,dis,det);
		        		window.open('hall_add.jsp');
		        	}
	        		
	            });

	        	$("body").on('click','#sub',function(){
	        		var name=$("#name").val();
	        		var pwd=$("#pwd").val();
	        		var phone=$("#phone").val();
	        		var pay_id=$("#pay_id").val();
	        		var pro=$("#pro").val();
	        		var city=$("#city").val();
	        		var dis=$("#dis").val();
	        		var det=$("#det").val();
	        		if(name==""||name==null||pwd==""||pwd==null||phone==""||phone==null||pay_id==""||pay_id==null||pro==""||pro==null||city==""||city==null||dis==""||dis==null||det==""||det==null){
		        		alert("请完整填写信息");
		        	}
	        		else{
	        			add_venue_info(name,pwd,phone,pay_id,pro,city,dis,det);
		        	}
	        		
	        		
	            });
	        	
	        });
    	</script>
	</head>
	<body>
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
            		
            		<div class="older-lieb r" style="min-height:500px;width:1200px;">
            			<div class="self-info">
            				<div class="userexinfo-form-section">
            					<p>场馆名称：</p>
					            <span>
					              <input type="text" id="name" class="ui-checkbox">
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
            					<p>密码：</p>
					            <span>
					              <input type="password" id="pwd" class="ui-checkbox">
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
            					<p>联系电话：</p>
					            <span>
					              <input type="text" id="phone" class="ui-checkbox">
					            </span>
          					</div>
          					<div class="userexinfo-form-section">
            					<p>支付账号：</p>
					            <span>
					              <input type="text" id="pay_id" class="ui-checkbox">
					            </span>
          					</div>
          					
          					<div class="date-picker userexinfo-form-section">

  								<p>地址：</p>
  								<span>
    								<div class="ui-select">
								      <input type="text" id="pro" class="ui-checkbox">
    								</div>
    								<span class="tip">省</span>
  								</span>
  								<span>
    								<div class="ui-select">
      									<input type="text" id="city" class="ui-checkbox">
    								</div>
    								<span class="tip">市</span>
  								</span>
  								<span>
								    <div class="ui-select">
								      <input type="text" id="dis" class="ui-checkbox">
								    </div>
								    <span class="tip">区</span>
								  </span>
								  <span>
								    <div class="ui-select">
								      <input type="text" id="det" class="ui-checkbox">
								    </div>
								    <span class="tip">街道、门牌</span>
								  </span>
							</div>

          					
          					<input type="submit" class="form-save-btn" style="min-width:150px;float:left;margin-left:50px;margin-top:90px;margin-bottom:10px;"value="添加展厅信息" id="hall">
          					<input type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:50px;margin-top:90px;margin-bottom:10px;"value="提交" id="sub">
            			</div>
            		</div>

        		</div>
    		</div>
        </div>
	</div>
	
	</body>
</html>