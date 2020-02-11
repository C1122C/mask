function login(id,pwd){//邮箱、密码
	//alert("IN LOG IN");
    $.ajax({
        type: "post",
        async: false,
        url: "/mask/Login",
        dataType: "json",
        data: {"type": "1",
        "id":id,
        "pwd":pwd},
        success: function (result) {
        	if (result[0].answer == "FAIL") {
                alert("用户名或密码错误");
            }
            else if(result[0].answer == "u"){
            	window.location.href='user_index.jsp';
            }
            else if(result[0].answer == "v"){
            	window.location.href='venue_statistic.jsp';
            }
            else if(result[0].answer == "m"){
            	window.location.href='manager_statistic.jsp';
            }
        	//alert("JUDGE WRONG");
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function val_mail(id){//邮箱
	$.ajax({
        type: "post",
        async: false,
        url: "/mask/Login",
        dataType: "json",
        data: {"type": "2",
        "mail":id},
        success: function (result) {
        	if (result[0].answer == "FAIL") {
                alert("不正确或已注册过的邮箱");
            }
            else{
            	alert("已发送验证邮件到您的邮箱，请输入邮件中的验证码");
            }
        	
        },error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });

}

