/*checked*/
function get_more_coupon(){
	var valu=[];
	var name=[];
	var type=[];
	var des=[];
	var vali=[];
	var tid=[];
	var change=[];
	var ismine=[];
	
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/Coupon",
        dataType: "json",
        data: {"type": "1"},
        success: function (result) {
        	for (var i = 0; i < result.length; i++) {
        		valu.push(result[i].valu);
                name.push(result[i].name);
                type.push(result[i].type);
                des.push(result[i].des);
                vali.push(result[i].vali);
                tid.push(result[i].tid);
                change.push(result[i].change);
                ismine.push(result[i].ismine);
            }
        	if(ismine.length==0){
        		var tar = document.getElementById("little_board");
        		tar.setAttribute("style","display:block");
        		setTimeout("close1()",3000);
        	}
        	else{
        		for(var i=0;i<ismine.length;i++){
        			var u1=document.createElement("ul");
                    u1.setAttribute("class","conpun-xinxii");
                    var l1=document.createElement("li");
                    l1.innerHTML=valu[i];
                    var l2=document.createElement("li");
                    l2.innerHTML=name[i];
                    var l3=document.createElement("li");
                    l3.innerHTML=des[i];
                    var l4=document.createElement("li");
                    l4.innerHTML=vali[i];
                    ul.appendChild(l1);
                    ul.appendChild(l2);
                    ul.appendChild(l3);
                    ul.appendChild(l4);
                    if(ismine[i]==1){
                    	var l5=document.createElement("li");
                        l5.innerHTML=type[i];
                        ul.appendChild(l5);
                        var tar = document.getElementById("mine");
                        tar.appendChild(ul);
                    }
                    else{
                    	var l6=document.createElement("li");
                    	var a1=document.createElement("a");
                    	a1.setAttribute("class","conpun-btn l change");
                    	a1.setAttribute("style","margin-top: 0px;");
                    	a1.setAttribute("name",tid[i]);
                    	a1.innerHTML=change[i]+"积分兑换";
                    	l6.appendChild(a1);
                    	ul.appendChild(l6);
                    	var tar1 = document.getElementById("change");
                        tar1.appendChild(ul);
                    }
                    
                    
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
function change_coupon(type_id){//兑换优惠券，参数是优惠券的类型ID
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/Coupon",
        dataType: "json",
        data: {"type": "2",
        "id":type_id},
        success: function (result) {
        	window.location.href='coupon.jsp';
         },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
/*checked*/
function reset(name){
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/Coupon",
        dataType: "json",
        data: {"type": "3",
        	"name":name},
        success: function (result) {
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function close1(){
	var tar = document.getElementById("little_board");
	tar.setAttribute("style","display:none");
}