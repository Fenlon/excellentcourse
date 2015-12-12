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
				href="${pageContext.request.contextPath}/servlet/RoleServlet?method=addUI">添加角色</a></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>名称</td>
			<td>拥有权限</td>
			<td>描述</td>
			<td>操作</td>
		</tr>
		<c:forEach var="r" items="${requestScope.roles }">
			<tr>
				<td><span>${r.name }</span></td>
				<td><c:forEach var="p" items="${r.privileges}">
						<div>${p.name }</div>
					</c:forEach></td>
				<td><span>${r.description }</span></td>
				<td><a
					href="${pageContext.request.contextPath}/servlet/RoleServlet?method=updatePrivilegeUI&id=${r.id}">为角色授权</a>
					<a
					href="${pageContext.request.contextPath}/servlet/RoleServlet?method=delete&id=${r.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
