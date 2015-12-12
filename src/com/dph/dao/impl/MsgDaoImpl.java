package com.dph.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dph.dao.MsgDao;
import com.dph.domain.Msg;
import com.dph.domain.QueryResult;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.utils.JdbcUtils;

public class MsgDaoImpl implements MsgDao
{
	public void add(Msg msg) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "insert into msgboard(id,title,content,userid,userip,time,msgparent)values(?,?,?,?,?,?,?) ";
			Object params[] = {msg.getId(), msg.getTitle(), msg.getContent(),
					msg.getUser().getId(), msg.getUserip(), msg.getTime(),
					msg.getMsgparent()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public void delete(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "delete from msgboard where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public void update(Msg msg) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "update msgboard set title=?, content=? where id=?";
			Object[] params = {msg.getTitle(), msg.getContent(), msg.getId()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public Msg find(String id) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from msgboard where id=?";
			Msg msg = runner.query(conn, sql, new BeanHandler<Msg>(Msg.class),
					id);
			if (msg == null)
			{
				return null;
			}
			sql = "select u.* from msgboard m, t_user u where m.id=? and u.id=m.userid";
			User user = runner.query(conn, sql, new BeanHandler<User>(
					User.class), id);
			msg.setUser(user);
			return msg;

		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public QueryResult getPartMsg(int startIndex, int pageSize)
			throws DaoException
	{
		List<Msg> msgs = null;
		QueryResult queryResult = new QueryResult();
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from msgboard where msgparent is NULL order by time desc limit ?,?";
			Object params[] = {startIndex, pageSize};
			msgs = runner.query(conn, sql, new BeanListHandler<Msg>(Msg.class),
					params);
			if (msgs == null)
			{
				return null;
			}
			for (Msg msg : msgs)
			{
				sql = "select u.* from msgboard m, t_user u where m.id=? and u.id=m.userid";
				User user = runner.query(conn, sql, new BeanHandler<User>(
						User.class), msg.getId());
				msg.setUser(user);
				sql = "select * from msgboard where msgparent=? order by time desc";
				List<Msg> huifus = runner.query(conn, sql,
						new BeanListHandler<Msg>(Msg.class), msg.getId());
				for (Msg msg2 : huifus)
				{// 找出回复的人
					sql = "select u.* from msgboard m, t_user u where m.id=? and u.id=m.userid";
					user = runner.query(conn, sql, new BeanHandler<User>(
							User.class), msg2.getId());
					msg2.setUser(user);
				}
				msg.setHuifus(huifus);
			}
			queryResult.setList(msgs);
			long totalrecord = countRow();
			queryResult.setTotalrecord(totalrecord);
			return queryResult;
		} catch (Exception e)
		{
			throw new DaoException(e.getMessage(), e);
		}
	}

	private long countRow() throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "select count(*) from msgboard where msgparent is NULL";
			Object[] a = runner.query(JdbcUtils.getConnection(), sql,
					new ArrayHandler());
			return Long.parseLong(a[0].toString());
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}
}
