package com.dph.service;

import java.util.List;

import com.dph.domain.Forum;
import com.dph.exception.DaoException;

public interface ForumService
{

	void add(Forum forum) throws DaoException;

	void delete(int id) throws DaoException;

	void update(Forum forum) throws DaoException;

	Forum find(int id) throws DaoException;

	List<Forum> getForums() throws DaoException;

}