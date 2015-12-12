package com.dph.web.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.exception.DaoException;
import com.dph.service.UpfileService;
import com.dph.service.impl.UpfileServiceImpl;
import com.dph.utils.WebUtils;

public class ListFileServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			String method = request.getParameter("method");
			String type = (String) request.getParameter("type");
			if (type == null || type.equals(""))
			{
				type = "all";
			}
			UpfileService service = new UpfileServiceImpl();
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			PageBean pagebean = service.getAllUpfile(info, type);
			request.setAttribute("pagebean", pagebean);
			request.setAttribute("type", type);
			if (method == null || "".equals(method))
			{
				request.getRequestDispatcher(
						"/WEB-INF/jsp/resource/listfile.jsp").forward(request,
						response);
			} else
			{
				request.getRequestDispatcher("/manager/resource/listfile.jsp")
						.forward(request, response);
			}

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
