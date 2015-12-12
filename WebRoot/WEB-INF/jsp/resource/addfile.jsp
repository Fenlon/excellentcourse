<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
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

.filecont {
	margin: 0 auto;
	width: 700px;
	height: 300px;
}

.cc11 {
	background: #CC9999;
}

.filecont td {
	border-bottom: 1px dotted gray;
}
</style>
</head>
<body>
	<div id="container" style="text-align: center;">
		<div>
			<c:url value="../../public/head.jsp" var="head"></c:url>
			<c:import url="${head }"></c:import>
		</div>
		<div class="cc11">
			<form
				action="${pageContext.request.contextPath }/servlet/UpfileServlet"
				method="post" enctype="multipart/form-data">
				<table class="filecont" frame="border" cellpadding="0"
					cellspacing="0">
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
		<div>
			<c:url value="../../public/foot.jsp" var="foot"></c:url>
			<c:import url="${foot }"></c:import>
		</div>
	</div>
</body>
</html>
