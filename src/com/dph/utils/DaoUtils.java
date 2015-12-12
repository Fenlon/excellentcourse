package com.dph.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dph.exception.DaoException;

public class DaoUtils
{
	/***
	 * 切记要关闭连接，应为这个不是用datasource 而是自己获取的链接所以需要手动关闭 更新数据库中某一字段的值设置为value 条件为id
	 * 
	 * @param value
	 *            设置的值
	 * @param id
	 *            条件
	 * @param clazz
	 *            返回值类型
	 * @param sql
	 *            执行的sql语句
	 * @throws DaoException
	 *             抛出的自定义异常数据库层出现错误
	 */
	public static void update(Object value, String id, Class<?> clazz,
			String sql) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			// String sql = "update t_user set " + condition + "=? where id=?";
			Object params[] = {value, id};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/***
	 * 从数据库中取出值
	 * 
	 * @param field
	 *            对应数据库中的字段名
	 * @param conditionVal
	 *            sql语句中的条件值
	 * @param sql
	 *            sql语句
	 * @param clazz
	 *            想要返回的值类型
	 * @return
	 * @throws DaoException
	 *             抛出自定义异常
	 */
	public static <T> T getData(String field, Object conditionVal, String sql,
			Class<T> clazz) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcUtils.getConnection(), sql, conditionVal,
					new ScalarHandler<T>(field));
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public static <T> List<T> getListData(Object[] params, String sql,
			Class<T> clazz) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcUtils.getConnection(), sql, params,
					new BeanListHandler<T>(clazz));
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}
}
