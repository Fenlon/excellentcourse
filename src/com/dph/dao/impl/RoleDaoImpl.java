package com.dph.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dph.dao.RoleDao;
import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.manager.Role;
import com.dph.utils.JdbcUtils;

public class RoleDaoImpl implements RoleDao
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.RoleDao#add(com.dph.manager.Role)
	 */
	public void add(Role r) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "insert into role(id,name,description)values(?,?,?) ";
			Object params[] = {r.getId(), r.getName(), r.getDescription()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.RoleDao#find(java.lang.String)
	 */
	public Role find(String id) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from role where id=?";
			Role r = runner.query(conn, sql, id, new BeanHandler<Role>(
					Role.class));
			if (r == null)
			{
				return null;
			}
			// 得到角色的权限
			sql = "select * from role_privilege rp,privilege p where rp.roleid=? and rp.privilegeid=p.id";
			List<Privilege> ps = runner.query(conn, sql, id,
					new BeanListHandler<Privilege>(Privilege.class));
			r.getPrivileges().addAll(ps);
			return r;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.RoleDao#updatePrivilege(com.dph.manager.Role,
	 * java.util.List)
	 */
	public void updatePrivilege(Role r, List<Privilege> ps) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "delete from role_privilege where roleid=?";
			runner.update(conn, sql, r.getId());

			for (Privilege p : ps)
			{
				sql = "insert into role_privilege (roleid,privilegeid) values(?,?)";
				Object[] params = {r.getId(), p.getId()};
				runner.update(conn, sql, params);
			}
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.RoleDao#getAll()
	 */
	public List<Role> getAll() throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from role";
			List<Role> rs = runner.query(conn, sql, new BeanListHandler<Role>(
					Role.class));
			if (rs.size() == 0)
			{
				return null;
			}
			for (Role r : rs)
			{
				sql = "select * from role_privilege rp,privilege p where rp.roleid=? and rp.privilegeid=p.id";
				List<Privilege> ps = runner.query(conn, sql, r.getId(),
						new BeanListHandler<Privilege>(Privilege.class));
				r.getPrivileges().addAll(ps);
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
	 * @see com.dph.dao.impl.RoleDao#update(com.dph.manager.Role)
	 */
	public void update(Role r) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "update role set name=?, description=? where id=?";
			Object[] params = {r.getName(), r.getDescription(), r.getId()};
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
			String sql = "delete from role where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}

	}
}
