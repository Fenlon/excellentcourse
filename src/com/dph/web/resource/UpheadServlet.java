package com.dph.web.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpheadServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		try
		{
			DiskFileItemFactory factory = new DiskFileItemFactory();

			factory.setSizeThreshold(1024 * 1024);
			factory.setRepository(new File(this.getServletContext()
					.getRealPath("/temp")));

			ServletFileUpload upload = new ServletFileUpload(factory);

			if (!upload.isMultipartContent(request))
			{// 传统方式处理表单
				request.getParameter("username");
				return;
			}
			upload.setFileSizeMax(1024 * 1024 * 5);
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list)
			{
				if (item.isFormField())
				{
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					// value=new String(value.getBytes("ios8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				} else
				{
					// 表示当前处理的是上传文件
					String filename = item.getName().substring(
							item.getName().lastIndexOf("\\") + 1);

					// 判断上传文件名是否为空
					if (filename == null || filename.trim().equals(""))
					{
						continue;
					}

					String ext = filename.substring(filename.lastIndexOf("."));
					/*
					 * if(!types.contains(ext)){ request.setAttribute("message",
					 * "本系统不支持"+ext+"这种类型文件上传！");
					 * request.getRequestDispatcher("/message.jsp"
					 * ).forward(request, response); return; }
					 */

					InputStream in = item.getInputStream();
					int len = 0;
					byte buffer[] = new byte[1024];
					// String
					// savePath=this.getServletContext().getRealPath("/WEB-INF/upload");
					String saveFileName = generateFilename(filename);
					String savepath = generateSavePath(this.getServletContext()
							.getRealPath("/image/UserPhoto"), saveFileName);
					FileOutputStream out = new FileOutputStream(savepath
							+ File.separator + saveFileName);
					//
					String path1 = savepath.substring(64);
					request.getSession().setAttribute("image",
							path1 + File.separator + saveFileName);

					while ((len = in.read(buffer)) > 0)
					{
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();// 删除临时文件
				}
			}

		} catch (FileUploadBase.FileSizeLimitExceededException e)
		{
			request.setAttribute("message", "文件大小不能超过5M");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		} catch (FileUploadException e)
		{

			throw new RuntimeException(e);
		}

		request.setAttribute("message", "上传成功");
		request.getRequestDispatcher("/upload.jsp").forward(request, response);
	}

	// 得到多重目录
	public String generateSavePath(String path, String filename)
	{
		int hascode = filename.hashCode();
		int dir1 = hascode & 15;
		int dir2 = (hascode >> 4) & 0xf;
		String savepath = path + File.separator + dir1 + File.separator + dir2;
		File file = new File(savepath);
		if (!file.exists())
		{
			file.mkdirs();
		}

		return savepath;
	}

	// 得到唯一的文件名
	public String generateFilename(String filename)
	{
		return UUID.randomUUID().toString() + "_" + filename;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

}
