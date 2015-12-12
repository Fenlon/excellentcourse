package com.dph.service.impl;

import java.util.List;

import com.dph.dao.DaoFactory;
import com.dph.dao.PrivilegeDao;
import com.dph.dao.ResourceDao;
import com.dph.dao.RoleDao;
import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.manager.Resource;
import com.dph.manager.Role;
import com.dph.service.ManagerService;
import com.dph.service.Permission;

public class ManagerServiceImpl implements ManagerService
{
	private ResourceDao resDao = DaoFactory.getInstance().getDao(
			ResourceDao.class);
	private PrivilegeDao pDao = DaoFactory.getInstance().getDao(
			PrivilegeDao.class);
	private RoleDao roleDao = DaoFactory.getInstance().getDao(RoleDao.class);

	// 提供资源相关的服务
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.ManagerService#addResource(com.dph.manager.Resource)
	 */
	@Permission("添加资源")
	public void addResource(Resource r) throws DaoException
	{
		resDao.add(r);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#findByID(java.lang.String)
	 */
	public Resource findByID(String id) throws DaoException
	{
		return resDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#findResource(java.lang.String)
	 */
	public Resource findResource(String uri) throws DaoException
	{
		return resDao.find(uri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#getAllResource()
	 */
	public List getAllResource() throws DaoException
	{
		return resDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.ManagerService#updateResource(com.dph.manager.Resource
	 * )
	 */
	public void updateResource(Resource r) throws DaoException
	{
		resDao.update(r);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.ManagerService#updateResourcePrivilege(com.dph.manager
	 * .Resource, com.dph.manager.Privilege)
	 */
	public void updateResourcePrivilege(Resource r, Privilege p)
			throws DaoException
	{
		resDao.updatePrivilege(r, p);
	}

	public void deleteLink(String id) throws DaoException
	{
		resDao.delete(id);
	}

	// 提供权限相关的服务

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.ManagerService#addRole(com.dph.manager.Privilege)
	 */
	@Permission("添加权限")
	public void addPrivilege(Privilege p) throws DaoException
	{
		pDao.add(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#findPrivilege(java.lang.String)
	 */
	public Privilege findPrivilege(String id) throws DaoException
	{
		return pDao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.ManagerService#deletePrivilege(java.lang.String)
	 */
	public void deletePrivilege(String id) throws DaoException
	{
		pDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.ManagerService#updatePrivilege(com.dph.manager.Privilege
	 * )
	 */
	public void updatePrivilege(Privilege p) throws DaoException
	{
		pDao.update(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#getAllPrivilege()
	 */
	public List<Privilege> getAllPrivilege() throws DaoException
	{
		return pDao.getAll();
	}

	// 提供角色相关的服务

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#addRole(com.dph.manager.Role)
	 */
	public void addRole(Role role) throws DaoException
	{
		roleDao.add(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#deleteRole(java.lang.String)
	 */
	public void deleteRole(String id) throws DaoException
	{
		roleDao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#getAllRole()
	 */
	public List<Role> getAllRole() throws DaoException
	{
		return roleDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#findRole(java.lang.String)
	 */
	public Role findRole(String id) throws DaoException
	{
		return roleDao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.service.impl.ManagerService#updateRole(com.dph.manager.Role)
	 */
	public void updateRole(Role r) throws DaoException
	{
		roleDao.update(r);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dph.service.impl.ManagerService#updateRolePrivilege(com.dph.manager
	 * .Role, java.util.List)
	 */
	public void updateRolePrivilege(Role r, List<Privilege> ps)
			throws DaoException
	{
		roleDao.updatePrivilege(r, ps);
	}
}
