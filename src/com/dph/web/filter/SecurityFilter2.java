package com.dph.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.constant.Constant;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.manager.Resource;
import com.dph.service.ManagerService;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.ManagerServiceImpl;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.WebUtils;

public class SecurityFilter2 implements Filter
{
	private ManagerService service = new ManagerServiceImpl();
	private UserBusinessService userService = new UserBusinessServiceImpl();

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException
	{
		try
		{
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			// 1是否登录
			User user = (User) request.getSession().getAttribute("user");
			// 2.没登陆，登陆去
			if (user == null)
			{
				WebUtils.solveEx_forward(request, response, Constant.NoLogin,
						Constant.ERRORPAGE);
				return;
			}
			// 3.得到用户想访问的资源
			String uri = request.getRequestURI();
			System.out.println(request.getRequestURL());
			// 4.得到访问该资源需要的权限
			Resource r = service.findResource(uri);
			if (r == null)
			{// 说明次资源不被限制
				chain.doFilter(request, response);
				return;
			}

			Privilege required_Privilege = r.getPrivilege(); // //得到访问资源需要的权限

			// 5.判断用户是否有相应权限
			List<Privilege> list = userService.getUserPrivileges(user.getId());
			if (!list.contains(required_Privilege))
			{
				// 6.没有权限，则提示用户权限不足，联系管理
				WebUtils.solveEx_forward(request, response, "权限不足，请联系管理员!",
						Constant.ERRORPAGE);
				return;
			}

			// 7.如果有，则放行
			chain.doFilter(request, response);
		} catch (DaoException e)
		{
			e.printStackTrace();
		}

	}

	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
