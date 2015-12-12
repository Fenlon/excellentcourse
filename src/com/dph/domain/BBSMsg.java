package com.dph.domain;

import java.util.Date;

public class BBSMsg
{
	private String id;
	private String content;
	private Date time;
	// private String username;
	private BBSMsg parentmsg;
	private Card card;
	private boolean isread;
	private String parentusername;
	private User user;

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Card getCard()
	{
		return card;
	}

	public void setCard(Card card)
	{
		this.card = card;
	}

	public String getParentusername()
	{
		return parentusername;
	}

	public void setParentusername(String parentusername)
	{
		this.parentusername = parentusername;
	}

	public boolean isIsread()
	{
		return isread;
	}

	public void setIsread(boolean isread)
	{
		this.isread = isread;
	}

	public BBSMsg getParentmsg()
	{
		return parentmsg;
	}

	public void setParentmsg(BBSMsg parentmsg)
	{
		this.parentmsg = parentmsg;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	/*
	 * public String getUsername() { return username; }
	 * 
	 * public void setUsername(String username) { this.username = username; }
	 */
}
