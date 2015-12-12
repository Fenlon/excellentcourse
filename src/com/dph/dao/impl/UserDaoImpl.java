package com.dph.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dph.dao.UserDao;
import com.dph.domain.QueryResult;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.manager.Role;
import com.dph.utils.DaoUtils;
import com.dph.utils.JdbcUtils;

public class UserDaoImpl implements UserDao
{

	public void add(User user) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "insert into t_user(id,username,password,gender,birthday,email,nickname,description)values(?,?,?,?,?,?,?,?) ";
			Object params[] = {user.getId(), user.getUsername(),
					user.getPassword(), user.getGender(), user.getBirthday(),
					user.getEmail(), user.getNickname(), user.getDescription()};
			runner.update(JdbcUtils.getConnection(), sql, params);
			// 为角色赋予权限
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public User findUser(String id) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from t_user where id=?";
			User user = runner.query(conn, sql, id, new BeanHandler<User>(
					User.class));
			// find role to the user
			sql = "select * from user_role ur , role r where ur.userid=? and r.id=ur.roleid";
			List<Role> rs = runner.query(conn, sql, new BeanListHandler<Role>(
					Role.class), id);
			// 其实role中还要添加权限的，但是有时候不需要，所以放在service层了
			user.getRoles().addAll(rs);
			return user;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public User findUser(String username, String password) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from t_user where username=? and password=?";
			Object params[] = {username, password};
			User user = runner.query(conn, sql, params, new BeanHandler<User>(
					User.class));
			if (user == null)
			{
				return null;
			}
			// find role to the user
			sql = "select * from user_role ur , role r where ur.userid=? and r.id=ur.roleid";
			List<Role> rs = runner.query(conn, sql, user.getId(),
					new BeanListHandler<Role>(Role.class));
			user.getRoles().addAll(rs);
			return user;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public void updateUser(User user) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "update t_user set username=?,gender=?,birthday=?,nickname=?,email=?,description=? where id=?";
			Object params[] = {user.getUsername(), user.getGender(),
					user.getBirthday(), user.getNickname(), user.getEmail(),
					user.getDescription(), user.getId()};
			runner.update(JdbcUtils.getConnection(), sql, params);

		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public void updateUserPwd(User user)
	{

	}

	public void update(String condition, Object value, String userid,
			Class<?> clazz) throws DaoException
	{
		String sql = "update t_user set " + condition + "=? where id=?";
		DaoUtils.update(value, userid, clazz, sql);
	}

	public void delete(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "delete from t_user where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public QueryResult getPartUser(int startIndex, int pageSize)
			throws DaoException
	{
		QueryResult result = new QueryResult();
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from t_user limit ?,?";
			Object params[] = {startIndex, pageSize};
			List<User> users = runner.query(conn, sql, params,
					new BeanListHandler<User>(User.class));

			if (users == null)
			{
				return null;
			}
			// 由于显示所有用户的时候没必要显示角色问题，只有点击单个的user进入细节时候才显示
			for (User user : users)
			{
				// find role to the user
				sql = "select * from user_role ur , role r where ur.userid=? and r.id=ur.roleid";
				List<Role> rs = runner.query(conn, sql, user.getId(),
						new BeanListHandler<Role>(Role.class));
				user.getRoles().addAll(rs);
			}
			int totalrecord = countUser();
			result.setList(users);
			result.setTotalrecord(totalrecord);
			return result;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public int countUser() throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "select count(*) from t_user";
			Object[] a = runner.query(JdbcUtils.getConnection(), sql,
					new ArrayHandler());
			return Integer.parseInt(a[0].toString());
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public User find(String username) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from t_user where username=?";
			User user = runner.query(conn, sql, username,
					new BeanHandler<User>(User.class));
			if (user == null)
			{
				return null;
			}
			// find role to the user
			sql = "select * from user_role ur , role r where ur.userid=? and r.id=ur.roleid";
			List<Role> rs = runner.query(conn, sql, user.getId(),
					new BeanListHandler<Role>(Role.class));
			user.getRoles().addAll(rs);
			return user;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public boolean findUserISExist(String username) throws DaoException
	{
		try
		{
			User user = find(username);
			if (user == null)
			{
				return false;
			}
			return true;
		} catch (Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public void updateRole(User user, List<Role> roles) throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			// first delete all role to user
			String sql = "delete from user_role where userid=?";
			runner.update(conn, sql, user.getId());

			// add new role to the user
			for (Role role : roles)
			{
				sql = "insert into user_role (userid,roleid) values(?,?)";
				Object[] params = {user.getId(), role.getId()};
				runner.update(conn, sql, params);
			}
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}

	}

	public <T> T getData(String field, String sql, Object conditionVal,
			Class<T> clazz) throws DaoException
	{
		return DaoUtils.getData(field, conditionVal, sql, clazz);
	}

	@Override
	public void updatePhotopath(String id, String photopath)
			throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "update t_user set photopath=? where id=?";
			Object[] params = {photopath, id};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List<User> getTeachers() throws DaoException
	{
		QueryResult result = new QueryResult();
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from t_user where id in (select ur.userid from role r,user_role ur where r.name='教师' and ur.roleid=r.id)";
			List<User> teachers = runner.query(conn, sql,
					new BeanListHandler<User>(User.class));
			return teachers;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}
}
