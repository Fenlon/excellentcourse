<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${notice.title }</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<style type="text/css">
.detailtable {
	margin: 50 auto;
	border: 1px solid red;
	width: 900px;
}

.detailtable td {
	border: 1px solid green;
}

.detailta div {
	width: 400px;
	margin: 0 auto;
	text-align: center;
	height: 50px;
	padding-top: 30px;
}

.detailtb {
	text-align: center;
	height: 50px;
}
</style>
</head>

<body>
	<div id="container">
		<div>
			<c:url value="../../public/head.jsp" var="head"></c:url>
			<c:import url="${head }"></c:import>
		</div>
		<table class="detailtable" cellpadding="0" cellspacing="0">
			<tr>
				<td class="detailta">
					<div>${notice.title }</div>
				</td>
			</tr>
			<tr>
				<td class="detailtb"><span>发布者:&nbsp;${notice.user.username
						}</span><span>&nbsp;&nbsp;
						发布时间:&nbsp;${fn:substringBefore(notice.time,".0") }</span></td>
			</tr>
			<tr>
				<td class="detailtc">${notice.content }</td>
			</tr>
		</table>
		<div>
			<c:url value="../../public/foot.jsp" var="foot"></c:url>
			<c:import url="${foot }"></c:import>
		</div>
	</div>
</body>
</html>
