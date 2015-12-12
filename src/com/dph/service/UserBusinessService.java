package com.dph.service;

import java.util.List;

import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.exception.UserHasExistException;
import com.dph.manager.Privilege;
import com.dph.manager.Role;

public interface UserBusinessService
{

	void register(User user) throws DaoException, UserHasExistException;

	User login(String username, String password) throws DaoException;

	void update(String condition, Object value, String userid, Class<?> clazz)
			throws DaoException;

	<T> T getData(String field, Object conditionVal, Class<T> clazz)
			throws DaoException;

	User findUser(String id) throws DaoException;

	String getIdByName(String username) throws DaoException;

	boolean findUserISExist(String username) throws DaoException;

	User find(String username) throws DaoException;

	public void updateRole(User user, List<Role> roles) throws DaoException;

	public List<Privilege> getUserPrivileges(String userid) throws DaoException;

	public PageBean getPartUser(QueryInfo info) throws DaoException;

	public void delete(String id) throws DaoException;

	public void updatephoto(String id, String photopath) throws DaoException;

	public List<User> getTeachers() throws DaoException;
}