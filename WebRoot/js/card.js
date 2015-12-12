$(function() {
			$(".answer").click(function() {
						isLogIn("#answer");
					});

			$("#cancle").click(function() {
						$("#answer").hide();
					});

			$("#close").click(function() {
						$("#login").hide();
					});
		});
function isLogIn(item) {
	$.get("UserServlet?method=isLogin", function(data) {
				if (data.msg == false) {
					$("#login").show();
					return;
				}
				$(item).show();
			}, "json");
}

function getIdByName(item) {
	$("#parentid").val($(item).attr("name"));
}

/*
 * function login() { var username = $("#username").val(); var password =
 * $("#password").val(); $.post("UserServlet?method=ajaxlogin", { username :
 * username, password : password }, function(data) { if (data.msg == false) {
 * alert("用户名密码错误!"); return; } $("#login").hide(); }, "json"); }
 */