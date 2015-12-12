<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新角色权限</title>
<style type="text/css">
table {
	border: 1px solid red;
}

table td {
	border: 1px solid green;
}
</style>
</head>

<body>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td>角色名称</td>
			<td><input type="text" name="name"
				value="${requestScope.r.name }"></td>
		</tr>
		<tr>
			<td>角色描述</td>
			<td><textarea rows="3" cols="50">${requestScope.r.description }</textarea></td>
		</tr>
		<tr>
			<td>角色原有权限</td>
			<td><c:forEach var="p" items="${requestScope.r.privileges }">
					<div>${p.name}</div>
				</c:forEach></td>
		</tr>
		<tr>
			<td>可选权限</td>
			<td>
				<form method="post"
					action="${pageContext.request.contextPath}/servlet/RoleServlet?method=updateRolePrivilege&id=${r.id}">
					<c:forEach var="p" items="${requestScope.ps }">
						<div>
							<input type="checkbox" name="privilege" value="${p.id }">${p.name}
						</div>
					</c:forEach>
					<div>
						<input type="submit" value="授权">
					</div>
				</form>
			</td>

		</tr>
	</table>
</body>
</html>
