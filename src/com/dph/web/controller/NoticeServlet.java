package com.dph.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.constant.Constant;
import com.dph.domain.Notice;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.exception.SecurityException;
import com.dph.service.NoticeService;
import com.dph.service.ServiceFactory;
import com.dph.utils.WebUtils;

public class NoticeServlet extends HttpServlet
{
	// private NoticeService noticeService = new NoticeServiceImpl();
	private NoticeService noticeService = ServiceFactory.getInstance()
			.createService(NoticeService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String method = request.getParameter("method");
		if ("listall".equals(method))
		{
			listAll(request, response);
			return;
		}
		if ("add".equals(method))
		{
			add(request, response);
			return;
		}
		if ("delete".equals(method))
		{
			delete(request, response);
			return;
		}
		if ("update".equals(method))
		{
			update(request, response);
			return;
		}
		if ("updateUI".equals(method))
		{
			User user = WebUtils
					.getBeanFromSession(request, "user", User.class);
			if (user == null)
			{
				WebUtils.solveEx_forward(request, response, "登录后才能进行此操作!",
						"/WEB-INF/jsp/message.jsp");
				return;
			}
			try
			{
				String id = request.getParameter("id");
				Notice notice = noticeService.findNotice(id);
				request.setAttribute("notice", notice);
				request.getRequestDispatcher("/manager/notice/updatenotice.jsp")
						.forward(request, response);
			} catch (DaoException e)
			{
				e.printStackTrace();
				WebUtils.solveEx_forward(request, response, "数据库层出现错误！",
						"/WEB-INF/jsp/message.jsp");
			}
			return;
		}
		if ("addUI".equals(method))
		{
			User user = WebUtils
					.getBeanFromSession(request, "user", User.class);
			if (user == null)
			{
				WebUtils.solveEx_forward(request, response, Constant.NoLogin,
						"/WEB-INF/jsp/message.jsp");
				return;
			}
			request.getRequestDispatcher("/manager/notice/addnotice.jsp")
					.forward(request, response);
			return;
		}
		if ("detail".equals(method))
		{
			try
			{
				String id = request.getParameter("id");
				Notice notice = noticeService.findNotice(id);
				request.setAttribute("notice", notice);
			} catch (DaoException e)
			{
				e.printStackTrace();
				WebUtils.solveEx_forward(request, response, Constant.DaoError,
						"/WEB-INF/jsp/message.jsp");
			}
			request.getRequestDispatcher("/WEB-INF/jsp/notice/detail.jsp")
					.forward(request, response);
			return;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			noticeService.deleteNotice(id);
			message = "公告删除成功!";
		} catch (Exception e)
		{
			if (e.getCause() instanceof SecurityException)
			{
				message = e.getCause().getMessage();
				e.printStackTrace();
			} else
			{
				message = "公告删除失败!";
				e.printStackTrace();
			}
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			Notice notice = WebUtils.request2Bean(request, Notice.class);
			noticeService.updateNotice(notice);
			message = "公告更新成功!";
		} catch (Exception e)
		{
			if (e.getCause() instanceof SecurityException)
			{
				message = e.getCause().getMessage();
				e.printStackTrace();
			} else
			{
				message = "公告更新失败!";
				e.printStackTrace();
			}
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			User user = WebUtils
					.getBeanFromSession(request, "user", User.class);
			if (user == null)
			{
				return;
			}
			Notice notice = WebUtils.request2Bean(request, Notice.class);
			notice.setId(WebUtils.generateId());
			notice.setTime(new Date());
			notice.setUser(user);
			noticeService.addNotice(notice);
			message = "发布公告成功!";
		}

		catch (Exception e)
		{
			if (e.getCause() instanceof SecurityException)
			{
				message = e.getCause().getMessage();
				e.printStackTrace();
			} else
			{
				message = "发布公告失败!";
				e.printStackTrace();
			}
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}

	}

	private void listAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String page = request.getParameter("page");
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			PageBean pagebean = noticeService.pageQuery(info);
			request.setAttribute("pagebean", pagebean);
			if (page != null)
			{
				if (page.equals("indexmore"))
				{
					// 说明是转发到首页点击进入的公告列表页面
					request.getRequestDispatcher(
							"/WEB-INF/jsp/notice/listnotice.jsp").forward(
							request, response);
					return;
				}
			}

			request.getRequestDispatcher("/manager/notice/listnotice.jsp")
					.forward(request, response);
		} catch (DaoException e)
		{
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

}
