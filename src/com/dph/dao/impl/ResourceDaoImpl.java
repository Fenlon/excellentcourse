package com.dph.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dph.dao.ResourceDao;
import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.manager.Resource;
import com.dph.utils.DaoUtils;
import com.dph.utils.JdbcUtils;

public class ResourceDaoImpl implements ResourceDao
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ResourceDao#add(com.dph.manager.Resource)
	 */
	public void add(Resource r) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "insert into resource(id,uri,description)values(?,?,?) ";
			Object params[] = {r.getId(), r.getUri(), r.getDescription()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ResourceDao#find(java.lang.String)
	 */
	public Resource find(String uri) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from resource where uri=?";
			Resource r = runner.query(conn, sql, uri,
					new BeanHandler<Resource>(Resource.class));
			if (r == null)
			{
				return null;
			}
			// 得到控制资源的权限
			sql = "select p.* from resource r,privilege p where r.uri=? and r.privilegeid=p.id";
			Privilege p = runner.query(conn, sql, uri,
					new BeanHandler<Privilege>(Privilege.class));
			r.setPrivilege(p);
			return r;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	private void setPrivilege(Resource r)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ResourceDao#findById(java.lang.String)
	 */
	public Resource findById(String id) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from resource where id=?";
			Resource r = runner.query(conn, sql, id, new BeanHandler<Resource>(
					Resource.class));
			if (r == null)
			{
				return null;
			}
			// 得到控制资源的权限
			sql = "select p.* from resource r,privilege p where r.id=? and r.privilegeid=p.id";
			Privilege p = runner.query(conn, sql, id,
					new BeanHandler<Privilege>(Privilege.class));
			r.setPrivilege(p);
			return r;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ResourceDao#getAll()
	 */
	public List<Resource> getAll() throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from resource";
			List<Resource> rs = runner.query(conn, sql,
					new BeanListHandler<Resource>(Resource.class));
			if (rs.size() == 0)
			{
				rs = null;
				return null;
			}
			for (Resource r : rs)
			{
				sql = "select p.* from resource r,privilege p where r.id=? and r.privilegeid=p.id";
				Privilege p = runner.query(conn, sql, r.getId(),
						new BeanHandler<Privilege>(Privilege.class));
				r.setPrivilege(p);
			}
			return rs;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.dao.impl.ResourceDao#updatePrivilege(com.dph.manager.Resource,
	 * com.dph.manager.Privilege)
	 */
	public void updatePrivilege(Resource r, Privilege p) throws DaoException
	{
		String sql = "update resource set  privilegeid=? where id=?";
		// DaoUtils.update(condition, num, cardid, clazz, sql);
		DaoUtils.update(p.getId(), r.getId(), null, sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.ResourceDao#update(com.dph.manager.Resource)
	 */
	public void update(Resource r) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "update resource set uri=?, description=?,privilegeid=? where id=?";
			Object[] params = {r.getUri(), r.getDescription(),
					r.getPrivilege(), r.getId()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void delete(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "delete from resource where id=?";

			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}

	}
}
