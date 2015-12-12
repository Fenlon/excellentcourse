package com.dph.test;

import org.junit.Test;

import com.dph.dao.impl.CardDaoImpl;
import com.dph.domain.Card;
import com.dph.exception.DaoException;

public class CardDaoTest
{

	@Test
	public void findCard() throws DaoException
	{
		String id = "0acbb8b4-25d9-4b4f-9bc7-7af82127c143";
		Card c = new CardDaoImpl().findCard(id);
		System.out.println(c.getAuther().getUsername() + "--"
				+ c.getLastuser().getUsername());
	}
}
