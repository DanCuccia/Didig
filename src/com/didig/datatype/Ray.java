package com.didig.datatype;

import com.didig.exception.ArgumentNullException;

/**
 * a line from an origin position in 3d space
 * @author Daniel Cuccia
 *
 */
public class Ray 
{
	private Vector3 position = new Vector3();
	private Vector3 direction = new Vector3();
	
	/**
	 * constructs this ray with variables initialized to 0
	 */
	public Ray()
	{ }
	
	/**
	 * construct this ray using the given arguments
	 * @param position location of origin
	 * @param direction vector from origin
	 * @throws ArgumentNullException if any argument is null
	 */
	public Ray(Vector3 position, Vector3 direction)
	{
		this.position.Set(position);
		this.direction.Set(direction);
	}
	
	/**
	 * Get the origin of this ray
	 * @return Vector3 position
	 */
	public Vector3 GetPosition()
	{ 
		return this.position; 
	}
	
	/**
	 * Set the origin of this ray 
	 * @param position new origin position
	 * @throws ArgumentNullException if new origin is null
	 */
	public void SetPosition(Vector3 position) throws ArgumentNullException
	{
		this.position.Set(position);
	}
	
	/**
	 * Get the vector of this ray
	 * @return Vector3 vector
	 */
	public Vector3 GetDirection()
	{
		return this.direction;
	}
	
	/**
	 * Set the vector of this ray
	 * @param direction new vector
	 * @throws ArgumentNullException if new vector is null
	 */
	public void SetDirection(Vector3 direction) throws ArgumentNullException
	{
		this.direction.Set(direction);
	}
}
