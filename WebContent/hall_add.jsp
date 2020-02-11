<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="new_hall" type="business.HallBean" scope="session"></jsp:useBean>
<jsp:useBean id="vname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginv.tld" prefix="MyTagv" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>MASK</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/venue.js"></script>
		<script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
				$("body").on('click','#draw',function(){
					var name=$("#h_name").val();
	        		var num=$("#s_num").val();
	        		var seat=$("#h_seat").val();
	                if(name==""||name==null){
		                alert("请输入展厅名称");
		            }
	                else{
	                	if(num==""||num==null){
	                		alert("请输入展厅座位数");
		              	}
	                	else{
	                		if(seat==""||seat==null){
	                			alert("请输入展厅信息");
	                		}
	                		else{
		                		//alert(name);
		                		//alert(num);
		                		//alert(seat);
	                			$("#loading").fadeIn(500);
	                			draw_a_hall(name,num,seat);
	    	                	$("#loading").fadeOut(500);
		                	}
		              	}
		            }
	                
	                
	            });	
	        	$("body").on('click','#continue',function(){
	        		var name=$("#h_name").val();
	        		var num=$("#s_num").val();
	        		var seat=$("#h_seat").val();
	                if(name==""||name==null){
		                alert("请输入展厅名称");
		            }
	                else{
	                	if(num==""||num==null){
	                		alert("请输入展厅座位数");
		              	}
	                	else{
	                		if(seat==""||seat==null){
	                			alert("请输入展厅信息");
	                		}
	                		else{
	                			save_a_hall(name,num,seat);
		                		$(".row-id-container").empty();
		                		$(".seats-wrapper").empty();
		                		$(this).next().hide();
		                		$(this).hide();
		                	}
		              	}
		            }
	        		
	            });
	        	$("body").on('click','#complete',function(){
	        		var name=$("#h_name").val();
	        		var num=$("#s_num").val();
	        		var seat=$("#h_seat").val();
	                if(name==""||name==null){
		                alert("请输入场馆名称");
		            }
	                else{
	                	if(num==""||num==null){
	                		alert("请输入场馆座位数");
		              	}
	                	else{
	                		if(seat==""||seat==null){
	                			alert("请输入座位信息");
	                		}
	                		else{
		                		save_a_hall(name,num,seat);
		                		window.opener=null;
		                		window.open('','_self');
		                		window.close();
		                	}
		              	}
		            }
	            });

	        	
	        });
	              
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
	
	
	
	<div class="s_container">

        <div class="main clearfix">
	        <div class="self-info" style="width:1200px;">
	          	<div class="date-picker userexinfo-form-section">
					<span>
						<div class="ui-select">
						<%if(new_hall.getHall().getName()==null){ %>
							<input id="h_name" class="ui-select" placeholder="场馆名称" style="border:0;">
					    <%}else{ %>
							<input id="h_name" class="ui-select" placeholder="场馆名称" style="border:0;" value=<%=new_hall.getHall().getName() %>>
						<%} %>
						</div>
					</span>
					<span>
						<div class="ui-select">
						<%if(new_hall.getHall().getSeatNum()==0){ %>
							<input id="s_num" class="ui-select" placeholder="座位数" style="border:0;">
						<%}else{ %>	
							<input id="s_num" class="ui-select" placeholder="座位数" style="border:0;" value=<%=new_hall.getHall().getSeatNum() %>>
						<%} %>
						</div>
					</span>
					
				</div>
				<div class="date-picker userexinfo-form-section">
					<span>
						<div class="ui-select">
						<%if(new_hall.getOriginal()==null){ %>
							<input id="h_seat" class="ui-select" placeholder="座位信息" style="border:0;width:500px;" maxlength="30000">
						<%}else{ %>	
							<input id="h_seat" class="ui-select" placeholder="座位信息" style="border:0;width:500px;" maxlength="30000" value=<%=new_hall.getOriginal() %>>
						<%} %>
						</div>
					</span>
					
					<span style="border:0;">
						<div class="ui-select" style="border:0;min-width: 100px;min-height:20px;">
							<input type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-top:2px;height:40px;cursor:pointer;" id="draw" value="确定">
						</div>
					</span>
					
				</div>
	         </div>
        	<div class="hall" style="width:1200px;">
        		
				<div class="seats-block" data-cols="26" data-section-id="1" data-section-name="普通区" data-seq-no="201802270302819">
			  		<div class="row-id-container">
			  		<%if(!(new_hall.getHall().getSeats()==null)){ %>
				    <%for(int j=0;j<new_hall.getHall().getSeats().length;j++){ %>
				        <span class="row-id"><%=j %></span>
				     <%} %> 
				     <%} %>
			  		</div>

  					<div class="seats-container">
					    <div class="screen-container" style="left:245px;">
					      <div class="screen">银幕中央</div>
					      <div class="c-screen-line"></div>
					    </div>

					    <div class="seats-wrapper">
					    <%if(!(new_hall.getHall().getSeats()==null)){ %>
					    <%for(int j=0;j<new_hall.getHall().getSeats().length;j++){%>
					        <div class="row">
					        <%for(int k=0;k<new_hall.getHall().getSeats()[j].length;k++){%>
					            <span class=<%=new_hall.getHall().getSeats()[j][k].getState() %> 
					              data-column-id=<%=new_hall.getHall().getSeats()[j][k].getRealY() %>
					              data-row-id=<%=new_hall.getHall().getSeats()[j][k].getRealX() %>
					              data-seat-col=<%=new_hall.getHall().getSeats()[j][k].getPosColum() %>
					              data-seat-row=<%=new_hall.getHall().getSeats()[j][k].getPosRow() %>
					              data-oid=<%=new_hall.getHall().getSeats()[j][k].getOid() %>
					              data-price=<%=new_hall.getHall().getSeats()[j][k].getPrice() %>
					            ></span>
					        <%} %>
					        </div>
					    <%} %>
					    <%} %>
					    </div>
  					</div>

				</div>

        	</div>

	      	
        </div>
    </div>
    <div style="height:30px;width:100%;"></div>
	<input type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:950px;margin-top:20px;margin-bottom:20px;"value="继续添加" id="continue">
	<input type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:50px;margin-top:20px;margin-bottom:20px;"value="完成" id="complete">
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>