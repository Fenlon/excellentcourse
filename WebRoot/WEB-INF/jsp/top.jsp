<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.1.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/top.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/top.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">

</head>

<h1>精品课程</h1>
<div>
	<div id="container">
		<div id="info">
			<c:if test="${user !=null }">
				<div>
					<img alt="头像"
						src="${pageContext.request.contextPath }/images/photos/noavatar_small.gif">
					${sessionScope.user.username } <span id="msgcount"><a
						href="${pageContext.request.contextPath }/servlet/RemindMsgServlet?method=forRmsgUI">消息</a>
						：${sessionScope.user.msgcount }</span>
				</div>
			</c:if>
		</div>

		<div id="daohangbar">
			<ul>
				<li><a hidefocus="true"
					href="${pageContext.request.contextPath }/index.jsp" title="精品课程首页"><span>首页</span>
				</a></li>
				<li><a hidefocus="true" href="#" title="在线留言"><span>留言板</span>
				</a></li>
				<li><a hidefocus="true" href="#" title="在线测试"><span>在线测试</span>
				</a></li>
				<li><a hidefocus="true" href="#" title="下载资料"><span>文件下载</span>
				</a></li>
				<li><a hidefocus="true" href="#" title="在线答疑"><span>在线答疑</span>
				</a></li>
			</ul>
		</div>

	</div>
	<div id="login">
		<span id="b"> <input id="close" type="button" value="取消">
		</span>
		<div id="a">
			<div class="a">您需要先登录才能完成本操作</div>
			<table>
				<tr>
					<td>用户名</td>
					<td><input id="username" type="text"></td>
					<td><a href="#">用户注册</a></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input id="password" type="text"></td>
					<td><a href="#">找回密码</a></td>
				</tr>
				<tr>
					<div id="c">
						<td colspan="2"><input type="checkbox" value="autologin">自动登录
							<input type="button" value="登录" onclick="login()"></td>
					</div>
				</tr>
			</table>
		</div>
	</div>
</div>
</html>
