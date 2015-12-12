<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>链接管理页面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<style type="text/css">
table {
	padding: 0px;
	margin: 0px;
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
			<td></td>
			<td><a
				href="${pageContext.request.contextPath}/servlet/LinkServlet?method=addUI">添加链接</a></td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<td>链接uri</td>
				<td>控制链接权限</td>
				<td>连接描述</td>
				<td>操作</td>
			</tr>
			<c:forEach var="l" items="${requestScope.ls }">
				<tr>
					<td><span>${l.uri }</span></td>
					<td><span>${l.privilege.name }</span></td>
					<td><span>${l.description }</span></td>
					<td><a
						href="${pageContext.request.contextPath}/servlet/LinkServlet?method=forUpdatePrivilegeUI&id=${l.id}">修改权限</a>
						<a
						href="${pageContext.request.contextPath}/servlet/LinkServlet?method=delete&id=${l.id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</thead>
	</table>
</body>
</html>
