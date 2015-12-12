package com.dph.service;

import java.util.List;

import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.manager.Resource;
import com.dph.manager.Role;

public interface ManagerService
{

	// 提供资源相关的服务
	void addResource(Resource r) throws DaoException;

	Resource findByID(String id) throws DaoException;

	Resource findResource(String uri) throws DaoException;

	List getAllResource() throws DaoException;

	void updateResource(Resource r) throws DaoException;

	void deleteLink(String id) throws DaoException;

	void updateResourcePrivilege(Resource r, Privilege p) throws DaoException;

	void addPrivilege(Privilege p) throws DaoException;

	Privilege findPrivilege(String id) throws DaoException;

	void deletePrivilege(String id) throws DaoException;

	void updatePrivilege(Privilege p) throws DaoException;

	List<Privilege> getAllPrivilege() throws DaoException;

	void addRole(Role role) throws DaoException;

	void deleteRole(String id) throws DaoException;;

	List<Role> getAllRole() throws DaoException;

	Role findRole(String id) throws DaoException;

	void updateRole(Role r) throws DaoException;

	void updateRolePrivilege(Role r, List<Privilege> ps) throws DaoException;

}