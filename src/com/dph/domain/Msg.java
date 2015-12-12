package com.dph.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;

import com.dph.constant.Constant;
import com.dph.utils.DomainUtils;

public class Msg
{
	private String id;
	private String title;
	private String content;
	private String userip;
	private Date time;
	private String msgparent;
	private String strtime;
	private User user;
	private List<Msg> huifus;

	public List<Msg> getHuifus()
	{
		return huifus;
	}

	public void setHuifus(List<Msg> huifus)
	{
		this.huifus = huifus;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getUserip()
	{
		return userip;
	}

	public void setUserip(String userip)
	{
		this.userip = userip;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
		long num = DomainUtils.getNum(this.time);
		int day = (int) (num / Constant.ONEDATMISECONDS);
		if (day > 5)
		{
			strtime = DateFormatUtils.format(time, "yyyy-MM-dd hh:mm");
			return;
		}
		// 下面说明是5天之内的回帖
		if (day == 0)
		{
			int hour = (int) (num / 3600000);
			if (hour == 0)
			{
				int m = (int) (num / 60000);
				if (m == 0)
				{
					strtime = num / 1000 + "秒前";
					return;
				}
				strtime = m + "分钟前";
				return;
			}
			strtime = hour + "小时前";
			return;
		}
		strtime = day + "天前";
	}

	public String getMsgparent()
	{
		return msgparent;
	}

	public void setMsgparent(String msgparent)
	{
		this.msgparent = msgparent;
	}

	public String getStrtime()
	{
		return strtime;
	}

	public void setStrtime(String strtime)
	{
		this.strtime = strtime;
	}
}
