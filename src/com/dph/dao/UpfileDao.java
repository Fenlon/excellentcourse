package com.dph.dao;

import com.dph.domain.QueryResult;
import com.dph.domain.Upfile;
import com.dph.exception.DaoException;

public interface UpfileDao
{

	/*
	 * id varchar(40) primary key, uuidname varchar(100) not null unique,
	 * filename varchar(100) not null, savepath varchar(255) not null, uptime
	 * datetime not null, description varchar(255), username varchar(40) not
	 * null
	 */
	void add(Upfile upfile) throws DaoException;

	QueryResult getAll(String type, int startIndex, int pageSize)
			throws DaoException;

	Upfile find(String id) throws DaoException;

	void delete(String id) throws DaoException;

	void update(Upfile upfile) throws DaoException;

}