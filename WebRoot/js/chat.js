function closechat2() {
	// 关闭窗口时候调用
	var key = $("#closechatwin").attr("name");

	closechat('', key);

	return "您确定退出？";
}

function receivemsg2(k) {
	$.get("../servlet/ChatServlet?method=receivemsg&key=" + k, function(data) {
				$(".msg").html(data.msgs);
			}, "json");
}

function sendmsg(fname, k, toname) {
	var msg = encodeURI(editor.getContent());
	$.post(	"../servlet/ChatServlet?method=sendmsg&fname=" + fname + "&key="
					+ k + "&toname=" + toname, {
				msg : msg
			});
	editor.setContent("");
}
function chat(name) {
	$.ajax({
				data : "get",
				url : "servlet/ChatServlet",
				data : "method=chat&name=" + name,
				cache : false,
				async : false,
				dataType : "json",
				success : function(data) {
					key = data.key.chatkey;
					alert(key);
					if (data.key == null) {
						alert("您还没有登录，请先登录!");
						return;
					} else {
						openChatWindow(key);
					}
				}
			});
}
function closechat(item, key) {
	$("#chatClient").hide();
	$.get("../servlet/ChatServlet?method=closechat&key=" + key, function(data) {
				alert(您 + "退出聊天了！");
			}, "json");
}

function closemsgbox() {
	$("#messagepanel").fadeOut("slow");
}
