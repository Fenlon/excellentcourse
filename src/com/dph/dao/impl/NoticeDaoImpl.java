package com.dph.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dph.dao.NoticeDao;
import com.dph.domain.Notice;
import com.dph.domain.QueryResult;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.utils.JdbcUtils;

public class NoticeDaoImpl implements NoticeDao
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.NoticeDao#add(com.dph.domain.Notice)
	 */
	public void add(Notice notice) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "insert into notice(id,title,content,username,time)values(?,?,?,?,?) ";
			Object params[] = {notice.getId(), notice.getTitle(),
					notice.getContent(), notice.getUser().getUsername(),
					notice.getTime()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.NoticeDao#delete(java.lang.String)
	 */
	public void delete(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "delete from notice where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.NoticeDao#update(com.dph.domain.Notice)
	 */
	public void update(Notice notice) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "update notice set title=?, content=? where id=?";
			Object[] params = {notice.getTitle(), notice.getContent(),
					notice.getId()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.NoticeDao#find(java.lang.String)
	 */
	public Notice find(String id) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from notice where id=?";
			Notice notice = runner.query(conn, sql, new BeanHandler<Notice>(
					Notice.class), id);
			if (notice == null)
			{
				return null;
			}
			sql = "select u.* from notice n, t_user u where n.id=? and u.username=n.username";
			User user = runner.query(conn, sql, new BeanHandler<User>(
					User.class), id);
			notice.setUser(user);
			return notice;

		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.NoticeDao#getPartMsg(int, int)
	 */
	public QueryResult getPartMsg(int startIndex, int pageSize)
			throws DaoException
	{
		List<Notice> notices = null;
		QueryResult queryResult = new QueryResult();
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from notice order by time desc limit ?,?";
			Object params[] = {startIndex, pageSize};
			notices = runner.query(conn, sql, new BeanListHandler<Notice>(
					Notice.class), params);
			if (notices == null)
			{
				return null;
			}
			for (Notice n : notices)
			{
				sql = "select u.* from notice n, t_user u where n.id=? and u.username=n.username";
				User user = runner.query(conn, sql, new BeanHandler<User>(
						User.class), n.getId());
				n.setUser(user);
			}
			queryResult.setList(notices);
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
			String sql = "select count(*) from notice";
			Object[] a = runner.query(JdbcUtils.getConnection(), sql,
					new ArrayHandler());
			return Long.parseLong(a[0].toString());
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	// 获得首页展示的公告
	public List<Notice> getIndexNotice(int num) throws DaoException
	{
		List<Notice> notices = null;
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from notice order by time desc limit 0,?";
			notices = runner.query(conn, sql, new BeanListHandler<Notice>(
					Notice.class), num);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return notices;
	}
}
