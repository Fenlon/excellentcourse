<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<form
	action="${pageContext.request.contextPath }/servlet/UserServlet?method=login"
	method="post">
	<div>
		<label>登陆界面</label>
	</div>
	<div>
		用户名:<input type="text" name="username">
	</div>
	<div>
		密码:<input type="text" name="password"> <span><a
			href="${pageContext.request.contextPath }/htm/sendemail.html">找回密码</a></span>
	</div>
	<div>
		<input type="reset" value="重置"> <input type="submit"
			value="登录">
	</div>
</form>
