package com.dph.dao;

import java.sql.SQLException;
import java.util.List;

import com.dph.domain.QueryResult;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.manager.Role;

public interface UserDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.UserDao#add(com.dph.domain.User)
	 */
	void add(User user) throws DaoException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.UserDao#findUser(java.lang.String)
	 */
	User findUser(String id) throws DaoException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.UserDao#findUser(java.lang.String,
	 * java.lang.String)
	 */
	User findUser(String username, String password) throws DaoException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.UserDao#updateUser(com.dph.domain.User)
	 */
	void updateUser(User user) throws DaoException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.UserDao#updateUserPwd(com.dph.domain.User)
	 */
	void updateUserPwd(User user);

	void update(String condition, Object value, String userid, Class<?> clazz)
			throws DaoException;

	void delete(String id) throws DaoException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.UserDao#getPartUser(int, int)
	 */
	QueryResult getPartUser(int startIndex, int pageSize) throws DaoException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.UserDao#countUser()
	 */
	int countUser() throws DaoException;

	User find(String username) throws DaoException;

	boolean findUserISExist(String username) throws DaoException;

	void updateRole(User user, List<Role> roles) throws DaoException;

	<T> T getData(String field, String sql, Object conditionVal, Class<T> clazz)
			throws DaoException;

	void updatePhotopath(String id, String photopath) throws DaoException;

	List<User> getTeachers() throws DaoException;

}