<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="list" type="business.UserIndexBean" scope="session"></jsp:useBean>  
<jsp:useBean id="city" type="business.CityBean" scope="session"></jsp:useBean>
<jsp:useBean id="user" type="business.UserInfoBean" scope="session"></jsp:useBean> 
<jsp:useBean id="item" class="model.Activity" scope="page"></jsp:useBean> 
<jsp:useBean id="temp" class="java.lang.String" scope="page"></jsp:useBean>
<jsp:useBean id="username" class="java.lang.String" scope="session"></jsp:useBean>
<%@ taglib uri="/WEB-INF/tlds/check_login.tld" prefix="MyTag" %>

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
        	
        	$("body").on('click','.t_more',function(){
                var t=$(this).attr("name");
                $("#loading").fadeIn(500);
                more(t);
                $("#loading").fadeOut(500);
            });	
        	$("body").on('click','.act',function(){
                var t=$(this).attr("name");
                $("#loading").fadeIn(500);
                a_detail(t);
                $("#loading").fadeOut(500);
            });	
        	$("body").on('click','.a_name',function(){
                var t=$(this).attr("name");
                $("#loading").fadeIn(500);
                a_detail(t);
                $("#loading").fadeOut(500);
            });
        	
        	$("body").on('click','.big_pic',function(){
                var t=$(this).attr("name");
                $("#loading").fadeIn(500);
                a_detail(t);
                $("#loading").fadeOut(500);
            });
        	$("body").on('mouseover','.act',function(){
	        	//alert("in");
                $(this).next().show();
            });	
        	$("body").on('mouseleave','.act',function(){
        		//alert("in");
                $(this).next().hide();
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

        	
        });

        setInterval(function () {
	        //alert("in");
    		var t=parseInt($("#counter").attr("name"));
    		var id="w"+t;
    		if(t==6){
        		t=1;
        	}
    		else{
        		t=t+1;
        	}
    		$("#counter").attr("name",t+"");
    		$("#"+id).show();
    		$("#"+id).siblings().hide();
    		
    	}, 2000);      
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
    <a id="counter" name="1"></a>
	
	<div class="w banner" style="margin-top:100px;">
	    <ul class="ban">
	    <%for(int i=0;i<list.getAd().size();i++){
	    	if(i==0){%>
	   		<li style="display:list-item;" id=<%="w"+i %>><a class="big_pic" name=<%=list.getAd().get(i).getId() %>><img class="banner-tu" src=<%=list.getAd().get(i).getApath() %> alt=<%=list.getAd().get(i).getName() %>></a></li>
	        <%}else{ %>
	        <li id=<%="w"+(i+1) %>><a class="big_pic" name=<%=list.getAd().get(i).getId() %>><img class="banner-tu" src=<%=list.getAd().get(i).getApath() %> alt=<%=list.getAd().get(i).getName() %>></a></li>
	        <%} %>
	    <%} %>
	    </ul>
	    
	</div>
	<!--  -->
	
	<!--  -->
	<div class="performance">
		<div class="type_drawer"  id="concert">
	        
	        <div class="type_t" style="margin-top: 15px;">
	            <div class="t-title">演唱会</div>
	                <a href="./type.jsp" name="1" class="t_more">更多</a>
	        	</div>
	        
	        <div class="d_con">
	            
	        	<%
				for (int i = 0; i < list.getConcert().size(); i++) {
					pageContext.setAttribute("item", list.getConcert().get(i));
					
			%>
		    	<div class="ds-cc">
		        	<span class="d_post">
		            	<a class="act" name=<%=list.getConcert().get(i).getId()%>>
		                	<img class="p_img" src=<%=list.getConcert().get(i).getPath()%> alt="项目海报" title=<%=list.getConcert().get(i).getName()%>/>
		                </a>
		                <ul class="show_pt" style="bottom: -15px;display:none;">
		                    <li class="a_place">
		                    	<jsp:getProperty name="item" property="vname" />
		                    </li>
		                    <li class="a_time">        
		                        <jsp:getProperty name="item" property="first" />-<jsp:getProperty name="item" property="last" />                          
		                    </li>
		                </ul>
		            </span>
		            <a name=<%=list.getConcert().get(i).getId()%> class="a_name"><jsp:getProperty name="item" property="name" /></a>
		            <div class=".m">
		            	<span class="money_m">￥</span>
		                <span class="money_price"><jsp:getProperty name="item" property="price" /></span>
		                <span class="qi">&nbsp;起</span>
		                <span class="a_city"><%=list.getConcert().get(i).getCity()%></span>
		            </div>
		        </div>
		       <%} %>    
		        
	    	</div>
	    </div>
	    
	    <div class="type_drawer"  id="musicale">
	        
	        <div class="type_t" style="margin-top: 15px;">
	            <div class="t-title">音乐会</div>
	                <a href="./type.jsp" name="2" class="t_more">更多</a>
	        	</div>
	        
	        <div class="d_con">
	            
	        	<%
				for (int i = 0; i < list.getMusicale().size(); i++) {
					pageContext.setAttribute("item", list.getMusicale().get(i));
					
			%>
		    	<div class="ds-cc">
		        	<span class="d_post">
		            	<a class="act" name=<%=list.getMusicale().get(i).getId()%>>
		                	<img class="p_img" src=<%=list.getMusicale().get(i).getPath()%> alt="项目海报" title=<%=list.getMusicale().get(i).getName()%>/>
		                </a>
		                <ul class="show_pt" style="bottom: -15px;display:none;">
		                    <li class="a_place">
		                    	<jsp:getProperty name="item" property="vname" />
		                    </li>
		                    <li class="a_time">        
		                        <jsp:getProperty name="item" property="first" />-<jsp:getProperty name="item" property="last" />                          
		                    </li>
		                </ul>
		            </span>
		            <a name=<%=list.getMusicale().get(i).getId()%> class="a_name"><jsp:getProperty name="item" property="name" /></a>
		            <div class=".m">
		            	<span class="money_m">￥</span>
		                <span class="money_price"><jsp:getProperty name="item" property="price" /></span>
		                <span class="qi">&nbsp;起</span>
		                <span class="a_city"><%=list.getMusicale().get(i).getCity()%></span>
		            </div>
		        </div>
		       <%} %>    
		        
	    	</div>
	    </div>
	    
	    <div class="type_drawer"  id="drama">
	        
	        <div class="type_t" style="margin-top: 15px;">
	            <div class="t-title">话剧舞台剧</div>
	                <a href="./type.jsp" name="3" class="t_more">更多</a>
	        	</div>
	        
	        <div class="d_con">
	            
	        	<%
				for (int i = 0; i < list.getDrama().size(); i++) {
					pageContext.setAttribute("item", list.getDrama().get(i));
					
				%>
		    	<div class="ds-cc">
		        	<span class="d_post">
		            	<a class="act" name=<%=list.getDrama().get(i).getId()%>>
		                	<img class="p_img" src=<%=list.getDrama().get(i).getPath()%> alt="项目海报" title=<%=list.getDrama().get(i).getName()%>/>
		                </a>
		                <ul class="show_pt" style="bottom: -15px;display:none;">
		                    <li class="a_place">
		                    	<jsp:getProperty name="item" property="vname" />
		                    </li>
		                    <li class="a_time">        
		                        <jsp:getProperty name="item" property="first" />-<jsp:getProperty name="item" property="last" />                          
		                    </li>
		                </ul>
		            </span>
		            <a name=<%=list.getDrama().get(i).getId()%> class="a_name"><jsp:getProperty name="item" property="name" /></a>
		            <div class=".m">
		            	<span class="money_m">￥</span>
		                <span class="money_price"><jsp:getProperty name="item" property="price" /></span>
		                <span class="qi">&nbsp;起</span>
		                <span class="a_city"><%=list.getDrama().get(i).getCity()%></span>
		            </div>
		        </div>
		       <%} %>    
		        
	    	</div>
	    </div>
	    
	    <div class="type_drawer"  id="ballet">
	        
	        <div class="type_t" style="margin-top: 15px;">
	            <div class="t-title">舞蹈芭蕾</div>
	                <a href="./type.jsp" name="4" class="t_more">更多</a>
	        </div>
	        
	        <div class="d_con">
	            
	        	<%
				for (int i = 0; i < list.getBallet().size(); i++) {
					pageContext.setAttribute("item", list.getBallet().get(i));
					
				%>
		    	<div class="ds-cc">
		        	<span class="d_post">
		            	<a class="act" name=<%=list.getBallet().get(i).getId()%>>
		                	<img class="p_img" src=<%=list.getBallet().get(i).getPath()%> alt="项目海报" title=<%=list.getBallet().get(i).getName()%>/>
		                </a>
		                <ul class="show_pt" style="bottom: -15px;display:none;">
		                    <li class="a_place">
		                    	<jsp:getProperty name="item" property="vname" />
		                    </li>
		                    <li class="a_time">        
		                        <jsp:getProperty name="item" property="first" />-<jsp:getProperty name="item" property="last" />                          
		                    </li>
		                </ul>
		            </span>
		            <a name=<%=list.getBallet().get(i).getId()%> class="a_name"><jsp:getProperty name="item" property="name" /></a>
		            <div class=".m">
		            	<span class="money_m">￥</span>
		                <span class="money_price"><jsp:getProperty name="item" property="price" /></span>
		                <span class="qi">&nbsp;起</span>
		                <span class="a_city"><%=list.getBallet().get(i).getCity()%></span>
		            </div>
		        </div>
		       <%} %>    
		        
	    	</div>
	    </div>
	    
	    <div class="type_drawer"  id="parent_child">
	        
	        <div class="type_t" style="margin-top: 15px;">
	            <div class="t-title">儿童亲子</div>
	                <a href="./type.jsp" name="5" class="t_more">更多</a>
	        </div>
	        
	        <div class="d_con">
	            
	        	<%
				for (int i = 0; i < list.getOpera().size(); i++) {
					pageContext.setAttribute("item", list.getOpera().get(i));
					
				%>
		    	<div class="ds-cc">
		        	<span class="d_post">
		            	<a class="act" name=<%=list.getOpera().get(i).getId()%>>
		                	<img class="p_img" src=<%=list.getOpera().get(i).getPath()%> alt="项目海报" title=<%=list.getOpera().get(i).getName()%>/>
		                </a>
		                <ul class="show_pt" style="bottom: -15px;display:none;">
		                    <li class="a_place">
		                    	<jsp:getProperty name="item" property="vname" />
		                    </li>
		                    <li class="a_time">        
		                        <jsp:getProperty name="item" property="first" />-<jsp:getProperty name="item" property="last" />                          
		                    </li>
		                </ul>
		            </span>
		            <a name=<%=list.getOpera().get(i).getId()%> class="a_name"><jsp:getProperty name="item" property="name" /></a>
		            <div class=".m">
		            	<span class="money_m">￥</span>
		                <span class="money_price"><jsp:getProperty name="item" property="price" /></span>
		                <span class="qi">&nbsp;起</span>
		                <span class="a_city"><%=list.getOpera().get(i).getCity()%></span>
		            </div>
		        </div>
		       <%} %>    
		        
	    	</div>
	    </div>
	    
	    <div class="type_drawer"  id="opera">
	        
	        <div class="type_t" style="margin-top: 15px;">
	            <div class="t-title">戏曲综艺</div>
	                <a href="./type.jsp" name="6" class="t_more">更多</a>
	        </div>
	        
	        <div class="d_con">
	            
	        	<%
				for (int i = 0; i < list.getOpera().size(); i++) {
					pageContext.setAttribute("item", list.getOpera().get(i));
					
				%>
		    	<div class="ds-cc">
		        	<span class="d_post">
		            	<a class="act" name=<%=list.getOpera().get(i).getId()%>>
		                	<img class="p_img" src=<%=list.getOpera().get(i).getPath()%> alt="项目海报" title=<%=list.getOpera().get(i).getName()%>/>
		                </a>
		                <ul class="show_pt" style="bottom: -15px;display:none;">
		                    <li class="a_place">
		                    	<jsp:getProperty name="item" property="vname" />
		                    </li>
		                    <li class="a_time">        
		                        <jsp:getProperty name="item" property="first" />-<jsp:getProperty name="item" property="last" />                          
		                    </li>
		                </ul>
		            </span>
		            <a name=<%=list.getOpera().get(i).getId()%> class="a_name"><jsp:getProperty name="item" property="name" /></a>
		            <div class=".m">
		            	<span class="money_m">￥</span>
		                <span class="money_price"><jsp:getProperty name="item" property="price" /></span>
		                <span class="qi">&nbsp;起</span>
		                <span class="a_city"><%=list.getOpera().get(i).getCity()%></span>
		            </div>
		        </div>
		       <%} %>    
		        
	    	</div>
	    </div>
	    
	    <div class="type_drawer"  id="entertainment">
	        
	        <div class="type_t" style="margin-top: 15px;">
	            <div class="t-title">休闲娱乐</div>
	                <a href="./type.jsp" name="7" class="t_more">更多</a>
	        </div>
	        
	        <div class="d_con">
	            
	        	<%
				for (int i = 0; i < list.getEntertainment().size(); i++) {
					pageContext.setAttribute("item", list.getEntertainment().get(i));
					
				%>
		    	<div class="ds-cc">
		        	<span class="d_post">
		            	<a class="act" name=<%=list.getEntertainment().get(i).getId()%>>
		                	<img class="p_img" src=<%=list.getEntertainment().get(i).getPath()%> alt="项目海报" title=<%=list.getEntertainment().get(i).getName()%>/>
		                </a>
		                <ul class="show_pt" style="bottom: -15px;display:none;">
		                    <li class="a_place">
		                    	<jsp:getProperty name="item" property="vname" />
		                    </li>
		                    <li class="a_time">        
		                        <jsp:getProperty name="item" property="first" />-<jsp:getProperty name="item" property="last" />                          
		                    </li>
		                </ul>
		            </span>
		            <a name=<%=list.getEntertainment().get(i).getId()%> class="a_name"><jsp:getProperty name="item" property="name" /></a>
		            <div class=".m">
		            	<span class="money_m">￥</span>
		                <span class="money_price"><jsp:getProperty name="item" property="price" /></span>
		                <span class="qi">&nbsp;起</span>
		                <span class="a_city"><%=list.getEntertainment().get(i).getCity()%></span>
		            </div>
		        </div>
		       <%} %>    
		        
	    	</div>
	    </div>
	    
	    <div class="type_drawer"  id="sports">
	        
	        <div class="type_t" style="margin-top: 15px;">
	            <div class="t-title">体育赛事</div>
	                <a href="./type.jsp" name="8" class="t_more">更多</a>
	        </div>
	        
	        <div class="d_con">
	            
	        	<%
				for (int i = 0; i < list.getSports().size(); i++) {
					pageContext.setAttribute("item", list.getSports().get(i));
					
				%>
		    	<div class="ds-cc">
		        	<span class="d_post">
		            	<a class="act" name=<%=list.getSports().get(i).getId()%>>
		                	<img class="p_img" src=<%=list.getSports().get(i).getPath()%> alt="项目海报" title=<%=list.getSports().get(i).getName()%>/>
		                </a>
		                <ul class="show_pt" style="bottom: -15px;display:none;">
		                    <li class="a_place">
		                    	<jsp:getProperty name="item" property="vname" />
		                    </li>
		                    <li class="a_time">        
		                        <jsp:getProperty name="item" property="first" />-<jsp:getProperty name="item" property="last" />                          
		                    </li>
		                </ul>
		            </span>
		            <a name=<%=list.getSports().get(i).getId()%> class="a_name"><jsp:getProperty name="item" property="name" /></a>
		            <div class=".m">
		            	<span class="money_m">￥</span>
		                <span class="money_price"><jsp:getProperty name="item" property="price" /></span>
		                <span class="qi">&nbsp;起</span>
		                <span class="a_city"><%=list.getSports().get(i).getCity()%></span>
		            </div>
		        </div>
		       <%} %>   
		        
	    	</div>
	    </div>
	</div>
	<div id="loading" class="loading" style="position:absolute; left:49%; top:40%; width:20px; height:20px; z-index:30;display: none">
	    <img src="./image/loading.gif"/>
	</div>
	</body>
</html>