package com.dph.web.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.dph.constant.Constant;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.exception.UserHasExistException;
import com.dph.manager.Role;
import com.dph.service.CardBusinessService;
import com.dph.service.ManagerService;
import com.dph.service.MsgService;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.CardBusinessServiceImpl;
import com.dph.service.impl.ManagerServiceImpl;
import com.dph.service.impl.MsgServiceImpl;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.ServiceUtils;
import com.dph.utils.WebUtils;
import com.dph.web.formBean.RegisterForm;

public class UserServlet extends HttpServlet
{
	private RegisterForm registerForm = null;
	private UserBusinessService businessService = new UserBusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String method = request.getParameter("method");
		if ("msgcount".equals(method))
		{
			getMsgCount(request, response);
			return;
		}
		if ("forinfo".equals(method))
		{
			getInfo(request, response);
			return;
		}
		if ("isLogin".equals(method))
		{
			isLogin(request, response);
			return;
		}
		if ("ajaxlogin".equals(method))
		{
			ajaxlogin(request, response);
			return;
		}
		if ("forloginUI".equals(method))
		{
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(
					request, response);
		}
		if ("forRegisterUI".equals(method))
		{
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(
					request, response);
		}
		if ("register".equals(method))
		{
			register(request, response);
			return;
		}
		if ("login".equals(method))
		{
			login(request, response);
			return;
		}

		if ("listall".equals(method))
		{
			listAll(request, response);
			return;
		}
		if ("updateRoleUI".equals(method))
		{
			updateRoleUI(request, response);
			return;
		}
		if ("updateUserRole".equals(method))
		{
			updateUserRole(request, response);
			return;
		}
		if ("delete".equals(method))
		{
			delete(request, response);
			return;
		}

		if ("logout".equals(method))
		{
			logout(request, response);
			return;
		}
	}

	private void isLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		System.out.println("ok");
		boolean flag;
		User user = (User) request.getSession().getAttribute("user");
		JSONObject msg = new JSONObject();
		if (user == null)
		{
			flag = false;
		} else
		{
			flag = true;
			msg.put("name", user.getUsername());
		}

		msg.put("msg", flag);
		System.out.println("ppp");
		response.getWriter().print(msg);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			if (session.getAttribute("user") == null)
			{
				request.setAttribute("message",
						"<font color='red'><b>您尚未登录！</b> </font>3秒后自动跳转到首页，如果没有跳转，请点击连接手动转到首页!");
			} else
			{
				session.removeAttribute("user");

				Cookie autoLoginCookie = null;
				Cookie cookies[] = request.getCookies();
				for (int i = 0; cookies != null && i < cookies.length; i++)
				{
					if (cookies[i].getName().equals("autologin"))
					{
						autoLoginCookie = cookies[i];
					}
				}
				if (autoLoginCookie != null)
				{
					autoLoginCookie.setMaxAge(0);
					autoLoginCookie.setPath("/ExcellentCourse");
					response.addCookie(autoLoginCookie);
				}

				request.setAttribute("message",
						"<font color='red'><b>恭喜注销成功！</b> </font>3秒后自动跳转到首页，如果没有跳转，请点击连接手动转到首页!");
			}

			response.setHeader("refresh", "3;url=" + request.getContextPath()
					+ "/index.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
		return;
	}

	private void getInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String id = request.getParameter("id");
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			User user = businessService.findUser(id);
			CardBusinessService cardService = new CardBusinessServiceImpl();
			MsgService msgService = new MsgServiceImpl();
			PageBean pageBean = cardService.getCardsByUser(user.getUsername(),
					info);
			request.setAttribute("u", user);
			request.setAttribute("pagebean", pageBean);

			// 下面解决msg从user获取相关信息下面两层都需要写 getMsgsByUser(String
			// username,QueryInfo info)

			request.getRequestDispatcher("/WEB-INF/jsp/user/userinfo.jsp")
					.forward(request, response);
		} catch (DaoException e)
		{
			WebUtils.solveEx_forward(request, response, Constant.DaoError,
					Constant.ERRORPAGE);
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			businessService.delete(id);
			message = "删除用户成功!";
		} catch (DaoException e)
		{
			message = "删除用户失败!";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void updateUserRole(HttpServletRequest request,
			HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			String[] roleids = request.getParameterValues("role");
			List<Role> roles = new ArrayList<Role>();
			ManagerService managerService = new ManagerServiceImpl();
			Role r = null;
			if (roleids != null)
			{
				for (String rid : roleids)
				{
					r = managerService.findRole(rid);
					roles.add(r);
					r = null;
				}
			}
			User user = businessService.findUser(id);
			businessService.updateRole(user, roles);
			message = "更新角色成功！";
		} catch (DaoException e)
		{
			message = "更新角色失败！";
			e.printStackTrace();
		} finally
		{
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void updateRoleUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String id = request.getParameter("id");
			User user = businessService.findUser(id);
			ManagerService managerService = new ManagerServiceImpl();
			List<Role> roles = managerService.getAllRole();
			request.setAttribute("roles", roles);
			request.setAttribute("u", user);
			request.getRequestDispatcher("/manager/user/updateUserRole.jsp")
					.forward(request, response);
		} catch (DaoException e)
		{
			WebUtils.solveEx_forward(request, response, "数据库层出现错误!",
					"/WEB-INF/jsp/message.jsp");
			e.printStackTrace();
		}

	}

	private void listAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			PageBean pagebean = businessService.getPartUser(info);
			request.setAttribute("pagebean", pagebean);
			request.getRequestDispatcher("/manager/user/listuser.jsp").forward(
					request, response);
		} catch (DaoException e)
		{
			WebUtils.solveEx_forward(request, response, "数据库层出现错误！",
					"/WEB-INF/jsp/message.jsp");
			e.printStackTrace();
		}
	}

	private void ajaxlogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user;
		try
		{
			user = businessService.login(username, password);
			JSONObject msg = new JSONObject();
			boolean flag = true;
			if (user == null)
			{
				flag = false;
			} else
			{
				request.getSession().setAttribute("user", user);
				autologin(response, user);
			}
			msg.put("msg", flag);
			response.getWriter().print(msg);
			return;
		} catch (DaoException e)
		{
			e.printStackTrace();
		}
	}

	private void getMsgCount(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		try
		{
			System.out.println("ppppp");
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("user");

			if (user == null)
			{
				return;
			}
			int msgcount;
			msgcount = businessService.getData("msgcount", user.getId(),
					Integer.class);
			System.out.println(user.getUsername() + "--" + msgcount);
			user.setMsgcount(msgcount);
			session.setAttribute("user", user);
			JSONObject msg = new JSONObject();
			msg.put("msgcount", msgcount);
			response.getWriter().print(msg);
		} catch (DaoException e)
		{
			e.printStackTrace();
		}

	}

	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			registerForm = WebUtils.request2Bean(request, RegisterForm.class);
			if (!registerForm.validate())
			{
				request.setAttribute("form", registerForm);// 将分装错误信息的formBean对象加到request中
				request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
						.forward(request, response);
				return;
			}

			User user = new User();
			WebUtils.copyBean(registerForm, user);

			user.setId(UUID.randomUUID().toString());
			businessService.register(user);
			// 下面说明注册成功 转到全局消息显示页面!
			request.setAttribute("message",
					"恭喜您，注册成功!！3秒后自动跳转到首页，如果没有跳转，请点击连接手动转到首页!");
			response.setHeader("refresh", "3;url=" + request.getContextPath()
					+ "/index.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
			return;
		} catch (DaoException e)
		{
			// dao（数据库层出现问题）
			request.setAttribute("message", "访问数据库出错了!!");
			e.printStackTrace();
		} catch (UserHasExistException e)
		{
			// 4：如果service处理不成功，且不成功的原因为用户已存在，则跳转到表单注册页面回显用户已存在
			registerForm.getErrors().put("username", "注册用户已存在!");
			request.setAttribute("form", registerForm);// 将分装错误信息的formBean对象加到request中
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(
					request, response);
			e.printStackTrace();
			return;
		} catch (Exception e)
		{
			// 5:如果service处理不成功，且不成功原因为其他，则跳转到全局消息显示页面，为用户显示有好的错误消息
			request.setAttribute("message", "服务器出现未知错误!");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
				request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try
		{
			User user = businessService.login(username, password);
			if (user == null)
			{
				request.setAttribute("message", "用户名密码错误!");
				response.setHeader("refresh",
						"3;url=" + request.getContextPath()
								+ "/servlet/UserServlet?method=forloginUI");
			} else
			{
				request.getSession().setAttribute("user", user);
				autologin(response, user);
				response.sendRedirect("" + request.getContextPath()
						+ "/index.jsp");// 登录成功返回首页
				return;
			}
		} catch (DaoException e)
		{
			request.setAttribute("message", "访问数据库出现问题!!");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
				request, response);
	}

	private Cookie makeCookie(User user, long expirestime)
	{
		long currenttime = System.currentTimeMillis();
		String cookieValue = user.getUsername()
				+ ":"
				+ (currenttime + expirestime * 1000)
				+ ":"
				+ ServiceUtils.md5(user.getUsername(), user.getPassword(),
						(currenttime + expirestime * 1000));
		Cookie cookie = new Cookie("autologin", cookieValue);
		cookie.setMaxAge((int) expirestime);
		cookie.setPath("/ExcellentCourse");// 必须设置，更具这个才能找到cookie客户端的
		return cookie;
	}

	private void autologin(HttpServletResponse response, User user)
	{
		// cookie有效期30天 以秒为单位
		long expirestime = 30 * 24 * 60 * 60;
		// 给客户机发送自动登陆的 cookie
		// autologin=username:expirestime:md5(password:expirestime:username)
		Cookie cookie = makeCookie(user, expirestime);
		response.addCookie(cookie);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

}
