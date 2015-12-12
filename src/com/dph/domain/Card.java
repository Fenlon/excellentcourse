package com.dph.domain;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.dph.constant.Constant;
import com.dph.utils.DomainUtils;

public class Card
{
	private String id;
	private String title;
	private String content;
	private Date timecreate;
	// private String autherName;
	// private String lastUserName;
	private Date timelast;

	private User auther;
	private User lastuser;
	private Forum forum;
	private String createtime;
	private String lasttime;

	public User getAuther()
	{
		return auther;
	}

	public void setAuther(User auther)
	{
		this.auther = auther;
	}

	public User getLastuser()
	{
		return lastuser;
	}

	public void setLastuser(User lastuser)
	{
		this.lastuser = lastuser;
	}

	public String getCreatetime()
	{
		return createtime;
	}

	public String getLasttime()
	{
		return lasttime;
	}

	public Forum getForum()
	{
		return forum;
	}

	public void setForum(Forum forum)
	{
		this.forum = forum;
	}

	private long answernum;
	private long browsenum;

	public long getAnswernum()
	{
		return answernum;
	}

	public void setAnswernum(long answernum)
	{
		this.answernum = answernum;
	}

	public long getBrowsenum()
	{
		return browsenum;
	}

	public void setBrowsenum(long browsenum)
	{
		this.browsenum = browsenum;
	}

	/*
	 * public int getForumid() { return forumid; }
	 * 
	 * public void setForumid(int forumid) { this.forumid = forumid; }
	 */

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

	public Date getTimecreate()
	{
		return timecreate;
	}

	public void setTimecreate(Date timecreate)
	{
		this.timecreate = timecreate;

		long num = DomainUtils.getNum(this.timecreate);
		int day = (int) (num / Constant.ONEDATMISECONDS);

		if (day > 7)
		{
			createtime = DateFormatUtils.format(timecreate, "yyyy-MM-dd hh:mm");
			return;
		}
		// 下面说明是7天之内的回帖
		if (day == 0)
		{
			int hour = (int) (num / 3600000);
			if (hour == 0)
			{
				int m = (int) (num / 60000);
				if (m == 0)
				{
					createtime = num / 1000 + "秒前";
					return;
				}
				createtime = m + "分钟前";
				return;
			}
			createtime = hour + "小时前";
			return;
		}
		createtime = day + "天前";
	}

	public Date getTimelast()
	{
		return timelast;
	}

	public void setTimelast(Date timelast)
	{
		this.timelast = timelast;

		long num = DomainUtils.getNum(this.timecreate);
		int day = (int) (num / Constant.ONEDATMISECONDS);

		if (day > 7)
		{
			this.lasttime = DateFormatUtils
					.format(timelast, "yyyy-MM-dd hh:mm");
			return;
		}
		// 下面说明是7天之内的回帖
		if (day == 0)
		{

			int hour = (int) (num / 3600000);
			if (hour == 0)
			{

				int m = (int) (num / 60000);
				if (m == 0)
				{

					this.lasttime = num / 1000 + "秒前";
					return;
				}
				this.lasttime = m + "分钟前";
				return;
			}
			this.lasttime = hour + "小时前";
			return;
		}

		this.lasttime = day + "天前";
	}

	/*
	 * public String getAutherName() { return autherName; }
	 * 
	 * public void setAutherName(String autherName) { this.autherName =
	 * autherName; }
	 */

	/*
	 * public String getLastUserName() { return lastUserName; }
	 * 
	 * public void setLastUserName(String lastUserName) { this.lastUserName =
	 * lastUserName; }
	 */
}
