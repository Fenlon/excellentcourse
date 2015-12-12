package com.dph.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.domain.Forum;
import com.dph.exception.DaoException;
import com.dph.service.ForumService;
import com.dph.service.impl.ForumServiceImpl;
import com.dph.utils.WebUtils;

public class ForumServlet extends HttpServlet
{
	private ForumService forumService = new ForumServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String method = request.getParameter("method");
		if ("forForumUI".equals(method))
		{
			forForumUI(request, response);
			return;
		}
		if ("listall".equals(method))
		{
			listAll(request, response);
			return;
		}
		if ("addUI".equals(method))
		{
			addUI(request, response);
			return;
		}
		if ("add".equals(method))
		{
			addForum(request, response);
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
			forumService.delete(Integer.parseInt(id));
			message = "删除板块成功!";
		} catch (DaoException e)
		{
			message = "删除板块失败!";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void addForum(HttpServletRequest request,
			HttpServletResponse response)
	{
		String message = "";
		try
		{
			Forum forum = WebUtils.request2Bean(request, Forum.class);
			forumService.add(forum);
			message = "添加板块成功!";
		} catch (DaoException e)
		{
			message = "添加板块失败!";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.getRequestDispatcher("/manager/forum/addforum.jsp").forward(
				request, response);
	}

	private void listAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			List<Forum> forums = forumService.getForums();
			request.setAttribute("forums", forums);
			request.getRequestDispatcher("/manager/forum/listforum.jsp")
					.forward(request, response);
		} catch (DaoException e)
		{
			e.printStackTrace();
		}
	}

	private void forForumUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			List<Forum> forums = forumService.getForums();
			request.setAttribute("forums", forums);
			request.getRequestDispatcher("/WEB-INF/jsp/bbs/bbs_index.jsp")
					.forward(request, response);
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
