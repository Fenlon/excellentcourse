<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新人报到编辑内容</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/editcard.css">

</head>

<body>
	<div id="container">
		<div>
			<c:url value="../../public/head.jsp" var="head"></c:url>
			<c:import url="${head }"></c:import>
		</div>
		<div>
			<form id="info"
				action="${pageContext.request.contextPath }/servlet/CardServlet?method=upCard&forumid=${requestScope.forumid}"
				method="post">
				<table class="edittable">
					<tr>
						<td class="aa">发帖说明</td>
						<td class="bb">新人报到(防灌水)</td>
						<td></td>
					</tr>
					<tr>
						<td class="aa">帖子主题</td>
						<td class="bb">
							<div>
								<input class="b1" type="text" name="title">
							</div>
						</td>
						<td class="b2">还可以输入80个字</td>
					</tr>
					<tr>
						<td class="aa">真实姓名</td>
						<td class="bb"><div>
								<input class="b1" name="name" type="text">
							</div>
							<div class="b1 b2">最大长度为20,不可修改</div></td>
						<td><img
							src="${pageContext.request.contextPath }/images/public/check_right.gif" /></td>
					</tr>
					<tr>
						<td class="aa">所在学院</td>
						<td class="bb"><div>
								<table>
									<c:forEach var="c" items="${requestScope.colleges }"
										varStatus="index">
										<c:if test="${(index.count)%4==1 }">
											<tr class="b1">
										</c:if>

										<td><input type="radio" name="college" value="${c }学院" />
											${c}学院</td>
										<c:if test="${(index.count)%4==0 }">
											</tr>
										</c:if>
									</c:forEach>
								</table>
							</div></td>
						<td><img
							src="${pageContext.request.contextPath }/images/public/check_right.gif" /></td>
					</tr>
					<tr>
						<td class="aa">学习格言</td>
						<td class="bb"><div>
								<input class="b1" name="motto" type="text" maxlength="100">
							</div>
							<div class="b1 b2">最大长度为100,不可修改</div></td>
						</td>
						<td><img
							src="${pageContext.request.contextPath }/images/public/check_right.gif" /></td>
					</tr>
					<tr>
						<td colspan="3">
							<!-- <textarea name="content" id="myEditor"></textarea> --> <script
								type="text/plain" id="myEditor" name="content"></script> <script
								type="text/javascript">
									var option = {
										emotionLocalization : true,
										initialContent : '',//初始化编辑器的内容 
										initialFrameWidth : 954,
										initialFrameHeight : 200,
										autoHeightEnabled : false,
										toolbars : [ [ "bold", "italic",
												"undo", "redo", "emotion" ] ]
									//设置宽度 
									}
									var editor = new UE.ui.Editor(option);
									editor.render("myEditor");
									//1.2.4以后可以使用一下代码实例化编辑器
									//UE.getEditor('myEditor')
								</script>
						</td>
					</tr>


					<tr>
						<td colspan="3">
							<div>
								<input class="s" type="submit" value="发表帖子"><input
									class="s" type="button" value="保存草稿">
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<c:url value="../../public/foot.jsp" var="foot"></c:url>
			<c:import url="${foot }"></c:import>
		</div>
	</div>
</body>
</html>
