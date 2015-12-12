package com.dph.chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateFormatUtils;

import com.dph.constant.Constant;
import com.dph.domain.Chat;
import com.dph.domain.User;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.WebUtils;

public class ChatServlet extends HttpServlet
{
	private ServletContext context;
	private UserBusinessService userservice = new UserBusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String method = request.getParameter("method");

		if ("chat".equals(method))
		{
			chat(request, response);
			return;
		}
		if ("closechat".equals(method))
		{
			closechat(request, response);
			return;
		}
		if ("checkIsHasMsg".equals(method))
		{// 前端ajax请求 查看是否有消息（仅限教师）
			checkIsHasMsg(request, response);
			return;
		}
		if ("sendmsg".equals(method))
		{
			sendMsg(request, response);
			return;
		}
		if ("receivemsg".equals(method))
		{
			receivemsg(request, response);
			return;
		}
		/*
		 * if ("login".equals(method)) { login(request, response); return; } if
		 * ("forchatpage".equals(method)) { login(request, response); return; }
		 */
		if ("openchat".equals(method))
		{
			openchat(request, response);
			return;
		}
	}

	private void openchat(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String key = request.getParameter("key");
		String toname = request.getParameter("toname");
		Chat c = new Chat();
		User user = WebUtils.getBeanFromSession(request, "user", User.class);
		context = request.getServletContext();
		List<Chat> chats = (List<Chat>) context
				.getAttribute(user.getUsername());
		if (chats != null)
		{
			for (Chat chat : chats)
			{
				if (chat.getChatkey().equals(key))
				{
					chat.setIschat(true);
					break;
				}
			}
		}

		c.setChatkey(key);
		c.setFromuname(user.getUsername());
		c.setFromuip(request.getRemoteAddr());
		c.setTouname(toname);
		request.setAttribute("c", c);
		request.getRequestDispatcher("/WEB-INF/jsp/chat/chat.jsp").forward(
				request, response);

	}

	private void closechat(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		String key = request.getParameter("key");
		System.out.println(key + "--------");
		context = request.getServletContext();
		context.removeAttribute(key);
		User user = WebUtils.getBeanFromSession(request, "user", User.class);
		// 移除关闭者的chats里面的chat
		List<Chat> chats = null;
		if (user != null)
		{
			chats = (List<Chat>) context.getAttribute(user.getUsername());
		}

		if (chats != null)
		{
			System.out.println("not null---" + chats.size());
			for (int i = 0; i < chats.size(); i++)
			{
				if (chats.get(i).getChatkey().equals(key))
				{
					chats.remove(i);
					System.out.println(key);
				}
			}
			System.out.println("--" + chats.size());
		}
		JSONObject ms = new JSONObject();
		ms.put("msg", user.getUsername());
		response.getWriter().print(ms);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String name = request.getParameter("name");
		context = request.getServletContext();
		List<String> chats = (List<String>) context.getAttribute(name);
		request.setAttribute("chats", chats);
		request.getRequestDispatcher("/chat/chat.jsp").forward(request,
				response);
	}

	private void receivemsg(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		String key = request.getParameter("key");
		context = request.getServletContext();
		List<String> msgs = (List<String>) context.getAttribute(key);
		JSONObject ms = new JSONObject();
		ms.put("msgs", msgs);
		response.getWriter().print(ms);
	}

	// 教师登录后ajax请求检查是不是有消息
	private void checkIsHasMsg(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		String username = request.getParameter("name");
		context = request.getServletContext();
		List<String> chats = (List<String>) context.getAttribute(username);

		JSONObject ms = new JSONObject();
		if (chats == null || chats.size() == 0)
		{
			ms.put("data", "null");
		} else
		{
			ms.put("data", chats);
		}
		response.getWriter().print(ms);
	}

	private void sendMsg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		context = request.getServletContext();
		String str = request.getParameter("msg");
		String msg = URLDecoder.decode(str, "utf-8");
		String key = request.getParameter("key");
		String fname = request.getParameter("fname");
		System.out.println(fname + "----");
		String toname = request.getParameter("toname");
		// 检查toname是不是的聊天列表中是否还有此key
		System.out.println(toname + "+++");

		User user = WebUtils.getBeanFromSession(request, "user", User.class);
		List<Chat> chats = null;
		String chatanme = "";
		if (user.getUsername().equals(fname))
		{
			chats = (List<Chat>) context.getAttribute(toname);
			chatanme = toname;
		} else
		{
			chats = (List<Chat>) context.getAttribute(fname);
			chatanme = fname;
		}

		boolean isexist = false;
		if (chats == null)
		{
			chats = new LinkedList<Chat>();
		} else
		{
			for (Chat chat : chats)
			{
				if (chat.getChatkey().equals(key))
				{
					isexist = true;
					break;
				}
			}
		}
		if (!isexist)
		{
			Chat c = new Chat();
			c.setChatkey(key);
			c.setFromuname(fname);
			c.setTouname(toname);
			c.setIschat(false);
			chats.add(c);
			context.setAttribute(chatanme, chats);
		}
		List<String> msgs = (List) context.getAttribute(key);
		if (msgs == null)
		{
			msgs = new ArrayList<String>();
		}
		String m = "&nbsp;" + fname + ": "
				+ DateFormatUtils.format(new Date(), "hh:mm:ss") + "</br>"
				+ msg + "</br>";
		msgs.add(m);
		// 下面可以判断数据大小，然后如果数据量很大的话就持久化到数据库中，在这里我就不做了
		context.setAttribute(key, msgs);
	}

	private void chat(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		try
		{
			System.out.println("ok");
			String name = request.getParameter("name");
			// 1:更具姓名找到教师
			// User teacher = userservice.find(name);
			// 通知教师 和用户 双向通知
			User user = WebUtils
					.getBeanFromSession(request, "user", User.class);
			if (user == null)
			{
				WebUtils.solveEx_forward(request, response, Constant.NoLogin,
						Constant.ERRORPAGE);
				return;
			}

			// 创建一个chat实体
			Chat c = new Chat();
			String key = UUID.randomUUID().toString();
			c.setChatkey(key);// 标识
			c.setFromuip(request.getRemoteAddr());
			c.setFromuname(user.getUsername());
			c.setTouname(name);
			c.setIschat(false);

			context = request.getServletContext();
			// 创建标识
			// 返回标识（其实就是application（attribute））
			// 1:通知对方 教师
			List<Chat> tchats = (List<Chat>) context.getAttribute(name);
			if (tchats == null)
			{
				tchats = new LinkedList<Chat>();
			}
			tchats.add(c);
			// 2:自己的聊天集合
			List<Chat> uchats = (List<Chat>) context.getAttribute(user
					.getUsername());
			if (uchats == null)
			{
				uchats = new LinkedList<Chat>();
			}
			Chat c2 = new Chat();
			c2.setChatkey(key);// 标识
			c2.setFromuip(request.getRemoteAddr());
			c2.setFromuname(user.getUsername());
			c2.setTouname(name);
			c2.setIschat(false);
			uchats.add(c2);
			// 将聊天的实体添加到集合中
			context.setAttribute(name, tchats);
			context.setAttribute(user.getUsername(), uchats);
			// 转发到聊天页面 带着聊天实体c
			request.setAttribute("c", c);
			response.sendRedirect(request.getContextPath()
					+ "/servlet/ChatServlet?method=openchat&toname="
					+ c.getTouname() + "&key=" + key);

			/*
			 * request.getRequestDispatcher("/chat/chat2.jsp").forward( request,
			 * response);
			 */
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

}
