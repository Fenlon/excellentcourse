<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<%@ taglib uri="/dph" prefix="dph"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("path", path);
%>
<html>
<head>
<title>论坛</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/pagequery.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/cardlist.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/pagequery.css">
<style type="text/css">
table td {
	font-size: small;
}

#container {
	border: 1px dotted green;
}

#info {
	border: 1px solid gray;
}

.t2c {
	width: 120px;
}
</style>
</head>

<body>
	<div id="container">
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
					title="论坛首页">论坛首页</a> > </a><a
					href="${pageContext.request.contextPath}/servlet/BBSUIServlet?method=forForumUI&forumid=${forum.id}">${forum.forumname
					}</a>
			</p>
		</div>
		<hr>
		<a style="margin-left: 5px; "
			href="${pageContext.request.contextPath}/servlet/BBSUIServlet?method=forEditBBSUI&forumid=${requestScope.forum.id}">发帖</a>
		<hr>
		<div>
			<div>
				<table class="t1 public" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<th colspan="2">
								<div class="font">
									<a href="#">全部主题</a><img
										src="${pageScope.path }/images/bbs/arrwd.gif">&nbsp; <a
										href="#">最新</a>&nbsp; <a href="#">热门</a>&nbsp; <a href="#">热帖</a>&nbsp;
									<a href="#">精华</a>&nbsp; <a href="#">更多</a><img
										src="${pageScope.path }/images/bbs/arrwd.gif">&nbsp; <span
										style="display: none;"> <span>|</span> <a
										href="javascript:;" title="显示置顶">显示置顶</a>
									</span>
								</div>
							</th>
							<td class="t1a t2c">作者</td>
							<td class="t2d t1a">回复/查看</td>
							<td class="t1a t2c">最后发表</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table class="t2 public" cellpadding="0" cellspacing="0">
				<c:forEach var="c" items="${requestScope.pagebean.list }">
					<tbody>
						<tr class="font">
							<td class="t2a"><a href="#" title="新窗口中打开"><img
									src="${pageScope.path }/images/bbs/folder_new.gif" /></a></td>
							<th class="t2b"><span>【<a href="#">${c.forum.forumname
										}</a>】
							</span> <a
								href="${pageContext.request.contextPath }/servlet/CardServlet?method=forCardContent&id=${c.id }">${c.title
									}</a> <c:if test="${c.forum.forumname=='新人报到' }">
									<img src="${pageScope.path }/images/bbs/new.small.gif">
								</c:if></th>
							<td class="preview font">
								<div>
									<dph:permission value="删除论坛主题">
										<a
											href="${pageContext.request.contextPath }/servlet/CardServlet?method=delete&forumid=${c.forum.id }&id=${c.id}&currentpage=${pagebean.currentpage}&pagesize=${pagebean.pagesize}">删除</a>
									</dph:permission>
									<input type="button" value="预览">
								</div>
							</td>
							<td class="t2c font"><div>
									<a
										href="${pageScope.path }/servlet/UserServlet?method=forinfo&id=${c.auther.id }">${c.auther.username}</a>
								</div>
								<div>${c.createtime}</div></td>
							<td class="t2d font"><div>
									<a href="#">${c.answernum }</a>
								</div>
								<div>${c.browsenum}</div></td>
							<td class="t2c font"><div>
									<a
										href="${pageScope.path }/servlet/UserServlet?method=forinfo&id=${c.lastuser.id }"
										c="1" mid="card_6226">${c.lastuser.username }</a>
								</div>
								<div>
									<a href="#"><span title="">${c.lasttime}</span></a>
								</div></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<table class="pagebar" cellpadding="0" cellspacing="0">
				<tr>
					<td><span>每页</span><input id="pagesize" type="text"
						style="width: 30px;" maxlength="2"
						value="${requestScope.pagebean.pagesize }"
						onchange="changePageSize(this.value,${requestScope.pagebean.pagesize },'${path}/servlet/BBSUIServlet?method=forForumUI&forumid=${forum.id}&')">条
					</td>
					<td class="pa"><span> <c:choose>
								<c:when test="${requestScope.pagebean.currentpage==1}">
									<c:out value="第一页     上一页"></c:out>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)"
										onclick="gotopage(1,'${path}/servlet/BBSUIServlet?method=forForumUI&forumid=${forum.id}&')">第一页</a>
									<a href="javascript:void(0)"
										onclick="gotopage(${requestScope.pagebean.previouspage},'${path}/servlet/BBSUIServlet?method=forForumUI&forumid=${forum.id}&')">上一页</a>
								</c:otherwise>
							</c:choose>


					</span> <c:forEach var="pagenum" items="${requestScope.pagebean.pagebar }">
							<c:if test="${pagenum==pagebean.currentpage}">
								<font color="red">${pagenum }</font>
							</c:if>
							<c:if test="${pagenum!=pagebean.currentpage}">
								<a href="javascript:void(0)"
									onclick="gotopage(${pagenum },'${path}/servlet/BBSUIServlet?method=forForumUI&forumid=${forum.id}&')">${pagenum}</a>
							</c:if>
						</c:forEach> <span> <c:choose>
								<c:when
									test="${requestScope.pagebean.currentpage==requestScope.pagebean.totalpage}">
									<c:out value="下一页     最后页"></c:out>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)"
										onclick="gotopage(${requestScope.pagebean.nextpage},'${path}/servlet/BBSUIServlet?method=forForumUI&forumid=${forum.id}&')">下一页</a>
									<a href="javascript:void(0)"
										onclick="gotopage(${requestScope.pagebean.totalpage},'${path}/servlet/BBSUIServlet?method=forForumUI&forumid=${forum.id}&')">最后页</a>
								</c:otherwise>
							</c:choose>
					</span></td>
					<td class="pa pb">GO<input name="currentpage" id="pagenum"
						type="text" style="width: 50px;" /> <input type="image"
						style="margin-bottom: -3px;"
						onclick="gotopage(document.getElementById('pagenum').value,'${path}/servlet/BBSUIServlet?method=forForumUI&forumid=${forum.id}&')"
						src="${pageContext.request.contextPath }/images/public/ok.jpg"></td>
				</tr>
			</table>
		</div>
		<div>
			<c:url value="../../public/foot.jsp" var="foot"></c:url>
			<c:import url="${foot }"></c:import>
		</div>
	</div>
</body>
</html>
