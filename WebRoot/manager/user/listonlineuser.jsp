<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'listuser.jsp' starting page</title>
<meta http-equiv="refresh" content="10">
</head>

<body>
	所有登陆用户为：${map.size() }
	<br />
	<c:forEach var="entry" items="${applicationScope.map}">
		<c:url var="url" value="/servlet/KickServlet">
			<c:param name="username" value="${entry.key}"></c:param>
		</c:url>
		${entry.key } + ${entry.value }  <a href="${url }">踢死你</a>
		<br />
	</c:forEach>

</body>
</html>
