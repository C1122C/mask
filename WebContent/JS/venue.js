//--------------------------------------------------venue_statistic
/*checked*/
function drawVenueCharts(SD,ED){
	var aname=[];
	var amoney = [];
	var orderkind=[];
	var v=[];
	var date=[];
	var consum=[];
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "1",
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
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "2",
        	"SD":SD,
        	"ED":ED},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		orderkind.push(result[i].kind);
        		v[i] = new Object(); 
        		v[i].name = result[i].kind; 
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
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "3",
        	"SD":SD,
        	"ED":ED},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		date.push(result[i].date);
        		consum.push(result[i].money);
            }
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
	var myChart1 = echarts.init(document.getElementById('top_ten'));
	option1 = {
			title : {
		        text: '活动排行',
		        subtext: SD+"-"+ED
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
		            name:'售票总金额',
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
	myChart1.setOption(option1);
	
	var myChart2 = echarts.init(document.getElementById('order'));
	option2 = {
			title : {
		        text: '订单状态',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:orderkind
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
		            name:'订单类型',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:v
		        }
		    ]
		};
	myChart2.setOption(option2);
	
	var myChart3 = echarts.init(document.getElementById('money'));
	option3 = {
			title : {
		        text: '财务情况',
		        subtext: SD+"-"+ED
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

//----------------------------------------------------------------venue_mod
function mod_venue_info(name,pwd,phone,pay_id,pro,city,dis,det){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "11",
        	"name":name,
        	"pwd":pwd,
        	"phone":phone,
        	"pay_id":pay_id,
        	"pro":pro,
        	"city":city,
        	"dis":dis,
        	"det":det},
        success: function (result) {
        	alert("成功提交申请，请等待审核结果");
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//----------------------------------------------------------------hall_mod
function delete_hall(id){//id 是hall的id
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "12",
        	"id":id},
        success: function (result) {
        	alert("成功提交申请，请等待审核结果");
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//----------------------------------------------------------------hall_add
function draw_a_hall(name,num,input){
	//alert("GET INT JS");
	//alert(name);
	//alert(num);
	//alert(input);
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "13",
        	"name":name,
        	"num":num,
        	"input":input},
        success: function (result) {
        	//alert("BACK");
        	window.location.href='hall_add.jsp';
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
	
}

function save_a_hall(name,num,input){//input :分开排，-分开座，。分开属性  排号。座号。状态（0-empty,1-selectable)
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "14",
        	"name":name,
        	"num":num,
        	"input":input},
        success: function (result) {
        	alert("成功提交申请，请等待审核结果");
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//-----------------------------------------------------------------venue_app

/*checked*/
function more_app(){
	var id=[];
	var date=[];
	var res=[];
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "15"},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
                id.push(result[i].id);
                date.push(result[i].date);
                res.push(result[i].res);
            }
        	if(id.length==0){
        		alert("已为您展示所有申请信息");
        	}
        	else{
        		for(var i=0;i<id.length;i++){
        			var cont=document.createElement("ul");
                    cont.setAttribute("class","conpun-xinxii");
                    var l1=document.createElement("li");
                    l1.setAttribute("style","width:255px;font-size:14px;");
                    l1.innerHTML=id[i];
                    var l2=document.createElement("li");
                    l2.setAttribute("style","width:255px;font-size:14px;");
                    l2.innerHTML=date[i];
                    var l3=document.createElement("li");
                    l3.setAttribute("style","width:255px;font-size:14px;");
                    l3.innerHTML=res[i];
                    cont.appendChild(l1);
                    cont.appendChild(l2);
                    cont.appendChild(l3);
                    
                    
                    var tar1 = document.getElementById("type_box");
                    tar1.appendChild(cont);
                }
        	}
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

//--------------------------------------------------------------------venue_reg
function add_venue_info(name,pwd,phone,pay_id,pro,city,dis,det){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/VenueInfo",
        dataType: "json",
        data: {"type": "16",
        	"name":name,
        	"pwd":pwd,
        	"phone":phone,
        	"pay_id":pay_id,
        	"pro":pro,
        	"city":city,
        	"dis":dis,
        	"det":det},
        success: function (result) {
        	alert("成功提交申请，请等待审核结果");
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//--------------------------------------------------------------------manager_app
function get_a_app(id){//申请的ID
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "7",
        	"aid":id},
        success: function (result) {
        	if(result[0].answer=="v"){
        		window.location.href='manager_app_detail.jsp';
        	}
        	else{
        		window.open('manager_app_hall.jsp');
        	}
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
function more_m_app(){
	var id=[];
	var type=[];
	var name=[];
	var res=[];
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "10"},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
                id.push(result[i].id);
                type.push(result[i].type);
                name.push(result[i].name);
                res.push(result[i].res);
            }
        	if(id.length==0){
        		alert("已为您展示所有申请信息");
        	}
        	else{
        		for(var i=0;i<id.length;i++){
        			var cont=document.createElement("ul");
                    cont.setAttribute("class","conpun-xinxii app_item");
                    cont.setAttribute("data-id",id[i]);
                    var l1=document.createElement("li");
                    l1.setAttribute("style","width:255px;font-size:14px;");
                    l1.innerHTML=type[i];
                    var l2=document.createElement("li");
                    l2.setAttribute("style","width:255px;font-size:14px;");
                    l2.innerHTML=name[i];
                    var l3=document.createElement("li");
                    l3.setAttribute("style","width:255px;font-size:14px;");
                    l3.innerHTML=res[i];
                    cont.appendChild(l1);
                    cont.appendChild(l2);
                    cont.appendChild(l3);
                    
                    
                    var tar1 = document.getElementById("type_box");
                    tar1.appendChild(cont);
                }
        	}
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//--------------------------------------------------------------------manager_app+detail
function turn_down(id){//申请的id
	//alert("IN DOWN JS ");
	//alert(id);
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "8",
        	"aid":id},
        success: function (result) {
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
//-------------------------------------------------------------------------manager_app_hall


function pass(id){//申请的id
	//alert("IN PASS JS ");
	//alert(id);
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/ManagerInfo",
        dataType: "json",
        data: {"type": "9",
        	"aid":id},
        success: function (result) {
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

