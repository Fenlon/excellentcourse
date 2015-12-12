<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加权限界面</title>
</head>

<body>
	<form method="post"
		action="${pageContext.request.contextPath}/servlet/PrivilegeServlet?method=add">
		<table>
			<tr>
				<td>权限名称</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>权限描述</td>
				<td><textarea name="description" cols="50" rows="5"></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="reset" val="重置"> <input type="submit"
					value="添加权限"></td>
			</tr>
		</table>
	</form>
</body>
</html>
