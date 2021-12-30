package com.didig.datatype;

import android.annotation.SuppressLint;
import android.util.*;
/**
 * 3-Dimensional float datatype
 * @author Daniel Cuccia
 *
 */
@SuppressLint("FloatMath")
public class Vector3 
{
	// --------------------------------------------
	//		Member Variables
	// --------------------------------------------

	public float x, y, z;
	
	
	// --------------------------------------------
	//		Initialization
	// --------------------------------------------
	/**
	 * Initialize values to 0
	 */
	public Vector3()
	{
		x = y = z = 0f;
	}
	
	/**
	 * initialize x and y to value
	 * @param value value for both fields
	 */
	public Vector3(float value)
	{
		x = y = z = value;
	}
	
	/**
	 * initialize fields separately
	 * @param x x value
	 * @param y y value
	 */
	public Vector3(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * copy constructor, will not point back to original datatype
	 * @param value
	 */
	public Vector3(Vector3 value)
	{
		this.x = value.x;
		this.y = value.y;
		this.z = value.z;
	}
	
	
	// --------------------------------------------
	//		API
	// --------------------------------------------
	
	/**
	 * Normalize values to equal 1, member variables are changed. 
	 */
	public void Normalize()
	{
		float length = FloatMath.sqrt( (this.x * this.x) + (this.y * this.y) + (this.z * this.z));
		this.x /= length;
		this.y /= length;
		this.z /= length;
	}
	
	/**
	 * Set this vector from another
	 * @param value original values
	 * @throws ArgumentNullException if value == null
	 */
	public void Set(Vector3 value)
	{
		if(value == null) return;
		this.x = value.x;
		this.y = value.y;
		this.z = value.z;
	}
	
	/**
	 * Get the magnitude of this vector
	 * @return magnitude
	 */
	public float Magnitude()
	{
		return FloatMath.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Set all components to 0f
	 */
	public void ResetToZero()
	{
		x = y = z = 0f;
	}
	
	/**
	 * Set all components to 1f
	 */
	public void ResetToOne()
	{
		x = y = z = 1f;
	}
	
	/**
	 * toString() will now display values
	 */
	@Override
	public String toString()
	{
		return "{X=" + this.x + ", Y=" + this.y + ", Z=" + this.z + "}";
	}
	
	/**
	 * get the string value of this component in string xml format
	 * @return String xml element with x,y,z as attributes
	 */
	public String ToXml()
	{
		return "<Vector3 x=\"" + x + "\" y=\"" + y + "\" z=\"" + z + "\"></Vector3>";
	}
	
	//---------------------------------------
	//		Static API
	//---------------------------------------
	
	/**
	 * Get a new Vector3 with both values equal to 1f
	 */
	public static Vector3 One()
	{
		return new Vector3(1f);
	}
	
	/**
	 * Get a new Vector3 with both values equal to 0f
	 */
	public static Vector3 Zero()
	{
		return new Vector3(0f);
	}
	
	/**
	 * float distance between two Vector3 variables
	 * @throws ArgumentNullException 
	 */
	public static float Distance(Vector3 one, Vector3 two)
	{
		if(one == null || two == null) return 0f;
		return FloatMath.sqrt(((one.x-two.x)*(one.x-two.x)) + 
				((one.y-two.y)*(one.y-two.y)) + 
				((one.z-two.z)*(one.z-two.z)));
	}
	
	/**
	 * Get the dot product of two vectors
	 * @param one first Vector
	 * @param two second Vector
	 * @return float Dot Product
	 */
	public static float Dot(Vector3 one, Vector3 two)
	{
		if( one == null || two == null ) return 0f;
		return one.x * two.x + one.y * two.y + one.z * two.z;
	}
	
	/**
	 * Normalize an input Vector3, without changing the input parameter
	 * @param input Vector3 to be normalized
	 * @return The Normalized Vector3, original input is not changed, if input was null, returns vector3.zero
	 */
	public static Vector3 Normalize(Vector3 input)
	{
		if(input == null) return Vector3.Zero();
		
		float length = FloatMath.sqrt( (input.x * input.x) + (input.y * input.y) + (input.z * input.z));
		input.x /= length;
		input.y /= length;
		input.z /= length;
		return input;
	}
	
	/**
	 * Clamp a vector to the given arguments
	 * @param value original value
	 * @param min minimum value
	 * @param max maximum value
	 * @param resultVector result output
	 */
	public static void Clamp(Vector3 value, Vector3 min, Vector3 max, Vector3 resultVector)
	{
		if(value == null || min == null || max == null || resultVector == null) return;
		
		resultVector.Set(value);
		
		if(value.x < min.x)
			resultVector.x = min.x;
		else if (value.x > max.x)
			resultVector.x = max.x;
		
		if(value.y < min.y)
			resultVector.y = min.y;
		else if (value.y > max.y)
			resultVector.y = max.y;
		
		if(value.z < min.z)
			resultVector.z = min.z;
		else if (value.z > max.z)
			resultVector.z = max.z;
	}
	
	/**
	 * get the cross vector between the given two
	 * @param one first vector
	 * @param two second vector
	 * @param result resultant cross vector
	 */
	public static void Cross(Vector3 one, Vector3 two, Vector3 result)
	{
		if(one == null || two == null || result == null) return;
		
		result.x = one.y * two.z - one.z * two.y;
		result.y = one.z * two.x - one.x * two.z;
		result.z = one.x * two.y - one.y * two.x;
	}
	
	/**
	 * Get the Magnitude of the given vector
	 * @param vector vector value to test
	 * @return float magnitude of the given vector
	 */
	public static float Magnitude(Vector3 vector)
	{
		return FloatMath.sqrt(vector.x * vector.x + vector.y * vector.y + vector.z * vector.z);
	}
	
	//---------------------------------------
	//		Static Basic Math API
	//---------------------------------------
	
	/**
	 * Because we can't overload the + operator... you may call this instead, one + two = out
	 * @param out output of (one + two)
	 * @param one value1 to be added
	 * @param two value2 to be added
	 */
	public static void Add(Vector3 out, Vector3 one, Vector3 two)
	{
		if(one == null || two == null || out == null) return;
		
		out.x = one.x + two.x;
		out.y = one.x + two.x;
		out.z = one.z + two.z;
	}
	
	/**
	 * Because we can't overload the - operator... you may call this instead
	 * @param out output of (one - two)
	 * @param one value1 which is subtracted by value2
	 * @param two value2 to subtract from 1
	 */
	public static void Subtract(Vector3 out, Vector3 one, Vector3 two)
	{
		if(one == null || two == null || out == null) return;
		
		out.x = one.x - two.x;
		out.y = one.y - two.y;
		out.z = one.z - two.z;
	}
	
	/**
	 * Because we can't overload the * operator... you may call this instead
	 * @param out output of (one * two)
	 * @param one value1 to be multiplied
	 * @param two value2 to be multiplied
	 */
	public static void Multiply(Vector3 out, Vector3 one, Vector3 two)
	{
		if(one == null || two == null || out == null) return;
		
		out.x = one.x * two.x;
		out.y = one.y * two.y;
		out.z = one.z * two.z;
	}
	
	/**
	 * Multiply a vector by float value
	 * @param out output of one * multiplier
	 * @param one original component
	 * @param mult resultant multiplication
	 */
	public static void Multiply(Vector3 out, Vector3 one, float mult)
	{
		if(out == null || one == null) return;
		
		out.x = one.x * mult;
		out.y = one.y * mult;
		out.z = one.z * mult;
	}
	
	/**
	 * Because we can't overload the / operator... you may call this instead, no operation if any value is null
	 * @param out output of (one / two)
	 * @param one value1 to be divided by two
	 * @param two value2 to divide one with
	 */
	public static void Divide(Vector3 out, Vector3 one, Vector3 two)
	{
		if(one == null || two == null || out == null) return;
		if(two.x == 0 || two.y == 0 || two.z == 0) return;
		
		out.x = one.x / two.x;
		out.y = one.y / two.y;
		out.z = one.z / two.z;
	}
	
	/**
	 * Transform a vector3 by a transformational matrix,
	 * W is assumed to be 1f
	 * @param input original vector to transform
	 * @param transform affecting matrix
	 * @param output resultant calculation output
	 */
	public static void Transform(Vector3 input, Matrix4 m, Vector3 output)
	{
		output.x = input.x * m.matrix[0][0] + 
					input.y * m.matrix[1][0] + 
					input.z * m.matrix[2][0] +
					1f * m.matrix[3][0];
		output.y = input.x * m.matrix[0][1] + 
					input.y * m.matrix[1][1] + 
					input.z * m.matrix[2][1] +
					1f * m.matrix[3][1];
		output.z = input.x * m.matrix[0][2] + 
					input.y * m.matrix[1][2] + 
					input.z * m.matrix[2][2] +
					1f * m.matrix[3][2];
	}
	
	/**
	 * Transform a vector3 by a transformational matrix,
	 * W is assumed to be 0f
	 * @param input original vector to transform
	 * @param transform affecting matrix
	 * @param output resultant calculation output
	 */
	public static void TransformNormal(Vector3 input, Matrix4 m, Vector3 output)
	{
		output.x = input.x * m.matrix[0][0] + 
				input.y * m.matrix[1][0] + 
				input.z * m.matrix[2][0] +
				0f * m.matrix[3][0];
		output.y = input.x * m.matrix[0][1] + 
				input.y * m.matrix[1][1] + 
				input.z * m.matrix[2][1] +
				0f * m.matrix[3][1];
		output.z = input.x * m.matrix[0][2] + 
				input.y * m.matrix[1][2] + 
				input.z * m.matrix[2][2] +
				0f * m.matrix[3][2];
	}
}
