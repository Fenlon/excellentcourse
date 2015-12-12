<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<style type="text/css">
table {
	width: 700px;
}

table td {
	border: 1px solid green;
}
</style>
</head>

<body>
	<table>
		<tr>
			<td></td>
			<td></td>
			<td><a
				href="${pageContext.request.contextPath}/servlet/PrivilegeServlet?method=addUI">添加权限</a></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>名称</td>
			<td>描述</td>
			<td>操作</td>
		</tr>
		<c:forEach var="p" items="${requestScope.privileges }">
			<tr>
				<td><span>${p.name }</span></td>
				<td><span>${p.description }</span></td>
				<td><a
					href="${pageContext.request.contextPath}/servlet/PrivilegeServlet?method=delete&id=${p.id}">删除</a>
					<a
					href="${pageContext.request.contextPath}/servlet/PrivilegeServlet?method=updateUI&id=${p.id}">修改</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
