<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>在线论坛首页</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<style type="text/css">
.t1 {
	width: 959;
	border: 1px solid #cdcdcd;
	margin-top: 5px;
}

.t1 td {
	height: 85px;
	border-bottom: 1px dotted #cdcdcd;
}

.t1a {
	float: left;
	width: 40px;
	margin-left: 10px;
}

.a h2 {
	padding-left: 10px;
}
</style>
</head>
<body>
	<div id="container">
		<div>
			<div>
				<c:url value="../../public/head.jsp" var="head"></c:url>
				<c:import url="${head }"></c:import>
			</div>
			<div>
				<p>
					<a href="${pageContext.request.contextPath}/index.jsp"><img
						title="网站首页"
						src="${pageContext.request.contextPath}/images/public/root.jpg">
						> <a
						href="${pageContext.request.contextPath}/servlet/ForumServlet?method=forForumUI"
						title="论坛首页">论坛首页</a>
				</p>
			</div>
			<table class="t1" cellspacing="0">
				<c:forEach var="f" items="${requestScope.forums }" varStatus="index">
					<c:if test="${index.count%3==1 }">
						<tr>
							<td>
								<div class="t1a">
									<img
										src="${pageContext.request.contextPath }/images/bbs/forum_new.gif">
								</div>
								<div class="t1b">
									<div>
										<a target=""
											href="${pageContext.request.contextPath}/servlet/BBSUIServlet?method=forForumUI&forumid=${f.id}">${f.forumname}</a>
									</div>
									<div>${f.manager }</div>
								</div>
							</td>
					</c:if>
					<c:if test="${index.count%3==2 }">
						<td>
							<div class="t1a">
								<img
									src="${pageContext.request.contextPath }/images/bbs/forum_new.gif">
							</div>
							<div class="t1b">
								<div>
									<a target=""
										href="${pageContext.request.contextPath}/servlet/BBSUIServlet?method=forForumUI&forumid=${f.id}">${f.forumname}</a>
								</div>
								<div>${f.manager }</div>
							</div>
						</td>
					</c:if>

					<c:if test="${index.count%3==0 }">
						<td>
							<div class="t1a">
								<img
									src="${pageContext.request.contextPath }/images/bbs/forum_new.gif">
							</div>
							<div class="t1b">
								<div>
									<a target=""
										href="${pageContext.request.contextPath}/servlet/BBSUIServlet?method=forForumUI&forumid=${f.id}">${f.forumname}</a>
								</div>
								<div>${f.manager }</div>
							</div>
						</td>
						</tr>
					</c:if>


				</c:forEach>
			</table>
			<div>
				<c:url value="../../public/foot.jsp" var="foot"></c:url>
				<c:import url="${foot }"></c:import>
			</div>
		</div>
	</div>
</body>
</html>
