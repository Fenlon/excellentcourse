package com.dph.dao;

import java.util.List;

import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.manager.Role;

public interface RoleDao
{

	void add(Role r) throws DaoException;

	Role find(String id) throws DaoException;

	void updatePrivilege(Role r, List<Privilege> ps) throws DaoException;

	List<Role> getAll() throws DaoException;

	void update(Role r) throws DaoException;

	void delete(String id) throws DaoException;

}