package com.dph.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory
{
	private Properties prop = null;
	private static DaoFactory instence = new DaoFactory();

	private DaoFactory()
	{
		try
		{
			prop = new Properties();

			InputStream inStream = this.getClass().getClassLoader()
					.getResourceAsStream("daoconfig.properties");
			prop.load(inStream);
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static DaoFactory getInstance()
	{
		return instence;
	}

	public <T> T getDao(Class<T> clazz)
	{
		String name = clazz.getSimpleName();
		String className = prop.getProperty(name);
		try
		{
			T dao = (T) Class.forName(className).newInstance();
			return dao;
		} catch (Exception e)
		{
			throw new RuntimeException();
		}
	}
}
