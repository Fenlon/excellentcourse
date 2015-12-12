package com.dph.domain;

import java.util.List;

public class QueryResult
{
	private List list; // 查询信息
	private long totalrecord; // 总共记录数

	public List getList()
	{
		return list;
	}

	public long getTotalrecord()
	{
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord)
	{
		this.totalrecord = totalrecord;
	}

	public void setList(List list)
	{
		this.list = list;
	}
}
