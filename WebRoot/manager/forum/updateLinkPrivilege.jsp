<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新链接权限</title>
</head>

<body>
	<table cellpadding="0" cellspacing="0">

		<tr>
			<td>链接uri</td>
			<td><input type="text" name="uri" value="${r.uri }"></td>
		</tr>
		<tr>
			<td>链接描述</td>
			<td><textarea rows="3" cols="50">${r.description }</textarea></td>
		</tr>
		<tr>
			<td>链接原有权限</td>
			<td><span> ${r.privilege.name } </span></td>
		</tr>
		<tr>
			<td>可选权限</td>
			<td>
				<form method="post"
					action="${pageContext.request.contextPath}/servlet/LinkServlet?method=updateLinkPrivilege&id=${r.id}">
					<c:forEach var="p" items="${requestScope.ps }">
						<div>
							<input type="radio" name="privilege" value="${p.id }">${p.name}
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
