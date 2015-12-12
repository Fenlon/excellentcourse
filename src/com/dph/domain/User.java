package com.dph.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dph.manager.Role;

public class User
{
	private Set<Role> roles = new HashSet<Role>();

	private String id;
	private String username;
	private String password;
	private String gender;
	private Date birthday;
	private String email;
	private String nickname;
	private String description;

	private String photopath;
	private int msgcount;

	public String getPhotopath()
	{
		return photopath;
	}

	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}

	public void setPhotopath(String photopath)
	{
		if (photopath == null || photopath == "")
		{
			this.photopath = "";
			return;
		}
		this.photopath = photopath;
	}

	public int getMsgcount()
	{
		return msgcount;
	}

	public void setMsgcount(int msgcount)
	{
		this.msgcount = msgcount;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
