package com.dph.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.dph.utils.JdbcUtils;

public class TransactionFilter implements Filter
{

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		// 如果在这里开启事物的话，就意味着所有的请求都开启事物，所以很占用资源和cpu
		// 因此只有当请求数据库时候才开启事物，所以就在Jdbc的getconnection中默认的把事物开启，下面的
		// runner就直接获取connection就行了
		// JdbcUtils.startTransaction();
		try
		{
			chain.doFilter(request, response);
			JdbcUtils.commitTransaction();
		} catch (Exception e)
		{
			e.printStackTrace();
			JdbcUtils.closeConnection();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
