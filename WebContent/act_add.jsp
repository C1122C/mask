<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="venue" type="business.VenueBean" scope="session"></jsp:useBean>
<jsp:useBean id="vname" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_loginv.tld" prefix="MyTagv" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mask</title>
		<link href="CSS/user.css" rel="stylesheet"  type="text/css">
		<script src="JS/user.js"></script>
	    <script src="JS/jquery-3.2.1.js"></script>
	    <script type="text/javascript">
		    
	        $(document).ready(function(){
	        	
	        	$("body").on('click','#logout',function(){
	        		logout();
	            });
	        	
	        	$(".photo_click").click(function(){
	                
	                $(this).siblings().show();
	            });

	        	
	        	
	        	
	        	$("body").on('click','.c_class',function(){
	        		$(this).next().remove();
	        		$(this).remove();
	            });
	        	
	            var img;
	            $("#picbtn").change(function(){
	                $(this).prev().empty();
	                $(this).parent().find(".photo_click").hide();
	                var tar1 = document.getElementById("photo_place");
	                var url = null ;
	                var file=$(this)[0].files[0];
	                //alert(filename+" "+file.type);
	                if (window.createObjectURL!=undefined) {
	                    url = window.createObjectURL(file) ;
	                } else if (window.URL!=undefined) {
	                    url = window.URL.createObjectURL(file) ;
	                } else if (window.webkitURL!=undefined) {
	                    url = window.webkitURL.createObjectURL(file) ;
	                }

	                //alert("ready to create");
	                var para = document.createElement("img");
	                para.setAttribute("src",url);
	                //alert(url);
	                para.setAttribute("with","180");
	                para.setAttribute("style","margin:auto;")
	                para.setAttribute("height","200");
	                tar1.appendChild(para);
	                img=file;
	                //alert(img);
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
	<div class="ol-older">
        <div class="older-w w">
            
            <ul class="older-l l">
                <a><li class="li-co3"><%=venue.getVenue().getName() %></li></a>
                <a href="./venue_statistic.jsp"><li class="old-li"><i class="i-ico">统计信息</i></li></a>
                <a href="./venue_act.jsp"><li class="old-li"><i class="i-ico">本馆演出</i></li></a>
                <a href="./act_add.jsp"><li class="coF old-li old-li-on"><i class="i-ico">发布活动</i></li></a>
                <a href="./venue_mod.jsp"><li class="old-li"><i class="i-ico">信息修改</i></li></a>
                <a href="./venue_app.jsp"><li class="old-li"><i class="i-ico">本馆申请</i></li></a>
                <a><li class="old-li"><i class="i-ico"  id="logout">退出登录</i></li></a>
            </ul>

            <div class="My-you">
            	<div class="div-xxk">
            		
            		<div class="older-lieb r" style="min-height:500px;">
            		<form method="post" name="upload" action="/mask/VenueInfo" enctype="multipart/form-data" id="picture_form">
            				<input name="type" type="text" value="10" style="display:none;" id="type"/>
            				<div class="self-info">
            					<div class="userexinfo-form-section">
	            					<p>活动名称：</p>
						            <span>
						              <input type="text" name="a_name" id="a_name" class="ui-checkbox">
						            </span>
	          					</div>
	          					<div class="date-picker userexinfo-form-section">

  								<p>展厅：</p>
	  								<span>
	    								<div class="ui-select">
									      <select name="a_hall" class="ui-select" id="a_hall" style="width:80px;">
									        <%for(int i=0;i<venue.getVenue().getHall().size();i++){ %>
									        <option value=<%=venue.getVenue().getHall().get(i).getName() %>><%=venue.getVenue().getHall().get(i).getName() %></option>
									        <%} %>
									      </select>
	    								</div>
	    							</span>
    							</div>
    							<div class="date-picker userexinfo-form-section">

	  								<p>类型：</p>
	    							<span>
	    								<div class="ui-select">
									      <select name="a_type" class="ui-select" id="a_type" style="width:80px;">
									        <option value="演唱会">演唱会</option>
									        <option value="音乐会">音乐会</option>
									        <option value="话剧舞台剧">话剧舞台剧</option>
									        <option value="舞蹈芭蕾">舞蹈芭蕾</option>
									        <option value="儿童亲子">儿童亲子</option>
									        <option value="戏曲综艺">戏曲综艺</option>
									        <option value="休闲娱乐">休闲娱乐</option>
									        <option value="体育赛事">体育赛事</option>
									      </select>
	    								</div>
	    							</span>
    							</div>
    							<div class="date-picker userexinfo-form-section">

  									<p>演出时间：</p>
	  								<span>
	    								<div class="ui-select">
									      <select name="a_s_year" class="ui-select" id="a_s_year">
									        <option value="2018">2018</option>
									        <option value="2019">2019</option>
									        <option value="2020">2020</option>
									        <option value="2021">2021</option>
									        <option value="2022">2022</option>
									      </select>
	    								</div>
	    								<span class="tip">年</span>
	  								</span>
								  	<span>
									    <div class="ui-select">
									      <select name="a_s_month" class="ui-select" id="a_s_month">
									        <option value="1">1</option>
									        <option value="2">2</option>
									        <option value="3">3</option>
									        <option value="4">4</option>
									        <option value="5">5</option>
									        <option value="6">6</option>
									        <option value="7">7</option>
									        <option value="8">8</option>
									        <option value="9">9</option>
									        <option value="10">10</option>
									        <option value="11">11</option>
									        <option value="12">12</option>
									      </select>
									    </div>
									    <span class="tip">月</span>
								  	</span>
								  	<span>
									    <div class="ui-select">
									      <select name="a_s_day" class="ui-select" id="a_s_day">
									        <option value="1">1</option>
									        <option value="2">2</option>
									        <option value="3">3</option>
									        <option value="4">4</option>
									        <option value="5">5</option>
									        <option value="6">6</option>
									        <option value="7">7</option>
									        <option value="8">8</option>
									        <option value="9">9</option>
									        <option value="10">10</option>
									        <option value="11">11</option>
									        <option value="12">12</option>
									        <option value="13">13</option>
									        <option value="14">14</option>
									        <option value="15">15</option>
									        <option value="16">16</option>
									        <option value="17">17</option>
									        <option value="18">18</option>
									        <option value="19">19</option>
									        <option value="20">20</option>
									        <option value="21">21</option>
									        <option value="22">22</option>
									        <option value="23">23</option>
									        <option value="24">24</option>
									        <option value="25">25</option>
									        <option value="26">26</option>
									        <option value="27">27</option>
									        <option value="28">28</option>
									        <option value="29">29</option>
									        <option value="30">30</option>
									        <option value="31">31</option>
								      </select>
								    </div>
								    <span class="tip">日</span>
								  	</span>
							</div>
							<div class="date-picker userexinfo-form-section">

  									<p>--</p>
	  								<span>
	    								<div class="ui-select">
									      <select name="a_e_year" class="ui-select" id="a_e_year">
									        <option value="2018">2018</option>
									        <option value="2019">2019</option>
									        <option value="2020">2020</option>
									        <option value="2021">2021</option>
									        <option value="2022">2022</option>
									      </select>
	    								</div>
	    								<span class="tip">年</span>
	  								</span>
								  	<span>
									    <div class="ui-select">
									      <select name="a_e_month" class="ui-select" id="a_e_month">
									        <option value="1">1</option>
									        <option value="2">2</option>
									        <option value="3">3</option>
									        <option value="4">4</option>
									        <option value="5">5</option>
									        <option value="6">6</option>
									        <option value="7">7</option>
									        <option value="8">8</option>
									        <option value="9">9</option>
									        <option value="10">10</option>
									        <option value="11">11</option>
									        <option value="12">12</option>
									      </select>
									    </div>
									    <span class="tip">月</span>
								  	</span>
								  	<span>
									    <div class="ui-select">
									      <select name="a_e_day" class="ui-select" id="a_e_day">
									        <option value="1">1</option>
									        <option value="2">2</option>
									        <option value="3">3</option>
									        <option value="4">4</option>
									        <option value="5">5</option>
									        <option value="6">6</option>
									        <option value="7">7</option>
									        <option value="8">8</option>
									        <option value="9">9</option>
									        <option value="10">10</option>
									        <option value="11">11</option>
									        <option value="12">12</option>
									        <option value="13">13</option>
									        <option value="14">14</option>
									        <option value="15">15</option>
									        <option value="16">16</option>
									        <option value="17">17</option>
									        <option value="18">18</option>
									        <option value="19">19</option>
									        <option value="20">20</option>
									        <option value="21">21</option>
									        <option value="22">22</option>
									        <option value="23">23</option>
									        <option value="24">24</option>
									        <option value="25">25</option>
									        <option value="26">26</option>
									        <option value="27">27</option>
									        <option value="28">28</option>
									        <option value="29">29</option>
									        <option value="30">30</option>
									        <option value="31">31</option>
								      </select>
								    </div>
								    <span class="tip">日</span>
								  	</span>
							</div>
								<div class="userexinfo-form-section">
	            					<p>时间：</p>
						            <span>
						              <input type="text" id="a_time" name="a_time" class="ui-checkbox">
						            </span>
	          					</div>
    							<div class="date-picker userexinfo-form-section">

	  								<p>售票方式：</p>
	    							<span>
	    								<div class="ui-select">
									      <select name="a_sale" class="ui-select" id="a_sale">
									        <option value="选座购票">选座购票</option>
									        <option value="系统配票">系统配票</option>
									      </select>
	    								</div>
	    							</span>
    							</div>
    							<div class="userexinfo-form-section">
	            					<p>描述：</p>
						            <span>
						              <input type="text" id="a_des" class="ui-checkbox" name="a_des">
						            </span>
	          					</div>
	          					<div class="userexinfo-form-section" style="height:370px;">
		            				<div class="up_col" style="padding-left: 0px;">
							           <div class="photo_click"></div>
							           <div class="photo_click1" style="display: none;text-align: center;" id="photo_place"></div>
							           <input type="file" name="file" class="follow_button" id="picbtn" style="display: none;margin-top:10px;width:100%;"/>
							        </div>
						        </div>
						        
							<div class="userexinfo-form-section">
	            					<p>价位信息：</p>
						            <span>
						              <input id="price_info" name="price_info" class="ui-checkbox" placeholder="价位信息" style="border:0;width:500px;" maxlength="30000">
						            </span>
	          					</div>
						        <div class='rapid' style="width:600px;margin-top:20px;" id="vote_box">
					                <ul class="xz-piaojia" id="price_box">
					               </ul>
					            </div>
          
          						<input type="submit" class="form-save-btn" style="margin-left:200px;margin-top:20px;margin-bottom:20px;" id="ok_now" value="设置价位">
            			</div>
            			</form>
            		</div>

        		</div>
    		</div>
        </div>
	</div>
	
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>