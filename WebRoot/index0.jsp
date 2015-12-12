<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>精品课程首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
body {
	text-align: center;
}

#container {
	width: 990px;
	height: 630px;
	margin: 0 auto;
	text-align: center;
}

#top1 {
	height: 130px;
	border: 1px solid red;
}

#main iframe {
	width: 990px;
	height: 530px;
	border: 1px solid green;
}
</style>

<script type="text/javascript">
	/* if (window != top) {
		top.location.href = location.href;
	} */
</script>
</head>

<body>
	<div id="container">
		<div id="top1">
			<c:url value="WEB-INF/jsp/head.jsp" var="top1"></c:url>
			<c:import url="${top1 }"></c:import>
		</div>
		<div id="main">
			<iframe name="main" src="htm/main.htm" scrolling="no" frameborder="0"></iframe>
		</div>
	</div>
</body>
</html>
