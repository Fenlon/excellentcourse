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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<style type="text/css">
#mainframe {
	width: 960px;
	height: 700px;
	border: 1px solid red;
}

.container {
	width: 960px;
	text-align: center;
}

.top1 {
	width: 960px;
}

.menu1 {
	width: 960px;
	height: 220px;
}

.center1 {
	width: 960px;
	height: 250px;
}

.subject1 {
	width: 960px;
	height: 510px;
	padding: 0px;
	margin: 0px;
}

.bottom1 {
	width: 960px;
	height: 106px;
}
</style>
<script type="text/javascript">
	if (window != top) {
		top.location.href = location.href;
	}
</script>
</head>

<body>
	<div id="container">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td><div id="top1">
						<c:url value="WEB-INF/public/head.jsp" var="top1"></c:url>
						<c:import url="${top1 }"></c:import>
					</div></td>
			</tr>
			<tr>
				<td><div id="main">
						<iframe id="main1" class="subject1"
							src="${pageContext.request.contextPath }/servlet/MainCenterUIServlet?method=formainUI"
							scrolling="no" name="maincenter"></iframe></td>
			</tr>

			<tr>
				<td>
					<table cellpadding="0" cellspacing="0"
						style="border-top-style: groove; border-width: thin; border-color: #99CCFF;padding: 0px; margin: 0px;">
						<tr>
							<td
								style=" width:960px; height:105px; text-align:center; background-color: #666666; font-size: small;">
								<p>
									Copyright @2013 <a href="http://www.zzti.edu.cn/"
										target="_blank">中原工学院</a><a href="http://www.cs.zzti.edu.cn/"
										target="_blank"> 计算机学院</a>设计版权所有 <br /> <br /> 技术支持：计算机学院
									软件113班 Fighting <br /> <br /> 课程网站所有资料仅用于教学与研究
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
