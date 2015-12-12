package com.dph.web.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dph.constant.Constant;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.WebUtils;

public class upHeadServlet extends HttpServlet
{
	private User user = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// List types=Arrays.asList(".jpg",".gif",".avi",".txt");

		try
		{
			user = WebUtils.getBeanFromSession(request, "user", User.class);

			if (user == null)
			{
				WebUtils.solveEx_forward(request, response, Constant.NoLogin,
						Constant.ERRORPAGE);
				return;
			}
			
			String method = request.getParameter("method");
			if ("forupheadUI".equals(method))
			{
				request.getRequestDispatcher("/WEB-INF/jsp/user/uphead.jsp")
						.forward(request, response);
				return;
			}

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 缓冲大小
			factory.setSizeThreshold(1024 * 1024);
			factory.setRepository(new File(this.getServletContext()
					.getRealPath("/temp")));

			ServletFileUpload upload = new ServletFileUpload(factory);

			/*
			 * upload.setProgressListener(new ProgressListener() {
			 * 
			 * @Override public void update(long arg0, long arg1, int arg2) { //
			 * TODO Auto-generated method stub
			 * System.out.println("当前已解析："+arg0); } });
			 */
			if (!upload.isMultipartContent(request))
			{// 传统方式处理表单
				request.getParameter("username");
				return;
			}
			upload.setFileSizeMax(1024 * 1024 * 20);
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list)
			{

				if (item.isFormField())
				{
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					// value=new String(value.getBytes("ios8859-1"),"UTF-8");
					// System.out.println(name + "=" + value);
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

					InputStream in = item.getInputStream();
					int len = 0;
					byte buffer[] = new byte[1024];
					// String
					// savePath=this.getServletContext().getRealPath("/WEB-INF/upload");
					// String saveFileName=generateFilename(filename);
					// 保存文件后缀
					String formatName = filename.substring(filename
							.lastIndexOf(".") + 1);/**/
					String saveFileName = generateFilename(formatName);

					String path = generateSavePath(saveFileName);
					// 保存上传的源文件，然后截图后删除
					String savepath = this.getServletContext().getRealPath(
							"images/photos" + File.separator + "srcimg/");
					File file = new File(savepath);
					if (!file.exists())
					{
						file.mkdir();
					}
					FileOutputStream out = new FileOutputStream(savepath
							+ File.separator + saveFileName);
					while ((len = in.read(buffer)) > 0)
					{
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();// 删除临时文件

					File imagefile = new File(this.getServletContext()
							.getRealPath("/images/photos")
							+ File.separator
							+ path);
					if (!imagefile.exists())
					{
						imagefile.mkdirs();
					}
					String cutpath = this.getServletContext().getRealPath(
							"images/photos")
							+ File.separator
							+ path
							+ File.separator
							+ saveFileName;
					System.out.println(cutpath);
					// 将文件路径保存到数据库中
					savetoUserDB(request, response, cutpath);
					imagefile = new File(savepath + File.separator
							+ saveFileName);
					/**/
					int x1 = (int) Double.parseDouble(request
							.getParameter("x1"));
					int x2 = (int) Double.parseDouble(request
							.getParameter("x2"));
					int y1 = (int) Double.parseDouble(request
							.getParameter("y1"));
					int y2 = (int) Double.parseDouble(request
							.getParameter("y2"));
					int width = (int) Double.parseDouble(request
							.getParameter("width"));
					int height = (int) Double.parseDouble(request
							.getParameter("height"));
					int x = Math.min(x1, x2);
					int y = Math.min(y1, y2);

					cutImg(imagefile, cutpath, formatName, x, y, width, height);
					imagefile.delete();
				}
			}

		} catch (FileUploadBase.FileSizeLimitExceededException e)
		{
			request.setAttribute("message", "文件大小不能超过20M");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} catch (FileUploadException e)
		{
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new RuntimeException(e);
		}

		request.setAttribute("message", "上传成功");
		request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
				request, response);
	}

	private void savetoUserDB(HttpServletRequest request,
			HttpServletResponse response, String cutpath)
	{
		try
		{
			int index = cutpath.indexOf("\\images");
			String path = cutpath.substring(index);
			String path2 = request.getContextPath() + path;

			path2 = path2.replace('\\', '/');
			System.out.println(path2);
			UserBusinessService service = new UserBusinessServiceImpl();
			service.updatephoto(user.getId(), path2);
		} catch (DaoException e)
		{
			e.printStackTrace();
			WebUtils.solveEx_forward(request, response, Constant.DaoError,
					Constant.ERRORPAGE);
		}
	}

	// 得到唯一的文件名
	public String generateFilename(String filetype)
	{
		// String str = UUID.randomUUID().toString() + "_" + filename;
		String str = user.getUsername() + "." + filetype;
		System.out.println(str);
		return str;
	}

	// 得到多重目录
	public String generateSavePath(String filename)
	{
		int hascode = filename.hashCode();
		int dir1 = hascode & 15;
		int dir2 = (hascode >> 4) & 0xf;
		String savepath = dir1 + File.separator + dir2;
		// File file=new File(savepath);

		return savepath;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doGet(request, response);
	}

	public void cutImg(File file, String file2path, String formatName, int x,
			int y, int w, int h)
	{
		BufferedImage img;
		try
		{
			img = (BufferedImage) ImageIO.read(file);
			BufferedImage imagecut = img.getSubimage(x, y, w, h);
			String realfilename = file2path.substring(0,
					file2path.lastIndexOf("."));
			String filename1 = realfilename + "_small." + formatName;
			String filename2 = realfilename + "_middle." + formatName;

			resetImageSize(filename1, imagecut, formatName, Constant.sizesmall);
			resetImageSize(filename2, imagecut, formatName, Constant.sizemiddle);

		} catch (IOException e)
		{
			img = null;
			e.printStackTrace();
		}

	}

	private void resetImageSize(String filename, BufferedImage imagesrc,
			String FileType, int size)
	{

		FileOutputStream fo = null;
		try
		{
			fo = new FileOutputStream(filename);
			double width = imagesrc.getWidth();
			double height = imagesrc.getHeight();
			double percent = size / width;
			int newWidth = (int) (width * percent);
			int newHeight = (int) (height * percent);
			BufferedImage image = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_BGR);
			Graphics graphics = image.createGraphics();
			graphics.drawImage(imagesrc, 0, 0, newWidth, newHeight, null);
			ImageIO.write(image, FileType, fo);
			fo.flush();
			fo.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (fo != null)
					fo.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

}
