package com.didig.exception;

public class DivideByZeroException extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	public DivideByZeroException()
	{
		super("Cannot Divide By Zero");
	}
	
	public DivideByZeroException(String location)
	{
		super("Attept to Divide By Zero at = " + location);
	}

}
