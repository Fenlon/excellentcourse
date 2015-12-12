package com.dph.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dph.dao.PrivilegeDao;
import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.utils.JdbcUtils;

public class PrivilegeDaoImpl implements PrivilegeDao
{
	/* (non-Javadoc)
	 * @see com.dph.dao.impl.PrivilegeDao#add(com.dph.manager.Privilege)
	 */
	public void add(Privilege p) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "insert into privilege(id,name,description)values(?,?,?) ";
			Object params[] = {p.getId(), p.getName(), p.getDescription()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.dph.dao.impl.PrivilegeDao#find(java.lang.String)
	 */
	public Privilege find(String id) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from privilege where id=?";
			Privilege p = runner.query(conn, sql, id,
					new BeanHandler<Privilege>(Privilege.class));
			return p;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.dph.dao.impl.PrivilegeDao#getAll()
	 */
	public List getAll() throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from privilege";
			List<Privilege> ps = runner.query(conn, sql,
					new BeanListHandler<Privilege>(Privilege.class));
			if (ps.size() == 0)
			{
				ps = null;
			}
			return ps;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.dph.dao.impl.PrivilegeDao#update(com.dph.manager.Privilege)
	 */
	public void update(Privilege p) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "update privilege set name=?, description=? where id=?";
			Object[] params = {p.getName(), p.getDescription(), p.getId()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.dph.dao.impl.PrivilegeDao#delete(java.lang.String)
	 */
	public void delete(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "delete from privilege where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}
}
