<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/dph" prefix="dph"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/m.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/chat.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/top.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/login.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/head.css">
<script type="text/javascript">
	function showitem(item, item2) {
		$(item).show();
		$(item2).hide();
	}
</script>
</head>
<body>
	<table cellpadding="0" cellspacing="0" class="taa1">
		<tr>
			<td class="taa12"></td>
			<td class="taa13"><c:choose>
					<c:when test="${user==null }">
						<div class="item1">
							<a href="#" onclick="showitem('.item3','.item1')">登录</a> | <a
								href="${pageContext.request.contextPath }/servlet/UserServlet?method=forRegisterUI"
								target="maincenter">注册</a> | <a
								href="${pageContext.request.contextPath }/htm/sendemail.html"
								target="maincenter">找回密码</a> | <a href="#">帮助</a> | <a href="#">联系我们&nbsp;</a>
						</div>
						<div class="item2 hide" style="display: none;">
							<span>欢迎您: </span><span>${user.username }</span> | <a href="#">我的信息</a>
							| <a href="#">消息</a> | <a target="maincenter"
								href="${pageContext.request.contextPath }/servlet/UserServlet?method=logout">退出</a>
							| <a href="#">联系我们&nbsp; </a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="item1 hide">
							<a
								href="${pageContext.request.contextPath }/servlet/ManagerServlet?method=formanagerUI"
								target="a_blank">管理员登录</a> | <a href="#"
								onclick="showitem('.item3','.item1')">登录</a> | <a
								href="${pageContext.request.contextPath }/servlet/UserServlet?method=forRegisterUI"
								target="maincenter">注册</a> | <a
								href="${pageContext.request.contextPath }/htm/sendemail.html"
								target="maincenter">找回密码</a> | <a href="#">帮助</a> | <a href="#">联系我们&nbsp;</a>
						</div>
						<div class="item2">
							<img width="30px" height="30px"
								src="${fn:replace(user.photopath,'.','_small.') }"> | <span>欢迎您:
							</span><span><a
								href="${pageContext.request.contextPath }/servlet/UserServlet?method=forinfo&id=${user.id}">${user.username
									}</a></span>
							<dph:permission value="后台管理">| <a
									href="${pageContext.request.contextPath }/servlet/ManagerServlet?method=formanagerUI"
									target="_blank">后台管理</a>
							</dph:permission>
							| <a
								href="${pageContext.request.contextPath }/servlet/UserServlet?method=forinfo&id=${user.id}">我的信息</a>
							| <a
								href="${pageContext.request.contextPath }/servlet/RemindMsgServlet?method=forRmsgUI">消息</a>
							| <a target="maincenter"
								href="${pageContext.request.contextPath }/servlet/UserServlet?method=logout">退出</a>
							| <a href="#">联系我们&nbsp; </a>
						</div>
					</c:otherwise>
				</c:choose>


				<div class="taa11 item3" style="display: none;">
					<table class="taa2" cellpadding="0" cellspacing="0">
						<tr>
							<td>用户名</td>
							<td><input type="text" name="username"></td>
							<td><input type="checkbox" value="autologin">自动登录</td>
							<td><a target="maincenter"
								href="${pageContext.request.contextPath }/htm/sendemail.html">找回密码</a></td>
						</tr>
						<tr>
							<td>密码</td>
							<td><input type="text" name="password"></td>
							<td><a href="#"
								onclick="login('.taa2 input[name='+'username'+']','.taa2 input[name='+'password'+']','${pageContext.request.contextPath}/index.jsp')">登录</a></td>
							<td><input type="reset"
								onclick="showitem('.item1','.item3')" value="取消"></td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
	<table class="div1" cellpadding="0" class="tbb1" cellspacing="0">
		<tr>
			<td><div>
					<div>
						<ul class="tbb1ul">
							<li class="k"><a
								href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
							<li class="a"><a
								href="${pageContext.request.contextPath }/pages/DescribeCourse/index.html"
								target="_blank">课程介绍</a></li>
							<li class="b"><a
								href="${pageContext.request.contextPath }/pages/TeachTeam/index.html"
								target="_blank">教学团队</a></li>
							<li class="c"><a
								href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
								target="_blank">教学资源</a></li>
							<li class="d"><a
								href="${pageContext.request.contextPath }/pages/TeachExperiment/index.html"
								target="_blank">实验教学</a></li>
							<li class="e"><a
								href="${pageContext.request.contextPath }/pages/TeachStudy/index.html"
								target="_blank">教学研究</a></li>
							<li class="f"><a
								href="${pageContext.request.contextPath }/pages/TeachFruit/index.html"
								target="_blank">教学成果</a></li>
							<li class="g"><a
								href="http://localhost:8080/OnlLineText/servlet/TestHomePageservlet"
								target="_blank">在线测试</a></li>
							<li class="h"><a
								href="${pageContext.request.contextPath }/servlet/ListFileServlet"
								target="_blank">下载专区</a></li>
							<li class="i"><a
								href="${pageContext.request.contextPath }/servlet/ForumServlet?method=forForumUI"
								target="_blank">在线论坛</a></li>
							<li class="j"><a
								href="${pageContext.request.contextPath }/servlet/MsgBoardServlet?method=forMsgBoardUI"
								target="_blank">留言板</a></li>
						</ul>
					</div></td>
		</tr>
		<tr>
			<td height="180px"
				style=" background-image: url('${pageContext.request.contextPath }/pages/image/111.jpg');height='181px'">
				<div class="div2">
					<dl class="k">
						<dt>首页</dt>
						<dd>
							<img alt=""
								src="${pageContext.request.contextPath }/pages/image/icon1.jpg" />
						</dd>
					</dl>
					<dl class="a">
						<dt>课程介绍</dt>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/DescribeCourse/index.html"
								target="_black">课程描述</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/DescribeCourse/index.html"
								target="_black">教学内容</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/DescribeCourse/index.html"
								target="_black">教学大纲</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/DescribeCourse/index.html"
								target="_black">建设规划</a>
						</dd>
					</dl>
					<dl class="b">
						<dt>教学团队</dt>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachTeam/index.html"
								target="_black">基本情况</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachTeam/index.html"
								target="_black">课程负责人</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachTeam/index.html"
								target="_black">主讲教师</a>
						</dd>
					</dl>
					<dl class="c">
						<dt>教学资源</dt>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
								target="_black">教学安排</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
								target="_black">多媒体课件</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
								target="_black">授课录像</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
								target="_black">电子教案</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
								target="_black">课程作业</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
								target="_black">课程习题</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
								target="_black">模拟卷</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachResources/index.html"
								target="_black">参考资料</a>
						</dd>
					</dl>
					<dl class="d">
						<dt>实验教学</dt>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachExperiment/index.html"
								target="_black">实验任务</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachExperiment/index.html"
								target="_black">实验大纲</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachExperiment/index.html"
								target="_black">实验指导</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachExperiment/index.html"
								target="_black">课程设计</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachExperiment/index.html"
								target="_black">实验作品</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachExperiment/index.html"
								target="_black">实验报告</a>
						</dd>
					</dl>
					<dl class="e">
						<dt>教学研究</dt>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachStudy/index.html"
								target="_black">教学方法</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachStudy/index.html"
								target="_black">教学改革</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachStudy/index.html"
								target="_black">教学课题</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachStudy/index.html"
								target="_black">建设论文</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachStudy/index.html"
								target="_black">学生评教</a>
						</dd>
					</dl>
					<dl class="f">
						<dt>教学成果</dt>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachFruit/index.html"
								target="_black">教学成果奖</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachFruit/index.html"
								target="_black">获成果奖项</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachFruit/index.html"
								target="_black">人才培养成果</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/pages/TeachFruit/index.html"
								target="_black">教材建设</a>
						</dd>
					</dl>
					<dl class="g">
						<dt>在线学习</dt>
						<dd>
							<a
								href="http://localhost:8080/OnlLineText/servlet/TestHomePageservlet"
								target="_blank">在线学习</a>
						</dd>
						<dd>
							<a
								href="http://localhost:8080/OnlLineText/servlet/TestHomePageservlet"
								target="_blank">在线自测</a>
						</dd>
					</dl>
					<dl class="h">
						<dt>下载专区</dt>
						<dd>
							<a
								href="${pageContext.request.contextPath }/servlet/ListFileServlet"
								target="_blank">教学软件</a>
						</dd>
						<dd>
							<a
								href="${pageContext.request.contextPath }/servlet/ListFileServlet"
								target="_blank">常用工具</a>
						</dd>
					</dl>
					<dl class="i">
						<dt>在线论坛</dt>
						<dd>
							<a
								href="${pageContext.request.contextPath }/servlet/ForumServlet?method=forForumUI"
								target="_blank">进入论坛</a>
						</dd>
					</dl>
					<dl class="j">
						<dt>留言板</dt>
						<dd>
							<a
								href="${pageContext.request.contextPath }/servlet/MsgBoardServlet?method=forMsgBoardUI"
								target="_blank">进入留言</a>
						</dd>
					</dl>
				</div>
			</td>
		</tr>
	</table>
	<div id="login">
		<span id="b"> <input id="close" type="button" value="取消">
		</span>
		<div id="a">
			<div class="a">您需要先登录才能完成本操作</div>
			<table>
				<tr>
					<td>用户名</td>
					<td><input id="username" type="text"></td>
					<td><a href="#">用户注册</a></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input id="password" type="text"></td>
					<td><a href="#">找回密码</a></td>
				</tr>
				<tr>
					<div id="c">
						<td colspan="2"><input type="checkbox" value="autologin">自动登录
							<input type="button" value="登录"
							onclick="login2('#username','#password','${pageContext.request.contextPath}')"></td>
					</div>
				</tr>
			</table>
		</div>
	</div>
	<div class="onlinetea">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<div class="teaa1" onclick="showtea()">
						<div>
							<img
								src="${pageContext.request.contextPath }/images/icos/teas.jpg">
						</div>
						<div>在</div>
						<div>线</div>
						<div>教</div>
						<div>师</div>
					</div>
				</td>
				<td class="oltea"><iframe
						src="${pageContext.request.contextPath }/servlet/MainCenterUIServlet?method=foroltea"></iframe></td>
			</tr>
		</table>

	</div>
	<div id="messagepanel">
		<div class="msgtitle">
			<span>消息提醒</span> <span class="msgtitle1"><a
				href="javascript:void(0)" onclick="closemsgbox()">关闭</a></span>
		</div>
		<div id="msgcontent"></div>
		<div id="msgbottom"></div>
	</div>
</body>
</html>