package com.dph.service.impl;

import com.dph.dao.DaoFactory;
import com.dph.dao.UpfileDao;
import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.QueryResult;
import com.dph.domain.Upfile;
import com.dph.exception.DaoException;
import com.dph.service.Permission;
import com.dph.service.UpfileService;
import com.dph.utils.ServiceUtils;

public class UpfileServiceImpl implements UpfileService
{
	private UpfileDao dao = DaoFactory.getInstance().getDao(UpfileDao.class);

	@Permission("上传文件")
	public void addUpfile(Upfile upfile) throws DaoException
	{
		dao.add(upfile);
	}

	public PageBean getAllUpfile(QueryInfo info, String type)
			throws DaoException
	{
		QueryResult queryResult = dao.getAll(type, info.getStartindex(),
				info.getPagesize());
		return ServiceUtils.getPageBeanFromQueryResult(info, queryResult);
	}

	public Upfile findUpfile(String id) throws DaoException
	{
		return dao.find(id);
	}

	@Permission("删除文件")
	public void deleteFile(String id) throws DaoException
	{
		dao.delete(id);
	}

}
