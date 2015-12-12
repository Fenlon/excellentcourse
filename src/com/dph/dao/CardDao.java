package com.dph.dao;

import java.util.List;

import com.dph.domain.Card;
import com.dph.domain.QueryResult;
import com.dph.exception.DaoException;

public interface CardDao
{
	void addCard(Card card) throws DaoException;

	Card findCard(String id) throws DaoException;

	void delete(String id) throws DaoException;

	QueryResult getPartCard(int forumid, int startIndex, int pageSize)
			throws DaoException;

	int countCard(int forumid) throws DaoException;

	void updateNum(String condition, Object num, String cardid, Class<?> clazz)
			throws DaoException;

	void update(Card card) throws DaoException;

	QueryResult getCardsByUser(String username, int startindex, int pagesize)
			throws DaoException;

	List<Card> getCards(int num) throws DaoException;
}