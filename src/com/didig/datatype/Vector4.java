package com.didig.datatype;

import com.didig.exception.ArgumentNullException;
import com.didig.exception.DivideByZeroException;

public class Vector4 
{
	// --------------------------------------------
	//		Member Variables
	// --------------------------------------------

	public float x, y, z, w;
	
	
	// --------------------------------------------
	//		Initialization
	// --------------------------------------------
	/**
	 * Initialize values to 0
	 */
	public Vector4()
	{
		x = y = z = w = 0f;
	}
	
	/**
	 * initialize x and y to value
	 * @param value value for both fields
	 */
	public Vector4(float value)
	{
		x = y = z = w = value;
	}
	
	/**
	 * initialize fields separately
	 * @param x x value
	 * @param y y value
	 */
	public Vector4(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	/**
	 * copy constructor, will not point back to original datatype
	 * @param value
	 */
	public Vector4(Vector4 value)
	{
		this.x = value.x;
		this.y = value.y;
		this.z = value.z;
		this.w = value.w;
	}
	
	
	// --------------------------------------------
	//		API
	// --------------------------------------------
	
	/**
	 * Normalize values to equal 1, member variables are changed. 
	 */
	public void Normalize()
	{
		float length = (float) Math.sqrt( (this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w));
		this.x /= length;
		this.y /= length;
		this.z /= length;
		this.w /= length;
	}
	
	/**
	 * toString() will now display values
	 */
	@Override
	public String toString()
	{
		return "{X=" + this.x + ", Y=" + this.y + ", Z=" + this.z + ", W=" + this.w + "}";
	}
	
	//---------------------------------------
	//		STATIC FUNCTIONAITY
	//---------------------------------------
	
	/**
	 * Get a new Vector3 with both values equal to 1f
	 */
	public static Vector4 One()
	{
		return new Vector4(1f);
	}
	
	/**
	 * Get a new Vector3 with both values equal to 0f
	 */
	public static Vector4 Zero()
	{
		return new Vector4(0f);
	}
	
	/**
	 * float distance between two Vector3 variables
	 * @throws ArgumentNullException 
	 */
	public static float Distance(Vector4 one, Vector4 two) throws ArgumentNullException
	{
		if(one == null || two == null)
		{
			throw new ArgumentNullException("Vector4.Distance");
		}
		return (float)Math.sqrt(Math.pow(one.x - two.x, 2) + 
				Math.pow(one.y - two.y, 2) + 
				Math.pow(one.z - two.z, 2) + 
				Math.pow(one.w - two.w, 2));
	}
	
	/**
	 * Normalize an input Vector4, without changing the input parameter
	 * @param input Vector4 to be normalized
	 * @return The Normalized Vector4, original input is not changed
	 * @throws ArgumentNullException 
	 */
	public static Vector4 Normalize(Vector4 input) throws ArgumentNullException
	{
		if(input == null)
		{
			throw new ArgumentNullException("Vector4.Normalize");
		}
		
		float length = (float) Math.sqrt( (input.x * input.x) + (input.y * input.y) + (input.z * input.z) + (input.w * input.w));
		input.x /= length;
		input.y /= length;
		input.z /= length;
		input.w /= length;
		return input;
	}
	
	/**
	 * Because we can't overload the + operator... you may call this instead
	 * @param out output of (one + two)
	 * @param one value1 to be added
	 * @param two value2 to be added
	 * @throws ArgumentNullException 
	 */
	public static void Add(Vector4 out, Vector4 one, Vector4 two) throws ArgumentNullException
	{
		if(one == null || two == null || out == null)
		{
			throw new ArgumentNullException("Vector3.Add");
		}
		
		out.x = one.x + two.x;
		out.y = one.x + two.x;
		out.z = one.z + two.z;
		out.w = one.w + two.w;
	}
	
	/**
	 * Because we can't overload the - operator... you may call this instead
	 * @param out output of (one - two)
	 * @param one value1 which is subtracted by value2
	 * @param two value2 to subtract from 1
	 * @throws ArgumentNullException 
	 */
	public static void Subtract(Vector4 out, Vector4 one, Vector4 two) throws ArgumentNullException
	{
		if(one == null || two == null || out == null)
		{
			throw new ArgumentNullException("Vector3.Subtract");
		}
		
		out.x = one.x - two.x;
		out.y = one.y - two.y;
		out.z = one.z - two.z;
		out.w = one.w - two.w;
	}
	
	/**
	 * Because we can't overload the * operator... you may call this instead
	 * @param out output of (one * two)
	 * @param one value1 to be multiplied
	 * @param two value2 to be multiplied
	 * @throws ArgumentNullException 
	 */
	public static void Multiply(Vector4 out, Vector4 one, Vector4 two) throws ArgumentNullException
	{
		if(one == null || two == null || out == null)
		{
			throw new ArgumentNullException("Vector3.Multiply");
		}
		
		out.x = one.x * two.x;
		out.y = one.y * two.y;
		out.z = one.z * two.z;
		out.w = one.w * two.w;
	}
	
	/**
	 * Because we can't overload the / operator... you may call this instead
	 * @param out output of (one / two)
	 * @param one value1 to be divided by two
	 * @param two value2 to divide one with
	 * @throws DivideByZeroException if two.x or two.y is equal to 0
	 */
	public static void Divide(Vector4 out, Vector4 one, Vector4 two) throws DivideByZeroException, ArgumentNullException
	{
		if(one == null || two == null || out == null)
		{
			throw new ArgumentNullException("Vector3.Divide");
		}
		if(two.x == 0 || two.y == 0 || two.z == 0 || two.w == 0)
		{
			throw new DivideByZeroException("Vector3.Divide");
		}
		
		out.x = one.x / two.x;
		out.y = one.y / two.y;
		out.z = one.z / two.z;
		out.w = one.w / two.w;
	}
}
