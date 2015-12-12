<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新用户角色权限</title>
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
			<td>用户名</td>
			<td><input type="text" value="${requestScope.u.username }"></td>
		</tr>
		<tr>
			<td>用户描述</td>
			<td><textarea rows="3" cols="50">${requestScope.u.description }</textarea></td>
		</tr>
		<tr>
			<td>用户原有角色</td>
			<td><c:forEach var="r" items="${requestScope.u.roles }">
					<div>${r.name}</div>
				</c:forEach></td>
		</tr>
		<tr>
			<td>可选角色</td>
			<td>
				<form method="post"
					action="${pageContext.request.contextPath}/servlet/UserServlet?method=updateUserRole&id=${u.id}">
					<c:forEach var="r" items="${requestScope.roles }">
						<div>
							<input type="checkbox" name="role" value="${r.id }">${r.name}
						</div>
					</c:forEach>
					<div>
						<input type="submit" value="授予角色">
					</div>
				</form>
			</td>

		</tr>
	</table>
</body>
</html>
