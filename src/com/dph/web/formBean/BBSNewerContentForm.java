package com.dph.web.formBean;

public class BBSNewerContentForm
{
	private String title;
	private String name;
	private String college;
	private String motto;// 个人学习格言
	private String Content;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

	public String getMotto()
	{
		return motto;
	}

	public void setMotto(String motto)
	{
		this.motto = motto;
	}

	public String getContent()
	{
		return Content;
	}

	public void setContent(String content)
	{
		Content = content;
	}

	@Override
	public String toString()
	{
		return "BBSNewerContentForm [title=" + title + ", name=" + name
				+ ", college=" + college + ", motto=" + motto + ", Content="
				+ Content + "]";
	}

}
