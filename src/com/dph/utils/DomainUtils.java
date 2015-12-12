package com.dph.utils;

import java.util.Date;

public class DomainUtils
{
	public static long getNum(Date d)
	{
		Date date = new Date();
		long num = date.getTime() - d.getTime();

		return num;
	}
}
