package com.dph.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.manager.Role;
import com.dph.service.ManagerService;
import com.dph.service.impl.ManagerServiceImpl;
import com.dph.utils.WebUtils;

public class RoleServlet extends HttpServlet
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
			request.getRequestDispatcher("/manager/role/addrole.jsp").forward(
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
		if ("updatePrivilegeUI".equals(method))
		{
			updatePrivilegeUI(request, response);
			return;
		}
		if ("updateRolePrivilege".equals(method))
		{
			updateRolePrivilege(request, response);
			return;
		}

		if ("delete".equals(method))
		{
			String message = "";
			try
			{
				String id = request.getParameter("id");
				service.deleteRole(id);
				message = "删除角色成功!";
			} catch (DaoException e)
			{
				message = "删除角色失败!";
				e.printStackTrace();
			} finally
			{
				WebUtils.solveEx_forward(request, response, message,
						"/WEB-INF/jsp/message.jsp");
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			service.deleteRole(id);
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

	private void updateRolePrivilege(HttpServletRequest request,
			HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			String[] ps = request.getParameterValues("privilege");
			List<Privilege> privileges = new ArrayList<Privilege>();
			for (int i = 0; i < ps.length; i++)
			{
				String pid = ps[i];
				privileges.add(service.findPrivilege(pid));
			}
			Role role = service.findRole(id);
			service.updateRolePrivilege(role, privileges);
			message = "授权成功!";
		} catch (DaoException e)
		{
			message = "授权失败!";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void updatePrivilegeUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

		try
		{
			String id = request.getParameter("id");
			Role r = service.findRole(id);
			List<Privilege> ps = service.getAllPrivilege();
			request.setAttribute("r", r);
			request.setAttribute("ps", ps);
			request.getRequestDispatcher(
					"/manager/role/updateRolePrivilege.jsp").forward(request,
					response);
		} catch (DaoException e)
		{
			WebUtils.solveEx_forward(request, response, "数据库层出现错误！",
					"/WEB-INF/jsp/message.jsp");
			e.printStackTrace();
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			Role r = WebUtils.request2Bean(request, Role.class);
			r.setId(UUID.randomUUID().toString());
			service.addRole(r);
			message = "添加角色成功!";
		} catch (DaoException e)
		{
			message = "添加角色失败!";
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
			List<Role> roles = service.getAllRole();
			request.setAttribute("roles", roles);
			request.getRequestDispatcher("/manager/role/listrole.jsp").forward(
					request, response);
		} catch (DaoException e)
		{
			WebUtils.solveEx_forward(request, response, "数据库层出现错误!",
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
