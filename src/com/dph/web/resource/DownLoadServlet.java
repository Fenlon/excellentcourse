package com.dph.web.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dph.domain.QueryInfo;
import com.dph.domain.Upfile;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.exception.SecurityException;
import com.dph.service.ServiceFactory;
import com.dph.service.UpfileService;
import com.dph.utils.WebUtils;

public class DownLoadServlet extends HttpServlet
{
	private UpfileService service = ServiceFactory.getInstance().createService(
			UpfileService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		User user = WebUtils.getBeanFromSession(request, "user", User.class);
		if (user == null)
		{
			WebUtils.solveEx_forward(request, response, "登陆后才能进行此操作!",
					"/WEB-INF/jsp/message.jsp");
			return;
		}
		String method = request.getParameter("method");

		if ("download".equals(method))
		{
			download(request, response);
			return;
		}

		if ("delete".equals(method))
		{
			delete(request, response);
			return;
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		String message = "";
		try
		{
			String id = request.getParameter("id");
			QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
			service.deleteFile(id);
			message = "删除资源成功!";
			response.sendRedirect(request.getContextPath()
					+ "/servlet/ListFileServlet?currentpage="
					+ info.getCurrentpage() + "&pagesize=" + info.getPagesize());
		} catch (Exception e)
		{
			if (e.getCause() instanceof SecurityException)
			{
				message = e.getCause().getMessage();
			} else
				message = "删除资源失败!";
			e.printStackTrace();
			WebUtils.solveEx_forward(request, response, message,
					"/WEB-INF/jsp/message.jsp");
		}
	}

	private void download(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String id = request.getParameter("id");
			Upfile upfile = service.findUpfile(id);
			File file = new File(upfile.getSavepath() + "\\"
					+ upfile.getUuidname());

			if (!file.exists())
			{
				WebUtils.solveEx_forward(request, response, "抱歉，源文件已被删除",
						"/WEB-INF/jsp/message.jsp");
				return;
			}

			response.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode(upfile.getFilename(), "UTF-8"));
			FileInputStream in = new FileInputStream(file);
			int len = 0;
			byte buffer[] = new byte[1024];
			OutputStream out = response.getOutputStream();
			while ((len = in.read(buffer)) > 0)
			{
				out.write(buffer, 0, len);
			}
			in.close();
		} catch (DaoException e)
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
