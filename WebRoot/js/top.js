$(function() {
			if ($.trim($("#info").html()).length > 0) {
				var interval = setInterval(function() {
							getMsgcount();
						}, 10 * 60 * 1000);
			}
			if (window.closed) {
				clearInterval(interval);
			}
		});
function getMsgcount() {
	$.get("UserServlet?method=msgcount", function(data) {
				$("#msgcount").text("消息:" + data.msgcount);
			}, "json");
}

function login2(username, password, url) {
	var username = $(username).val();
	var password = $(password).val();
	$.post(url + "/servlet/UserServlet?method=ajaxlogin", {
				username : username,
				password : password
			}, function(data) {
				if (data.msg == false) {
					alert("用户名密码错误!");
					return;
				}
				// window.location.href = url;
				$("#login").hide();
			}, "json");
}

function showtea() {
	var show = $(".oltea").css("display");
	if (show == 'none') {
		$(".oltea").show();
	} else {
		$(".oltea").hide();
	}
}