<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统管理首页</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/manager.css">
</head>

<body style="text-align: center;">
	<div id="container">
		<table cellpadding="0px;" cellspacing="0px;">
			<tr>
				<td style="background:gray;text-align: center;"><h1>
						<span>欢迎进入后台管理</span>
					</h1></td>
			</tr>

			<tr>
				<td>
					<div class="div1">
						<ul>
							<li class="k"><a href="/ExcellentCourse/index.jsp">首页</a></li>
							<li class="a"><a href="../DescribeCourse/index.html">课程介绍</a></li>
							<li class="b"><a href="../TeachTeam/index.html">教学团队</a></li>
							<li class="c"><a href="../TeachResources/index.html">教学资源</a></li>
							<li class="d"><a href="../TeachExperiment/index.html">实验教学</a></li>
							<li class="e"><a href="../TeachStudy/index.html">教学研究</a></li>
							<li class="f"><a href="../TeachFruit/index.html">教学成果</a></li>
							<li class="g"><a href="#">在线学习</a></li>
							<li class="h"><a
								href="${pageContext.request.contextPath }/servlet/ListFileServlet"
								target="maincenter">下载专区</a></li>
							<li class="i"><a
								href="${pageContext.request.contextPath }/servlet/ForumServlet?method=forForumUI"
								target="maincenter">在线论坛</a></li>
							<li class="j"><a
								href="${pageContext.request.contextPath }/servlet/MsgBoardServlet?method=forMsgBoardUI"
								target="maincenter">留言板</a></li>
						</ul>
					</div>
				</td>
			</tr>
			<tr>
				<td><iframe src="../manager/main.html" frameborder="0"
						width="960px"> </iframe></td>
			</tr>
			<tr>
				<td width="960px"><iframe id="bottom" class="bottom"
						src="../pages/bottom.html" scrolling="no" name="bottom"></iframe></td>
			</tr>
		</table>
	</div>
</body>
</html>
