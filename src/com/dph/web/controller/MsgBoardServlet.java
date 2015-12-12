package com.dph.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.domain.Msg;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.exception.SecurityException;
import com.dph.service.MsgService;
import com.dph.service.ServiceFactory;
import com.dph.utils.WebUtils;

public class MsgBoardServlet extends HttpServlet
{
	private MsgService msgService = ServiceFactory.getInstance().createService(
			MsgService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		String method = request.getParameter("method");
		if ("forMsgBoardUI".equals(method))
		{
			forMsgBoardUI(request, response);
			return;
		}
		if ("postmsg".equals(method))
		{
			postMsg(request, response);
			return;
		}
		if ("answer".equals(method))
		{
			answer(request, response);
			return;
		}
		if ("delete".equals(method))
		{
			delete(request, response);
			return;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			msgService.deleteMsg(id);
			response.sendRedirect(request.getContextPath()
					+ "/servlet/MsgBoardServlet?method=forMsgBoardUI&currentpage="
					+ info.getCurrentpage() + "&pagesize=" + info.getPagesize());
		} catch (Exception e)
		{
			if (e.getCause() instanceof SecurityException)
			{
				message = e.getCause().getMessage();
				e.printStackTrace();
				WebUtils.solveEx_forward(request, response, message,
						"/WEB-INF/jsp/message.jsp");
			} else
			{
				message = "公告删除失败!";
				e.printStackTrace();
				WebUtils.solveEx_forward(request, response, message,
						"/WEB-INF/jsp/message.jsp");
			}
		}
	}

	private void answer(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String content = request.getParameter("content");
			String parentid = request.getParameter("parentid");
			User user = WebUtils
					.getBeanFromSession(request, "user", User.class);
			if (user == null)
			{
				throw new Exception("您还未登录!");
			}
			Msg msg = new Msg();
			msg.setContent(content);
			msg.setId(WebUtils.generateId());
			msg.setTime(new Date());
			msg.setUserip(request.getRemoteAddr());
			msg.setMsgparent(parentid);
			msg.setUser(user);
			msgService.addMsg(msg);

			// 重定向到刚才的页面
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			response.sendRedirect(request.getContextPath()
					+ "/servlet/MsgBoardServlet?method=forMsgBoardUI&currentpage="
					+ info.getCurrentpage() + "&pagesize=" + info.getPagesize());
		} catch (DaoException e)
		{
			WebUtils.solveEx_forward(request, response,
					"<font color='green'>数据库层出错了</font>",
					"/WEB-INF/jsp/message.jsp");
			e.printStackTrace();
		} catch (Exception e)
		{
			WebUtils.solveEx_forward(request, response, "<font color='green'>"
					+ e.getMessage() + "</font>", "/WEB-INF/jsp/message.jsp");
			e.printStackTrace();
		}
	}

	private void forMsgBoardUI(HttpServletRequest request,
			HttpServletResponse response)
	{
		try
		{
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			PageBean pageBean = msgService.pageQuery(info);
			request.setAttribute("pagebean", pageBean);
			request.getRequestDispatcher("/WEB-INF/jsp/msg_board/msg_index.jsp")
					.forward(request, response);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void postMsg(HttpServletRequest request,
			HttpServletResponse response)
	{
		try
		{
			User user = WebUtils
					.getBeanFromSession(request, "user", User.class);
			if (user == null)
			{
				throw new Exception("您还未登录!");
			}

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			if ("".equals(title) || title == null || "".equals(content)
					|| content == null)
			{
				throw new Exception("内容不能为空!");
			}
			Msg msg = new Msg();
			msg.setContent(content);
			msg.setTitle(title);
			msg.setId(WebUtils.generateId());
			msg.setTime(new Date());
			msg.setUserip(request.getRemoteAddr());

			msg.setUser(user);
			msgService.addMsg(msg);
			response.sendRedirect("MsgBoardServlet?method=forMsgBoardUI");
		} catch (DaoException e)
		{
			e.printStackTrace();
			WebUtils.solveEx_forward(request, response,
					"<font color='green'>数据库层出错了</font>",
					"/WEB-INF/jsp/message.jsp");
		} catch (Exception e)
		{
			e.printStackTrace();
			WebUtils.solveEx_forward(request, response, "<font color='green'>"
					+ e.getMessage() + "</font>", "/WEB-INF/jsp/message.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
