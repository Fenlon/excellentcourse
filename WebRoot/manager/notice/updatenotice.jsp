<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新公告界面</title>
</head>

<body>
	<div id="container">
		<h2>修改公告</h2>
		<form method="post"
			action="${pageContext.request.contextPath}/servlet/NoticeServlet?method=update&id=${notice.id}">
			<table>
				<tr>
					<td>标题</td>
					<td><input type="text" name="title" value="${notice.title }"><span>不能超过255字符</span></td>
				</tr>
				<tr>
					<td>内容</td>
					<td><textarea rows="10" cols="100" name="content">${notice.content }</textarea></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="button" val="重置"> <input
						type="submit" value="更新"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
