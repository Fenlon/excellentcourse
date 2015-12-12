<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${sessionScope.user.username }的个人提醒</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/mymsg.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.1.js">
	
</script>
<script type="text/javascript">
	function gotoCard(item) {
		var cardid = $(item).prevAll("input").val();
		var msgid = $(item).nextAll("input").val();
		if (cardid == null || msgid == null) {
			return;
		}
		var url = '${pageContext.request.contextPath }/servlet/CardServlet?method=forCardContent&id='
				+ cardid + "&msgid=" + msgid;
		window.location.href = url;
	}
</script>
</head>
<body>
	<div id="container">
		<div>
			<c:url value="../public/head.jsp" var="head"></c:url>
			<c:import url="${head}"></c:import>
		</div>

		<table class="mymsgt" cellpadding="0" cellspacing="0">
			<tr>
				<td id="left"><div>
						<ul>
							<li>通知</li>
							<li><div class="em">
									<em class="notice_pm"></em> <a
										href="${pageContext.request.contextPath }/servlet/RemindMsgServlet?method=listmsgs">消息</a>
								</div></li>
							<li><div class="em">
									<em class="notice_mycard"></em> <a href="#">我的帖子</a>
								</div></li>
							<li><div class="em">
									<em class="notice_system"></em> <a href="#">系统提醒</a>
								</div></li>
						</ul>
					</div></td>
				<td id="right"><div>
						<c:if test="${ requestScope.flag=='msgflag' }">
							<div id="msg">
								<c:forEach var="m" varStatus="index"
									items="${requestScope.bbsmsgs }">
									<div>
										<div>${index.count }</div>
										<div>在${m.time }</div>
										<div>${m.user.username }回复了您的帖子</div>
										<div>
											<input type="hidden" value="${m.card.id }"> <a
												href="javascript:void(0)" onclick="gotoCard(this)">${m.card.title}</a>
											<a href="javascript:void(0)" onclick="gotoCard(this)">查看</a>
											<input type="hidden" value="${m.id }">
										</div>
										<hr>
									</div>
								</c:forEach>
							</div>
						</c:if>
						<div id="mycard"></div>
						<div id="system"></div>
					</div></td>
			</tr>
		</table>
		<div>
			<c:url value="../public/foot.jsp" var="foot"></c:url>
			<c:import url="${foot }"></c:import>
		</div>
	</div>
</body>
</html>
