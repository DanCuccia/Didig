package com.didig.datatype;

/**
 * Two-Dimensional float dataType
 * @author Daniel Cuccia
 * 
 */
public class Vector2 
{
	// --------------------------------------------
	//		Member Variables
	// --------------------------------------------

	public float x, y;
	
	
	// --------------------------------------------
	//		Initialization
	// --------------------------------------------
	/**
	 * Initialize values to 0
	 */
	public Vector2()
	{
		x = y = 0f;
	}
	
	/**
	 * initialize x and y to value
	 * @param value value for both fields
	 */
	public Vector2(float value)
	{
		x = y = value;
	}
	
	/**
	 * initialize fields separately
	 * @param x x value
	 * @param y y value
	 */
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * copy ctor, will not point back to original value
	 * @param value
	 */
	public Vector2(Vector2 value)
	{
		this.x = value.x;
		this.y = value.y;
	}
	
	
	// --------------------------------------------
	//		API
	// --------------------------------------------
	
	/**
	 * Normalize values to equal 1, member variables are changed. 
	 */
	public void Normalize()
	{
		float length = (float) Math.sqrt( (this.x * this.x) + (this.y * this.y) );
		this.x /= length;
		this.y /= length;
	}
	
	/**
	 * Set this vector from another
	 * @param value original values
	 * @throws ArgumentNullException if value == null
	 */
	public void Set(Vector2 value)
	{
		this.x = value.x;
		this.y = value.y;
	}
	
	/**
	 * Get the magnitude of this vector
	 * @return magnitude
	 */
	public float Magnitude()
	{
		return (float)Math.sqrt(x * x + y * y);
	}
	
	/**
	 * toString() will now display values
	 */
	@Override
	public String toString()
	{
		return "{Vector2: X=" + this.x + ", Y=" + this.y + "}";
	}
	
	//---------------------------------------
	//		STATIC FUNCTIONAITY
	//---------------------------------------
	
	/**
	 * Get a new Vector2 with both values equal to 1f
	 */
	public static Vector2 One()
	{
		return new Vector2(1f);
	}
	
	/**
	 * Get a new Vector2 with both values equal to 0f
	 */
	public static Vector2 Zero()
	{
		return new Vector2(0f);
	}
	
	/**
	 * float distance between two Vector2 variables
	 */
	public static float Distance(Vector2 one, Vector2 two)
	{
		if(one == null || two == null)
			return -1f;
		
		return (float)Math.sqrt(Math.pow(one.x - two.x, 2) + Math.pow(one.y - two.y, 2));
	}
	
	/**
	 * Normalize an input Vector2, without changing the input parameter
	 * @param input Vector2 to be normalized
	 * @return The Normalized Vector2, orignal input is not changed
	 */
	public static Vector2 Normalize(Vector2 input)
	{
		if(input == null)
			return Vector2.Zero();
		
		
		float length = (float) Math.sqrt( (input.x * input.x) + (input.y * input.y) );
		input.x /= length;
		input.y /= length;
		return input;
	}
	
	/**
	 * Because we can't overload the + operator... you may call this instead
	 * @param out output of (one + two)
	 * @param one value1 to be added
	 * @param two value2 to be added
	 */
	public static void Add(Vector2 out, Vector2 one, Vector2 two)
	{
		if(one == null || two == null || out == null)
			return;
		
		
		out.x = one.x + two.x;
		out.y = one.x + two.x;
	}
	
	/**
	 * Because we can't overload the - operator... you may call this instead
	 * @param out output of (one - two)
	 * @param one value1 which is subtracted by value2
	 * @param two value2 to subtract from 1
	 */
	public static void Subtract(Vector2 out, Vector2 one, Vector2 two)
	{
		if(one == null || two == null || out == null)
			return;
		
		
		out.x = one.x - two.x;
		out.y = one.y - two.y;
	}
	
	/**
	 * Because we can't overload the * operator... you may call this instead
	 * @param out output of (one * two)
	 * @param one value1 to be multiplied
	 * @param two value2 to be multiplied
	 */
	public static void Multiply(Vector2 out, Vector2 one, Vector2 two)
	{
		if(one == null || two == null || out == null)
			return;
		
		out.x = one.x * two.x;
		out.y = one.y * two.y;
	}
	
	/**
	 * Because we can't overload the / operator... you may call this instead
	 * @param out output of (one / two)
	 * @param one value1 to be divided by two
	 * @param two value2 to divide one with
	 * @throws DivideByZeroException if two.x or two.y is equal to 0
	 */
	public static void Divide(Vector2 out, Vector2 one, Vector2 two)
	{
		if(one == null || two == null || out == null)
			return;
		if(two.x == 0 || two.y == 0)
			return;
		
		out.x = one.x / two.x;
		out.y = one.y / two.y;
	}
}
