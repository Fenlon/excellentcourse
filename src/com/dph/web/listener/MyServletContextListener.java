package com.dph.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dph.chat.ChatServer;

public class MyServletContextListener implements ServletContextListener
{

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		// 启动聊天服务

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { new ChatServer().start(); } }).start();
		 */
	}

}
