package com.dph.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dph.dao.ForumDao;
import com.dph.domain.Forum;
import com.dph.exception.DaoException;
import com.dph.utils.JdbcUtils;

public class ForumDaoImpl implements ForumDao
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ForumDao#addForum(com.dph.domain.Forum)
	 */
	public void addForum(Forum forum) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "insert into forum(id,forumname,manager)values(?,?,?) ";
			Object params[] = {forum.getId(), forum.getForumname(),
					forum.getManager()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ForumDao#delete(int)
	 */
	public void delete(int id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "delete from forum where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ForumDao#update(com.dph.domain.Forum)
	 */
	public void update(Forum forum) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "update forum set forumname=?,manager=? where id=?";
			Object params[] = {forum.getForumname(), forum.getManager(),
					forum.getId()};
			runner.update(JdbcUtils.getConnection(), sql, params);

		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ForumDao#findForum(java.lang.String)
	 */
	public Forum findForum(int id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "select * from forum where id=?";
			Forum forum = runner.query(JdbcUtils.getConnection(), sql, id,
					new BeanHandler<Forum>(Forum.class));
			return forum;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ForumDao#getAll()
	 */
	public List<Forum> getAll() throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "select * from forum";
			return runner.query(JdbcUtils.getConnection(), sql,
					new BeanListHandler<Forum>(Forum.class));
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}
}
