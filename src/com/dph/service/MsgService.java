package com.dph.service;

import com.dph.domain.Msg;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.QueryResult;
import com.dph.exception.DaoException;

public interface MsgService
{

	void addMsg(Msg msg) throws DaoException;

	void deleteMsg(String id) throws DaoException;

	void updateMsg(Msg msg) throws DaoException;

	Msg findmsg(String id) throws DaoException;

	public PageBean pageQuery(QueryInfo info) throws DaoException;
}