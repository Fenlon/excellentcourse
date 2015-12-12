package com.dph.dao;

import java.util.List;

import com.dph.domain.BBSMsg;
import com.dph.domain.QueryResult;
import com.dph.exception.DaoException;

public interface BBSMsgDao
{
	void addBBSMsg(BBSMsg msg) throws DaoException;

	BBSMsg findBBSMsg(String id) throws DaoException;

	void delete(String id) throws DaoException;

	QueryResult getPartBBSMsg(String cardId, int startIndex, int pageSize)
			throws DaoException;

	<T> List<T> getListData(Object[] params, String sql, Class<T> clazz)
			throws DaoException;

	List<BBSMsg> getRemindMsgs(String username) throws DaoException;

	void update(String field, Object value, String id) throws DaoException;
}