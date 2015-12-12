package com.dph.service;

import java.util.List;

import com.dph.domain.BBSMsg;
import com.dph.exception.DaoException;

public interface BBSMsgBussinessService
{
	void addBBSMsg(BBSMsg msg) throws DaoException;

	BBSMsg findBbsMsg(String id) throws DaoException;

	List<BBSMsg> getRemindMsgs(String username) throws DaoException;

	void updateIsRead(String id) throws DaoException;

	void deleteBBSMsg(String id) throws DaoException;
}