package com.dph.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils
{
	private static DataSource ds = null;
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>(); // map
	static
	{
		ds = new ComboPooledDataSource();
	}

	public static DataSource getDataSource()
	{
		return ds;
	}

	public static Connection getConnection()
	{
		try
		{
			Connection conn = tl.get();
			if (conn == null)
			{
				conn = ds.getConnection();
				conn.setAutoCommit(false);
				tl.set(conn);
			}
			return conn;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void startTransaction()
	{
		try
		{
			Connection conn = tl.get();
			if (conn == null)
			{
				conn = ds.getConnection();
				tl.set(conn);
			}
			conn.setAutoCommit(false);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void commitTransaction()
	{
		try
		{
			Connection conn = tl.get();
			if (conn != null)
			{
				conn.commit();
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void closeConnection()
	{
		try
		{
			Connection conn = tl.get();
			if (conn != null)
			{
				conn.close();
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		} finally
		{
			tl.remove();// 移动要记得移出
		}
	}
}
