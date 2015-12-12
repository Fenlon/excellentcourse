<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/m.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/top.js"
	type="text/javascript"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}

.div1 ul li:hover {
	background-color: #6699FF;
}

a:HOVER,a {
	text-decoration: none;
}

ul {
	font-size: medium;
	background: #c9c9a7;
	list-style-type: none;
	text-align: center;
	padding: 0px;
	height: 40px;
	margin: 0px;
}

ul li {
	line-height: 40px;
	height: 40px;
	width: 87px;
	float: left;
	padding-top: 10px;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
}

.div2 dl {
	width: 87px;
	margin: 0px;
	float: left;
}

.div2,dl {
	height: 180px;
}

.div2 {
	width: 960px;
	border: 1px solid red;
	height: 180px;
	background-color: #CCCCFF;
	font: 14px/1.5 微软雅黑, Tahoma, Helvetica, 'SimSun', sans-serif;
	opacity: 0.9;
}

.div2 dl img {
	width: 85px;
}

dl dt {
	display: none;
}

dl dd {
	text-align: center;
	margin: 0px;
	padding: 0px;
	margin-top: 1px;
}
</style>
</head>
<body>
	<div id="container" style="width: 960px;">



		<div class="div1">
			<div>
				<ul>
					<li class="k"><a href="#">首页</a></li>
					<li class="a"><a href="Page2.htm">课程介绍</a></li>
					<li class="b"><a href="#">教学团队</a></li>
					<li class="c"><a href="#">教学资源</a></li>
					<li class="d"><a href="#">实验教学</a></li>
					<li class="e"><a href="#">教学研究</a></li>
					<li class="f"><a href="#">教学成果</a></li>
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
			<div class="div2">
				<dl class="k">
					<dt>首页</dt>
					<dd>
						<img alt=""
							src="${pageContext.request.contextPath }/htms/image/icon1.jpg" />
					</dd>
				</dl>
				<dl class="a">
					<dt>课程介绍</dt>
					<dd>
						<a href="">课程描述</a>
					</dd>
					<dd>
						<a href="">教学内容</a>
					</dd>
					<dd>
						<a href="">教学大纲</a>
					</dd>
					<dd>
						<a href="">建设规划</a>
					</dd>
				</dl>
				<dl class="b">
					<dt>教学团队</dt>
					<dd>
						<a href="">基本情况</a>
					</dd>
					<dd>
						<a href="">课程负责人</a>
					</dd>
					<dd>
						<a href="">主讲教师</a>
					</dd>
				</dl>
				<dl class="c">
					<dt>教学资源</dt>
					<dd>
						<a href="">教学安排</a>
					</dd>
					<dd>
						<a href="">多媒体课件</a>
					</dd>
					<dd>
						<a href="">授课录像</a>
					</dd>
					<dd>
						<a href="">电子教案</a>
					</dd>
					<dd>
						<a href="">课程作业</a>
					</dd>
					<dd>
						<a href="">课程习题</a>
					</dd>
					<dd>
						<a href="">模拟卷</a>
					</dd>
					<dd>
						<a href="">参考资料</a>
					</dd>
				</dl>
				<dl class="d">
					<dt>实验教学</dt>
					<dd>
						<a href="">实验任务</a>
					</dd>
					<dd>
						<a href="">实验大纲</a>
					</dd>
					<dd>
						<a href="">实验指导</a>
					</dd>
					<dd>
						<a href="">课程设计</a>
					</dd>
					<dd>
						<a href="">实验作品</a>
					</dd>
					<dd>
						<a href="">实验报告</a>
					</dd>
				</dl>
				<dl class="e">
					<dt>教学研究</dt>
					<dd>
						<a href="">教学方法</a>
					</dd>
					<dd>
						<a href="">教学改革</a>
					</dd>
					<dd>
						<a href="">教学课题</a>
					</dd>
					<dd>
						<a href="">建设论文</a>
					</dd>
					<dd>
						<a href="">学生评教</a>
					</dd>
				</dl>
				<dl class="f">
					<dt>教学成果</dt>
					<dd>
						<a href="">教学成果奖</a>
					</dd>
					<dd>
						<a href="">获成果奖项</a>
					</dd>
					<dd>
						<a href="">人才培养成果</a>
					</dd>
					<dd>
						<a href="">教材建设</a>
					</dd>
				</dl>
				<dl class="g">
					<dt>在线学习</dt>
					<dd>
						<a href="">在线学习</a>
					</dd>
					<dd>
						<a href="">在线自测</a>
					</dd>
				</dl>
				<dl class="h">
					<dt>下载专区</dt>
					<dd>
						<a href="">教学软件</a>
					</dd>
					<dd>
						<a href="">常用工具</a>
					</dd>
				</dl>
				<dl class="i">
					<dt>在线论坛</dt>
					<dd>
						<a
							href="${pageContext.request.contextPath }/servlet/ForumServlet?method=forForumUI"
							target="maincenter">进入论坛</a>
					</dd>
				</dl>
				<dl class="j">
					<dt>留言板</dt>
					<dd>
						<a
							href="${pageContext.request.contextPath }/servlet/MsgBoardServlet?method=forMsgBoardUI"
							target="maincenter">进入留言</a>
					</dd>
				</dl>
			</div>
		</div>

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
								<input type="button" value="登录" onclick="login()"></td>
						</div>
					</tr>
				</table>
			</div>
		</div>

	</div>
</body>
</html>