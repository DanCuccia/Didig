package com.didig.exception;

public class ArgumentNullException extends Exception
{
	private static final long serialVersionUID = 1L;

	public ArgumentNullException()
	{
		super("Null Reference Encountered");
	}
	
	public ArgumentNullException(String locationInfo)
	{
		super("Null Reference Encountered at = " + locationInfo);
	}
}
