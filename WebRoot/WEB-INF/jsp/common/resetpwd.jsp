<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>重置密码</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
</head>
<body>
	<div id="container">
		<div>
			<c:url value="../../public/head.jsp" var="head"></c:url>
			<c:import url="${head }"></c:import>
		</div>
		${uid}
		<form
			action="${pageContext.request.contextPath }/servlet/ResetPwdServlet"
			method="post">
			<input type="hidden" value="${requestScope.uid }" name="uid">
			设置新密码：<input type="text" name="password"> <input
				type="submit" value="提交">
		</form>
	</div>
</body>
</html>
