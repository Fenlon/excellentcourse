package com.dph.web.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.manager.Privilege;
import com.dph.service.ManagerService;
import com.dph.service.UserBusinessService;
import com.dph.service.impl.ManagerServiceImpl;
import com.dph.service.impl.UserBusinessServiceImpl;

public class PermissionTag extends SimpleTagSupport
{

	private String value;

	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public void doTag() throws JspException, IOException
	{

		// 关断用户拥有权限值中，是否包含value
		PageContext pagecontext = (PageContext) this.getJspContext();
		HttpSession session = pagecontext.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null)
		{
			// ManagerService service = new ManagerServiceImpl();
			try
			{
				UserBusinessService service = new UserBusinessServiceImpl();
				List<Privilege> privileges = service.getUserPrivileges(user
						.getId());
				boolean b = false;
				for (Privilege p : privileges)
				{
					if (p.getName().equals(value))
					{
						b = true;
						break;
					}
				}
				if (b)
				{
					this.getJspBody().invoke(null);
				}
			} catch (DaoException e)
			{
				e.printStackTrace();
			}

		}
	}
}
