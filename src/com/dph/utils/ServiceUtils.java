package com.dph.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.servlet.http.HttpSession;

import sun.misc.BASE64Encoder;

import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.QueryResult;
import com.dph.domain.User;

public class ServiceUtils
{

	private static ThreadLocal<User> tl = new ThreadLocal<User>(); // map

	public static void setUser(User user)
	{
		try
		{
			tl.set(user);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static User getUser()
	{
		try
		{
			User user = tl.get();
			return user;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	// 清除threadlocal中的user对象
	public static void cleartlUser()
	{
		try
		{
			User user = tl.get();
			if (user != null)
			{
				user = null;
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		} finally
		{
			tl.remove();// 移动要记得移出
		}
	}

	public static String md5(String message)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte md5[] = digest.digest(message.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			message = encoder.encode(md5);
		} catch (NoSuchAlgorithmException e)
		{
			new RuntimeException(e);
		}
		return message;
	}

	public static PageBean getPageBeanFromQueryResult(QueryInfo info,
			QueryResult queryResult)
	{
		PageBean bean = new PageBean();
		bean.setList(queryResult.getList());
		bean.setCurrentpage(info.getCurrentpage());
		bean.setPagesize(info.getPagesize());
		bean.setTotalrecord(queryResult.getTotalrecord());
		return bean;
	}

	public static String md5(String username, String password, long expirestime)
	{
		try
		{
			String value = password + ":" + expirestime + ":" + username;
			MessageDigest md = MessageDigest.getInstance("md5");
			byte md5[] = md.digest(value.getBytes());
			BASE64Encoder encode = new BASE64Encoder();
			return encode.encode(md5);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

}
