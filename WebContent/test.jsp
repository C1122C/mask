<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


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
	        		//change_select(city,t);
	        		$("#loading").fadeOut(500);
	            });
	        	$("body").on('click','.xl-r',function(){
	        		var t=$(this).html();
	        		$("#act_type").html(t);
	        		var city=$("#location").html();
	        		$("#loading").fadeIn(500);
	        		//change_select(city,t);
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
	                    //alert(key);
	                    $("#loading").fadeIn(500);
	                    select_by_input(key);
	                    $("#loading").fadeOut(500);
	                }
	            });

	        	$(window).bind('beforeunload',function(){
	            	logout();
	        	});
	        });

	        setInterval(function () {
		        //alert("in");
        		var t=parseInt($("#counter").attr("name"));
        		var id="w"+t;
        		if(t==3){
            		t=1;
            	}
        		else{
            		t=t+1;
            	}
        		$("#counter").attr("name",t+"");
        		$("#"+id).show();
        		$("#"+id).siblings().hide();
        		
        	}, 3000);      
    </script>
	</head>
	<body>
		
    <header class="hed hed1">
        <div class="hed w">
            <a href="./index.jsp"><span class="logo" style="margin-bottom: -15px;"></span></a>
            <div class="l hed-cshi">    
                <a class="over_show" id="location">全国</a>
                
                <ul class="chengshi bye" style="display:none;">
                                            <li><a class="city_bt">北京</a ></li>
                                            <li><a class="city_bt">上海</a ></li>
                                            <li><a class="city_bt">广州</a ></li>
                                            <li><a class="city_bt">深圳</a ></li>
                                            <li><a class="city_bt">重庆</a ></li>
                                            <li><a class="city_bt">成都</a ></li>
                                            <li><a class="city_bt">天津</a ></li>
                                            <li><a class="city_bt">南京</a ></li>
                                            <li><a class="city_bt">郑州</a ></li>
                                            <li><a class="city_bt">绍兴</a ></li>
                                            <li><a class="city_bt">西安</a ></li>
                                            <li><a class="city_bt">武汉</a ></li>
                                            <li><a class="city_bt">长沙</a ></li>
                                            <li><a class="city_bt">合肥</a ></li>
                                            <li><a class="city_bt">佛山</a ></li>
                                            <li><a class="city_bt">无锡</a ></li>
                                            <li><a class="city_bt">杭州</a ></li>
                                            <li><a class="city_bt">石家庄</a ></li>
                                            <li><a class="city_bt">青岛</a ></li>
                                            <li><a class="city_bt">大连</a ></li>
                                            <li><a class="city_bt">绵阳</a ></li>
                </ul>
            </div>
            <ul class="l hed-ul">
                <li><a class="older hed-on">首页</a></li>
                <li class="mar-r dh-fl">
                    <a class="older over_show" id="act_type">
                    	全部演出
                    </a>
                                       
                    <span class="dh-xl xl-xs xl-xs-1 bye" style="display:none;">     
                         <a>
                            <span class="dh-xl-xl">
                                <span class="xl-r">演唱会</span>
                            </span>
                         </a>
                         <a>
                            <span class="dh-xl-xl">
                                <span class="xl-r">演唱会</span>
                            </span>
                         </a>
                         <a>
                            <span class="dh-xl-xl">
                                <span class="xl-r">演唱会</span>
                            </span>
                         </a>
                         
                   </span>
                </li>
                
            </ul>
            
            <div class="r hed-su" onkeydown="keyLogin3(event)">
                <form id="key-search" action="/list/" method="get" role="form">                
                	<input type="text" name="keyword" placeholder="输入演出、场馆名称..." class="l su-ipt" style="margin-top:10px;" id="search_input">
                	<i class="iconfont su-tb" style="height:71px;width:40px;background-image: url(./image/search.png);background-repeat: no-repeat;background-position:0 0;margin-top:12px;" id="search_icon"></i>
                </form>                
                <div class="wode-dongxi">
                    <a class="older hed-on" style="margin-top:-10px;">个人中心</a>
                </div>
            </div>
        </div>

    </header>
    <a id="counter" name="1"></a>
	
	<div class="w banner" style="margin-top:100px;">
	    <ul class="ban">
	        <li style="display:list-item;" id="w1"><a class="big_pic" name="1"><img class="banner-tu" src="./image/w1.jpg" alt="轮播图"></a></li>
	        <li id="w2"><a class="big_pic" name="1"><img class="banner-tu" src="./image/w2.jpg" alt="轮播图"></a></li>
	        <li id="w3"><a class="big_pic" name="1"><img class="banner-tu" src="./image/w3.jpg" alt="轮播图"></a></li>
	    </ul>
	    
	</div>
	<!--  -->
	<div class="drawer" id="like">                
	    <div class="d_title">
	        <div class="t-title">猜你喜欢</div>
	            <a name="0" class="t_more">更多</a>
	    </div>
	    
	    <div class="d_con">
	    <div class="ds-cc">
	        	<span class="d_post">
	            	<a class="act" name="">
	                	<img class="p_img" src="./image/test.jpg" alt="项目海报" title=""/>
	                </a>
	                <ul class="show_pt" style="bottom: -15px;display:none;">
	                    <li class="a_place">
	                    	地点
	                    </li>
	                    <li class="a_time">        
	                      	 时间                          
	                    </li>
	                </ul>
	            </span>
	            <a name="" class="a_name">名字</a>
	            <div class=".m">
	            	<span class="money_m">￥</span>
	                <span class="money_price">价格</span>
	                <span class="qi">&nbsp;起</span>
	                <span class="a_city">城市</span>
	            </div>
	        </div>
	          
	        
	                   
	    </div>
	</div>
	<!--  -->
	<div class="performance">
		<div class="type_drawer"  id="concert">
	        
	        <div class="type_t" style="margin-top: 15px;">
	            <div class="t-title">演唱会</div>
	                <a href="./type.jsp" name="1" class="t_more">更多</a>
	        	</div>
	        
	        <div class="d_con">
	            
	        	<div class="ds-cc">
	        	<span class="d_post">
	            	<a class="act" name="">
	                	<img class="p_img" src="./image/test.jpg" alt="项目海报" title=""/>
	                </a>
	                <ul class="show_pt" style="bottom: -15px;display:none;">
	                    <li class="a_place">
	                    	地点
	                    </li>
	                    <li class="a_time">        
	                      	 时间                          
	                    </li>
	                </ul>
	            </span>
	            <a name="" class="a_name">名字</a>
	            <div class=".m">
	            	<span class="money_m">￥</span>
	                <span class="money_price">价格</span>
	                <span class="qi">&nbsp;起</span>
	                <span class="a_city">城市</span>
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