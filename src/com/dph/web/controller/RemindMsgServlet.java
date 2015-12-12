package com.dph.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.domain.BBSMsg;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.service.BBSMsgBussinessService;
import com.dph.service.impl.BBSMsgBusinessServiceImpl;
import com.dph.utils.WebUtils;

public class RemindMsgServlet extends HttpServlet
{
	private BBSMsgBussinessService bbsMsgBussinessService = new BBSMsgBusinessServiceImpl();
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.request = request;
		this.response = response;
		String method = request.getParameter("method");
		if ("forRmsgUI".equals(method))
		{
			request.getRequestDispatcher("/WEB-INF/jsp/mymsg.jsp").forward(
					request, response);
			return;
		}
		if ("listmsgs".equals(method))
		{
			listmsgs();
			return;
		}
	}

	private void listmsgs() throws ServletException, IOException
	{
		try
		{
			User user = WebUtils
					.getBeanFromSession(request, "user", User.class);
			String username = null;
			if (user == null)
			{
				return;
			}
			username = user.getUsername();
			List<BBSMsg> bbsmsgs = bbsMsgBussinessService
					.getRemindMsgs(username);
			request.setAttribute("bbsmsgs", bbsmsgs);
			request.setAttribute("flag", "msgflag");
			request.getRequestDispatcher("/WEB-INF/jsp/mymsg.jsp").forward(
					request, response);
		} catch (DaoException e)
		{
			request.setAttribute("message",
					"<font color='red'><b>服务器数据库层出错了！</b> </font>");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

}
