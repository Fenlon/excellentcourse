<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
#login {
	width: 400px;
	height: 300px;
	border: 1px;
	background-color: pink;
	text-align: center;
	display: none;
}
</style>
</head>

<h1>精品课程</h1>
<div id="login">
	<div>您需要先登录才能完成本操作</div>
	<table>
		<tr>
			<td>用户名</td>
			<td><input type="text"></td>
			<td><a href="#">用户注册</a></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="text"></td>
			<td><a href="#">找回密码</a></td>
		</tr>
		<tr>
			<td><input type="checkbox" value="autologin">自动登录</td>
		</tr>
		<tr>
			<td><input type="button" value="登录"></td>
		</tr>
	</table>
</div>
</html>
