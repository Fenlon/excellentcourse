package com.dph.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dph.dao.CardDao;
import com.dph.domain.BBSMsg;
import com.dph.domain.Card;
import com.dph.domain.Forum;
import com.dph.domain.QueryResult;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.utils.DaoUtils;
import com.dph.utils.JdbcUtils;

public class CardDaoImpl implements CardDao
{
	private ForumDaoImpl forumDaoImpl = new ForumDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.CardDao#addCard(com.dph.domain.Card)
	 */
	public void addCard(Card card) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "insert into card(id,title,content,timecreate,timelast,authername,lastusername,forumid)values(?,?,?,?,?,?,?,?) ";
			Object params[] = {card.getId(), card.getTitle(),
					card.getContent(), card.getTimecreate(),
					card.getTimelast(), card.getAuther().getUsername(),
					card.getLastuser().getUsername(), card.getForum().getId()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}

	}

	private void setForum2Card(Card card) throws DaoException
	{
		String sql = "select forumid from card where id=?";
		Integer forunid = DaoUtils.getData("forumid", card.getId(), sql,
				Integer.class);
		Forum forum = forumDaoImpl.findForum(forunid);
		card.setForum(forum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.CardDao#findCard(java.lang.String)
	 */
	public Card findCard(String id) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from card where id=?";
			Card card = runner.query(conn, sql, new BeanHandler<Card>(
					Card.class), id);
			sql = "select u.* from t_user u, card c where c.id=? and u.username=c.authername";
			User auther = runner.query(conn, sql, new BeanHandler<User>(
					User.class), id);
			sql = "select u.* from t_user u, card c where c.id=? and u.username=c.lastusername";
			User lastuser = runner.query(conn, sql, new BeanHandler<User>(
					User.class), id);
			card.setAuther(auther);
			card.setLastuser(lastuser);
			setForum2Card(card);
			return card;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.CardDao#delete(java.lang.String)
	 */
	public void delete(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "delete from card where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.CardDao#getPartCard(int, int)
	 */
	public QueryResult getPartCard(int forumid, int startIndex, int pageSize)
			throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryResult result = new QueryResult();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from card where forumid=? order by timecreate desc limit ?,?";
			Object params[] = {forumid, startIndex, pageSize};
			List<Card> cards = runner.query(conn, sql,
					new BeanListHandler<Card>(Card.class), params);
			if (cards.size() == 0)
			{
				cards = null;
			}
			if (cards != null)
			{
				for (Card card : cards)
				{
					setCardUser(card, card.getId(), runner, conn);
					setForum2Card(card);
				}
			}
			result.setList(cards);
			int totalrecord = countCard(forumid);
			result.setTotalrecord(totalrecord);
			return result;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	private void setCardUser(Card card, String id, QueryRunner runner,
			Connection conn) throws SQLException
	{
		// 包括auther 和lastuser
		String sql = "select u.* from t_user u, card c where c.id=? and u.username=c.authername";
		User auther = runner.query(conn, sql,
				new BeanHandler<User>(User.class), id);
		sql = "select u.* from t_user u, card c where c.id=? and u.username=c.lastusername";
		User lastuser = runner.query(conn, sql, new BeanHandler<User>(
				User.class), id);
		card.setAuther(auther);
		card.setLastuser(lastuser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.CardDao#countCard()
	 */
	public int countCard(int forumid) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "select count(*) from card where forumid=?";
			Object[] a = runner.query(JdbcUtils.getConnection(), sql, forumid,
					new ArrayHandler());
			return Integer.parseInt(a[0].toString());
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void updateNum(String condition, Object num, String cardid,
			Class<?> clazz) throws DaoException
	{
		String sql = "update card set " + condition + "=? where id=?";
		// DaoUtils.update(condition, num, cardid, clazz, sql);
		DaoUtils.update(num, cardid, clazz, sql);
	}

	public void update(Card card) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "update card set title=?, content=?,timelast=?,lastusername=? where id=?";
			Object[] params = {card.getTitle(), card.getContent(),
					card.getTimelast(), card.getLastuser().getUsername(),
					card.getId()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public QueryResult getCardsByUser(String username, int startindex,
			int pagesize) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryResult result = new QueryResult();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from card where authername=? order by timecreate desc limit ?,?";
			Object params[] = {username, startindex, pagesize};
			List<Card> cards = runner.query(conn, sql,
					new BeanListHandler<Card>(Card.class), params);
			if (cards.size() == 0)
			{
				cards = null;
			}
			if (cards != null)
			{
				for (Card card : cards)
				{
					setCardUser(card, card.getId(), runner, conn);
					setForum2Card(card);
				}
			}
			result.setList(cards);
			int totalrecord = countCard(username);
			result.setTotalrecord(totalrecord);
			return result;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	// 计算某人的帖子数量
	private int countCard(String username) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "select count(*) from card where authername=?";
			Object[] a = runner.query(JdbcUtils.getConnection(), sql,
					new ArrayHandler(), username);
			return Integer.parseInt(a[0].toString());
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List<Card> getCards(int num) throws DaoException
	{
		List<Card> cards = null;
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from  card order by timecreate desc limit 0,? ";
			cards = runner.query(conn, sql, new BeanListHandler<Card>(
					Card.class), num);
			if (cards.size() == 0)
			{
				cards = null;
			}
			if (cards != null)
			{
				for (Card card : cards)
				{
					setForum2Card(card);
				}
			}
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
		return cards;
	}
}
