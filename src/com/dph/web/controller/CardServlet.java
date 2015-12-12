package com.dph.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dph.domain.BBSMsg;
import com.dph.domain.Card;
import com.dph.domain.Forum;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.exception.SecurityException;
import com.dph.service.BBSMsgBussinessService;
import com.dph.service.CardBusinessService;
import com.dph.service.ForumService;
import com.dph.service.ServiceFactory;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.BBSMsgBusinessServiceImpl;
import com.dph.service.impl.ForumServiceImpl;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.WebUtils;
import com.dph.web.formBean.BBSNewerContentForm;

public class CardServlet extends HttpServlet
{
	private CardBusinessService businessService = ServiceFactory.getInstance()
			.createService(CardBusinessService.class);
	private UserBusinessService userBusinessService = new UserBusinessServiceImpl();
	private BBSMsgBussinessService bbsMsgBussinessService = ServiceFactory
			.getInstance().createService(BBSMsgBussinessService.class);
	private boolean isdownmsgcount = true;// 判断是不是冲消息页面调过来的额，如果不是（即在刷新操作）则不处理数据
	private Card card = null;
	private static String oldmsgid = "";
	private static String userid = "";
	private static String remoteip = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String method = request.getParameter("method");
		if ("forCardContent".equals(method))
		{
			// 更新查看次数在gotodetails函数中
			gotoCardDetial(request, response);
			return;
		}
		if ("delete".equals(method))
		{
			delete(request, response);
			return;
		}
		if ("deleteHuiFu".equals(method))
		{
			deleteHuiFu(request, response);
			return;
		}

	}

	private void deleteHuiFu(HttpServletRequest request,
			HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			String cid = request.getParameter("cid");
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			bbsMsgBussinessService.deleteBBSMsg(id);
			response.sendRedirect(request.getContextPath()
					+ "/servlet/CardServlet?method=forCardContent&id=" + cid
					+ "&currentpage=" + info.getCurrentpage() + "&pagesize="
					+ info.getPagesize());

		} catch (Exception e)
		{
			if (e.getCause() instanceof SecurityException)
			{
				message = e.getCause().getMessage();
				e.printStackTrace();
				WebUtils.solveEx_forward(request, response, message,
						"/WEB-INF/jsp/message.jsp");
			} else
			{
				message = "删除回复失败!";
				e.printStackTrace();
				WebUtils.solveEx_forward(request, response, message,
						"/WEB-INF/jsp/message.jsp");
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			String forumid = request.getParameter("forumid");
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			businessService.deleteCard(id);
			response.sendRedirect(request.getContextPath()
					+ "/servlet/BBSUIServlet?method=forForumUI&forumid="
					+ forumid + "&currentpage=" + info.getCurrentpage()
					+ "&pagesize=" + info.getPagesize());

		} catch (Exception e)
		{
			if (e.getCause() instanceof SecurityException)
			{
				message = e.getCause().getMessage();
				e.printStackTrace();
				WebUtils.solveEx_forward(request, response, message,
						"/WEB-INF/jsp/message.jsp");
			} else
			{
				message = "删除主题失败!";
				e.printStackTrace();
				WebUtils.solveEx_forward(request, response, message,
						"/WEB-INF/jsp/message.jsp");
			}
		}
	}

	private void answer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		User user = (User) request.getSession().getAttribute("user");

		if (judgeObjectIsNull(user, User.class, request, response))
		{
			return;
		}

		try
		{
			String answerContent = request.getParameter("answer");
			String parentmsgid = request.getParameter("parentid");
			BBSMsg msg = new BBSMsg();
			msg.setContent(answerContent);
			msg.setId(UUID.randomUUID().toString());
			msg.setTime(new Date());
			msg.setUser(user);
			msg.setCard(card);
			msg.setIsread(false);
			BBSMsg parMsg = bbsMsgBussinessService.findBbsMsg(parentmsgid);
			msg.setParentmsg(parMsg);
			// card的改变
			card.setAnswernum(card.getAnswernum() + 1);// 更新回复数
			card.setLastuser(user);// 跟新最后回复人姓名
			card.setTimelast(new Date());// 更新最后回复时间

			// 下面的要放在一个事物中
			// JdbcUtils.startTransaction();
			String autherId = null;
			String name = null;// 保存下面的所需要的寻找id需要的额username
			if (parentmsgid == null || parentmsgid == "")
			{
				name = card.getAuther().getUsername();
				msg.setParentusername(name);
				autherId = userBusinessService.getIdByName(name);
			} else
			{
				name = parMsg.getUser().getUsername();
				msg.setParentusername(name);
				autherId = userBusinessService.getIdByName(name);
			}
			bbsMsgBussinessService.addBBSMsg(msg);
			businessService.update(card);
			businessService.updateNum("answernum", card.getAnswernum() + 1,
					card.getId(), Long.class);
			// 更新被回复之人的信息数目
			User auther = userBusinessService.findUser(autherId);// 被回复作者
			userBusinessService.update("msgcount", auther.getMsgcount() + 1,
					auther.getId(), Integer.class);
			// JdbcUtils.commitTransaction();

			response.sendRedirect(request.getContextPath()
					+ "/servlet/CardServlet?method=forCardContent&id="
					+ card.getId() + "");
			// request.getRequestDispatcher("").forward(request, response);
		} catch (DaoException e)
		{
			request.setAttribute("message",
					"<font color='red'><b>服务器数据库层出错了！</b> </font>");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
			e.printStackTrace();
		} finally
		{
			// JdbcUtils.closeConnection();
		}
	}

	private boolean judgeObjectIsNull(Object o, Class<?> clazz,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		if (o != null)
		{
			return false;
		}
		String name = clazz.getSimpleName();
		if ("User".equals(name))
		{
			request.setAttribute("message",
					"<font color='red'><b>您还未登录！</b> </font>");
		} else if ("Card".equals(name))
		{

			request.setAttribute("message",
					"<font color='red'><b>服务器出错了！</b> </font>");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
				request, response);
		return true;
	}

	private void gotoCardDetial(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String id = request.getParameter("id");
			String msgid = request.getParameter("msgid");
			// 不在刷新时候才增加浏览数
			if (!oldmsgid.equals(msgid))
			{
				isdownmsgcount = false;
			}
			if (oldmsgid == "")
			{
				isdownmsgcount = true;
			}
			System.out.println(isdownmsgcount);
			// 得到相应的想要查询信息的参数 封装在info中
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			// 为card初始化,就是上面的全局的card应为进入单个帖子，后面的回复什么的都是针对此贴，所以可以共享对象
			card = businessService.findCard(id);

			if (judgeObjectIsNull(card, Card.class, request, response))
			{
				return;
			}

			// 这一个是为了下面的if语句中用的，应为到帖子细节时候可能要更改数据库中的信息，需要次对象，所以创建
			User user = WebUtils
					.getBeanFromSession(request, "user", User.class);
			// 只有当从消息中进入时候才会执行下面if中的代码，应为其他情况没有带着msgid过来
			// ，点击查看消息，时候要修改数据库并且在一个事物中

			if (msgid != null && user != null && isdownmsgcount)
			{
				// JdbcUtils.startTransaction();
				if (user.getMsgcount() <= 0)
				{
					userBusinessService.update("msgcount", 0, user.getId(),
							Integer.class);
					user.setMsgcount(0);
				} else
				{
					userBusinessService
							.update("msgcount", user.getMsgcount() - 1,
									user.getId(), Integer.class);
					user.setMsgcount(user.getMsgcount() - 1);
				}

				bbsMsgBussinessService.updateIsRead(msgid);
				// 重新更改Session中的user对象

				request.getSession(false).setAttribute("user", user);
				// JdbcUtils.commitTransaction();
				oldmsgid = msgid;
			}
			// 应为上面有的情况不带msgid过来，就会存在array大小的问题，所以就直接加上一个无用的数据

			// JdbcUtils.startTransaction();
			PageBean pageBean = businessService.pageQueryMsgs(info,
					card.getId());
			// 更新查看次数
			if (user != null && !userid.equals(user.getId()))
			{
				businessService.updateNum("browsenum", card.getBrowsenum() + 1,
						card.getId(), Long.class);
				userid = user.getId();
			}

			if (user == null)
			{
				String ip = request.getRemoteAddr();
				if (!remoteip.equals(ip))
				{
					businessService.updateNum("browsenum",
							card.getBrowsenum() + 1, card.getId(), Long.class);
				}
			}

			// JdbcUtils.commitTransaction();
			if (card.getForum().getForumname().equals("新人报到".trim()))
			{
				String str = card.getContent();
				String[] s = str.split("-");
				String name = s[0];
				String college = s[1];
				String motto = s[2];
				String content = s[3];
				request.setAttribute("name", name);
				request.setAttribute("motto", motto);
				request.setAttribute("college", college);
				request.setAttribute("content", content);
			}

			request.setAttribute("c", card);
			request.setAttribute("pagebean", pageBean);
			request.getRequestDispatcher("/WEB-INF/jsp/bbs/card.jsp").forward(
					request, response);
			return;
		} catch (DaoException e)
		{
			e.printStackTrace();
			request.setAttribute("message",
					"<font color='red'><b>服务器数据库层出错了！</b> </font>");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	private void upCard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String forumid = request.getParameter("forumid");
		if ("".equals(forumid))
		{
			return;
		}
		try
		{
			HttpSession session = request.getSession(false);
			if (session != null)
			{
				User user = (User) session.getAttribute("user");
				if (judgeObjectIsNull(user, User.class, request, response))
				{
					return;
				}
				// 下面说明user不为空
				// 更具Id得到forum 对象 然后判断是普通的还是新人报到，应为形式不一样
				ForumService forumService = new ForumServiceImpl();
				Forum forum = forumService.find(Integer.parseInt(forumid));
				Card card = new Card();
				if (!forum.getForumname().equals("新人报到".trim()))
				{
					// 不是新人报到
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					card.setTitle(title);
					card.setContent(content);
				} else
				{
					BBSNewerContentForm form = WebUtils.request2Bean(request,
							BBSNewerContentForm.class);
					card.setContent(form.getName() + "-" + form.getCollege()
							+ "-" + form.getMotto() + "-" + form.getContent());
					card.setTitle(form.getTitle());
				}

				card.setAuther(user);

				card.setId(WebUtils.generateId());
				card.setLastuser(user);

				Date time = new Date(System.currentTimeMillis());
				card.setTimecreate(time);
				card.setTimelast(time);

				card.setForum(forum);
				businessService.addCard(card);

			}

			request.setAttribute("message",
					"<font color='red'><b>发帖成功！</b> </font>");

		} catch (DaoException e)
		{

			request.setAttribute("message",
					"<font color='red'><b>服务器数据库层出错了！</b> </font>");
			e.printStackTrace();
		} finally
		{
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String method = request.getParameter("method");
		if ("upCard".equals(method))
		{
			upCard(request, response);
			return;
		}
		if ("answer".equals(method))
		{
			answer(request, response);
			return;
		}
	}
}
