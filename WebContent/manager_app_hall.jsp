<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="cur_app" type="business.AppBean" scope="session"></jsp:useBean>
<jsp:useBean id="mname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginm.tld" prefix="MyTagm" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>MASK</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/venue.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function(){
	        	$("body").on('click','#pass',function(){
	        		var id=$(this).attr("name");
	        		pass(id);
	        		alert("申请已通过");
	        		window.opener=null;
	        		window.open('','_self');
	        		window.close();
	            });
	        	$("body").on('click','#down',function(){
	        		var id=$(this).attr("name");
	        		turn_down(id);
	        		alert("申请已驳回");
	        		window.opener=null;
	        		window.open('','_self');
	        		window.close();
	            });
	        	
	        	
	        });
	              
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
	
	
	
	<div class="s_container">

        <div class="main clearfix">
	        <div class="self-info" style="width:1200px;">
	          	<div class="date-picker userexinfo-form-section">
					<span style="font-size:16px;">
						场馆名称：
					</span>
					<span>
    					<div class="ui-select">
							<select name="year" class="ui-select" id="v_name">
								<option value=<%=cur_app.getApp().getHall().getName() %>><%=cur_app.getApp().getHall().getName() %></option>
							</select>
						</div>
					</span>
					
				</div>
	         </div>
        	<div class="hall" style="width:1200px;">
        		
				<div class="seats-block" id=<%=cur_app.getApp().getHall().getName() %>>
			  		<div class="row-id-container">
				     <%for(int j=0;j<cur_app.getApp().getHall().getSeats().length;j++){ %>
				        <span class="row-id"><%=j %></span>
				     <%} %>   
			  		</div>

  					<div class="seats-container">
					    <div class="screen-container" style="left:245px;">
					      <div class="screen">银幕中央</div>
					      <div class="c-screen-line"></div>
					    </div>

					    <div class="seats-wrapper">
					        <%for(int j=0;j<cur_app.getApp().getHall().getSeats().length;j++){%>
					        <div class="row">
					        <%for(int k=0;k<cur_app.getApp().getHall().getSeats()[j].length;k++){%>
					            <span class=<%=cur_app.getApp().getHall().getSeats()[j][k].getState() %> 
					              data-column-id=<%=cur_app.getApp().getHall().getSeats()[j][k].getRealY() %>
					              data-row-id=<%=cur_app.getApp().getHall().getSeats()[j][k].getRealX() %>
					              data-seat-col=<%=cur_app.getApp().getHall().getSeats()[j][k].getPosColum() %>
					              data-seat-row=<%=cur_app.getApp().getHall().getSeats()[j][k].getPosRow() %>
					              data-oid=<%=cur_app.getApp().getHall().getSeats()[j][k].getOid() %>
					              data-price=<%=cur_app.getApp().getHall().getSeats()[j][k].getPrice() %>
					            ></span>
					        <%} %>
					        </div>
					    <%} %>
					    </div>
  					</div>

				</div>
				
        	</div>

	      	
        </div>
    </div>
    <div style="height:10px;width:100%;"></div>
	<input name=<%=cur_app.getApp().getId() %> type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:950px;margin-top:20px;margin-bottom:20px;"value="通过" id="pass">
	<input name=<%=cur_app.getApp().getId() %> type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:50px;background-color:gray;color:black;margin-top:20px;margin-bottom:20px;"value="驳回" id="down">
	
	</body>
</html>