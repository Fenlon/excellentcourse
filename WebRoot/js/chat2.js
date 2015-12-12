var key;

function openChatWindow() {
	window.open("chat/chat.jsp");
}

function receivemsg() {
	alert(key);
	$.get("../servlet/ChatServlet?method=receivemsg&key=" + key,
			function(data) {
				// $(".msg").val(data.msgs);
				$(".msg").html(data.msgs);
			}, "json");
}

function sendmsg(name) {
	alert(key);
	var msg = encodeURI(editor.getContent());
	$.post(
			"../servlet/ChatServlet?method=sendmsg&name=" + name + "&key="
					+ key, {
				msg : msg
			});
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
						openChatWindow();
					}
				}
			});
}
function closechat(item) {
	$.get("../servlet/ChatServlet?method=closechat&key=" + key, function(data) {
				alert(data.msg + "退出聊天了！");
			}, "json");
}

function beginchat(item) {
	key = $(item).attr("name");
	alert(key);
	openChatWindow();
}
function getcontent(key) {

}
function chat2(link) {
	$("#chatClient").show();
	key = $(link).html();
}