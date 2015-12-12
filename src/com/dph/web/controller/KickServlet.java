package com.dph.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dph.constant.Constant;
import com.dph.utils.WebUtils;

public class KickServlet extends HttpServlet
{
	/***
	 * 剔除登陆中的不良用户
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso8859-1"), "UTF-8");

		Map map = (Map) this.getServletContext().getAttribute("map");
		HttpSession session = (HttpSession) map.get(username);
		if (session != null)
		{
			Cookie autoLoginCookie = null;
			Cookie cookies[] = request.getCookies();

			if (session == request.getSession())
			{
				WebUtils.solveEx_forward(request, response, "你把自己给踢了。。。",
						Constant.ERRORPAGE);
				return;
			}

			for (int i = 0; cookies != null && i < cookies.length; i++)
			{
				if (cookies[i].getName().equals("autologin"))
				{
					autoLoginCookie = cookies[i];
					break;
				}
			}
			if (autoLoginCookie != null)
			{
				autoLoginCookie.setMaxAge(0);
				autoLoginCookie.setPath("/ExcellentCourse");
				response.addCookie(autoLoginCookie);
			}

			session.invalidate();
			map.remove(username);
		}

		response.sendRedirect(request.getContextPath()
				+ "/manager/user/listonlineuser.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
