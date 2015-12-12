package com.dph.dao;

import java.util.List;

import com.dph.exception.DaoException;
import com.dph.manager.Privilege;

public interface PrivilegeDao
{

	void add(Privilege p) throws DaoException;

	Privilege find(String id) throws DaoException;

	List getAll() throws DaoException;

	void update(Privilege p) throws DaoException;

	void delete(String id) throws DaoException;

}