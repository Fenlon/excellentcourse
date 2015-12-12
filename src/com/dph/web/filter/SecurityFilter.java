package com.dph.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.domain.User;
import com.dph.service.ManagerService;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.ManagerServiceImpl;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.ServiceUtils;

public class SecurityFilter implements Filter
{
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 现将user放在threadlocal中
		ServiceUtils.setUser((User) request.getSession().getAttribute("user"));
		chain.doFilter(request, response);
		// 线程回来后移除user
		ServiceUtils.cleartlUser();
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
