package com.dph.exception;

public class UserHasExistException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserHasExistException()
	{
		// TODO Auto-generated constructor stub
	}

	public UserHasExistException(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserHasExistException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserHasExistException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserHasExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
