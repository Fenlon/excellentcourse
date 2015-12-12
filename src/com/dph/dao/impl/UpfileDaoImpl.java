package com.dph.dao.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dph.dao.UpfileDao;
import com.dph.domain.QueryResult;
import com.dph.domain.Upfile;
import com.dph.exception.DaoException;
import com.dph.utils.JdbcUtils;

public class UpfileDaoImpl implements UpfileDao
{

	/*
	 * id varchar(40) primary key, uuidname varchar(100) not null unique,
	 * filename varchar(100) not null, savepath varchar(255) not null, uptime
	 * datetime not null, description varchar(255), username varchar(40) not
	 * null
	 */
	public void add(Upfile upfile) throws DaoException
	{
		try
		{
			String type = "";
			QueryRunner runner = new QueryRunner();
			String fileType = upfile.getType();
			if (fileType.equals("jpg") || fileType == "bmp"
					|| fileType.equals("gif") || fileType.equals("JPG")
					|| fileType.equals("BMP") || fileType.equals("GIF"))
			{
				type = "image";
			} else if (fileType.equals("AVI") || fileType.equals("avi")
					|| fileType.equals("RMVB") || fileType.equals("rmvb")
					|| fileType.equals("DVD") || fileType.contains("rm"))
			{
				type = "movie";
			} else if (fileType.equals("mp3"))
			{
				type = "music";
			} else if (fileType.equals("doc") || fileType.equals("docx")
					|| fileType.equals("txt") || fileType.equals("xlsx"))
			{
				type = "doc";
			} else if (fileType.equals("zip") || fileType.equals("war")
					|| fileType.equals("rar"))
			{
				type = "package";
			} else
			{
				type = "others";
			}

			String sql = "insert into upfile(id,uuidname,filename,savepath,uptime,description,username,type) values(?,?,?,?,?,?,?,?)";
			Object params[] = {upfile.getId(), upfile.getUuidname(),
					upfile.getFilename(), upfile.getSavepath(),
					upfile.getUptime(), upfile.getDescription(),
					upfile.getUsername(), type};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (Exception e)
		{
			throw new DaoException(e);
		}
	}

	public QueryResult getAll(String type, int startIndex, int pageSize)
			throws DaoException
	{
		try
		{
			String sql = "";
			Object[] params;
			QueryResult result = new QueryResult();
			QueryRunner runner = new QueryRunner();
			if ("all".equals(type))
			{
				sql = "select * from upfile order by uptime desc limit ?,?";
				params = new Object[2];
				params[0] = startIndex;
				params[1] = pageSize;
			} else
			{
				sql = "select * from upfile where type=? order by uptime desc limit ?,?";
				params = new Object[3];
				params[0] = type;
				params[1] = startIndex;
				params[2] = pageSize;
			}
			List<Upfile> files = runner.query(JdbcUtils.getConnection(), sql,
					params, new BeanListHandler(Upfile.class));
			int totalrecord = count(type);
			result.setTotalrecord(totalrecord);
			result.setList(files);
			return result;
		} catch (Exception e)
		{
			throw new DaoException(e);
		}
	}

	private int count(String type) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			if ("all".equals(type))
			{
				String sql = "select  count(*) from upfile ";
				Object[] a = runner.query(JdbcUtils.getConnection(), sql,
						new ArrayHandler());
				return Integer.parseInt(a[0].toString());
			} else
			{
				String sql = "select count(*) from upfile where type=?";
				Object[] a = runner.query(JdbcUtils.getConnection(), sql, type,
						new ArrayHandler());
				return Integer.parseInt(a[0].toString());
			}

		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	public Upfile find(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "select * from upfile where id=?";
			return (Upfile) runner.query(JdbcUtils.getConnection(), sql, id,
					new BeanHandler(Upfile.class));
		} catch (Exception e)
		{
			throw new DaoException(e);
		}
	}

	public void delete(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			Upfile file = find(id);
			String filepath = file.getSavepath() + File.separator
					+ file.getUuidname();
			System.out.println(filepath);
			File file2 = new File(filepath);
			if (file2.exists())
			{
				file2.delete();
			}
			String sql = "delete from upfile where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (Exception e)
		{
			throw new DaoException(e);
		}
	}

	public void update(Upfile upfile)
	{

	}

}
