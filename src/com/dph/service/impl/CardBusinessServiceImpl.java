package com.dph.service.impl;

import java.util.List;

import com.dph.dao.BBSMsgDao;
import com.dph.dao.CardDao;
import com.dph.dao.DaoFactory;
import com.dph.domain.Card;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.QueryResult;
import com.dph.exception.DaoException;
import com.dph.service.CardBusinessService;
import com.dph.service.Permission;
import com.dph.utils.ServiceUtils;

public class CardBusinessServiceImpl implements CardBusinessService
{
	CardDao dao = DaoFactory.getInstance().getDao(CardDao.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.CardBusinessService#addCard(com.dph.domain.Card)
	 */
	public void addCard(Card card) throws DaoException
	{
		dao.addCard(card);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.CardBusinessService#deleteCard(java.lang.String)
	 */
	@Permission("删除论坛主题")
	public void deleteCard(String id) throws DaoException
	{
		dao.delete(id);
	}

	public Card findCard(String id) throws DaoException
	{
		return dao.findCard(id);
	}

	public PageBean pageQuery(QueryInfo info, int forumid) throws DaoException
	{
		QueryResult queryResult = dao.getPartCard(forumid,
				info.getStartindex(), info.getPagesize());
		return ServiceUtils.getPageBeanFromQueryResult(info, queryResult);
	}

	// 更新浏览数和回复数
	@Override
	public void updateNum(String condition, long num, String cardid,
			Class<?> clazz) throws DaoException
	{
		dao.updateNum(condition, num, cardid, clazz);
	}

	public PageBean pageQueryMsgs(QueryInfo info, String cardId)
			throws DaoException
	{
		BBSMsgDao msgDao = DaoFactory.getInstance().getDao(BBSMsgDao.class);
		QueryResult queryResult = msgDao.getPartBBSMsg(cardId,
				info.getStartindex(), info.getPagesize());
		return ServiceUtils.getPageBeanFromQueryResult(info, queryResult);
	}

	@Override
	public void update(Card card) throws DaoException
	{
		dao.update(card);
	}

	@Override
	public PageBean getCardsByUser(String username, QueryInfo info)
			throws DaoException
	{
		QueryResult result = dao.getCardsByUser(username, info.getStartindex(),
				info.getPagesize());
		return ServiceUtils.getPageBeanFromQueryResult(info, result);

	}

	// 获得首页显示的最新帖子
	public List<Card> getIndexCards(int num) throws DaoException
	{
		return dao.getCards(num);
	}
}
