package com.dph.service.impl;

import java.util.List;

import com.dph.dao.DaoFactory;
import com.dph.dao.ForumDao;
import com.dph.domain.Forum;
import com.dph.exception.DaoException;
import com.dph.service.ForumService;

public class ForumServiceImpl implements ForumService
{
	ForumDao dao = DaoFactory.getInstance().getDao(ForumDao.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ForumService#add(com.dph.domain.Forum)
	 */
	public void add(Forum forum) throws DaoException
	{
		dao.addForum(forum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ForumService#delete(int)
	 */
	public void delete(int id) throws DaoException
	{
		dao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ForumService#update(com.dph.domain.Forum)
	 */
	public void update(Forum forum) throws DaoException
	{
		dao.update(forum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ForumService#find(int)
	 */
	public Forum find(int id) throws DaoException
	{
		return dao.findForum(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ForumService#getForums()
	 */
	public List<Forum> getForums() throws DaoException
	{
		return dao.getAll();
	}
}
