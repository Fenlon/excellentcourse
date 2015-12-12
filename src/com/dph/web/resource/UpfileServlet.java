package com.dph.web.resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dph.constant.Constant;
import com.dph.domain.Upfile;
import com.dph.domain.User;
import com.dph.exception.SecurityException;
import com.dph.service.ServiceFactory;
import com.dph.service.UpfileService;
import com.dph.utils.WebUtils;

public class UpfileServlet extends HttpServlet
{
	private UpfileService service = ServiceFactory.getInstance().createService(
			UpfileService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		User user = WebUtils.getBeanFromSession(request, "user", User.class);
		if (user == null)
		{
			new WebUtils().solveEx_SendR(request, response, "登录后才能进行此操作！请先登录!",
					"/index.jsp");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/jsp/resource/addfile.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String message = "";
		User user = WebUtils.getBeanFromSession(request, "user", User.class);
		if (user == null)
		{
			new WebUtils().solveEx_SendR(request, response, "登录后才能进行此操作！请先登录!",
					"/index.jsp");
			return;
		}
		if (!ServletFileUpload.isMultipartContent(request))
		{
			message = "不支持此类文件上传";
			WebUtils.solveEx_forward(request, response, message,
					Constant.ERRORPAGE);
			return;
		}
		try
		{
			String savepath = this.getServletContext().getRealPath(
					"/WEB-INF/upload");

			// String savepath = this.getServletContext().getRealPath("/image");
			Upfile upfile = WebUtils.doUpload(request, savepath, user);

			service.addUpfile(upfile);
			message = "上传成功";
		} catch (FileUploadBase.FileSizeLimitExceededException e)
		{
			message = "文件大小不鞥超过2G";
			e.printStackTrace();
		} catch (Exception e)
		{
			if (e.getCause() instanceof SecurityException)
			{
				message = e.getCause().getMessage();
			} else
				message = "上传失败";
			e.printStackTrace();
		}
		WebUtils.solveEx_forward(request, response, message, Constant.ERRORPAGE);
	}
}
