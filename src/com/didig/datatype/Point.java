package com.didig.datatype;

import com.didig.exception.DivideByZeroException;

/**
 * Two-dimensional integer wrapper
 * @author Daniel Cuccia
 *
 */
public class Point 
{
	// ----------------------------------
	//		Member Variables
	// ----------------------------------
	
	public int x, y;
	
	
	// ----------------------------------
	//		Initialization
	// ----------------------------------
	
	/**
	 * initialize x = y = 0
	 */
	public Point()
	{
		this.x = this.y = 0;
	}
	
	/**
	 * initialize both values to input parameter
	 * @param value x and y equal to this
	 */
	public Point(int value)
	{
		this.x = this.y = value;
	}
	
	/**
	 * initialize both values independently
	 * @param x x value
	 * @param y y value
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * copy constructor, will not reference original Point
	 * @param value x and y values
	 */
	public Point(Point value)
	{
		this.x = value.x;
		this.y = value.y;
	}
	
	
	// ----------------------------------
	//			API
	// ----------------------------------
	
	/**
	 * toString() will return values
	 */
	@Override
	public String toString()
	{
		return "{X=" + this.x + ", Y=" + this.y + "}";
	}
	
	// ------------------------------------
	//			Static Functionality
	// ------------------------------------
	
	/**
	 * get a new Point initialized to one
	 */
	public static Point One()
	{
		return new Point(1);
	}
	
	/**
	 * get a new Point initialized to zero
	 * @return
	 */
	public static Point Zero()
	{
		return new Point(0);
	}
	
	/**
	 * Because we can't overload operators, out = one + two
	 * @param out output
	 * @param one first variable
	 * @param two second variable
	 */
	public static void Add(Point out, Point one, Point two)
	{
		out.x = one.x + two.x;
		out.y = one.y + two.y;
	}
	
	/**
	 * Because we can't overload operators, out = one - two
	 * @param out output
	 * @param one first variable
	 * @param two second variable
	 */
	public static void Subtract(Point out, Point one, Point two)
	{
		out.x = one.x - two.x;
		out.y = one.y - two.y;
	}
	
	/**
	 * Because we can't overload operators, out = one * two
	 * @param out output
	 * @param one first variable
	 * @param two second variable
	 */
	public static void Multiply(Point out, Point one, Point two)
	{
		out.x = one.x * two.x;
		out.y = one.y * two.y;
	}
	
	/**
	 * Because we can't overload operators, out = one / two
	 * @param out output
	 * @param one first variable
	 * @param two second variable
	 * @throws DivideByZeroException 
	 */
	public static void Divide(Point out, Point one, Point two) throws DivideByZeroException
	{
		if(two.x == 0 || two.y == 0)
			throw new DivideByZeroException("Point::Divide");
		
		out.x = one.x / two.x;
		out.y = one.y / two.y;
	}
}
