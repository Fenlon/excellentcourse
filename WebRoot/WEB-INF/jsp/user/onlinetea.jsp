<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<style type="text/css">
.menue {
	background: gray;
	border: 1px solid red;
	text-align: center;
	padding-top: 5px;
	font-size: medium;
}

.m1c table td {
	font-size: small;
	height: 30px;
	background: pink;
	line-height: 30px;
}

.m1c div img {
	margin: 0px;
	padding: 0px;
}

.m1c div span {
	
}
</style>
</head>

<body>
	<div>
		<div class="menue">在线教师</div>
		<div class="m1c">
			<table cellpadding="0" cellspacing="10">
				<c:forEach var="t" items="${requestScope.olts}">
					<tr>
						<td><img
							src="${pageContext.request.contextPath }/images/icos/tea.jpg"></td>
						<td><span> <a
								href="${pageContext.request.contextPath }/servlet/ChatServlet?method=chat&name=${t.username}"
								target="_blank">${t.nickname }</a>
						</span></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
	<div>
		<div class="menue">所有教师</div>
		<div class="m1c">
			<table cellpadding="0" cellspacing="10">
				<c:forEach var="t" items="${requestScope.ts}">
					<tr>
						<td><img
							src="${pageContext.request.contextPath }/images/icos/tea.jpg"></td>
						<td><span>${t.nickname }</span></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
