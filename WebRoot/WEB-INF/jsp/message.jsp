<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/dph" prefix="dph"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>全局消息显示页面</title>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/m.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/chat.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/top.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/login.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/head.css">
<script type="text/javascript">
	function showitem(item, item2) {
		$(item).show();
		$(item2).hide();
	}
</script>

<style type="text/css">
.msgcontent {
	font-size: medium;
	margin: 0 auto;
	text-align: center;
	width: 500px;
	height: 249px;
	border: 5px solid gray;
}

.msgtitle {
	background: gray;
	height: 40px;
	font-size: large;
	padding-top: 15px;
	font-weight: bolder;
}

.mmcontent {
	background: #c9c9a7;
	height: 192px;
	vertical-align: middle;
	line-height: 192px;
}

.msgmsg {
	height: 353px;
	background: teal;
	padding-top: 100px;
}
</style>
</head>

<body>
	<div id="container">
		<div>
			<table cellpadding="0" cellspacing="0" class="taa1">
				<tr>
					<td class="taa12"></td>
					<td class="taa13"><c:choose>
							<c:when test="${user==null }">
								<div class="item1">
									<a href="#" onclick="showitem('.item3','.item1')">登录</a> | <a
										href="${pageContext.request.contextPath }/servlet/UserServlet?method=forRegisterUI"
										target="maincenter">注册</a> | <a
										href="${pageContext.request.contextPath }/htm/sendemail.html"
										target="maincenter">找回密码</a> | <a href="#">帮助</a> | <a
										href="#">联系我们&nbsp;</a>
								</div>
								<div class="item2 hide" style="display: none;">
									<span>欢迎您: </span><span>${user.username }</span> | <a href="#">我的信息</a>
									| <a href="#">消息</a> | <a target="maincenter"
										href="${pageContext.request.contextPath }/servlet/UserServlet?method=logout">退出</a>
									| <a href="#">联系我们&nbsp; </a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="item1 hide">
									<a
										href="${pageContext.request.contextPath }/servlet/ManagerServlet?method=formanagerUI"
										target="a_blank">管理员登录</a> | <a href="#"
										onclick="showitem('.item3','.item1')">登录</a> | <a
										href="${pageContext.request.contextPath }/servlet/UserServlet?method=forRegisterUI"
										target="maincenter">注册</a> | <a
										href="${pageContext.request.contextPath }/htm/sendemail.html"
										target="maincenter">找回密码</a> | <a href="#">帮助</a> | <a
										href="#">联系我们&nbsp;</a>
								</div>
								<div class="item2">
									<span>欢迎您: </span><span><a
										href="${pageContext.request.contextPath }/servlet/UserServlet?method=forinfo&id=${user.id}">${user.username
											}</a></span> | <img width="20px" height="20px"
										src="${fn:replace(user.photopath,'.','_small.') }">
									<dph:permission value="后台管理">| <a
											href="${pageContext.request.contextPath }/servlet/ManagerServlet?method=formanagerUI"
											target="_blank">后台管理</a>
									</dph:permission>
									| <a
										href="${pageContext.request.contextPath }/servlet/UserServlet?method=forinfo&id=${user.id}">我的信息</a>
									| <a
										href="${pageContext.request.contextPath }/servlet/RemindMsgServlet?method=forRmsgUI">消息</a>
									| <a target="maincenter"
										href="${pageContext.request.contextPath }/servlet/UserServlet?method=logout">退出</a>
									| <a href="#">联系我们&nbsp; </a>
								</div>
							</c:otherwise>
						</c:choose>


						<div class="taa11 item3" style="display: none;">
							<table class="taa2" cellpadding="0" cellspacing="0">
								<tr>
									<td>用户名</td>
									<td><input type="text" name="username"></td>
									<td><input type="checkbox" value="autologin">自动登录</td>
									<td><a target="maincenter"
										href="${pageContext.request.contextPath }/htm/sendemail.html">找回密码</a></td>
								</tr>
								<tr>
									<td>密码</td>
									<td><input type="text" name="password"></td>
									<td><a href="#"
										onclick="login('.taa2 input[name='+'username'+']','.taa2 input[name='+'password'+']','${pageContext.request.contextPath}/index.jsp')">登录</a></td>
									<td><input type="reset"
										onclick="showitem('.item1','.item3')" value="取消"></td>
								</tr>
							</table>
						</div></td>
				</tr>
			</table>

			<table class="div1" cellpadding="0" class="tbb1" cellspacing="0">
				<tr>
					<td><div>
							<div>
								<ul class="tbb1ul">
									<li class="k"><a
										href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
									<li class="a"><a
										href="${pageContext.request.contextPath }/pages/DescribeCourse/index.html"
										target="_blank">课程介绍</a></li>
									<li class="b"><a
										href="${pageContext.request.contextPath }/pages/TeachTeam/index.html"
										target="_blank">教学团队</a></li>
									<li class="c"><a
										href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
										target="_blank">教学资源</a></li>
									<li class="d"><a
										href="${pageContext.request.contextPath }/pages/TeachExperiment/index.html"
										target="_blank">实验教学</a></li>
									<li class="e"><a
										href="${pageContext.request.contextPath }/pages/TeachStudy/index.html"
										target="_blank">教学研究</a></li>
									<li class="f"><a
										href="${pageContext.request.contextPath }/pages/TeachFruit/index.html"
										target="_blank">教学成果</a></li>
									<li class="g"><a
										href="http://localhost:8080/OnlLineText/servlet/TestHomePageservlet"
										target="_blank">在线测试</a></li>
									<li class="h"><a
										href="${pageContext.request.contextPath }/servlet/ListFileServlet"
										target="_blank">下载专区</a></li>
									<li class="i"><a
										href="${pageContext.request.contextPath }/servlet/ForumServlet?method=forForumUI"
										target="_blank">在线论坛</a></li>
									<li class="j"><a
										href="${pageContext.request.contextPath }/servlet/MsgBoardServlet?method=forMsgBoardUI"
										target="_blank">留言板</a></li>
								</ul>
							</div></td>
				</tr>
			</table>

		</div>
		<div class="msgmsg">
			<div class="msgcontent">
				<div class="msgtitle">JAVAEE精品课程温馨提醒您</div>
				<div class="mmcontent">${requestScope.message}</div>
			</div>
		</div>
		<div id="messagepanel">
			<div class="msgtitle">
				<span>消息提醒</span> <span class="msgtitle1"><a
					href="javascript:void(0)" onclick="closemsgbox()">关闭</a></span>
			</div>
			<div id="msgcontent"></div>
			<div id="msgbottom"></div>
		</div>
		<div>
			<c:url value="../../WEB-INF/public/foot.jsp" var="foot"></c:url>
			<c:import url="${foot}"></c:import>
		</div>
	</div>
</body>
</html>
