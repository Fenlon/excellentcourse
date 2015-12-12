<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.1.js">
	
</script>
<script type="text/javascript">
	/*新闻滚动*/
	$(function() {
		var $this = $(".scrollNews");
		var scrollTimer;
		$this.hover(function() {
			clearInterval(scrollTimer);
		}, function() {
			scrollTimer = setInterval(function() {
				scrollNews($this);
			}, 3000);
		}).trigger("mouseleave");
	});
	function scrollNews(obj) {
		var $self = obj.find("div:first");
		var lineHeight = $self.find("div:first").height(); //获取行高
		$self.animate({
			"marginTop" : -lineHeight + "px"
		}, 600, function() {
			$self.css({
				marginTop : 0
			}).find("div:first").appendTo($self); //appendTo能直接移动元素
		})
	}
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/maincenter.css">
</head>
<body>
	<table class="table1" cellpadding="3" cellspacing="7">
		<tr>
			<td class="maintable1">
				<table class="news2" cellpadding="0" cellspacing="0">
					<tr>
						<td><div>
								<div class="label1">
									最新公告&nbsp;&nbsp;<a class="more"
										href="${pageContext.request.contextPath }/servlet/NoticeServlet?method=listall&page=indexmore"
										target="_blank">more</a>
								</div>
							</div>
							<div class="news">
								<div class="scrollNews">
									<div class="item">
										<c:forEach var="n" items="${requestScope.ns }">
											<div>
												<img
													src="${pageContext.request.contextPath }/images/icos/news.jpg">
												&nbsp;<a target="_blank"
													href="${pageContext.request.contextPath }/servlet/NoticeServlet?method=detail&id=${n.id}"
													title="${n.title }">${n.title }</a>
											</div>
										</c:forEach>
									</div>
								</div>
							</div></td>
					</tr>
					<tr>
						<td>
							<div class="msgcard">
								<div>
									<div class="label1">
										最新帖子&nbsp;&nbsp;<a class="more"
											href="${pageContext.request.contextPath }/servlet/ForumServlet?method=forForumUI"
											target="_blank">more</a>
									</div>
								</div>
								<div>
									<div class="item">
										<c:forEach var="c" items="${requestScope.cs }">
											<div>
												<img
													src="${pageContext.request.contextPath }/images/bbs/folder_new.gif">
												&nbsp;<a target="_blank"
													href="${pageContext.request.contextPath }/servlet/CardServlet?method=forCardContent&id=${c.id}"
													title="[${c.forum.forumname }] ${c.title }">${c.title }</a>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="tuijian item">
								<div class="label1">相关推荐</div>
								<div>
									<img
										src="${pageContext.request.contextPath }/images/icos/link.jpg">
									&nbsp;<a href="http://www.jpkcw.com/" target="_blank">国家精品课程网站</a>
								</div>
								<div>
									<img
										src="${pageContext.request.contextPath }/images/icos/link.jpg">
									&nbsp;<a href="http://netclass.csu.edu.cn/jpkc/"
										target="_blank">中南大学精品课程网站</a>
								</div>
								<div>
									<img
										src="${pageContext.request.contextPath }/images/icos/link.jpg">
									&nbsp;<a href="http://www.jpk.pku.edu.cn/pkujpk/"
										target="_blank">北京大学精品课程网站</a>
								</div>
								<div>
									<img
										src="${pageContext.request.contextPath }/images/icos/link.jpg">
									&nbsp;<a
										href="http://www.tsinghua.edu.cn/publish/th/6198/index.html"
										target="_blank">清华大学精品课程网站</a>
								</div>
								<div>
									<img
										src="${pageContext.request.contextPath }/images/icos/link.jpg">
									&nbsp;<a href="http://www.ie.sjtu.edu.cn/jc" target="_blank">上海交大精品课程网站</a>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</td>

			<td>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td><div class="label1">课程描述</div></td>
					</tr>
					<tr>
						<td><table>
								<tr>
									<td><a
										href="${pageContext.request.contextPath }/pages/DescribeCourse/index.html"
										target="_blank"><img alt=""
											src="${pageContext.request.contextPath }/pages/image/f.jpg" /></a></td>
									<td line-height:px>&nbsp;&nbsp;
										《J2EE开发技术》是软件工程专业重点建设的一门核心课程,同时也是该专业的一门重要的专业知识课程。该课程在2007届软件技术专业首次开设,目标是培养学生使用流行、成熟的框架技术,开发基于J2EE框架的WEB应用系统的职业能力。本课程的先修课程：
										Java程序设计、数据库</td>
								</tr>
							</table>&nbsp;&nbsp;&nbsp;
							J2EE开发技术应用于基于B/S应用系统开发领域,是软件技术专业学生毕业后的主要就业方向,因此J2EE开发技术课程在软件技术专业课程体系中具有重要的地位,应作为专业核心课程和必修课程。
							当前企业应用开发的重要方向就是基于框架技术的开发应用,本课程是培养软件企业需要的高技能人才的职业能力的核心课程,主要培养学生使用流行、成熟的框架技术,开发基于J2EE框架的WEB应用系统的职业能力。

							<br /></td>
					</tr>
					<tr>
						<td><div id="label4" class="label1">师资团队</div></td>
					</tr>
					<tr>
						<td><table cellpadding="0" cellspacing="0" class="table2">
								<tr>
									<td><a href=""> <img alt="点击了解更多"
											src="${pageContext.request.contextPath }/pages/image/1zheng.jpg" />
									</a><br />郑秋生</td>
									<td><a href=""> <img alt="点击了解更多"
											src="${pageContext.request.contextPath }/pages/image/1guo.jpg" /></a><br />郭清宇</td>
									<td><a href=""> <img alt="点击了解更多"
											src="${pageContext.request.contextPath }/pages/image/1liu.jpg" /></a><br />刘风华</td>
									<td><a href=""> <img alt="点击了解更多"
											src="${pageContext.request.contextPath }/pages/image/1liuw.jpg" /></a><br />刘卫光</td>
									</td>
								</tr>
								<tr></tr>
							</table></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>