<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>上传页面</title>
<link href="${pageContext.request.contextPath }/css/common.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.btn {
	background: #D6D6D6;
	height: 35px;
}

table {
	margin: 0 auto;
	width: 500px;
}

table td {
	border-bottom: 1px dotted gray;
}
</style>
</head>
<body>
	<div id="container" style="text-align: center;">
		<form
			action="${pageContext.request.contextPath }/servlet/UpfileServlet"
			method="post" enctype="multipart/form-data">
			<table frame="border">
				<tr>
					<td>上传文件名</td>
					<td><input type="text" name="filename"></td>
				</tr>
				<tr>
					<td>上传文件</td>
					<td><input type="file" name="file"></td>
				</tr>

				<tr>
					<td>文件描述</td>
					<td><textarea rows="6" cols="50" name="description"></textarea>
					</td>
				</tr>

				<tr>
					<td></td>
					<td><input class="btn" type="submit" value="上传"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
