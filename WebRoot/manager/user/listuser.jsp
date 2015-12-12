<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/pagequery.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<style type="text/css">
table {
	width: 700px;
}

table td {
	border: 1px solid green;
	font-size: xx-small;
}
</style>
</head>

<body>
	<table>
		<tr>
			<td></td>
			<td></td>
			<td><a
				href="${pageContext.request.contextPath}/servlet/UserServlet?method=forRegisterUI">添加用户</a></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>用户名</td>
			<td>昵称</td>
			<td>生日</td>
			<td>邮箱</td>
			<td>角色</td>
			<td>操作</td>
		</tr>
		<c:forEach var="u" items="${requestScope.pagebean.list }">
			<tr>
				<td><span>${u.username }</span></td>
				<td><span>${u.nickname }</span></td>
				<td><span>${u.birthday }</span></td>
				<td><span>${u.email }</span></td>
				<td>
					<ul>
						<c:forEach var="r" items="${u.roles}">
							<li>${r.name}</li>
						</c:forEach>
					</ul>
				</td>



				<td>
					<div>
						<a
							href="${pageContext.request.contextPath}/servlet/UserServlet?method=updateRoleUI&id=${u.id}">为用户授角色</a>
					</div>
					<div>
						<a
							href="${pageContext.request.contextPath}/servlet/UserServlet?method=delete&id=${u.id}">删除</a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<table>
		<tr>
			<td><span>每页</span><input id="pagesize" type="text"
				style="width: 30px;" maxlength="2"
				value="${requestScope.pagebean.pagesize }"
				onchange="changePageSize(this.value,${requestScope.pagebean.pagesize },'${pageContext.request.contextPath }/servlet/UserServlet?method=listall&')">条
			</td>
			<td><span> <c:choose>
						<c:when test="${requestScope.pagebean.currentpage==1}">
							<c:out value="第一页     上一页"></c:out>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0)"
								onclick="gotopage(1,'${pageContext.request.contextPath}/servlet/UserServlet?method=listall&')">第一页</a>
							<a href="javascript:void(0)"
								onclick="gotopage(${requestScope.pagebean.previouspage},'${pageContext.request.contextPath}/servlet/UserServlet?method=listall&')">上一页</a>
						</c:otherwise>
					</c:choose>


			</span> <c:forEach var="pagenum" items="${requestScope.pagebean.pagebar }">
					<c:if test="${pagenum==pagebean.currentpage}">
						<font color="red">${pagenum }</font>
					</c:if>
					<c:if test="${pagenum!=pagebean.currentpage}">
						<a href="javascript:void(0)"
							onclick="gotopage(${pagenum },'${pageContext.request.contextPath}/servlet/UserServlet?method=listall&')">${pagenum}</a>
					</c:if>
				</c:forEach> <span> <c:choose>
						<c:when
							test="${requestScope.pagebean.currentpage==requestScope.pagebean.totalpage}">
							<c:out value="下一页     最后页"></c:out>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0)"
								onclick="gotopage(${requestScope.pagebean.nextpage},'${pageContext.request.contextPath}/servlet/UserServlet?method=listall&')">下一页</a>
							<a href="javascript:void(0)"
								onclick="gotopage(${requestScope.pagebean.totalpage},'${pageContext.request.contextPath}/servlet/UserServlet?method=listall&')">最后页</a>
						</c:otherwise>
					</c:choose>

			</span></td>
			<td>GO<input name="currentpage" id="pagenum" type="text"
				style="width: 50px;" /> <input type="image"
				style="margin-bottom: -3px;"
				onclick="gotopage(document.getElementById('pagenum').value,'${pageContext.request.contextPath}/servlet/UserServlet?method=listall&')"
				src="${pageContext.request.contextPath }/images/public/ok.jpg"></td>
		</tr>
	</table>
</body>
</html>
