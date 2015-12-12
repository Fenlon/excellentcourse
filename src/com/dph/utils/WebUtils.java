package com.dph.utils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dph.domain.Upfile;
import com.dph.domain.User;

public class WebUtils
{
	// T为泛型（避免了返回值单一问题或者调用者需要强转问题，传过来什么类型，返回什么类型） 这里面还用到了反射机制
	public static <T> T request2Bean(HttpServletRequest request,
			Class<T> beanClass)
	{
		try
		{
			T bean = beanClass.newInstance();
			Enumeration e = request.getParameterNames();// 得到所有请求参数名，分装在枚举中
			while (e.hasMoreElements())
			{
				String name = (String) e.nextElement();// 依次拿出参数名
				String value = request.getParameter(name);// 得到参数名对应的值
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;

			/*
			 * T bean = beanClass.newInstance(); //�õ�request����������� Map map
			 * = request.getParameterMap();
			 * //map{name=aa,password=bb,birthday=1990-09-09}
			 * bean(name=aa,password=dd,birthday=Date)
			 * 
			 * ConvertUtils.register(new Converter(){
			 * 
			 * public Object convert(Class type, Object value) {
			 * if(value==null){ return null; } String str = (String) value;
			 * if(str.trim().equals("")){ return null; }
			 * 
			 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); try {
			 * return df.parse(str); } catch (ParseException e) { throw new
			 * RuntimeException(e); } } }, Date.class); BeanUtils.populate(bean,
			 * map); return bean;
			 */
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void copyBean(Object src, Object dest)
	{

		try
		{
			ConvertUtils.register(new Converter()
			{
				@Override
				// 注册日期转换器 因为原来formbean中为 String类型 而在User中是Date类型，所以需要转换
				public Object convert(Class type, Object value)
				{
					if (value == null)
					{
						return null;
					}

					String str = value.toString();
					if (str.trim().equals(""))
					{
						return null;
					}
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					try
					{
						return dateFormat.parse(str);
					} catch (ParseException e)
					{
						throw new RuntimeException(e);
					}
				}
			}, Date.class);
			BeanUtils.copyProperties(dest, src);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	// 产生全球唯一的id 因为用UUID
	public static String generateId()
	{
		return UUID.randomUUID().toString();
	}

	/***
	 * @param request
	 *            封装客服带过来的请求信息的对象
	 * @param attr
	 *            想要获取Session域中那个属性的值
	 * @param clazz
	 *            想要返回值类型
	 * @return
	 */
	public static <T> T getBeanFromSession(HttpServletRequest request,
			String attr, Class<T> clazz)
	{
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			return null;
		}
		T obj = (T) session.getAttribute(attr);
		return obj;
	}

	public void solveEx_SendR(HttpServletRequest request,
			HttpServletResponse response, String message, String url)
	{
		request.setAttribute("message", message);
		response.setHeader("refresh", "3;url=" + request.getContextPath() + url);
		try
		{
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void solveEx_forward(HttpServletRequest request,
			HttpServletResponse response, String message, String url)
	{
		request.setAttribute("message", message);
		try
		{
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static Upfile doUpload(HttpServletRequest request, String uppath,
			User user) throws FileSizeLimitExceededException
	{

		Upfile bean = new Upfile();
		try
		{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(request.getSession()
					.getServletContext().getRealPath("/WEB-INF/temp")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			upload.setFileSizeMax(1000 * 1000 * 1000 * 2);// 最大上传文件2G

			List<FileItem> list = upload.parseRequest(request);
			String nickname = "";
			for (FileItem item : list)
			{
				if (item.isFormField())
				{
					String name = item.getFieldName(); // username=aaa
														// description=bbb
					if (name.equals("filename"))
					{
						nickname = item.getString("UTF-8");
					} else
					{
						String value = item.getString("UTF-8");
						BeanUtils.setProperty(bean, name, value);
					}
				} else
				{
					// 得到上传文件名
					String filename = "";
					if (nickname == null || nickname.equals(""))
					{
						filename = item.getName().substring(
								item.getName().lastIndexOf("\\") + 1);
					} else
					{
						filename = item.getName().substring(
								item.getName().lastIndexOf("\\") + 1);
						filename = nickname
								+ filename.substring(filename.lastIndexOf("."));
					}

					// 得到文件上传的类型
					String type = item.getName().substring(
							item.getName().lastIndexOf(".") + 1);
					// 得到上传文件的UUID
					String uuidname = generateFilename(filename);
					// 的到存储路径
					String savepath = generateSavePath(uppath, uuidname);

					InputStream in = item.getInputStream();
					int len = 0;
					byte buffer[] = new byte[1024];
					FileOutputStream out = new FileOutputStream(savepath
							+ File.separator + uuidname);

					while ((len = in.read(buffer)) > 0)
					{
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();

					bean.setUsername(user.getUsername());
					bean.setFilename(filename);
					bean.setId(UUID.randomUUID().toString());
					bean.setSavepath(savepath);
					bean.setUptime(new Date());
					bean.setUuidname(uuidname);
					bean.setType(type);
				}
			}
			return bean;
		} catch (FileUploadBase.FileSizeLimitExceededException e)
		{
			throw e;
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private static String generateFilename(String filename)
	{
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		return UUID.randomUUID().toString() + "." + ext;
	}

	private static String generateSavePath(String path, String filename)
	{
		int hashcode = filename.hashCode(); // 121221
		int dir1 = hashcode & 15;
		int dir2 = (hashcode >> 4) & 0xf;

		String savepath = path + File.separator + dir1 + File.separator + dir2;
		// System.out.print(savepath);
		File file = new File(savepath);
		if (!file.exists())
		{
			file.mkdirs();
		}
		return savepath;
	}
}
