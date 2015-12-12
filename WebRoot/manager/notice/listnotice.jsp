<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>公告管理页面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/pagequery.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/pagequery.css">
<style type="text/css">
table td {
	text-align: center;
	border: 1px solid green;
}

.noticeta {
	text-align: left;
	width: 450px;
}
</style>
</head>

<body>
	<div id="container">
		<table class="noticetable" cellpadding="0" cellspacing="0">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><a
					href="${pageContext.request.contextPath }/servlet/NoticeServlet?method=addUI">发布公告</a></td>
			</tr>
			<tr>
				<td>标题</td>
				<td>发布者</td>
				<td>时间</td>
				<td>操作</td>
			</tr>
			<c:forEach var="n" items="${requestScope.pagebean.list }">
				<tr>
					<td class="noticeta">${n.title }</td>
					<td>${n.user.username }</td>
					<td>${fn:substringBefore(n.time,".0")}</td>
					<td><a
						href="${pageContext.request.contextPath }/servlet/NoticeServlet?method=updateUI&id=${n.id }">修改</a>
						<a
						href="${pageContext.request.contextPath }/servlet/NoticeServlet?method=delete&id=${n.id }">删除</a></td>
				</tr>
			</c:forEach>
		</table>

		<table>
			<tr>
				<td><span>每页</span><input id="pagesize" type="text"
					style="width: 30px;" maxlength="2"
					value="${requestScope.pagebean.pagesize }"
					onchange="changePageSize(this.value,${requestScope.pagebean.pagesize },'${pageContext.request.contextPath }/servlet/NoticeServlet?method=listall&')">条
				</td>
				<td><span> <c:choose>
							<c:when test="${requestScope.pagebean.currentpage==1}">
								<c:out value="第一页     上一页"></c:out>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)"
									onclick="gotopage(1,'${pageContext.request.contextPath}/servlet/NoticeServlet?method=listall&')">第一页</a>
								<a href="javascript:void(0)"
									onclick="gotopage(${requestScope.pagebean.previouspage},'${pageContext.request.contextPath}/servlet/NoticeServlet?method=listall&')">上一页</a>
							</c:otherwise>
						</c:choose>


				</span> <c:forEach var="pagenum" items="${requestScope.pagebean.pagebar }">
						<c:if test="${pagenum==pagebean.currentpage}">
							<font color="red">${pagenum }</font>
						</c:if>
						<c:if test="${pagenum!=pagebean.currentpage}">
							<a href="javascript:void(0)"
								onclick="gotopage(${pagenum },'${pageContext.request.contextPath}/servlet/NoticeServlet?method=listall&')">${pagenum}</a>
						</c:if>
					</c:forEach> <span> <c:choose>
							<c:when
								test="${requestScope.pagebean.currentpage==requestScope.pagebean.totalpage}">
								<c:out value="下一页     最后页"></c:out>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)"
									onclick="gotopage(${requestScope.pagebean.nextpage},'${pageContext.request.contextPath}/servlet/NoticeServlet?method=listall&')">下一页</a>
								<a href="javascript:void(0)"
									onclick="gotopage(${requestScope.pagebean.totalpage},'${pageContext.request.contextPath}/servlet/NoticeServlet?method=listall&')">最后页</a>
							</c:otherwise>
						</c:choose>

				</span></td>
				<td>GO<input name="currentpage" id="pagenum" type="text"
					style="width: 50px;" /> <input type="image"
					style="margin-bottom: -3px;"
					onclick="gotopage(document.getElementById('pagenum').value,'${pageContext.request.contextPath}/servlet/NoticeServlet?method=listall&')"
					src="${pageContext.request.contextPath }/images/public/ok.jpg"></td>
			</tr>
		</table>
	</div>
</body>
</html>
