package com.dph.service.impl;

import java.util.List;

import com.dph.dao.DaoFactory;
import com.dph.dao.NoticeDao;
import com.dph.domain.Notice;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.QueryResult;
import com.dph.exception.DaoException;
import com.dph.service.NoticeService;
import com.dph.service.Permission;
import com.dph.utils.ServiceUtils;

public class NoticeServiceImpl implements NoticeService
{
	NoticeDao dao = DaoFactory.getInstance().getDao(NoticeDao.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#addMsg(com.dph.domain.Msg)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.NoticeService#addNotice(com.dph.domain.Notice)
	 */
	@Permission("发布公告")
	public void addNotice(Notice notice) throws DaoException
	{
		dao.add(notice);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#deleteMsg(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.NoticeService#deleteNotice(java.lang.String)
	 */
	@Permission("删除公告")
	public void deleteNotice(String id) throws DaoException
	{
		dao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#updateMsg(com.dph.domain.Msg)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.NoticeService#updateNotice(com.dph.domain.Notice)
	 */
	@Permission("修改公告")
	public void updateNotice(Notice notice) throws DaoException
	{
		dao.update(notice);
	}

	// 获得首页所需要的公告
	public List<Notice> getIndexNotices(int num) throws DaoException
	{
		return dao.getIndexNotice(num);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#findmsg(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.NoticeService#findNotice(java.lang.String)
	 */
	public Notice findNotice(String id) throws DaoException
	{
		return dao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.MsgService#queryMsg(int, int)
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.NoticeService#pageQuery(com.dph.domain.QueryInfo)
	 */
	public PageBean pageQuery(QueryInfo info) throws DaoException
	{
		QueryResult queryResult = dao.getPartMsg(info.getStartindex(),
				info.getPagesize());
		return ServiceUtils.getPageBeanFromQueryResult(info, queryResult);
	}
}
