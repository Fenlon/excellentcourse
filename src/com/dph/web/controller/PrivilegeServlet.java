package com.dph.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.service.ManagerService;
import com.dph.service.impl.ManagerServiceImpl;
import com.dph.utils.WebUtils;

public class PrivilegeServlet extends HttpServlet
{
	ManagerService service = new ManagerServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String method = request.getParameter("method");
		if ("listall".equals(method))
		{
			listAll(request, response);
			return;
		}
		if ("addUI".equals(method))
		{
			request.getRequestDispatcher("/manager/privilege/addprivilege.jsp")
					.forward(request, response);
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
		if ("updateUI".equals(method))
		{
			updateUI(request, response);
			return;
		}
		if ("update".equals(method))
		{
			update(request, response);
			return;
		}
	}

	private void updateUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String id = request.getParameter("id");
			Privilege p = service.findPrivilege(id);
			request.setAttribute("p", p);
			request.getRequestDispatcher(
					"/manager/privilege/updateprivilege.jsp").forward(request,
					response);
		} catch (DaoException e)
		{
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			Privilege p = WebUtils.request2Bean(request, Privilege.class);
			service.updatePrivilege(p);
			message = "更新成功！";
		} catch (DaoException e)
		{
			message = "更新失败！";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		String message = "";
		try
		{
			service.deletePrivilege(id);
			message = "删除成功!";
		} catch (DaoException e)
		{
			message = "删除失败!";
			e.printStackTrace();
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
			Privilege p = WebUtils.request2Bean(request, Privilege.class);
			p.setId(UUID.randomUUID().toString());
			service.addPrivilege(p);
			message = "添加权限成功!";
		} catch (DaoException e)
		{
			message = "添加权限失败!";

			e.printStackTrace();
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
			List<Privilege> privileges = service.getAllPrivilege();
			request.setAttribute("privileges", privileges);
			request.getRequestDispatcher("/manager/privilege/listprivilege.jsp")
					.forward(request, response);
		} catch (DaoException e)
		{
			WebUtils.solveEx_forward(request, response,
					"<font color='red'>数据库出错误了</font>",
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
