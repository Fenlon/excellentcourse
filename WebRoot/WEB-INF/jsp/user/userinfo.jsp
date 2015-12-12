<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${u.username }</title>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/cardlist.css">
<script type="text/javascript">
	function showitem(show, hide) {
		$(show).show();
		$(hide).hide();
	}
</script>
<style type="text/css">
.p {
	font-size: small;
	margin-top: 30px;
}

.p2 a {
	margin-left: 15px;
}

.pinfo {
	font-size: small;
}

.pinfo td {
	padding-top: 10px;
}

.persion table {
	width: 920px;
	margin: 0 auto;
	padding: 20px;
	border: 1px dotted gray;
}

.persion table th {
	border-bottom: 1px dotted gray;
}

.persion table td {
	border-bottom: 1px dotted gray;
}

.pcard {
	font-size: small;
	display: none;
}

.t2a {
	width: 150px;
}

.t2d {
	width: 100px;
}

.pcard1 {
	text-align: left;
}

.t2c {
	width: 140px;
}
</style>
</head>
<body>
	<div id="container">
		<div>
			<c:url value="../../public/head.jsp" var="head"></c:url>
			<c:import url="${head }"></c:import>
		</div>
		<div class="persion">
			<table class="p " cellpadding="0" cellspacing="0">
				<tr>
					<td><img src="${fn:replace(u.photopath,'.','_small.')}"></td>
					<td><div>
							${u.username }&nbsp;&nbsp;
							<c:if test="${sessionScope.user.username==u.username }">
								<a
									href="${pageContext.request.contextPath }/servlet/upHeadServlet?method=forupheadUI">修改头像</a>
							</c:if>
						</div>
						<div>
							<a
								href="${pageContext.request.contextPath
						}/servlet/UserServlet?method=forinfo&id=${u.id}">${pageContext.request.contextPath
								}/servlet/UserServlet?method=forinfo&id=${u.id}</a>
						</div></td>
					<td><a href="#">加为好友</a></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2" class="p2"><a href="javascript:void(0)"
						onclick="showitem('.pcard','.pinfo')">论坛</a><a
						href="javascript:void(0)">留言</a><a href="javascript:void(0)"
						onclick="showitem('.pinfo','.pcard')">个人资料</a></td>
				</tr>
			</table>

			<table class="pcard " cellpadding="0" cellspacing="0">
				<tr>
					<td class="t2a">板块</td>
					<td>主题</td>
					<td>发表时间</td>
					<td class="t2d">回复/查看</td>
					<td>最后发帖</td>
				</tr>
				<c:forEach var="c" items="${requestScope.pagebean.list }">
					<tbody>
						<tr>
							<td><a href="#" title="新窗口中打开"><img
									src="${pageContext.request.contextPath }/images/bbs/folder_new.gif" /></a>
								<span>【<a
									href="${pageContext.request.contextPath }/servlet/BBSUIServlet?method=forForumUI&forumid=${c.forum.id}">${c.forum.forumname
										}</a>】
							</span></td>
							<th class="t2b font pcard1"><a
								href="${pageContext.request.contextPath }/servlet/CardServlet?method=forCardContent&id=${c.id }">${c.title
									}</a></th>
							<td class="t2c font">
								<div>${c.createtime}</div>
							</td>
							<td class="t2d font"><div>
									<a href="#">${c.answernum }</a>
								</div>
								<div>${c.browsenum}</div></td>
							<td class="t2c font"><div>
									<a
										href="${pageContext.request.contextPath }/servlet/UserServlet?method=forinfo&id=${c.lastuser.id}">${c.lastuser.username
										}</a>
								</div>
								<div>
									<a href="#"><span title="">${c.lasttime}</span></a>
								</div></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>

			<table class="pinfo " cellpadding="0" cellspacing="0">
				<tr>
					<td>${u.username }</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>角色&nbsp; <c:forEach var="r"
							items="${requestScope.u.roles }">
							<span>${r.name}</span>
						</c:forEach>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>邮箱&nbsp;${u.email }</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>统计信息&nbsp;好友数&nbsp;10&nbsp;|&nbsp;回帖数&nbsp;10&nbsp;|&nbsp;留言数&nbsp;10&nbsp;</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>性别&nbsp;${u.gender}&nbsp;</td>
					<td>生日&nbsp;${u.birthday }</td>
					<td width="400px">居住地&nbsp;-</td>
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
