package com.dph.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharsetFilter implements Filter
{
	private FilterConfig config = null;
	private String defaultCharset = "UTF-8";

	@Override
	public void destroy()
	{

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String charset = this.config.getInitParameter("charset");
		if (charset == null || charset == "")
		{
			charset = defaultCharset;
		}
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);

		// 下面解决get方式请求表单时候的乱码问题
		// 应为get是按着queryString方式本身在形成uri时候就经过了内部的编码，所以一般形式的是不能解决问题的
		// 因为我们对request的getParameter(name)方法不满意，所以我要要强化这个函数，就需要重新包装这个类(HttpServletRequest)
		chain.doFilter(new MyRequest(request), response);
	}

	private class MyRequest extends HttpServletRequestWrapper
	{
		HttpServletRequest request;

		public MyRequest(HttpServletRequest request)
		{
			super(request);
			this.request = request;
		}

		@Override
		public String getParameter(String name)
		{
			String value = request.getParameter(name);
			if (!request.getMethod().equalsIgnoreCase("get"))
			{
				return value;
			}
			if (value == null)
			{
				return null;
			}

			try
			{
				value = new String(value.getBytes("iso8859-1"),
						request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			return value;
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException
	{
		this.config = config;
	}

}
