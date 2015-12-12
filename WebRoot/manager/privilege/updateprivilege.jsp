<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新权限界面</title>
</head>

<body>
	<form method="post"
		action="${pageContext.request.contextPath}/servlet/PrivilegeServlet?method=update&id=${requestScope.p.id}">
		<table>
			<tr>
				<td>权限名称</td>
				<td><input type="text" name="name"
					value="${requestScope.p.name }"></td>
			</tr>
			<tr>
				<td>权限描述</td>
				<td><textarea name="description" cols="50" rows="5">${ requestScope.p.description}</textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="reset" val="重置"> <input type="submit"
					value="更新"></td>
			</tr>
		</table>
	</form>
</body>
</html>
