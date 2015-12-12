package com.dph.dao;

import com.dph.domain.Msg;
import com.dph.domain.QueryResult;
import com.dph.exception.DaoException;

public interface MsgDao
{

	void add(Msg msg) throws DaoException;

	void delete(String id) throws DaoException;

	void update(Msg msg) throws DaoException;

	Msg find(String id) throws DaoException;

	QueryResult getPartMsg(int startIndex, int pageSize) throws DaoException;

}