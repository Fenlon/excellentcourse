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
<title>留言板</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/card.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/msg.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/pagequery.js"></script>
<script type="text/javascript">
	function editmsg(){
		$(".postmsg").show();
	}
	function closeeditmsg(){
		$(".postmsg").hide();
	}
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/card.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/pagequery.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/head.css">
<style type="text/css">
#answer {
	width: 522px;
	height: 295px;
	border: 3px solid black;
	margin: auto;
	position: fixed;
	left: 30%;
	top: 30%;
	overflow: auto;
	display: none;
	z-index: 1000;
	background: gray;
	padding: 0px;
}

.answertitle {
	height: 30px;
	background: gray;
	cursor: move;
}

.answertitle input {
	margin-left: 475px;
}

/* .msgcontent {
	margin: 0 auto;
	text-align: center;
} */
.postmsg {
	width: 960px;
	height: 300px;
	display: none;
}

.tta {
	border: 0px;
	border-top: 1px dotted gray;
	font-size: small;
}

.tta img {
	width: 50px;
	height: 50px;
}

.tta2 {
	width: 700px;
}

.ttb {
	margin-top: 20px;
}

.title {
	width: 500px;
}

.ttb1 {
	font-weight: bolder;
	font-size: large;
	width: 75px;
}

.ttb2 {
	height: 32px;
	margin-left: 20px;
	background-color: #A2B5CD;
	border: 0px;
}

.ttb3 {
	text-align: right;
}

.tbbb2 {
	border-top: 1px solid gray;
}

.msgc {
	text-align: left;
}

.msgdelete {
	width: 150px;
}

.msgdelete span a {
	margin-left: 5px;
}

.msghfdelete {
	font-size: smaller;
	margin-left: 600px;
}
</style>
</head>

<body>
	<div id="container">
		<div class="msgcontent">
			<div class="msgc">
				<c:url value="../../public/head.jsp" var="head"></c:url>
				<c:import url="${head }"></c:import>
			</div>

			<div>
				<div class="msgc">
					<p>
						<a href="${pageContext.request.contextPath}/index.jsp"><img
							title="网站首页"
							src="${pageContext.request.contextPath}/images/public/root.jpg">
							> <a
							href="${pageContext.request.contextPath}/servlet/MsgBoardServlet?method=forMsgBoardUI"
							title="留言板">留言板</a>
					</p>
				</div>
				<hr>
				<div class="msgc" style=" font-size: medium;">
					<span>共<font color='red'>${pagebean.totalrecord}</font>条
					</span><a href="javascript:void(0)" onclick="editmsg()">我要留言</a>
				</div>
				<div class="postmsg">
					<form method="post"
						action="${pageContext.request.contextPath }/servlet/MsgBoardServlet?method=postmsg">
						<table class="ttb t_msg_card" cellpadding="0" cellspacing="0">
							<tr>
								<td class="ttb1"></td>
								<td class="ttb3"><input onclick="closeeditmsg()"
									type="button" value="关闭"></td>
							</tr>
							<tr>
								<td class="ttb1">主题</td>
								<td><input class="title" type="text" name="title"></td>
							</tr>
							<tr>
								<td class="ttb1">内容</td>
								<script type="text/plain" id="Editor" name="content"></script>
								<script type="text/javascript">
								var option = {
									emotionLocalization : true,
									initialContent : '',//初始化编辑器的内容 
									initialFrameWidth : 900,
									initialFrameHeight : 120,
									autoHeightEnabled : false,
									toolbars : [ [ "bold", "italic", "undo",
											"redo", "emotion","insertimage","attachment","link" ] ]
								//设置宽度 
								}
								var editor = new UE.ui.Editor(option);
								editor.render("Editor");
								//1.2.4以后可以使用一下代码实例化编辑器
								//UE.getEditor('myEditor')
							</script>
							</tr>
							<tr>
								<td></td>
								<td><input type="reset" class="ttb1 ttb2" value="重置"><input
									type="submit" class="ttb1 ttb2" value="发表"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div>
				<table class="pagebar " cellpadding="0" cellspacing="0">
					<tr>
						<td><span>每页</span><input id="pagesize" type="text"
							style="width: 30px;" maxlength="2"
							value="${requestScope.pagebean.pagesize }"
							onchange="changePageSize(this.value,${requestScope.pagebean.pagesize },'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">条
						</td>
						<td class="pa"><span> <c:choose>
									<c:when test="${requestScope.pagebean.currentpage==1}">
										<c:out value="第一页     上一页"></c:out>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)"
											onclick="gotopage(1,'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">第一页</a>
										<a href="javascript:void(0)"
											onclick="gotopage(${requestScope.pagebean.previouspage},'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">上一页</a>
									</c:otherwise>
								</c:choose>


						</span> <c:forEach var="pagenum"
								items="${requestScope.pagebean.pagebar }">
								<c:if test="${pagenum==pagebean.currentpage}">
									<font color="red">${pagenum }</font>
								</c:if>
								<c:if test="${pagenum!=pagebean.currentpage}">
									<a href="javascript:void(0)"
										onclick="gotopage(${pagenum },'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">${pagenum}</a>
								</c:if>
							</c:forEach> <span> <c:choose>
									<c:when
										test="${requestScope.pagebean.currentpage==requestScope.pagebean.totalpage}">
										<c:out value="下一页     最后页"></c:out>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)"
											onclick="gotopage(${requestScope.pagebean.nextpage},'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">下一页</a>
										<a href="javascript:void(0)"
											onclick="gotopage(${requestScope.pagebean.totalpage},'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">最后页</a>
									</c:otherwise>
								</c:choose>

						</span></td>
						<td class="pa pb">GO<input name="currentpage" id="pagenum"
							type="text" style="width: 50px;" /> <input type="image"
							style="margin-bottom: -3px;"
							onclick="gotopage(document.getElementById('pagenum').value,'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')"
							src="${pageContext.request.contextPath }/images/public/ok.jpg"></td>
					</tr>
				</table>
				<c:forEach var="m" items="${requestScope.pagebean.list }"
					varStatus="index">
					<table class="t_msg_card" cellpadding="0" cellspacing="0">
						<tr>
							<td class="ta"></td>
							<td class="tb"><div class="tb1">
									<img
										src="${pageContext.request.contextPath }/images/bbs/online_member.gif">
									<span>发表于${m.strtime }</span>|<span><a href="#">只看该作者</a></span>
								</div></td>
							<td class="tc"><span style="margin-left: 110px;">${
									index.count+(requestScope.pagebean.currentpage-1)*requestScope.pagebean.pagesize}#</span></td>
						</tr>
						<tr>
							<td class="ta fontcenter">
								<div style="margin-bottom: 10px">
									<a
										href="${pageContext.request.contextPath }/servlet/UserServlet?method=forinfo&id=${m.user.id}">${m.user.username}</a>
								</div>
								<div>
									<img src="${fn:replace(m.user.photopath,'.','_middle.') }">
								</div>
								<div>
									<span>主题</span> <span>帖子</span> <span>积分</span>
								</div>
								<div>
									<span>大虾</span>
								</div>
								<div>
									<img
										src="${pageContext.request.contextPath }/images/bbs/star_level2.gif">
								</div>
								<div>
									<img
										src="${pageContext.request.contextPath }/images/bbs/ico_jdt.png">
								</div>
								<div>
									<span> <img
										src="${pageContext.request.contextPath }/images/bbs/pmto.gif">
									</span> <span><a href="#">发消息</a></span>
								</div>
							</td>
							<td class="tb" colspan="2"><span>
									<div class="tb1">标题: ${m.title }</div>
									<div class="tb1 tbbb2">内容: ${m.content }</div> <c:if
										test="${m.huifus!=null }">
										<table class="tb1" style="border: 0px;" cellpadding="0"
											cellspacing="0">
											<c:forEach var="h" items="${m.huifus }">
												<tr>
													<td>
														<table cellpadding="0" cellspacing="0" class="tta">
															<tr>
																<td class="tta1"><img
																	src='${fn:replace(h.user.photopath,'.','_middle.') }'></td>
																<td class="tta2"><div>
																		<a
																			href="${pageContext.request.contextPath }/servlet/UserServlet?method=forinfo&id=${h.user.id}">${h.user.username
																			}</a>
																		<dph:permission value="删除留言">
																			<a class="msghfdelete"
																				href="${pageContext.request.contextPath }/servlet/MsgBoardServlet?method=delete&id=${h.id }&currentpage=${pagebean.currentpage}&pagesize=${pagebean.pagesize}">删除</a>
																		</dph:permission>
																	</div>
																	<div>回复于: ${h.strtime }</div></td>
															</tr>
															<tr>
																<td class="tta1"></td>
																<td class="tta2"><div>内容: ${h.content }</div></td>
															</tr>
														</table>
													</td>
												</tr>
											</c:forEach>
										</table>
									</c:if>
							</span></td>
						</tr>
						<tr>
							<td class="ta"></td>
							<td class="tb ">
								<div class="tb1">
									<div>
										<a href="javascript:void(0)"><span>&nbsp;<img
												src="${pageContext.request.contextPath }/images/bbs/ad.gif"></span>Fighting
											For Your Team !</a>
									</div>
									<div>
										<a href="javascript:void(0)"><span><img
												src="${pageContext.request.contextPath }/images/bbs/cmmnt.gif"></span>点评</a>
										<a class="answer" name="${m.id }" onclick="getIdByName(this)"
											href="javascript:void(0)"><span><img
												src="${pageContext.request.contextPath }/images/bbs/fastreply.gif"></span>回复</a>
										<c:if test="${sessionScope.user !=null }">
											<a href="">补充</a>
										</c:if>
									</div>
								</div>
							</td>
							<td class="msgdelete"><span> <dph:permission
										value="删除留言">
										<a
											href="${pageContext.request.contextPath }/servlet/MsgBoardServlet?method=delete&id=${m.id }&currentpage=${pagebean.currentpage}&pagesize=${pagebean.pagesize}">删除</a>
									</dph:permission>
							</span><span><a href="#">使用道具<img
										src="${pageContext.request.contextPath }/images/bbs/arrwd.gif"></a>
							</span><span><a href="#">举报</a></span></td>
						</tr>
					</table>

				</c:forEach>
			</div>

			<table class="pagebar" cellpadding="0" cellspacing="0">
				<tr>
					<td><span>每页</span><input id="pagesize" type="text"
						style="width: 30px;" maxlength="2"
						value="${requestScope.pagebean.pagesize }"
						onchange="changePageSize(this.value,${requestScope.pagebean.pagesize },'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">条
					</td>
					<td class="pa"><span> <c:choose>
								<c:when test="${requestScope.pagebean.currentpage==1}">
									<c:out value="第一页     上一页"></c:out>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)"
										onclick="gotopage(1,'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">第一页</a>
									<a href="javascript:void(0)"
										onclick="gotopage(${requestScope.pagebean.previouspage},'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">上一页</a>
								</c:otherwise>
							</c:choose>


					</span> <c:forEach var="pagenum" items="${requestScope.pagebean.pagebar }">
							<c:if test="${pagenum==pagebean.currentpage}">
								<font color="red">${pagenum }</font>
							</c:if>
							<c:if test="${pagenum!=pagebean.currentpage}">
								<a href="javascript:void(0)"
									onclick="gotopage(${pagenum },'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">${pagenum}</a>
							</c:if>
						</c:forEach> <span> <c:choose>
								<c:when
									test="${requestScope.pagebean.currentpage==requestScope.pagebean.totalpage}">
									<c:out value="下一页     最后页"></c:out>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)"
										onclick="gotopage(${requestScope.pagebean.nextpage},'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">下一页</a>
									<a href="javascript:void(0)"
										onclick="gotopage(${requestScope.pagebean.totalpage},'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')">最后页</a>
								</c:otherwise>
							</c:choose>

					</span></td>
					<td class="pa pb">GO<input name="currentpage" id="pagenum2"
						type="text" style="width: 50px;" /> <input type="image"
						style="margin-bottom: -3px;"
						onclick="gotopage(document.getElementById('pagenum2').value,'${path}/servlet/MsgBoardServlet?method=forMsgBoardUI&')"
						src="${pageContext.request.contextPath }/images/public/ok.jpg"></td>
				</tr>
			</table>


		</div>


		<div id="answer">
			<div class="answertitle">
				<div>
					<input id="cancle" type="button" value="取消">
				</div>
			</div>
			<form
				action="${pageContext.request.contextPath }/servlet/MsgBoardServlet?method=answer&currentpage=${requestScope.pagebean.currentpage}&pagesize=${requestScope.pagebean.pagesize}"
				method="post">

				<div id="aa">
					<input type="hidden" id="parentid" name="parentid" value="">
				</div>
				<textarea id="answercontent" name="content"></textarea>
				<script type="text/javascript">
					var option = {
						emotionLocalization : true,
						initialContent : '编写文章内容...',//初始化编辑器的内容 
						initialFrameWidth : 520,
						initialFrameHeight : 165,
						autoHeightEnabled : false,
						toolbars : [ [ "bold", "italic", "undo", "redo",
								"emotion","insertimage","attachment","link" ] ]
					//设置宽度 
					}
					var editor = new UE.ui.Editor(option);
					editor.render("answercontent");
					//1.2.4以后可以使用一下代码实例化编辑器
					//UE.getEditor('myEditor')
				</script>
				<input type="submit" value="提交回复" onclick="">
			</form>
		</div>

		<div>
			<c:url value="../../public/foot.jsp" var="foot"></c:url>
			<c:import url="${foot }"></c:import>
		</div>
	</div>
</body>
</html>
