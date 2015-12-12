package com.dph.manager;

public class Resource
{
	private String id;
	private String uri; // /Excellent Course/servlet/Servlet1
	private String description;

	private Privilege privilege;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUri()
	{
		return uri;
	}

	public void setUri(String uri)
	{
		this.uri = uri;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Privilege getPrivilege()
	{
		return privilege;
	}

	public void setPrivilege(Privilege privilege)
	{
		this.privilege = privilege;
	}

}
