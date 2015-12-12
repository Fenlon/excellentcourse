package com.dph.web.formBean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.dph.web.UI.RandomImageServlet;

public class RegisterForm
{
	private String username;
	private String password;
	private String password2;
	private String gender;
	private String nickname;
	private String birthday;
	private String email;
	private String description;
	private String checkimgcode;

	private Map<String, String> errors = new HashMap<String, String>();

	// 用户名不能为空(3-8字母或者下划线_或则数字0-9)
	// 密码不能为空(a-z A-Z _ 0-9)（6-10位数字）
	// 确认密码，必须和前一次一样
	// 电子邮箱不能为空（要求格式合法）
	// 生日可以为空，但是如果不为空则要求必须是一个日期格式
	// 昵称不能为空
	public boolean validate()
	{
		boolean isOK = true;
		if (this.username == null || this.username.trim().equals(""))
		{
			isOK = false;
			this.errors.put("username", "用户名不能为空!");
		} else
		{
			if (!this.username.matches("[0-9A-Za-z_]{3,8}"))
			{
				isOK = false;
				errors.put("username", "用户名只能为字母下划线数字并且3-8位!");
			}
		}

		if (this.password == null || this.password.trim().equals(""))
		{
			isOK = false;
			this.errors.put("password", "密码不能为空!");
		} else
		{
			if (!this.password.matches("[0-9A-Za-z_]{6,10}"))
			{
				isOK = false;
				errors.put("password", "密码可包含a-z A-Z _ 0-9,且为6-10位！");
			}
		}

		if (this.password2 == null || this.password2.trim().equals(""))
		{
			isOK = false;
			this.errors.put("password2", "确认密码不能为空!");
		} else
		{
			if (!this.password.equals(password2))
			{
				isOK = false;
				errors.put("password2", "密码不一致!");
			}
		}

		if (this.email == null || this.email.trim().equals(""))
		{
			isOK = false;
			this.errors.put("email", "邮箱不能为空!");
		} else
		{
			// www.ss@sina.com.cn
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+"))
			{
				isOK = false;
				errors.put("email", "请填写正确的邮箱格式!");
			}
		}

		if (this.birthday != null && !birthday.trim().equals(""))
		{
			try
			{
				DateLocaleConverter converter = new DateLocaleConverter();
				converter.convert(this.birthday, "yyyy-MM-dd");
			} catch (Exception e)
			{
				isOK = false;
				this.errors.put("birthday", "日期格式非法!");
			}
		}

		if (this.nickname == null || this.nickname.trim().equals(""))
		{
			isOK = false;
			this.errors.put("nickname", "昵称不能为空!!");
		} else
		{
			if (!this.nickname.matches("^([\u4e00-\u9fa5]+)[0-9]*"))
			{
				isOK = false;
				errors.put("nickname", "请填写正确的昵称格式（汉字（_0-9））!");
			}
		}

		if (this.description.trim().length() >= 100)
		{
			isOK = false;
			errors.put("description", "个人描述不能大于100个字符!");
		}

		if (this.checkimgcode == null || this.checkimgcode.trim().equals(""))
		{
			isOK = false;
			this.errors.put("checkimgcode", "验证码不能为空!!");
		} else
		{
			String checkimgcode = RandomImageServlet.getCheckimgcode();
			if (!this.checkimgcode.equals(checkimgcode))
			{
				isOK = false;
				errors.put("checkimgcode", "验证码填写错误!");
			}
		}
		RandomImageServlet.setCheckimgcode("");
		return isOK;
	}

	public String getCheckimgcode()
	{
		return checkimgcode;
	}

	public void setCheckimgcode(String checkimgcode)
	{
		this.checkimgcode = checkimgcode;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Map<String, String> getErrors()
	{
		return errors;
	}

	public void setErrors(Map<String, String> errors)
	{
		this.errors = errors;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword2()
	{
		return password2;
	}

	public void setPassword2(String password2)
	{
		this.password2 = password2;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getBirthday()
	{
		return birthday;
	}

	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
