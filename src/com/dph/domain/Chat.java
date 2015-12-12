package com.dph.domain;

import java.io.Serializable;

public class Chat implements Serializable
{
	private String fromuname;
	private String fromuip;
	private String touname;
	private String chatkey;// 聊天之间共有的标识
	private boolean ischat;

	public boolean isIschat()
	{
		return ischat;
	}

	public void setIschat(boolean ischat)
	{
		this.ischat = ischat;
	}

	public String getFromuname()
	{
		return fromuname;
	}

	@Override
	public String toString()
	{
		return "Chat [fromuname=" + fromuname + ", fromuip=" + fromuip
				+ ", touname=" + touname + ", chatkey=" + chatkey + ", ischat="
				+ ischat + "]";
	}

	public void setFromuname(String fromuname)
	{
		this.fromuname = fromuname;
	}

	public String getFromuip()
	{
		return fromuip;
	}

	public void setFromuip(String fromuip)
	{
		this.fromuip = fromuip;
	}

	public String getTouname()
	{
		return touname;
	}

	public void setTouname(String touname)
	{
		this.touname = touname;
	}

	public String getChatkey()
	{
		return chatkey;
	}

	public void setChatkey(String chatkey)
	{
		this.chatkey = chatkey;
	}

}
