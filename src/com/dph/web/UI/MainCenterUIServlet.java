package com.dph.web.UI;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.constant.Constant;
import com.dph.domain.Card;
import com.dph.domain.Notice;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.service.CardBusinessService;
import com.dph.service.NoticeService;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.CardBusinessServiceImpl;
import com.dph.service.impl.NoticeServiceImpl;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.WebUtils;

public class MainCenterUIServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			String method = request.getParameter("method");
			if (method.equals("foroltea"))
			{
				// 为了得到教师列表
				getolTea(request, response);
				return;
			}
			// 否则得到主页面所需要的数据
			if (method.equals("formainUI"))
			{
				NoticeService noticeService = new NoticeServiceImpl();
				CardBusinessService cardService = new CardBusinessServiceImpl();
				List<Notice> ns = noticeService
						.getIndexNotices(Constant.NEWSNUM);
				List<Card> cs = cardService.getIndexCards(Constant.CARDNUM);
				request.setAttribute("ns", ns);
				request.setAttribute("cs", cs);
				request.getRequestDispatcher("/WEB-INF/jsp/maincenter.jsp")
						.forward(request, response);
				return;
			}
		} catch (DaoException e)
		{
			e.printStackTrace();
			WebUtils.solveEx_forward(request, response, Constant.DaoError,
					Constant.ERRORPAGE);
		}
	}

	private void getolTea(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			UserBusinessService service = new UserBusinessServiceImpl();
			// get the online teachers and set to the requestScope to the page
			// if it is not null

			List<User> olteas = (List<User>) request.getServletContext()
					.getAttribute("onlineteas");
			if (olteas != null)
			{
				request.setAttribute("olts", olteas);
			}
			// get all teachers and set to page
			List<User> teas = service.getTeachers();
			request.setAttribute("ts", teas);
			request.getRequestDispatcher("/WEB-INF/jsp/user/onlinetea.jsp")
					.forward(request, response);
		} catch (DaoException e)
		{
			e.printStackTrace();
		}
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

}
