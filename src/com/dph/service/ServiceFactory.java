package com.dph.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Properties;

import com.dph.domain.User;
import com.dph.exception.SecurityException;
import com.dph.manager.Privilege;
import com.dph.service.impl.UserBusinessServiceImpl;
import com.dph.utils.ServiceUtils;

public class ServiceFactory
{

	/*
	 * 此类暂时没有使用，此类是使用代理+注解来进行后台管理
	 */

	private Properties prop = null;
	private static ServiceFactory instance = new ServiceFactory();

	public static ServiceFactory getInstance()
	{
		return instance;
	}

	private ServiceFactory()
	{
		try
		{
			prop = new Properties();
			InputStream inStream = this.getClass().getClassLoader()
					.getResourceAsStream("serviceconfig.properties");
			prop.load(inStream);
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public <T> T getService(Class<T> clazz)
	{
		String name = clazz.getSimpleName();
		String className = prop.getProperty(name);
		try
		{
			T service = (T) Class.forName(className).newInstance();
			return service;
		} catch (Exception e)
		{
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T createService(Class<T> clazz)
	{

		final T service = getService(clazz);

		return (T) Proxy.newProxyInstance(
				ServiceFactory.class.getClassLoader(), service.getClass()
						.getInterfaces(), new InvocationHandler()
				{

					// BusinessServiceProxy.addCategory(Category c)
					// BusinessServiceProxy.addBook(Book b)
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable
					{

						// 得到web层调用的方法
						String methodName = method.getName(); // addCategory

						// 反射出真实对象上相应的方法，检查真实对象方法上有没有权限注解
						Method realMethod = service.getClass().getMethod(
								methodName, method.getParameterTypes());
						Permission permission = realMethod
								.getAnnotation(Permission.class);
						// 如果没有权限注解
						if (permission == null)
						{
							return method.invoke(service, args);
						}
						// 真实对象相应的方法上有权限注解,则得到访问该方法需要的权限
						Privilege p = new Privilege(permission.value()); // 得到方法需要的权限
						// 检查用户是否有权限 //AppContext ThreadLocal
						User user = ServiceUtils.getUser();
						if (user == null)
						{
							throw new SecurityException("您还没有登录!");
						}
						// 得到用户所有权限
						UserBusinessService userService = new UserBusinessServiceImpl();

						List<Privilege> list = userService
								.getUserPrivileges(user.getId());
						if (list.contains(p))
						{
							return method.invoke(service, args);
						}
						throw new SecurityException("你没有权限");
					}
				});
	}
}
