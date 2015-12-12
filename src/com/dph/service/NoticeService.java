package com.dph.service;

import java.util.List;

import com.dph.domain.Notice;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.exception.DaoException;

public interface NoticeService
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#addMsg(com.dph.domain.Msg)
	 */
	void addNotice(Notice notice) throws DaoException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#deleteMsg(java.lang.String)
	 */
	void deleteNotice(String id) throws DaoException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#updateMsg(com.dph.domain.Msg)
	 */
	void updateNotice(Notice notice) throws DaoException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#findmsg(java.lang.String)
	 */
	Notice findNotice(String id) throws DaoException;

	PageBean pageQuery(QueryInfo info) throws DaoException;

	public List<Notice> getIndexNotices(int num) throws DaoException;
}