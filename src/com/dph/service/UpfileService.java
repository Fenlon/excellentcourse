package com.dph.service;

import java.util.List;

import com.dph.domain.PageBean;
import com.dph.domain.QueryInfo;
import com.dph.domain.Upfile;
import com.dph.exception.DaoException;

public interface UpfileService
{
	void addUpfile(Upfile upfile) throws DaoException;

	public PageBean getAllUpfile(QueryInfo info, String type)
			throws DaoException;

	Upfile findUpfile(String id) throws DaoException;

	void deleteFile(String id) throws DaoException;

}