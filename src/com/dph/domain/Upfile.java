package com.dph.domain;

import java.util.Date;

public class Upfile
{
	private String id;
	private String uuidname;
	private String filename;
	private String savepath;
	private Date uptime;
	private String description;
	private String username;
	private String type;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUuidname()
	{
		return uuidname;
	}

	public void setUuidname(String uuidname)
	{
		this.uuidname = uuidname;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getSavepath()
	{
		return savepath;
	}

	public void setSavepath(String savepath)
	{
		this.savepath = savepath;
	}

	public Date getUptime()
	{
		return uptime;
	}

	public void setUptime(Date uptime)
	{
		this.uptime = uptime;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
