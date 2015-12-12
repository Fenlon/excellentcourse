package com.dph.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dph.dao.DaoFactory;
import com.dph.dao.RoleDao;
import com.dph.dao.UserDao;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.QueryResult;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.exception.UserHasExistException;
import com.dph.manager.Privilege;
import com.dph.manager.Role;
import com.dph.service.UserBusinessService;
import com.dph.utils.ServiceUtils;

public class UserBusinessServiceImpl implements UserBusinessService
{
	UserDao dao = DaoFactory.getInstance().getDao(UserDao.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.UserBusinessService#register(com.dph.domain.User)
	 */
	public void register(User user) throws DaoException, UserHasExistException
	{
		if (dao.findUserISExist(user.getUsername()))
		{
			throw new UserHasExistException("用户已存在！");
		} else
		{
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			dao.add(user);
		}
	}

	public void update(String condition, Object value, String userid,
			Class<?> clazz) throws DaoException
	{
		dao.update(condition, value, userid, clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.UserBusinessService#login(java.lang.String,
	 * java.lang.String)
	 */
	public User login(String username, String password) throws DaoException
	{
		password = ServiceUtils.md5(password);
		return dao.findUser(username, password);
	}

	public <T> T getData(String field, Object conditionVal, Class<T> clazz)
			throws DaoException
	{
		String sql = "select " + field + " from t_user where id=?";
		return dao.getData(field, sql, conditionVal, clazz);
	}

	public String getIdByName(String username) throws DaoException
	{
		String sql = "select id from t_user where username=?";
		return dao.getData("id", sql, username, String.class);
	}

	public User findUser(String id) throws DaoException
	{
		return dao.findUser(id);
	}

	@Override
	public boolean findUserISExist(String username) throws DaoException
	{
		return dao.findUserISExist(username);
	}

	@Override
	public User find(String username) throws DaoException
	{
		return dao.find(username);
	}

	public void updateRole(User user, List<Role> roles) throws DaoException
	{
		dao.updateRole(user, roles);
	}

	public List<Privilege> getUserPrivileges(String userid) throws DaoException
	{
		RoleDao roleDao = DaoFactory.getInstance().getDao(RoleDao.class);
		List<Privilege> allPrivileges = new ArrayList<Privilege>();
		User user = dao.findUser(userid);
		Set<Role> roles = user.getRoles();
		for (Role role : roles)
		{
			Role r = roleDao.find(role.getId());
			Set<Privilege> privileges = r.getPrivileges();
			allPrivileges.addAll(privileges);
		}
		return allPrivileges;
	}

	public PageBean getPartUser(QueryInfo info) throws DaoException
	{
		PageBean pagebean = new PageBean();
		QueryResult result = dao.getPartUser(info.getStartindex(),
				info.getPagesize());
		return ServiceUtils.getPageBeanFromQueryResult(info, result);
	}

	public void delete(String id) throws DaoException
	{
		dao.delete(id);
	}

	@Override
	public void updatephoto(String id, String photopath) throws DaoException
	{
		dao.updatePhotopath(id, photopath);

	}

	@Override
	public List<User> getTeachers() throws DaoException
	{
		return dao.getTeachers();
	}
}
