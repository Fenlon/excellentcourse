package com.dph.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.ServiceUtils;

public class AutoLoginFilter implements Filter
{

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException
	{

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		try
		{// 1检查用户是否已经登录，没有登录才自动登录
			User user = (User) request.getSession().getAttribute("user");
			if (user != null)
			{
				chain.doFilter(request, response);
				return;
			}

			// 2.没有登录，执行自动登录

			// 看用户有没有带自动登录的cookie
			Cookie autoLoginCookie = null;
			Cookie cookies[] = request.getCookies();
			for (int i = 0; cookies != null && i < cookies.length; i++)
			{
				if (cookies[i].getName().equals("autologin"))
				{
					autoLoginCookie = cookies[i];
				}
			}
			if (autoLoginCookie == null)
			{
				chain.doFilter(request, response);
				return;
			}
			// /用户带了自动登陆的cookie，先检查cookie的有效期
			String values[] = autoLoginCookie.getValue().split("\\:");
			if (values.length != 3)
			{
				chain.doFilter(request, response);
				return;
			}
			long expirestime = Long.parseLong(values[1]);
			if (System.currentTimeMillis() > expirestime)
			{
				chain.doFilter(request, response);
				return;
			}

			// 检查cookie的是
			String username = values[0];
			String client_md5 = values[2];

			UserBusinessService service = new UserBusinessServiceImpl();
			user = service.find(username);
			if (user == null)
			{
				chain.doFilter(request, response);
				return;
			}
			// //autologin=username:expirestime:md5(password:expirestime:username)
			String server_md5 = ServiceUtils.md5(user.getUsername(),
					user.getPassword(), expirestime);
			if (!server_md5.equals(client_md5))
			{
				chain.doFilter(request, response);
				return;
			}
			request.getSession().setAttribute("user", user);
			chain.doFilter(request, response);
		} catch (DaoException e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * private String md5(String username, String password, long expirestime) {
	 * 
	 * try { String value = password + ":" + expirestime + ":" + username;
	 * MessageDigest md = MessageDigest.getInstance("md5"); byte md5[] =
	 * md.digest(value.getBytes()); BASE64Encoder encode = new BASE64Encoder();
	 * 
	 * return encode.encode(md5);
	 * 
	 * } catch (Exception e) { throw new RuntimeException(e); } }
	 */

	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
