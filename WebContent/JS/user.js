/*checked*/
function logout(){
	window.location.href='index.jsp';
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/Login",
        dataType: "json",
        data: {"type": "4"},
        success: function (result) {
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            //alert(XMLHttpRequest.status);
            //alert(XMLHttpRequest.readyState);
            //alert(textStatus);
        }
    });
}
/*checked*/
function delete_account(){
	var con=confirm("确定要删除您的账号吗"); 
	if(con==true){
		window.location.href='index.jsp';
		$.ajax({
	        type: "post",
	        async: false,
	        url: "/mask/UserInfo",
	        dataType: "json",
	        data: {"type": "1"},
	        success: function (result) {
	        	
	        },error:function (XMLHttpRequest, textStatus, errorThrown) {
	            alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
	        }
	    });
	}
	
}
/*checked*/
function save_person_info(name,pass,gender,year,month,day,pay_id,interest){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserInfo",
        dataType: "json",
        data: {"type": "2",
        	"name":name,
        	"pass":pass,
        	"gender":gender,
        	"year":year,
        	"month":month,
        	"day":day,
        	"payid":pay_id,
        	"interest":interest},
        success: function (result) {
        	alert("已为您修改个人信息");
            
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function drawUserCharts(SD,ED){
	var sum;
	var orderkind=[];
	var v1 = [];
	var actkind=[];
	var v2=[];
	var v3=[];
	var date=[];
	var consum=[];
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserInfo",
        dataType: "json",
        data: {"type": "3",
        	"SD":SD,
        	"ED":ED},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		sum=result[i].sum;
        		v1[i] = new Object(); 
        		v1[i].name = result[i].kind; 
        		v1[i].value = result[i].num; 
        		
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
        url: "/mask/UserInfo",
        dataType: "json",
        data: {"type": "4",
        	"SD":SD,
        	"ED":ED},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		actkind.push(result[i].kind);
        		v2[i] = new Object(); 
        		v2[i].name = result[i].kind; 
        		v2[i].value = result[i].num; 
        		v3[i] = new Object(); 
        		v3[i].name = result[i].kind; 
        		v3[i].value = result[i].money; 
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
        url: "/mask/UserInfo",
        dataType: "json",
        data: {"type": "5",
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
	var myChart1 = echarts.init(document.getElementById('user_order_st'));
	option1 = {
		    title : {
		        text: '我的订单',
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
		            data:v1
		        }
		    ]
		};
	myChart1.setOption(option1);
	
	var myChart2 = echarts.init(document.getElementById('user_order_money'));
	option2 = {
		    title : {
		        text: '下单比例',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:actkind
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
		            name:'各类型演出',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:v2
		        }
		    ]
		};
	myChart2.setOption(option2);
	
	var myChart4 = echarts.init(document.getElementById('user_order_money1'));
	option4 = {
		    title : {
		        text: '消费比例',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:actkind
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
		            name:'各类型演出',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:v3
		        }
		    ]
		};
	myChart4.setOption(option4);
	
	var myChart3 = echarts.init(document.getElementById('user_order_time'));
	option3 = {
			title : {
		        text: '我的消费记录',
		        subtext: SD+'至'+ED+":总消费额"+sum
		        
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['日消费总额']
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
		            name:'日消费总额',
		            type:'line',
		            data:consum,
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最高消费日'},
		                    {type : 'min', name: '最低消费日'}
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