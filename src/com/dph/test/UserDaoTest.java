package com.dph.test;

import java.util.Date;

import org.junit.Test;

import com.dph.dao.UserDao;
import com.dph.dao.impl.UserDaoImpl;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.utils.SendEmail;

public class UserDaoTest
{

	private UserDao dao = new UserDaoImpl();

	@Test
	public void main()
	{
		sendEmail();
	}

	public UserDaoTest()
	{

	}

	@Test
	public void testGetTeacher() throws DaoException
	{
		UserDaoImpl dao = new UserDaoImpl();
		dao.getTeachers();
		System.out.println("aaa");
	}

	@Test
	public void testAdd() throws DaoException
	{
		User user = new User();
		user.setId("114");
		user.setUsername("dphenixiong");
		user.setPassword("123");
		user.setBirthday(new Date());
		user.setNickname("熊凤龙");
		user.setEmail("dphenixogn@gmail.com");
		user.setDescription("he is a good boy");
		dao.add(user);
	}

	@Test
	public void testUpdate() throws DaoException
	{
		User user = new User();
		user.setId("111");
		user.setUsername("fenton");
		// user.setBirthday();
		user.setNickname("熊凤龙aaa");
		user.setEmail("dphenixogn@gmail.com");
		user.setDescription("he is a perfect boy");
		dao.updateUser(user);
	}

	@Test
	public void testFindUser() throws DaoException
	{
		String id = "111";
		User u = dao.findUser(id);
		System.out.println(u.toString());
	}

	@Test
	public void testFindUser2() throws DaoException
	{
		String username = "fenton";
		String pwd = "123";
		User u = dao.findUser(username, pwd);
		System.out.println(u.toString());
	}

	@Test
	public void testCountUser() throws DaoException
	{

		int i = dao.countUser();
		System.out.println(i);
	}

	@Test
	public void testGetPart() throws DaoException
	{
		/*
		 * List list = dao.getPartUser(0, 10); System.out.println("ppp");
		 */
	}

	@Test
	public void testGetObj() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException
	{

	}

	@Test
	public void testGetData() throws DaoException
	{
		/*
		 * System.out.println(DaoUtils.getData(field, conditionVal, sql,
		 * clazz)("username", "03fce369-e3b4-4806-9f32-039b07cae183",
		 * String.class));
		 */
	}

	@Test
	public void testGetListData() throws DaoException
	{
		/*
		 * String sql = "select id from bbs_msg where parentusername=? ";
		 * List<String> list = DaoUtils.getListData("id", "fenton", sql,
		 * String.class); System.out.println(list.get(0));
		 */
	}

	public void sendEmail()
	{
		User user = new User();
		user.setId("114");
		user.setUsername("dphenixiong");
		user.setPassword("123");
		user.setBirthday(new Date());
		user.setNickname("熊凤龙");
		// user.setEmail("zgjpkc@163.com");
		user.setEmail("zgjpkc@163.com");
		user.setDescription("he is a good boy");
		// new Thread(new SendEmail()).start();
		new SendEmail(user, null).run();
	}
}
