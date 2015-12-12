package com.dph.service.impl;

import java.util.List;

import com.dph.dao.BBSMsgDao;
import com.dph.dao.DaoFactory;
import com.dph.domain.BBSMsg;
import com.dph.exception.DaoException;
import com.dph.service.BBSMsgBussinessService;

public class BBSMsgBusinessServiceImpl implements BBSMsgBussinessService
{
	BBSMsgDao dao = DaoFactory.getInstance().getDao(BBSMsgDao.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.BBSMsgBussinessService#addBBSMsg(com.dph.domain.
	 * BBSMsg)
	 */
	public void addBBSMsg(BBSMsg msg) throws DaoException
	{
		dao.addBBSMsg(msg);
	}

	public BBSMsg findBbsMsg(String id) throws DaoException
	{
		return dao.findBBSMsg(id);
	}

	@Override
	public List<BBSMsg> getRemindMsgs(String username) throws DaoException
	{

		return dao.getRemindMsgs(username);

	}

	public void updateIsRead(String id) throws DaoException
	{
		dao.update("isread", true, id);
	}

	public void deleteBBSMsg(String id) throws DaoException
	{
		dao.delete(id);
	}

}
