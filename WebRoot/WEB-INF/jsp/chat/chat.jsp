<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>和${c.touname }聊天</title>
<script type="text/javascript" src="../js/jquery-1.9.1.js">
	
</script>
<script type="text/javascript" src="../ueditor/ueditor.config.js">
	
</script>
<script type="text/javascript" src="../ueditor/ueditor.all.js">
	
</script>
<script type="text/javascript" src="../js/login.js">
	
</script>
<script type="text/javascript" src="../js/chat.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<script type="text/javascript">
	window.onbeforeunload = closechat2; // 注册关闭前的事件
	// window.onunload = UnloadConfirm; // 注册关闭后的事件
	$(document).ready(function(e) {
		$(".drag").mousedown(function(e) {
			iDiffX = e.pageX - $(this).offset().left;
			iDiffY = e.pageY - $(this).offset().top;
			$(document).mousemove(function(e) {
				$("#chatClient").css({
					"left" : (e.pageX - iDiffX),
					"top" : (e.pageY - iDiffY)
				});
			});
		});
		$(".drag").mouseup(function() {
			$(document).unbind("mousemove");
		});
	});
</script>
<style type="text/css">
body a {
	text-decoration: none;
}

#chatClient {
	width: 500px;
	height: 550px;
	border: 1px solid gray;
	background: gray;
	margin: 0 auto;
	position: absolute;
}

#chatClient * {
	opacity: 1;
}

.drag {
	width: 500px;
	height: 25px;
	background: gray;
	cursor: move;
	line-height: 25px;
}

.drag a {
	margin-left: 430px;
}

.b {
	width: 500px;
	height: 25px;
	background: pink;
}

.c {
	
}

.d {
	width: 353px;
	float: left;
}

.msg {
	height: 315px;
	background: white;
	overflow: auto;
}

.edit {
	height: 150px;
	background: activeborder;
}

.send {
	height: 35px;
	background: gray;
	text-align: right;
}

.send div {
	font-size: x-small;
	padding-top: 12px;
}

.send a {
	float: left;
	margin-top: 8px;
}

.pinfo {
	width: 147px;
	height: 500px;
	background: #f5edbc;
	float: right;
}
</style>


</head>
<body>
	<div>
		<c:if test="${chats!=null }">
			<table>
				<c:forEach var="c" items="${chats }">
					<tr>
						<td><a href="javascript:void(0)" onclick="chat2(this)">${c}</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>

	<div id='chatClient'>
		<div class='drag'>
			<div>
				${c.touname} <a href="javascript:void(0)" id="closechatwin"
					name="${c.chatkey }" onclick="closechat(this,this.name)">关闭</a>
			</div>
		</div>
		<div class='b'></div>
		<div class='c'>
			<div class='d'>
				<div class='msg' id="${c.chatkey}">
					<!-- <textarea rows='10' cols='45'></textarea> -->
				</div>
				<form method='post'
					action='../servlet/ChatServlet?method=sendmsg&name=F'>
					<div class='edit'>
						<!-- 	<textarea id='content'></textarea> -->
						<script type='text/javascript' id='content' name='msg'></script>
						<script type='text/javascript'>
							var option = {
								emotionLocalization : true,
								initialFrameWidth : 350,
								initialFrameHeight : 100,
								autoHeightEnabled : false,
								wordCount : true,
								toolbars : [ [ 'bold', 'italic', 'undo',
										'redo', 'emotion', 'insertimage' ] ]
							//设置宽度 
							}
							var editor = new UE.ui.Editor(option);
							editor.render('content');
							//1.2.4以后可以使用一下代码实例化编辑器
							//UE.getEditor('myEditor')
						</script>
					</div>
					<div class='send'>
						<div>
							<a href='#'>精品课程首页</a> <input type='button' name="${c.chatkey }"
								value='关闭' onclick='receivemsg(this.name)'> <input
								type='button' id='btnsend' value='发送' name="${c.chatkey }"
								onclick="sendmsg('${user.username}',this.name,'${c.touname }')">
						</div>

						<div class='iey'>
							<a href=""></a>
						</div>
					</div>
				</form>
			</div>
			<div class='pinfo'>
				<span>注意事项</span></br> <span>1：禁止发表不良内容</span></br> <span>2：如果您的消息突然为空说明对方退出了聊天但是你还可以和他聊天，系统会重新通知他的</span>
				</br> <span>3：聊天框可以拖动</span> <span>1：禁止发表不良内容</span> <span>1：禁止发表不良内容</span>
			</div>
		</div>
	</div>

</body>
</html>
