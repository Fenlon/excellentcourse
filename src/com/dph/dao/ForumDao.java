package com.dph.dao;

import java.util.List;

import com.dph.domain.Forum;
import com.dph.exception.DaoException;

public interface ForumDao
{

	void addForum(Forum forum) throws DaoException;

	void delete(int id) throws DaoException;

	void update(Forum forum) throws DaoException;

	Forum findForum(int id) throws DaoException;

	List<Forum> getAll() throws DaoException;

}