package com.didig.exception;

public class InvalidOperationException extends Exception
{
	private static final long serialVersionUID = 1L;

	public InvalidOperationException()
	{
		super("Invalid Operation");
	}
	
	public InvalidOperationException(String location)
	{
		super("Invalid Operation at = " + location);
	}
}
