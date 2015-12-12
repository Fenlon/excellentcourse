package com.dph.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils
{
	private Properties prop = null;
	private static ConfigUtils configUtils = new ConfigUtils();
	private String propFile = "config.properties";

	private ConfigUtils()
	{
		try
		{
			prop = new Properties();

			InputStream inStream = this.getClass().getClassLoader()
					.getResourceAsStream(propFile);
			prop.load(inStream);
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static ConfigUtils getInstance()
	{
		return configUtils;
	}

	public String getContent(String key)
	{
		return prop.getProperty(key);
	}
}
