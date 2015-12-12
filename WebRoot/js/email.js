$(function() {
			$("input[name='username']").blur(function() {
						validatename();
						if ($.trim($("input[name='email']").val()).length >= 1) {
							validate();
						}
					});
			$("input[name='email']").blur(function() {
						if (!validateEmail(this)) {
							$(".warn").eq(1).show();
							return;
						}
						if ($.trim($("input[name='username']").val()).length >= 1) {
							validatename();
						}
						validate();
						$(".warn").eq(1).hide();
					});
		});

function validateEmail(item) {
	var email = $(item).val();
	var re = new RegExp("\\w+@\\w+(\\.\\w+)+")
	r = email.match(re);
	if (r == null) {
		return false;
	}
	return true;
}
function validatename() {
	var username = $.trim($("input[name='username']").val());
	$.get(	"../servlet/SendEmailServlet?method=ajaxvalidatename&username="
					+ username, function(data) {
				if (!data.msg) {
					$(".warn").eq(0).show();
				} else {
					$(".warn").eq(0).hide();
				}
			}, "json");
}

function validate() {
	var username = $.trim($("input[name='username']").val());
	var email = $.trim($("input[name='email']").val());
	$.get(	"../servlet/SendEmailServlet?method=ajaxvalidate&username="
					+ username + "&email=" + email, function(data) {
				if (!data.msg) {
					alert("输入地址与注册地址不一致！")
					$("input[name='email']").val("");
				}
			}, "json");
}
