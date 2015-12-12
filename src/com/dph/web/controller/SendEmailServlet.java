package com.dph.web.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.WebUtils;

public class SendEmailServlet extends HttpServlet implements Servlet
{
	private UserBusinessService userBusinessService = new UserBusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String method = request.getParameter("method");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		try
		{
			if ("ajaxvalidate".equals(method))
			{
				// 此方法ajax请求调用
				// 验证用户名
				ajaxvalidate(username, email, response);
				return;
			}
			if ("ajaxvalidatename".equals(method))
			{
				// 此方法ajax请求调用
				// 验证用户名
				ajaxvalidatename(username, response);
				return;
			}

			if (!validate(username, email))
			{
				WebUtils.solveEx_forward(request, response,
						"<font color='green'>请认真填写信息，ok？</font>",
						"/WEB-INF/jsp/message.jsp");
				return;
			}
			User user = userBusinessService.find(username);
			Thread thread = new Thread(new com.dph.utils.SendEmail(user,
					request.getServletContext()));
			thread.start();
			String message = "已经向"
					+ "<font color='red'><b>"
					+ user.getEmail()
					+ "</font>发送邮件成功,<font color='green'>请在一个小时之内查看邮箱点击操作</font>,如果没有收到,请待会再收！3秒后自动跳转到首页，如果没有跳转，请点击连接手动转到首页!";
			new WebUtils().solveEx_SendR(request, response, message,
					"/index.jsp");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private boolean ajaxvalidatename(String username,
			HttpServletResponse response) throws Exception
	{
		boolean flag;
		User user = null;
		JSONObject msg = new JSONObject();
		if ("".equals(username) || username == null)
		{
			flag = false;
			msg.put("msg", flag);
			response.getWriter().print(msg);
			return flag;
		}

		user = userBusinessService.find(username);
		if (user == null)
		{
			flag = false;
		} else
		{
			flag = true;
		}
		msg.put("msg", flag);
		response.getWriter().print(msg);
		return flag;
	}

	private void ajaxvalidate(String username, String email,
			HttpServletResponse response) throws Exception
	{
		boolean flag;
		User user = null;
		JSONObject msg = new JSONObject();
		user = userBusinessService.find(username);
		if (user == null)
		{
			flag = false;
		}
		if (user != null && user.getEmail().equals(email))
		{
			flag = true;
		} else
		{
			flag = false;
		}
		msg.put("msg", flag);
		response.getWriter().print(msg);
	}

	private boolean validate(String username, String email) throws DaoException
	{
		if (!userBusinessService.findUserISExist(username))
		{
			return false;
		}
		if (!email.matches("\\w+@\\w+(\\.\\w+)+"))
		{
			return false;
		}
		return true;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
