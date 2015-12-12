package com.dph.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.ServiceUtils;
import com.dph.utils.WebUtils;

public class ResetPwdServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String time = request.getParameter("time");
		// 一个小时内必须重置密码，否则邮箱连接无效
		if (time == null)
		{
			return;
		}
		if (new Date().getTime() - Long.parseLong(time) > 1 * 0.5 * 60 * 1000)
		{
			new WebUtils()
					.solveEx_SendR(
							request,
							response,
							"没有在有效时间内充值密码,<font color='red'>邮箱链接已过期</font>,请重新重置操作！<font color='red'>3秒中后跳转到找回密码界面！</font>",
							"/htm/sendemail.html");
			return;
		}
		String uid = request.getParameter("uid");
		request.setAttribute("uid", uid);
		request.getRequestDispatcher("/WEB-INF/jsp/common/resetpwd.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String uid = request.getParameter("uid");
		if (uid != null || uid != "")
		{
			String newpwd = request.getParameter("password");
			try
			{
				UserBusinessService userBusinessService = new UserBusinessServiceImpl();
				userBusinessService.update("password",
						ServiceUtils.md5(newpwd), uid, String.class);
				User user = userBusinessService.findUser(uid);

				user = userBusinessService.login(user.getUsername(), newpwd);
				System.out.println(user);
				if (user != null)
				{
					request.getSession(true).setAttribute("user", user);
				}
			} catch (DaoException e)
			{
				e.printStackTrace();
				WebUtils.solveEx_forward(request, response, "出错了，请手动回到首页！",
						"/WEB-INF/jsp/message.jsp");
			}
		}
	}
}
