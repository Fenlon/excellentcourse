package com.dph.dao;

import java.util.List;

import com.dph.domain.Notice;
import com.dph.domain.QueryResult;
import com.dph.exception.DaoException;

public interface NoticeDao
{

	void add(Notice notice) throws DaoException;

	void delete(String id) throws DaoException;

	void update(Notice notice) throws DaoException;

	Notice find(String id) throws DaoException;

	QueryResult getPartMsg(int startIndex, int pageSize) throws DaoException;

	List<Notice> getIndexNotice(int num) throws DaoException;

}