package com.dph.service;

import java.util.List;

import com.dph.domain.Card;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.exception.DaoException;

public interface CardBusinessService
{
	void addCard(Card card) throws DaoException;

	void deleteCard(String id) throws DaoException;

	PageBean pageQuery(QueryInfo info, int forumid) throws DaoException;

	Card findCard(String id) throws DaoException;

	void updateNum(String condition, long num, String cardid, Class<?> clazz)
			throws DaoException;

	PageBean pageQueryMsgs(QueryInfo info, String cardId) throws DaoException;

	void update(Card card) throws DaoException;

	PageBean getCardsByUser(String username, QueryInfo info)
			throws DaoException;

	public List<Card> getIndexCards(int num) throws DaoException;
}