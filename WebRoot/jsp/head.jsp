<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/m.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/common.css">
<script type="text/javascript">
	function showitem(item, item2) {
		$(item).show();
		$(item2).hide();
	}
</script>
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
	list-style-type: none;
	text-align: center;
}

.tbb1ul {
	font-size: medium;
	background: #c9c9a7;
	padding: 0px;
	height: 40px;
	margin: 0px;
}

.tbb1ul li {
	float: left;
}

ul li {
	line-height: 40px;
	height: 40px;
	width: 87px;
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

.taa12 {
	width: 460px;
}

.taa13 {
	width: 500px;
}

.taa1 td {
	background: gray;
	height: 65px;
	text-align: right;
	border-right: 1px solid green;
}

.tbb1 {
	width: 960px;
}

.taa2 td {
	text-align: center;
	height: 30px;
	width: 80px;
	font-size: x-small;
}

.taa2 input {
	background: gray;
	border: 1px dotted pink;
}

.taa11 {
	margin-left: 100px;
	text-align: right;
}

.tcc1 {
	width: 960px;
}

.tcc1 td {
	border: 1px dotted green;
}

.heafframe {
	width: 958px;
	height: 600px;
	border: 1px dotted green;
	padding: 0px;
}
</style>
</head>
<body>
	<div id="container">
		<table cellpadding="0" cellspacing="0" class="taa1">
			<tr>
				<td class="taa12"></td>
				<td class="taa13">
					<div class="item1">
						<a href="#" onclick="showitem('.item3','.item1')">登录</a> | <a
							href="#">注册</a> | <a href="#">帮助</a> | <a href="#">联系我们&nbsp;</a>
					</div>
					<div class="item2" style="display: none;">
						<span>欢迎您: </span><span>dphenixiong</span> | <a href="#">我的信息</a>
						| <a href="#">消息</a> | <a href="#"
							onclick="showitem('.item1','.item2')">退出</a> | <a href="#">联系我们&nbsp;
						</a>
					</div>
					<div class="taa11 item3" style="display: none;">
						<table class="taa2" cellpadding="0" cellspacing="0">
							<tr>
								<td>用户名</td>
								<td><input type="text"></td>
								<td><input type="checkbox" value="autologin">自动登录</td>
								<td><a href="#">找回密码</a></td>
							</tr>
							<tr>
								<td>密码</td>
								<td><input type="text"></td>
								<td><a href="#" onclick="showitem('.item2','.item3')">登录</a></td>
								<td><input type="reset"
									onclick="showitem('.item1','.item3')" value="取消"></td>
							</tr>

						</table>
					</div>
				</td>
			</tr>
		</table>
		<table class="div1" cellpadding="0" class="tbb1" cellspacing="0">
			<tr>
				<td><div>
						<div>
							<ul class="tbb1ul">
								<li class="k"><a href="">首页</a></li>
								<li class="a"><a href="Page2.htm">课程介绍</a></li>
								<li class="b"><a href="#">教学团队</a></li>
								<li class="c"><a href="#">教学资源</a></li>
								<li class="d"><a href="#">实验教学</a></li>
								<li class="e"><a href="#">教学研究</a></li>
								<li class="f"><a href="#">教学成果</a></li>
								<li class="g"><a href="#">在线学习</a></li>
								<li class="h"><a href="#">下载专区</a></li>
								<li class="i"><a
									href="${pageContext.request.contextPath }/servlet/ForumServlet?method=forForumUI"
									target="maincenter">在线论坛</a></li>
								<li class="j"><a href="${pageContext }">留言板</a></li>
							</ul>
						</div></td>
			</tr>
			<tr>
				<td height="180px"
					style=" background-image: url('${pageContext.request.contextPath }/htms/image/fla.gif');height='181px'">
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
								<a href="">进入论坛</a>
							</dd>
						</dl>
						<dl class="j">
							<dt>留言板</dt>
							<dd>
								<a href="">进入留言</a>
							</dd>
						</dl>
					</div>
				</td>
			</tr>
			<tr>
				<td><iframe class="maincenter" src="main.html"></iframe></td>
			</tr>
		</table>
	</div>
</body>
</html>