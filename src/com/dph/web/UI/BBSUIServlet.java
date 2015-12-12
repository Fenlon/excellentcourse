package com.dph.web.UI;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dph.domain.Forum;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.exception.DaoException;
import com.dph.exception.SecurityException;
import com.dph.service.CardBusinessService;
import com.dph.service.ForumService;
import com.dph.service.ServiceFactory;
import com.dph.utils.ConfigUtils;
import com.dph.utils.WebUtils;

public class BBSUIServlet extends HttpServlet
{
	private ForumService forumService = ServiceFactory.getInstance()
			.createService(ForumService.class);
	CardBusinessService cardBusinessService = ServiceFactory.getInstance()
			.createService(CardBusinessService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String method = request.getParameter("method");

		if ("forForumUI".equals(method))
		{
			try
			{
				String idStr = request.getParameter("forumid");
				int forumid = Integer.parseInt(idStr);

				Forum forum = forumService.find(forumid);
				QueryInfo info = WebUtils
						.request2Bean(request, QueryInfo.class);

				PageBean pageBean = cardBusinessService
						.pageQuery(info, forumid);
				request.setAttribute("forum", forum);
				request.setAttribute("pagebean", pageBean);
			} catch (Exception e)
			{
				request.setAttribute("message", "访问数据库出错了!!");
				request.getRequestDispatcher("/WEB-INF/jsp/message.jsp")
						.forward(request, response);
				e.printStackTrace();
				return;
			}

			request.getRequestDispatcher("/WEB-INF/jsp/bbs/cardlist.jsp")
					.forward(request, response);
			return;
		}

		if ("forEditBBSUI".equals(method))
		{
			editeCard(request, response);
			return;
		}
	}

	private void editeCard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String message = "";
		try
		{
			String forumid = request.getParameter("forumid");
			HttpSession session = request.getSession(false);
			if (session != null)
			{
				if (session.getAttribute("user") == null)
				{
					message = "<font color='red'><b>您尚未登录！</b> </font>3秒后自动跳转到首页，如果没有跳转，请点击连接手动转到首页!";

					new WebUtils().solveEx_SendR(request, response, message,
							"/index.jsp");
					return;
				}
			}
			// 判断是 新人报到还是其他的，新人报道和其他的不一样啊，亲
			Forum forum = forumService.find(Integer.parseInt(forumid));
			if (!forum.getForumname().equals("新人报到".trim()))
			{// 如果不是新人报道
				request.setAttribute("forum", forum);
				request.getRequestDispatcher("/WEB-INF/jsp/bbs/othercard.jsp")
						.forward(request, response);
				return;
			}

			String key = "college";
			String collStr = ConfigUtils.getInstance().getContent(key);
			String colleges[] = collStr.split(":");
			request.setAttribute("colleges", colleges);
			request.setAttribute("forumid", forumid);
			request.getRequestDispatcher("/WEB-INF/jsp/bbs/newer.jsp").forward(
					request, response);
			return;
		} catch (NumberFormatException e)
		{
			message = "数值转换出错!";
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
			e.printStackTrace();
		} catch (DaoException e)
		{
			message = "数据库层出现错误!";
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
