package com.dph.dao;

import java.util.List;

import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.manager.Resource;

public interface ResourceDao
{

	void add(Resource r) throws DaoException;

	Resource find(String uri) throws DaoException;

	Resource findById(String id) throws DaoException;

	List<Resource> getAll() throws DaoException;

	void updatePrivilege(Resource r, Privilege p) throws DaoException;

	void update(Resource r) throws DaoException;

	void delete(String id) throws DaoException;

}