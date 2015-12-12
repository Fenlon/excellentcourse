var checkMsg;
var checkIsLogin
$(function() {
			checkIsLogin = setInterval(function() {
						isLogIn_first();
					}, 5000);
		});

function login(username, password, url) {
	var username = $(username).val();
	var password = $(password).val();
	var i = window.location.href.indexOf("servlet");
	var posturl = "UserServlet?method=ajaxlogin";
	if (i < 0) {
		posturl = "servlet/UserServlet?method=ajaxlogin";
	}
	$.post(posturl, {
				username : username,
				password : password
			}, function(data) {
				if (data.msg == false) {
					alert("用户名密码错误!");
					return;
				}
				$(".item3").hide();
				$(".item2").show();
				window.location.href = url;
				/* $("#login").hide(); */
		}, "json");
}

function isLogIn_first() {
	// 已进入网站就开始检查，用于区别教师和普通用户
	var i = window.location.href.indexOf("servlet");
	var url = "UserServlet?method=isLogin";
	if (i < 0) {
		url = "servlet/UserServlet?method=isLogin";
	}
	$.get(url, function(data) {
				if (data.msg == false) {
					// alert("请先登录!");
					return;
				}
				// 登陆之后停掉islogin
				clearInterval(checkIsLogin);
				// 检查用户是不是有消息
				checkIsHasMsg(data.name);
			}, "json");
}
function check(name) {
	var i = window.location.href.indexOf("servlet");
	var url = "ChatServlet";
	if (i < 0) {
		url = "servlet/ChatServlet"; /* servlet/ChatServlet?method=openchat&name=data.data[i].fromuname */
	}
	$.get(url + "?method=checkIsHasMsg&name=" + name, function(data) {
		var c = "";
		if ($.trim($("#msgcontent").html()).length < 0) {
			$("#messagepanel").fadeOut("slow");
		}
		if (data.data.length > 0) {
			for (var i = 0; i < data.data.length; i++) {
				if (data.data[i].ischat == false) {
					c = c
							+ "<div class='msgitem'><a target='_blank' onclick='closemsgpanel()' href='"
							+ url + "?method=openchat&toname="
							+ data.data[i].fromuname + "&" + "key="
							+ data.data[i].chatkey + "'>"
							+ data.data[i].fromuname + "向您发送聊天请求"
							+ "</a></div>";
					$("#msgcontent").html(c);
					$("#messagepanel").show();
					continue;
				}
				receivemsg(data.data[i].chatkey, data.data[i].chatkey);
			}
		}
	}, "json");
}

function receivemsg(k, msgflag) {
	$.ajax({
				data : "post",
				url : "../servlet/ChatServlet",
				data : "method=receivemsg&key=" + k,
				cache : false,
				async : true,
				dataType : "json",
				success : function(data) {
					$("#" + msgflag).html(data.msgs);
					var scrollTop = $(".msg")[0].scrollHeight;
					// alert(scrollTop);
					$(".msg").scrollTop(scrollTop);
				}
			});
}

function receivemsg3(k, msgflag) {
	$.get("../servlet/ChatServlet?method=receivemsg&key=" + k, function(data) {
				alert(data.msgs);
				$("#" + msgflag).html(data.msgs);
				var scrollTop = $(".msg")[0].scrollHeight;
				// alert(scrollTop);
				$(".msg").scrollTop(scrollTop);
			}, "json");
}

function closemsgpanel() {
	$("#messagepanel").hide();
}

function checkIsHasMsg(name) {
	checkMsg = setInterval(function() {
				check(name);
			}, 1.5 * 1000);
}
