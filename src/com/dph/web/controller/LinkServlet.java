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
import com.dph.manager.Resource;
import com.dph.service.ManagerService;
import com.dph.service.impl.ManagerServiceImpl;
import com.dph.utils.WebUtils;

public class LinkServlet extends HttpServlet
{
	private ManagerService service = new ManagerServiceImpl();

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
			request.getRequestDispatcher("/manager/link/addlink.jsp").forward(
					request, response);
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
			update(request, response);
			return;
		}
		if ("forUpdatePrivilegeUI".equals(method))
		{
			forUpdatePrivilegeUI(request, response);
			return;
		}
		if ("updateLinkPrivilege".equals(method))
		{
			updateLinkPrivilege(request, response);
			return;
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			Resource r = service.findByID(id);
			service.updateResource(r);
			message = "更新链接成功!";
		} catch (DaoException e)
		{
			message = "更新链接失败!";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			service.deleteLink(id);
			message = "删除链接成功!";
		} catch (DaoException e)
		{
			message = "删除链接失败!";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void updateLinkPrivilege(HttpServletRequest request,
			HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			String privilegeid = request.getParameter("privilege");
			Privilege p = service.findPrivilege(privilegeid);
			Resource r = service.findByID(id);
			r.setPrivilege(p);
			service.updateResourcePrivilege(r, p);
			message = "更改权限成功!";
		} catch (DaoException e)
		{
			message = "更改权限失败!";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void forUpdatePrivilegeUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String id = request.getParameter("id");
			Resource r = service.findByID(id);
			// 得到所有的权限
			List<Privilege> ps = service.getAllPrivilege();
			request.setAttribute("r", r);
			request.setAttribute("ps", ps);
			request.getRequestDispatcher(
					"/manager/link/updateLinkPrivilege.jsp").forward(request,
					response);
		} catch (DaoException e)
		{
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			Resource r = WebUtils.request2Bean(request, Resource.class);
			r.setId(UUID.randomUUID().toString());
			service.addResource(r);
			message = "添加链接成功!";
		} catch (DaoException e)
		{
			message = "添加链接失败!";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void listAll(HttpServletRequest request,
			HttpServletResponse response)
	{
		String message = "";
		try
		{
			List<Resource> ls = service.getAllResource();
			request.setAttribute("ls", ls);
			request.getRequestDispatcher("/manager/link/listlink.jsp").forward(
					request, response);
		} catch (DaoException e)
		{
			message = "数据库层出现错误！";
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
			e.printStackTrace();
		} catch (Exception e)
		{
			message = "对不起俺出错了！";
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
