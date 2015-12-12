<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加论坛板块界面</title>
</head>

<body>
	<form method="post"
		action="${pageContext.request.contextPath}/servlet/ForumServlet?method=add">
		<table>
			<tr>
				<td>板块名称</td>
				<td><input type="text" name="forumname"></td>
			</tr>
			<tr>
				<td>版主</td>
				<td><input type="text" name="manager"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="reset" val="重置"> <input type="submit"
					value="添加板块"></td>
			</tr>
		</table>
	</form>
</body>
</html>
