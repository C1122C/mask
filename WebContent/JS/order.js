//----------------------------------------------------------------------------vote
/*checked*/
function select_vote(id,time,tic,num,sum){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "1",
        "id":id,
        "time":time,
        "tic":tic,
        "num":num,
        "sum":sum},
        success: function (result) {
        	if (result[0].answer == "SUCCESS") {
        		window.location.href='pay.jsp';
            }
            else{
            	var tar = document.getElementById("little_board");
        		tar.setAttribute("style","display:block");
        		setTimeout("close2()",3000);
            }
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function close2(){
	var tar = document.getElementById("little_board");
	tar.setAttribute("style","display:none");
}

//------------------------------------------------------------------------------select
/*checked*/
function end_select(par){//aid:showTime:rc-rr-sc-sr-price:sum
	//alert(par);
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "2",
        "par":par},
        success: function (result) {
        	if (result[0].answer == "SUCCESS") {
        		window.location.href='pay.jsp';
            }
            else{
            	var tar = document.getElementById("little_board");
            	var tar1 = document.getElementById("message");
            	tar1.innerHTML="请重新尝试";
        		tar.setAttribute("style","display:block");
        		setTimeout("close2()",3000);
            }
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

//---------------------------------------------------------------------pay
/*checked*/
function time_out_cancle(id){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "4",
        "id":id},
        success: function (result) {
        	if (result[0].answer == "SUCCESS") {
        		var tar = document.getElementById("little_board1");
        		tar.setAttribute("style","display:block");
            }
            else{
            	var tar = document.getElementById("little_board");
            	tar.setAttribute("style","display:block");
        		setTimeout("close2()",3000);
            }
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function connect_pay(par){//oid:分：秒：优惠券id-优惠券钱数：总额
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "3",
        "par":par},
        success: function (result) {
        	if (result[0].answer == "SUCCESS") {
        		window.location.href='pay_id.jsp';
            }
            else{
            	var tar = document.getElementById("little_board");
            	var tar1 = document.getElementById("message");
            	tar1.innerHTML="请重新尝试";
        		tar.setAttribute("style","display:block");
        		setTimeout("close2()",3000);
            }
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

//------------------------------------------------------------------------------pay_id
/*checked*/
function pay_money(par){//订单号：账号：密码：分：秒：pay.getInfo：总计钱数
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "5",
        "par":par},
        success: function (result) {
        	if (result[0].answer == "SUCCESS") {
        		window.location.href='order_scan.jsp';
            }
            else{
            	var tar = document.getElementById("little_board");
            	var tar1 = document.getElementById("message");
            	tar1.innerHTML=result[0].answer;
        		tar.setAttribute("style","display:block");
        		setTimeout("close2()",3000);
            }
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

//---------------------------------------------------------------------------------order_scan
/*checked*/
function select_order(t){//汉字的种类 全部订单 进行中 已完成 已取消
	//alert("IN JS METHOD");
	//alert(t);
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "7",
        "t":t},
        success: function (result) {
        	if (result[0].answer == "SUCCESS") {
        		window.location.href='order_scan.jsp';
            }
            
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function pay_order(id){//order id
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "10",
        	"id":id},
        success: function (result) {
        	window.location.href='pay.jsp';
            
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function refresh(){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "12"},
        success: function (result) {
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function cancle_order(id){//order id
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "6",
        "id":id},
        success: function (result) {
        	if (result[0].answer == "FAIL") {
        		var tar = document.getElementById("little_board");
        		var tar1 = document.getElementById("message");
        		tar1.innerHTML="请重新尝试";
            	tar.setAttribute("style","display:block");
        		setTimeout("close2()",3000);
            }
        	else{
        		var tar = document.getElementById("little_board");
        		var tar1 = document.getElementById("message");
        		tar1.innerHTML=result[0].answer;
            	tar.setAttribute("style","display:block");
        		setTimeout("close2()",3000);
        	}
            
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function more_order(){
	var id=[];
	var state=[];
	var c_time=[];
	var aid=[];
	var path=[];
	var a_name=[];
	var a_time=[];
	var v_name=[];
	var sum=[]
	var judge=[];
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "8"},
        success: function (result) {
        	
        		for (var i = 0; i < result.length; i++) {
            			id.push(result[i].id);
                        state.push(result[i].state);
                        c_time.push(result[i].c_time);
                        aid.push(result[i].aid);
                        path.push(result[i].path);
                        a_name.push(result[i].a_name);
                        a_time.push(result[i].a_time);
                        v_name.push(result[i].v_name);
                        sum.push(result[i].sum);
                        judge.push(result[i].judge);
                    }
                	if(id.length==0){
                		var tar = document.getElementById("little_board");
                		var tar1 = document.getElementById("message");
                		tar1.innerHTML="没有更多了哦！";
                    	tar.setAttribute("style","display:block");
                		setTimeout("close2()",3000);
                	}
                	else{
                		for(var i=0;i<id.length;i++){
                			var lieb=document.createElement("div");
                            lieb.setAttribute("class","lieb-liebiao");
                            var top=document.createElement("div");
                            top.setAttribute("class","liebiao-top");
                            var s1=document.createElement("span");
                            s1.innerHTML="订单号：";
                            var s2=document.createElement("span");
                            s2.innerHTML=id[i];
                            var s3=document.createElement("span");
                            s3.setAttribute("class","coF");
                            s3.innerHTML=state[i];
                            var s4=document.createElement("span");
                            s4.innerHTML=c_time[i];
                            top.appendChild(s1);
                            top.appendChild(s2);
                            top.appendChild(s3);
                            top.appendChild(s4);
                            lieb.appendChild(top);
                            var con=document.createElement("div");
                            con.setAttribute("class","liebiao-con");
                            var con1=document.createElement("div");
                            con1.setAttribute("class","con1");
                            var det=document.createElement("a");
                            det.setAttribute("class","detail");
                            det.setAttribute("name",aid[i]);
                            var im=document.createElement("img");
                            im.setAttribute("src",path[i]);
                            im.setAttribute("alt",a_name[i]);
                            im.setAttribute("title",a_name[i]);
                            det.appendChild(im);
                            con1.appendChild(det);
                            var a1=document.createElement("a");
                            var slh=document.createElement("p");
                            slh.setAttribute("class","slh con1-tit detail");
                            slh.setAttribute("name",aid[i]);
                            slh.innerHTML=a_name[i];
                            a1.appendChild(slh);
                            con1.appendChild(a1);
                            var p1=document.createElement("p");
                            p1.setAttribute("class","con1-sc slh2");
                            p1.innerHTML="时间"+a_time[i];
                            con1.appendChild(p1);
                            var p2=document.createElement("p");
                            p2.setAttribute("class","con1-sc slh2");
                            p2.innerHTML="场馆"+v_name[i];
                            con1.appendChild(p2);
                            con.appendChild(con1);
                            var con2=document.createElement("div");
                            con2.setAttribute("class","con2");
                            var p3=document.createElement("p");
                            p3.setAttribute("class","coF");
                            p3.innerHTML="共计￥"+sum[i];
                            con2.appendChild(p3);
                            con.appendChild(con2);
                            if(judge[i]==1){
                            	var d11=document.createElement("div");
                                d11.setAttribute("class","con2 bor-r");
                                var aa1=document.createElement("a");
                                aa1.setAttribute("class","pay1");
                                aa1.setAttribute("name",id[i]);
                                aa1.innerHTML="支付";
                                d11.appendChild(aa1);
                                con.appendChild(d11);
                            }
                            else if(judge[i]<=3){
                            	var d1=document.createElement("div");
                                d1.setAttribute("class","con2 bor-r");
                                var aa=document.createElement("a");
                                aa.setAttribute("class","Quxiao");
                                aa.setAttribute("name",id[i]);
                                aa.innerHTML="取消";
                                d1.appendChild(aa);
                                con.appendChild(d1);
                            }
                            lieb.appendChild(con);
                            
                            var tar1 = document.getElementById("order_box");
                            tar1.appendChild(lieb);
                        }
                	}
            	
            
            
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function order_reset(){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/UserOrder",
        dataType: "json",
        data: {"type": "11"},
        success: function (result) {
        	
            
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}