package com.dph.web.listener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.dph.domain.User;
import com.dph.manager.Role;

public class UserListener implements HttpSessionAttributeListener
{

	public void attributeAdded(HttpSessionBindingEvent se)
	{

		Map map = (Map) se.getSession().getServletContext().getAttribute("map");
		ServletContext context = se.getSession().getServletContext();
		if (map == null)
		{
			map = new HashMap();
			context.setAttribute("map", map);
		}

		Object object = se.getValue();
		if (object instanceof User)
		{
			User user = (User) object;
			// 如果当前是教师的话就把它加到教师在线列表集合中
			List<User> teas = null;
			boolean istea = false;
			Set<Role> roles = user.getRoles();
			if (roles != null)
			{
				for (Role role : roles)
				{
					if (role.getName().equals("教师"))
					{

						istea = true;
						break;
					}
				}
			}

			if (istea)// if it is teacher
			{
				teas = (List<User>) context.getAttribute("onlineteas");
				if (teas == null)
				{
					teas = new LinkedList<User>();
				}

				teas.add(user);
				// set the new data to the domain
				context.setAttribute("onlineteas", teas);
			}

			map.put(user.getUsername(), se.getSession());
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se)
	{
		// 如果session中remove的是教师就把它冲在线列表中移除
		Map map = (Map) se.getSession().getServletContext().getAttribute("map");
		ServletContext context = se.getSession().getServletContext();
		if (map == null)
		{
			return;
		}
		Object object = se.getValue();
		if (object instanceof User)
		{
			User user = (User) object;
			// 如果当前是教师的话就把它加到教师在线列表集合中
			List<User> teas = null;
			boolean istea = false;
			Set<Role> roles = user.getRoles();
			if (roles != null)
			{
				for (Role role : roles)
				{
					if (role.getName().equals("教师"))
					{

						istea = true;
						break;
					}
				}
			}

			if (istea)// if it is teacher
			{
				teas = (List<User>) context.getAttribute("onlineteas");
				if (teas == null)
				{
					return;
				}

				teas.remove(user);// 移除教师
				// set the new data to the domain
				context.setAttribute("onlineteas", teas);
			}
			map.remove(user.getUsername());// 在线列表中移除用户
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent se)
	{
		// TODO Auto-generated method stub

	}

}
