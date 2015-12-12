package com.dph.service.impl;

import com.dph.dao.DaoFactory;
import com.dph.dao.MsgDao;
import com.dph.domain.Msg;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.QueryResult;
import com.dph.exception.DaoException;
import com.dph.service.MsgService;
import com.dph.service.Permission;
import com.dph.utils.ServiceUtils;

public class MsgServiceImpl implements MsgService
{
	MsgDao dao = DaoFactory.getInstance().getDao(MsgDao.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#addMsg(com.dph.domain.Msg)
	 */
	public void addMsg(Msg msg) throws DaoException
	{
		dao.add(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#deleteMsg(java.lang.String)
	 */
	@Permission("删除留言")
	public void deleteMsg(String id) throws DaoException
	{
		dao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#updateMsg(com.dph.domain.Msg)
	 */
	public void updateMsg(Msg msg) throws DaoException
	{
		dao.update(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#findmsg(java.lang.String)
	 */
	public Msg findmsg(String id) throws DaoException
	{
		return dao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#queryMsg(int, int)
	 */

	public PageBean pageQuery(QueryInfo info) throws DaoException
	{
		QueryResult queryResult = dao.getPartMsg(info.getStartindex(),
				info.getPagesize());
		return ServiceUtils.getPageBeanFromQueryResult(info, queryResult);
	}

}
