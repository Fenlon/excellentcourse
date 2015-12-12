package com.dph.domain;

import com.dph.constant.Constant;

public class QueryInfo
{
	private int currentpage = 1; // 当前页码 默认为1
	private int pagesize = Constant.MSG_COUNTPERPAGE; // 每一页显示的信息数目
	private int startindex; // 数据库开始检索位子

	public int getCurrentpage()
	{
		return currentpage;
	}

	public void setCurrentpage(int currentpage)
	{
		this.currentpage = currentpage;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		if (pagesize < 1)
		{
			pagesize = Constant.MSG_COUNTPERPAGE;
		}
		this.pagesize = pagesize;
	}

	public int getStartindex()
	{
		this.startindex = (currentpage - 1) * this.pagesize;
		return startindex;
	}
}
