<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<jsp:useBean id="act" type="business.ActBean" scope="session"></jsp:useBean>
<jsp:useBean id="vname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginv.tld" prefix="MyTagv" %>
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
	        	$("body").on('change','#date',function(){
	        		var t=$(this).val();
	                var node1=document.getElementById("t_time");
                    node1.setAttribute("name",t);
                    $("#"+t).show();
                    $("#"+t).siblings().hide();
                    
	            });

	        	$("body").on('click','.add2',function(){
	        		$(this).removeClass();
	                $(this).addClass("selected");
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var v1=$("#sellinfo").attr("name");
	                v1=v1+ares+":";
	                $("#sellinfo").attr("name",v1);
	                
	            });
	        	$("body").on('click','.add3',function(){
	        		$(this).removeClass();
	                $(this).addClass("selected");
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var v1=$("#sellinfo").attr("name");
	                v1=v1+ares+":";
	                $("#sellinfo").attr("name",v1);
	            });
	        	$("body").on('click','.add4',function(){
	        		$(this).removeClass();
	                $(this).addClass("selected");
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var v1=$("#sellinfo").attr("name");
	                v1=v1+ares+":";
	                $("#sellinfo").attr("name",v1);
	            });
	        	$("body").on('click','.add5',function(){
	        		$(this).removeClass();
	                $(this).addClass("selected");
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var v1=$("#sellinfo").attr("name");
	                v1=v1+ares+":";
	                $("#sellinfo").attr("name",v1);
	            });
	        	$("body").on('click','.add6',function(){
	        		$(this).removeClass();
	                $(this).addClass("selected");
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var v1=$("#sellinfo").attr("name");
	                v1=v1+ares+":";
	                $("#sellinfo").attr("name",v1);
	            });
	        	$("body").on('click','.add7',function(){
	        		$(this).removeClass();
	                $(this).addClass("selected");
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var v1=$("#sellinfo").attr("name");
	                v1=v1+ares+":";
	                $("#sellinfo").attr("name",v1);
	            });
	        	$("body").on('click','.add8',function(){
	        		$(this).removeClass();
	                $(this).addClass("selected");
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var v1=$("#sellinfo").attr("name");
	                v1=v1+ares+":";
	                $("#sellinfo").attr("name",v1);
	            });
	        	$("body").on('click','.add10',function(){
	        		$(this).removeClass();
	                $(this).addClass("selected");
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var v1=$("#sellinfo").attr("name");
	                v1=v1+ares+":";
	                $("#sellinfo").attr("name",v1);
	            });
	        	$("body").on('click','.add9',function(){
	        		$(this).removeClass();
	                $(this).addClass("selected");
	                var a1=$(this).attr("data-column-id");
	                var a2=$(this).attr("data-row-id");
	                var a3=$(this).attr("data-seat-col");
	                var a4=$(this).attr("data-seat-row");
	                var a5=$(this).attr("data-price");
	                var ares=a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5;
	                var v1=$("#sellinfo").attr("name");
	                v1=v1+ares+":";
	                $("#sellinfo").attr("name",v1);
	            });
	        	
	        	
	        	

	        	$("body").on('click','#sub_in',function(){
		        	var place=$("#index").val();
		        	var v=document.getElementById("aid");
	        		var par=v.name;
	        		if(place==""||place==null){
	                	alert("请输入订单号");
		            }
	        		else{
	        			var con=confirm("确认检票?");
		        		if(con){
		        			checked(place,par);
		        		}
	        		}
	        		
	            });

	        	$("body").on('click','#sale',function(){
	        		var user=prompt("请输入Mask会员账号（0表示非会员）");
	        		var par=user;
	        		var v=document.getElementById("aid");
	        		var par=par+":"+v.name;
	        		var v1=document.getElementById("t_time");
	        		var par=par+":"+v1.name+":";
	        		var vv=$("#sellinfo").attr("name");
	        		par=par+vv;
	        		//alert(par);
	        		sell(par);
	        		$("#sellinfo").attr("name","");
	        		
	            });
	        	
	        	$("body").on('click','#complete',function(){
		        	window.opener=null;
            		window.open('','_self');
            		window.close();
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
	<a id="t_time" name=<%=act.getAct().getHalls().get(0).getShowTime() %>></a>
	<a id="aid" name=<%=act.getAct().getId() %>></a>
	<a id="sellinfo" name=""></a>
	
	<div class="s_container">
		<%for(int i=0;i<act.getAct().getHalls().size();i++){ 	
        		if(i==0){%>
        		<div class="main clearfix" id=<%=act.getAct().getHalls().get(i).getShowTime() %> data-section-name=<%=act.getAct().getHalls().get(i).getName() %>>
        		<% }else{%>
        		<div class="main clearfix" id=<%=act.getAct().getHalls().get(i).getShowTime() %> data-section-name=<%=act.getAct().getHalls().get(i).getName() %> style="display:none;">
        		<% }%>
        
	        <div class="self-info" style="width:1200px;">
	          	<div class="date-picker userexinfo-form-section">
					<span>
						<div class="ui-select">
							<select id="date" class="ui-select">
							<%for(int j=0;j<act.getAct().getHalls().size();j++){ %>
								<option><%=act.getAct().getHalls().get(j).getShowTime() %></option>
							<%} %>
							</select>
						</div>
					</span>
					<span>
						<div class="ui-select">
							<input id="index" class="ui-select" placeholder="输入订单号" style="border:0;width:160px;magin-left:30px;margin-right:30px;">
						</div>
					</span>
					
					<span style="border:0;">
						<div class="ui-select" style="border:0;min-width: 100px;min-height:20px;">
							<input id="sub_in" type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-top:0px;height:40px;"value="检票">
						</div>
					</span>
					
				</div>
	         </div>
        	<div class="hall" style="width:1200px;">
        		<div class="seat-example">
        		<%for(int j=0;j<act.getAct().getHalls().get(i).getB_pic().size();j++){%>
		            <div class=<%=act.getAct().getHalls().get(i).getB_pic().get(j) %>>
		            	<span><%=act.getAct().getHalls().get(i).getPrices().get(j)+"元" %></span>
		            </div>
		        <%} %>
		            <div class="sold-example example">
		            	<span>已售座位</span>
		            </div>
		            <div class="add-example1 example">
		            	<span>已检座位</span>
		            </div>
		            <div class="selected-example example">
		            	<span>已选座位（出售）</span>
		            </div>
		            <div class="c_s-example example">
		            	<span>已选座位（检票）</span>
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

	      	
        </div>
        <%} %>
    </div>
    <div style="height:30px;width:100%;"></div>
	<input type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:750px;"value="出售" id="sale">
	<input type="submit" class="form-save-btn" style="min-width:100px;float:left;margin-left:50px;"value="完成" id="complete">
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>