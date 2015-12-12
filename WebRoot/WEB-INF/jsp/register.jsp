<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>注册页面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Birthday-Calendar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/datepicker.js"></script>
<script type="text/javascript">
	function changeImage(image) {
		image.src = image.src + "?" + new Date().getTime();
	}
</script>

</head>

<body>
	<div>
		<h1>
			<b>欢迎来到注册页面</b>
		</h1>
	</div>
	<form
		action="${pageContext.request.contextPath}/servlet/UserServlet?method=register"
		method="post">
		<table>
			<tr>
				<td>登录帐号:</td>
				<td><input type="text" name="username" value="${form.username}" /><span
					class="message">${form.errors.username} </span></td>
			</tr>
			<tr>
				<td>登录密码:</td>
				<td><input type="text" name="password"
					value="${form.password }" /><span class="message">${form.errors.password}
				</span></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="text" name="password2"
					value="${form.password2}" /><span class="message">${form.errors.password2}
				</span></td>
			</tr>

			<tr>
				<td>性别:</td>
				<td><input type="radio" name="gender" value="男"
					${form.gender=='男'?'checked':'' }>男 <input type="radio"
					name="gender" value="女" ${form.gender=='女'?'checked':'' }>女</td>
			</tr>

			<tr>
				<td>电子邮箱:</td>
				<td><input type="text" name="email" value="${form.email }" /><span
					class="message">${form.errors.email} </span></td>
			</tr>
			<tr>
				<td>生日:</td>
				<td><input type="text" title="点击选择" name="birthday"
					id="birthday" value="${form.birthday }" /><span class="message">${form.errors.birthday}
				</span></td>
			</tr>
			<tr>
				<td>昵称:</td>
				<td><input type="text" name="nickname"
					value="${form.nickname }" /><span class="message">${form.errors.nickname}
				</span></td>
			</tr>
			<tr>
				<td>个人描述:</td>
				<td><textarea cols="60" rows="5" name="description">${form.description }</textarea><span
					class="message">${form.errors.description} </span></td>
			</tr>
			<tr>
				<td>验证码:</td>
				<td><img alt="验证码" title="换一张" onclick="changeImage(this)"
					style="cursor: pointer"
					src="${pageContext.request.contextPath }/servlet/RandomImageServlet"></td>
			</tr>
			<tr>
				<td>图片认证:</td>
				<td><input type="text" name="checkimgcode" /> <span
					class="message">${form.errors.checkimgcode} </span></td>
			</tr>
			<tr>
				<td><input type="reset" value="重置"></td>
				<td><input type="submit" value="注册"></td>
			</tr>
		</table>
	</form>
</body>
</html>
