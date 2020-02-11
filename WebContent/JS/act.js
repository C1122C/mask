//--------------------------------------------------------user_index
/*checked*/
function change_select(city,t){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ActSelectAndScan",
        dataType: "json",
        data: {"type": "1",
        "city":city,
        "sort":t},
        success: function (result) {
        	//alert("ABOUT TO REFRESH");
        	window.location.href='type.jsp';
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function select_by_input(input){//用户原始输入,一旦用户输入，便在全国和全部演出范围内查找
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ActSelectAndScan",
        dataType: "json",
        data: {"type": "2",
        "inp":input},
        success: function (result) {
        	if (result[0].answer == "SUCCESS") {
        		window.location.href='type.jsp';
        	}
        	else{
        		alert("FAIL");
        	}
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function more(t){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ActSelectAndScan",
        dataType: "json",
        data: {"type": "3",
        "sort":t},
        success: function (result) {
        	//alert(result);
        	if (result[0].answer == "SUCCESS") {
        		window.location.href='type.jsp';
        	}
        	else{
        		alert("FAIL");
        	}
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function a_detail(aid){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ActSelectAndScan",
        dataType: "json",
        data: {"type": "4",
        "aid":aid},
        success: function (result) {
        	if (result[0].answer == "1") {
        		window.location.href='vote.jsp';
            }
            else if(result[0].answer == "2"){
            	window.location.href='select.jsp';
            }
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

//--------------------------------------------------------type
/*checked*/
function more_list(){
	var id=[];
	var name=[];
	var s_time=[];
	var e_time=[];
	var vname=[];
	var city=[];
	var price=[];
	var path=[];
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ActSelectAndScan",
        dataType: "json",
        data: {"type": "5"},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
                    id.push(result[i].id);
                    name.push(result[i].name);
                    s_time.push(result[i].s_time);
                    e_time.push(result[i].e_time);
                    vname.push(result[i].vname);
                    city.push(result[i].city);
                    price.push(result[i].price);
                    path.push(result[i].path);
                }
            	if(id.length==0){
            		var tar = document.getElementById("little_board");
            		tar.setAttribute("style","display:block");
            		setTimeout("close()",3000);
            	}
            	else{
            		for(var i=0;i<id.length;i++){
            			var alist=document.createElement("div");
                        alist.setAttribute("class","a_list_res_item");
                        var cct=document.createElement("a");
                        cct.setAttribute("class","cc-title");
                        cct.setAttribute("name",id[i]);
                        var lp=document.createElement("img");
                        lp.setAttribute("class","list_post");
                        lp.setAttribute("src",path[i]);
                        lp.setAttribute("alt",name[i]);
                        lp.setAttribute("title",name[i]);
                        lp.setAttribute("style","display:block;");
                        cct.appendChild(lp);
                        alist.appendChild(cct);
                        var ld=document.createElement("div");
                        ld.setAttribute("class","list_detail");
                        var cct1=document.createElement("a");
                        cct1.setAttribute("class","cc-title");
                        cct1.setAttribute("name",id[i]);
                        cct1.innerHTML=name[i];
                        ld.appendChild(cct1);
                        var lt=document.createElement("span");
                        lt.setAttribute("class","list_time");
                        lt.innerHTML=s_time[i]+"-"+e_time[i];
                        ld.appendChild(lt);
                        var lc=document.createElement("span");
                        lc.setAttribute("class","list_place");
                        lc.innerHTML="["+city[i]+"]"+vname[i];
                        ld.appendChild(lc);
                        alist.appendChild(ld);
                        var ir=document.createElement("div");
                        ir.setAttribute("class","item_right");
                        var rp=document.createElement("span");
                        rp.setAttribute("class","r_price");
                        var ccp=document.createElement("span");
                        ccp.setAttribute("class","cc_price");
                        ccp.innerHTML=price[i];
                        rp.appendChild(ccp);
                        
                        ir.appendChild(rp);
                        var blc=document.createElement("a");
                        blc.setAttribute("class","blc");
                        blc.setAttribute("name",id[i]);
                        blc.innerHTML="立即购买";
                        ir.appendChild(blc);
                        alist.appendChild(ir);
                        
                        var tar1 = document.getElementById("type_box");
                        tar1.appendChild(alist);
                    }
            	}
        	
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function close(){
	var tar = document.getElementById("little_board");
	tar.setAttribute("style","display:none;");
}

//--------------------------------------------------------venue_act

function get_scene(aid){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ActSelectAndScan",
        dataType: "json",
        data: {"type": "4",
        "aid":aid},
        success: function (result) {
        	window.open("scene.jsp");
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function get_a_statistic(aid){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "4",
        "aid":aid},
        success: function (result) {
        	window.open('act_statistic.jsp');
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

/*checked*/
function more_venue_act(){
	var id=[];
	var name=[];
	var s_time=[];
	var e_time=[];
	var vname=[];
	var city=[];
	var price=[];
	var path=[];
	var state=[];
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "6"},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
                id.push(result[i].id);
                name.push(result[i].name);
                s_time.push(result[i].s_time);
                e_time.push(result[i].e_time);
                vname.push(result[i].vname);
                city.push(result[i].city);
                price.push(result[i].price);
                path.push(result[i].path);
                state.push(result[i].state);
            }
        	if(id.length==0){
        		var tar = document.getElementById("little_board");
        		tar.setAttribute("style","display:block");
        		setTimeout("close()",3000);
        	}
        	else{
        		for(var i=0;i<id.length;i++){
        			var alist=document.createElement("div");
                    alist.setAttribute("class","a_list_res_item");
                    alist.setAttribute("style","width:790px;");
                    var cct=document.createElement("a");
                    cct.setAttribute("class","cc-title");
                    var lp=document.createElement("img");
                    lp.setAttribute("class","list_post");
                    lp.setAttribute("src",path[i]);
                    lp.setAttribute("alt",name[i]);
                    lp.setAttribute("title",name[i]);
                    lp.setAttribute("style","display:block;");
                    cct.appendChild(lp);
                    alist.appendChild(cct);
                    var ld=document.createElement("div");
                    ld.setAttribute("class","list_detail");
                    var cct1=document.createElement("a");
                    cct1.setAttribute("class","cc-title");
                    cct1.innerHTML=name[i];
                    ld.appendChild(cct1);
                    var lt=document.createElement("span");
                    lt.setAttribute("class","list_time");
                    lt.innerHTML=s_time[i]+"-"+e_time[i];
                    ld.appendChild(lt);
                    var lc=document.createElement("span");
                    lc.setAttribute("class","list_place");
                    lc.innerHTML="["+city[i]+"]"+vname[i];
                    ld.appendChild(lc);
                    alist.appendChild(ld);
                    var ir=document.createElement("div");
                    ir.setAttribute("class","item_right");
                    if(state[i]=="已结束"){
                    	var a11=document.createElement("a");
                        a11.setAttribute("class","blc chakan");
                        a11.setAttribute("name",id[i]);
                        a11.innerHTML="详情查看";
                        ir.appendChild(a11);
                    }
                    else{
                    	var a22=document.createElement("a");
                        a22.setAttribute("class","blc xianchang");
                        a22.setAttribute("name",id[i]);
                        a22.innerHTML="现场";
                        ir.appendChild(a22);
                    }
                    alist.appendChild(ir);
                    
                    var tar1 = document.getElementById("type_box");
                    tar1.appendChild(alist);
                }
        	}
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

//------------------------------------------------------------scene

/*checked*/
function checked(par,aid){
	//alert(par);
	//alert(aid);
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "8",
        "par":par,
        "aid":aid},
        success: function (result) {
        	alert(result[0].answer);
        	window.location.href='scene.jsp';
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//user(0表示非会员）:aid:time:realc-realr-seatc-seatr-price:返回应付钱数
/*checked*/
function sell(par){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "7",
        "par":par},
        success: function (result) {
        	alert("请支付"+result[0].answer+"元");
        	window.location.href='scene.jsp';
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

//-------------------------------------------------------------act_statistic
/*checked*/
function drawActCharts(){
	var date=[];
	var consum=[];
	
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "9"},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		date.push(result[i].date);
        		consum.push(result[i].consum);
            }
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
	var myChart2 = echarts.init(document.getElementById('seri'));
	option2 = {
			title : {
		        text: '平台售票情况',
		        subtext: ''
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['日售票额']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : date
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} 元'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'日售票额',
		            type:'line',
		            data:consum,
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最高销售日'},
		                    {type : 'min', name: '最低销售日'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
	myChart2.setOption(option2);
}



//-------------------------------------------------------------------manager_statistic


function drawPlatCharts(SD,ED){
	var vname=[];
	var vmoney = [];
	var aname=[];
	var amoney = [];
	var v=[];
	var date=[];
	var consum=[];
	var sum;
	var usum;
	var add;
	var lose;
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "1",
        	"SD":SD,
        	"ED":ED},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		vname.push(result[i].name);
        		vmoney.push(result[i].money);
        	}
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "2",
        	"SD":SD,
        	"ED":ED},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		aname.push(result[i].name);
        		amoney.push(result[i].money);
        	}
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "3",
        	"SD":SD,
        	"ED":ED},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		usum=result[0].sum;
        		add=result[0].add;
        		lose=result[0].lose
        		v[i] = new Object(); 
        		v[i].name = result[i].sort; 
        		v[i].value = result[i].num; 
        	}
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "4",
        	"SD":SD,
        	"ED":ED},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		sum=result[0].sum;
        		date.push(result[i].date);
        		consum.push(result[i].money);
            }
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
	
	var myChart1 = echarts.init(document.getElementById('top_ven'));
	option1 = {
			title : {
		        text: '平台销售额排行',
		        subtext: '场馆'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['收益']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : vname
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} 元'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'售票总金额',
		            type:'bar',
		            data:vmoney,
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最高销售额'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
	myChart1.setOption(option1);
	
	var myChart4 = echarts.init(document.getElementById('top_act'));
	option4 = {
			title : {
		        text: '平台销售额排行',
		        subtext: '活动'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['演出收益']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : aname
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} 元'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'每场售票总金额',
		            type:'bar',
		            data:amoney,
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最高销售额'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
	myChart4.setOption(option4);
	
	var myChart2 = echarts.init(document.getElementById('user'));
	option2 = {
			title : {
		        text: '平台用户总数：'+usum+"/新增："+add+"/流失："+lose,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:['普通会员','白银会员','黄金会员','钻石会员','皇冠会员']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {
		                show: true, 
		                type: ['pie', 'funnel'],
		                option: {
		                    funnel: {
		                        x: '25%',
		                        width: '50%',
		                        funnelAlign: 'left',
		                        max: 1548
		                    }
		                }
		            },
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    series : [
		        {
		            name:'用户比例',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:v
		        }
		    ]
		};
	myChart2.setOption(option2);
	
	var myChart3 = echarts.init(document.getElementById('plat'));
	option3 = {
			title : {
		        text: '平台财务情况',
		        subtext: "平台总销售额:"+sum
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['日售票额']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : date
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} 元'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'日售票额',
		            type:'line',
		            data:consum,
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最高销售日'},
		                    {type : 'min', name: '最低销售日'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
	myChart3.setOption(option3);
}
//-------------------------------------------------------------------------------manager_account
function close_act(id){//活动的id
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "6",
        	"id":id},
        success: function (result) {
        	window.location.href='manager_account.jsp';
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function more_m_act(){
	var id=[];
	var name=[];
	var total=[];
	var path=[];
	var state=[];
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "5"},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
                id.push(result[i].id);
                name.push(result[i].name);
                total.push(result[i].total);
                state.push(result[i].state);
                path.push(result[i].path);
            }
        	if(id.length==0){
        		alert("已显示所有未结算活动信息");
        	}
        	else{
        		for(var i=0;i<id.length;i++){
        			
        			var alist=document.createElement("div");
                    alist.setAttribute("class","a_list_res_item");
                    alist.setAttribute("style","width:790px;");
                    var cct=document.createElement("a");
                    cct.setAttribute("class","cc-title");
                    var lp=document.createElement("img");
                    lp.setAttribute("class","list_post");
                    lp.setAttribute("src",path[i]);
                    lp.setAttribute("alt",name[i]);
                    lp.setAttribute("title",name[i]);
                    lp.setAttribute("style","display:block;");
                    cct.appendChild(lp);
                    alist.appendChild(cct);
                    
                    var ld=document.createElement("div");
                    ld.setAttribute("class","list_detail");
                    var cct1=document.createElement("a");
                    cct1.setAttribute("class","cc-title");
                    cct1.innerHTML=name[i];
                    ld.appendChild(cct1);
                    var lt=document.createElement("span");
                    lt.setAttribute("class","list_time");
                    lt.innerHTML="总金额："+total[i];
                    ld.appendChild(lt);
                    var lc=document.createElement("span");
                    lc.setAttribute("class","list_place");
                    lc.innerHTML=state[i];
                    ld.appendChild(lc);
                    alist.appendChild(ld);
                    if(state[i].equals("已结束")){
						var ir=document.createElement("div");
	                    ir.setAttribute("class","item_right");
	                	var a11=document.createElement("a");
	                    a11.setAttribute("class","blc");
	                    a11.setAttribute("name",id[i]);
	                    a11.innerHTML="结算";
	                    ir.appendChild(a11);
	                    alist.appendChild(ir);
	                }
                    var tar1 = document.getElementById("type_box");
                    tar1.appendChild(alist);
                }
        	}
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
