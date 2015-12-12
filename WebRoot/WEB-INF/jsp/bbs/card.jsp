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
<title>${requestScope.c.forum.forumname }</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/card.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/pagequery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/top.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/drag.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/card.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/pagequery.css">

<style type="text/css">
#container {
	border: 1px solid gray;
}

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

.tb {
	border-left: 1px solid gray;
}

.cardtable {
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
	font-size: small;
}

.deletehuifu {
	width: 100px;
}

.pagebar {
	width: 800px;
	font-size: small;
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
			<div>
				<p>
					<a href="${pageContext.request.contextPath}/index.jsp"><img
						title="网站首页"
						src="${pageContext.request.contextPath}/images/public/root.jpg">
						> <a
						href="${pageContext.request.contextPath}/servlet/ForumServlet?method=forForumUI"
						title="论坛首页">论坛首页</a> > </a><a
						href="${pageContext.request.contextPath}/servlet/BBSUIServlet?method=forForumUI&forumid=${c.forum.id}">${c.forum.forumname
						}</a> > <a
						href="${pageContext.request.contextPath}/servlet/CardServlet?method=forCardContent&id=${c.id}">${c.title
						}</a>
				</p>
			</div>

			<table class="cardtable" cellpadding="0" cellspacing="0">
				<tr>
					<td><a
						href="${pageContext.request.contextPath }/servlet/BBSUIServlet?method=forEditBBSUI&forumid=${c.forum.id}"><img
							alt="发新帖"
							src="${pageContext.request.contextPath }/images/bbs/pn_post.png"><a
							href="#"><img alt="发新帖"
								src="${pageContext.request.contextPath }/images/bbs/pn_reply.png"></a></td>
					<td class="td">

						<table cellpadding="0" cellspacing="0" class="pagebar">
							<tr>
								<td><span>每页</span><input id="pagesize" type="text"
									style="width: 30px;" maxlength="2"
									value="${requestScope.pagebean.pagesize }"
									onchange="changePageSize(this.value,${requestScope.pagebean.pagesize },'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">条
								</td>
								<td class="pa"><span> <c:choose>
											<c:when test="${requestScope.pagebean.currentpage==1}">
												<c:out value="第一页     上一页"></c:out>
											</c:when>
											<c:otherwise>
												<a href="javascript:void(0)"
													onclick="gotopage(1,'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">第一页</a>
												<a href="javascript:void(0)"
													onclick="gotopage(${requestScope.pagebean.previouspage},'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">上一页</a>
											</c:otherwise>
										</c:choose>


								</span> <c:forEach var="pagenum"
										items="${requestScope.pagebean.pagebar }">
										<c:if test="${pagenum==pagebean.currentpage}">
											<font color="red">${pagenum }</font>
										</c:if>
										<c:if test="${pagenum!=pagebean.currentpage}">
											<a href="javascript:void(0)"
												onclick="gotopage(${pagenum },'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">${pagenum}</a>
										</c:if>
									</c:forEach> <span> <c:choose>
											<c:when
												test="${requestScope.pagebean.currentpage==requestScope.pagebean.totalpage}">
												<c:out value="下一页     最后页"></c:out>
											</c:when>
											<c:otherwise>
												<a href="javascript:void(0)"
													onclick="gotopage(${requestScope.pagebean.nextpage},'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">下一页</a>
												<a href="javascript:void(0)"
													onclick="gotopage(${requestScope.pagebean.totalpage},'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">最后页</a>
											</c:otherwise>
										</c:choose>

								</span></td>
								<td class="pa pb">GO<input name="currentpage" id="pagenum"
									type="text" style="width: 50px;" /> <input type="image"
									style="margin-bottom: -3px;"
									onclick="gotopage(document.getElementById('pagenum').value,'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')"
									src="${pageContext.request.contextPath }/images/public/ok.jpg"></td>
							</tr>
						</table>

					</td>
					<td></td>
				</tr>
			</table>

			<table class="cardtable" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ta fontcenter"><span>查看<a href="#">${c.browsenum
								}</a></span>&nbsp;|&nbsp;<span>回复<a href="#">${c.answernum }</a></span></td>
					<td class="tb  fontlarg">
						<div class="tb1">
							<span>[<a href="#">${c.forum.forumname }</a>]
							</span> <span>${c.title }</span>
						</div>
					</td>
					<td class="tc tc1"><span> <img title="打印" alt="打印"
							src="${pageContext.request.contextPath }/images/bbs/print.png">&nbsp;
							<img title="打印" alt="打印"
							src="${pageContext.request.contextPath }/images/bbs/thread-prev.png">&nbsp;
							<img title="打印" alt="打印"
							src="${pageContext.request.contextPath }/images/bbs/thread-next.png">
					</span></td>
				</tr>
				<tr>
					<td class="ta"><a
						href="${pageScope.path }/servlet/UserServlet?method=forinfo&id=${c.auther.id }">${c.auther.username}</a></td>
					<td class="tb"><div class="tb1">
							<img
								src="${pageContext.request.contextPath }/images/bbs/online_member.gif">
							<span>发表于${c.createtime }</span>|<span><a href="#">只看该作者</a></span>
						</div></td>
					<td class="tc"><span><a href="#">楼主</a></span>&nbsp;&nbsp; <span>
							电梯直达 </span> <input style="width: 20px;height: 17px;" type="text"
						id="pagenum3"> <img
						onclick="gotopage(document.getElementById('pagenum3').value,'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')"
						src="${pageContext.request.contextPath }/images/bbs/fj_btn.png">
					</td>
				</tr>
				<tr>
					<td class="ta fontcenter">
						<div>
							<img
								src="${fn:replace(requestScope.c.auther.photopath,'.','_middle.') }">
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
					<td class="tb" colspan="2"><div class="tb1">
							<div class="cardt1">
								<span>${c.title }</span>
							</div>
							<c:choose>
								<c:when test="${c.forum.forumname=='新人报到' }">

									<div class="cardt2">
										<span>真实姓名:</span><span class="tbb21">${requestScope.name
											}</span>
									</div>
									<div class="cardt2">
										<span>所属学院:</span><span class="tbb21">${requestScope.college
											}</span>
									</div>
									<div class="cardt2">
										<span>学习格言:</span><span class="tbb21">${requestScope.motto
											}</span>
									</div>
									<div>
										<span>${requestScope.content }</span>
									</div>

								</c:when>
								<c:otherwise>
									<div class="cardt2">
										<span>${c.content }</span>
									</div>
								</c:otherwise>
							</c:choose>

						</div></td>
				</tr>
				<tr>
					<td></td>
					<td class="tb ">
						<div class="tb1">
							<div>
								<a javascript:void(0)  href="#"><span>&nbsp;<img
										src="${pageContext.request.contextPath }/images/bbs/ad.gif"></span>Fighting
									For Your Team !</a>
							</div>
							<div>
								<a javascript:void(0)  href="#"><span><img
										src="${pageContext.request.contextPath }/images/bbs/cmmnt.gif"></span>点评</a>
								<a class="answer" javascript:void(0)  href="#"><span><img
										src="${pageContext.request.contextPath }/images/bbs/fastreply.gif"></span>回复</a>
								<c:if test="${sessionScope.user !=null }">
									<a href="">补充</a>
								</c:if>
							</div>
						</div>
					</td>
					<td><span class="post"><a href="#">使用道具<img
								src="${pageContext.request.contextPath }/images/bbs/arrwd.gif"></a>&nbsp;
							<a href="#">举报</a></span></td>
				</tr>
			</table>
		</div>
		<div>
			<c:if test="${requestScope.pagebean.list!=null}">
				<c:forEach var="m" varStatus="index"
					items="${requestScope.pagebean.list }">
					<table class="cardtable" cellpadding="0" cellspacing="0">
						<tr>
							<td class="ta"><a
								href="${pageScope.path }/servlet/UserServlet?method=forinfo&id=${m.user.id }">${m.user.username}</a></td>
							<td class="tb"><div class="tb1">
									<img
										src="${pageContext.request.contextPath }/images/bbs/online_member.gif">
									<span>发表于${m.time }</span>|<span><a href="#">只看该作者</a></span>
								</div></td>
							<td class="tc index"><span>${
									index.count+(requestScope.pagebean.currentpage-1)*requestScope.pagebean.pagesize}#</span></td>
						</tr>
						<tr>
							<td class="ta fontcenter">
								<div>
									<img src="${fn:replace(m.user.photopath,'.','_middle.')}">
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
							<td class="tb" colspan="2">
								<div class="tb1">
									<c:if test="${m.parentmsg!=null }">
										<div>
											<div>
												<span><img
													src="${pageContext.request.contextPath }/images/bbs/icon_quote_s.gif"></span>
												<span><a href="#">${m.parentmsg.content}</a></span> <img
													src="${pageContext.request.contextPath }/images/bbs/icon_quote_e.gif">
											</div>
										</div>
									</c:if>
									<div class="tb1">
										<span>${m.content }</span>
									</div>
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<td></td>
							<td class="tb">
								<div calss="tb1">
									<div>
										<a href="javascript:void(0)"><span>&nbsp;<img
												src="${pageContext.request.contextPath }/images/bbs/ad.gif"></span>Fighting
											For Your Team !</a>
									</div>
									<div>
										<a href="javascript:void(0)"> <span><img
												src="${pageContext.request.contextPath }/images/bbs/cmmnt.gif"></span>点评
										</a> <a class="answer" name="${m.id }" href="javascript:void(0)"
											onclick="getIdByName(this)"><span><img
												src="${pageContext.request.contextPath }/images/bbs/fastreply.gif"></span>回复</a>
										<a class="answer" href="javascript:void(0)"><span><img
												src="${pageContext.request.contextPath }/images/bbs/rec_add.gif"></span>支持</a>
										<a class="answer" href="javascript:void(0)"><span><img
												src="${pageContext.request.contextPath }/images/bbs/rec_subtract.gif"></span>反对</a>

									</div>
								</div>
							</td>
							<td><span class="tb3 tc"> <dph:permission
										value="删除论坛回复">
										<a
											href="${pageContext.request.contextPath }/servlet/CardServlet?method=deleteHuiFu&id=${m.id}&cid=${c.id}&pagesize=${pagebean.pagesize}&currentpage=${pagebean.currentpage}">删除</a>
									</dph:permission> <a href="#">评分</a>&nbsp;<a href="#">举报</a></span></td>
						</tr>
					</table>
				</c:forEach>
			</c:if>
		</div>


		<table class="cardtable" cellpadding="0" cellspacing="0">
			<tr>
				<td><a href="#"><img alt="发新帖"
						src="${pageContext.request.contextPath }/images/bbs/pn_post.png"><a
						href="#"><img alt="发新帖"
							src="${pageContext.request.contextPath }/images/bbs/pn_reply.png"></a></td>
				<td class="td">

					<table cellpadding="0" cellspacing="0" class="pagebar">
						<tr>
							<td><span>每页</span><input id="pagesize" type="text"
								style="width: 30px;" maxlength="2"
								value="${requestScope.pagebean.pagesize }"
								onchange="changePageSize(this.value,${requestScope.pagebean.pagesize },'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">条
							</td>
							<td class="pa"><span> <c:choose>
										<c:when test="${requestScope.pagebean.currentpage==1}">
											<c:out value="第一页     上一页"></c:out>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)"
												onclick="gotopage(1,'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">第一页</a>
											<a href="javascript:void(0)"
												onclick="gotopage(${requestScope.pagebean.previouspage},'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">上一页</a>
										</c:otherwise>
									</c:choose>


							</span> <c:forEach var="pagenum"
									items="${requestScope.pagebean.pagebar }">
									<c:if test="${pagenum==pagebean.currentpage}">
										<font color="red">${pagenum }</font>
									</c:if>
									<c:if test="${pagenum!=pagebean.currentpage}">
										<a href="javascript:void(0)"
											onclick="gotopage(${pagenum },'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">${pagenum}</a>
									</c:if>
								</c:forEach> <span> <c:choose>
										<c:when
											test="${requestScope.pagebean.currentpage==requestScope.pagebean.totalpage}">
											<c:out value="下一页     最后页"></c:out>
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)"
												onclick="gotopage(${requestScope.pagebean.nextpage},'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">下一页</a>
											<a href="javascript:void(0)"
												onclick="gotopage(${requestScope.pagebean.totalpage},'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')">最后页</a>
										</c:otherwise>
									</c:choose>

							</span></td>
							<td class="pa pb">GO<input name="currentpage" id="pagenum2"
								type="text" style="width: 50px;" /> <input type="image"
								style="margin-bottom: -3px;"
								onclick="gotopage(document.getElementById('pagenum2').value,'${path}/servlet/CardServlet?method=forCardContent&id=${c.id }&')"
								src="${pageContext.request.contextPath }/images/public/ok.jpg"></td>
						</tr>
					</table>

				</td>
				<td></td>
			</tr>
		</table>


		<div>
			<table class="cardtable">
				<tr>
					<td class="ta fontcenter"><div>
							<c:choose>
								<c:when test="${sessionScope.user==null }">
									<img
										src="${pageContext.request.contextPath }/images/photos/noavator.gif">
								</c:when>
								<c:otherwise>
									<img
										src="${fn:replace(sessionScope.user.photopath,'.','_middle.') }">
								</c:otherwise>
							</c:choose>

						</div></td>
					<td class="tb"><div class="tb1">
							<form>
								<script type="text/plain" id="myEditor" name="content"></script>
								<script type="text/javascript">
									var option = {
										emotionLocalization : true,
										initialContent : '',//初始化编辑器的内容 
										initialFrameWidth : 600,
										initialFrameHeight : 200,
										autoHeightEnabled : false,
										toolbars : [ [ "bold", "italic",
												"undo", "redo", "emotion","insertimage","attachment","link" ] ]
									//设置宽度 
									}
									var editor = new UE.ui.Editor(option);
									editor.render("myEditor");
									//1.2.4以后可以使用一下代码实例化编辑器
									//UE.getEditor('myEditor')
								</script>
							</form>
						</div></td>
					<td class="tc">
						<div>禁止发表一些不良信息！</div>
					</td>
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
				action="${pageContext.request.contextPath }/servlet/CardServlet?method=answer&currentpage=${requestScope.pagebean.currentpage}&pagesize=${requestScope.pagebean.pagesize}"
				method="post">
				<div id="aa">
					<input type="hidden" id="parentid" name="parentid" value="">
				</div>
				<textarea id="answercontent" name="answer"></textarea>
				<script type="text/javascript">
					var option = {
						emotionLocalization : true,
						initialContent : '编写文章内容...',//初始化编辑器的内容 
						initialFrameWidth : 520,
						initialFrameHeight : 165,
						autoHeightEnabled : false,
						zIndex:2000,
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
