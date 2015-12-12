package com.dph.domain;

import java.util.List;

import com.dph.constant.Constant;

public class PageBean
{
	private List list;
	private long totalrecord;
	private int pagesize;
	private int totalpage;
	private int currentpage;
	private int previouspage;
	private int nextpage;
	private int[] pagebar;

	public List getList()
	{
		return list;
	}

	public void setList(List list)
	{
		this.list = list;
	}

	public long getTotalrecord()
	{
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord)
	{
		this.totalrecord = totalrecord;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		if (pagesize < 1)
		{
			this.pagesize = 5;
		} else
		{
			this.pagesize = pagesize;
		}
	}

	public int getTotalpage()
	{
		if (this.totalrecord % this.pagesize == 0)
		{
			this.totalpage = (int) (this.totalrecord / this.pagesize);
		} else
		{
			this.totalpage = (int) (this.totalrecord / this.pagesize + 1);
		}
		return totalpage;
	}

	public int getCurrentpage()
	{
		return currentpage;
	}

	public void setCurrentpage(int currentpage)
	{
		this.currentpage = currentpage;
	}

	public int getPreviouspage()
	{
		if (this.currentpage - 1 < 1)
		{
			previouspage = 1;
		} else
		{
			previouspage = this.currentpage - 1;
		}
		return previouspage;
	}

	public int getNextpage()
	{
		if (this.currentpage + 1 > this.totalpage)
		{
			this.nextpage = this.totalpage;
		} else
		{
			this.nextpage = this.currentpage + 1;
		}
		return nextpage;
	}

	public int[] getPagebar()
	{
		int startPage;
		int endPage;
		int pageBar[] = null;

		int pagebarCount = Constant.PAGEBARCOUNT;
		if (this.getTotalpage() <= pagebarCount)
		{
			pageBar = new int[this.getTotalpage()];
			startPage = 1;
			endPage = this.totalpage;
		} else
		{
			pageBar = new int[pagebarCount];
			startPage = this.currentpage - (pagebarCount / 2) + 1;
			endPage = this.currentpage + (pagebarCount / 2);
			if (startPage < 1)
			{
				startPage = 1;
				endPage = pagebarCount;
			}

			if (endPage > this.totalpage)
			{
				endPage = this.totalpage;
				startPage = this.totalpage - pagebarCount + 1;
			}
		}

		int index = 0;
		for (int i = startPage; i <= endPage; i++)
		{
			pageBar[index] = i;
			index++;
		}
		this.pagebar = pageBar;
		return this.pagebar;
	}
}
