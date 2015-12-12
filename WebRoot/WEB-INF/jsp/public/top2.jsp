<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript">
	function denglu() {
		var str1 = document.getElementById("text1");
		var str2 = document.getElementById("Password1");
		if (str1.value == "" || str2.value == "") {
			alert("请输入完整信息！");
		} else {
			this.div1.style.display = "none";
			this.div2.style.display = "block";
			this.label1.innerHTML = "欢迎" + this.text1.value + "！";
		}
	}
	function tuichu() {
		this.window.close();
	}
</script>
<link href="${pageContext.request.contextPath }/css/common.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
</head>
<body>
	<div id="div1" style="display: block">
		<table class="t1">
			<tr>
				<td style="width: 311px;">
					<!--  <img alt="中原工学院" src="image/g.gif" /> -->
				</td>
				<td style="width: 89px;"></td>
				<td>
					<table>
						<tr>
							<td
								style="width: 80px; border-width: medium; border-left-style: groove;">
								<label> 用户名</label>
							</td>
							<td class="style4"><input type="text" id="text1" /></td>
							<td style="width: 160px;"><input id="Checkbox1"
								type="checkbox" value="自动登录" />&nbsp;自动登录</td>
							<td
								style="width: 160px; border-width: medium; border-left-style: groove;">
								<a>.找回密码</a>
							</td>
						</tr>
						<tr>
							<td style="border-width: medium; border-left-style: groove;">
								<label> 密 码</label>
							</td>
							<td class="style4"><input id="Password1" type="password" />
							</td>
							<td><input id="Button1" type="button" value="登陆"
								onclick="denglu()" /></td>
							<td style="border-width: medium; border-left-style: groove;">
								<a>.会员注册</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div id="div2" style="display: none;">
		<table>
			<tr>
				<td style="width: 311px;"><img alt="中原工学院"
					src="${pageContext.request.contextPath }/htms/image/g.gif" /></td>
				<td style="width: 549px;">
					<table>
						<tr>
							<td style="width: 229px; text-align: right;"><label
								id="label1"> </label></td>
							<td
								style="width: 120px; border-width: medium; border-left-style: groove;">
								<a href="">我的信息</a>
							</td>
							<td
								style="width: 100px; border-width: medium; border-left-style: groove;">
								<a href="">消息</a>
							</td>
							<td
								style="width: 100px; border-width: medium; border-left-style: groove;">
								<a onclick="tuichu()">退出</a>
							</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td style="border-width: medium; border-left-style: groove;">
								<a href="">去留言！</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
