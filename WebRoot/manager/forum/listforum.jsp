<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>论坛管理页面</title>
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
				href="${pageContext.request.contextPath}/servlet/ForumServlet?method=addUI">添加板块</a></td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<td>板块名称</td>
				<td>版主</td>
				<td>操作</td>
			</tr>
			<c:forEach var="f" items="${requestScope.forums }">
				<tr>
					<td><span>${f.forumname }</span></td>
					<td><span>${f.manager }</span></td>
					<td><a
						href="${pageContext.request.contextPath}/servlet/ForumServlet?method=forUpdateManagerUI&id=${f.id}">修改版主</a>
						<a
						href="${pageContext.request.contextPath}/servlet/ForumServlet?method=delete&id=${f.id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</thead>
	</table>
</body>
</html>
