package com.didig.util;

/**
 * Holds timing values which passed since the last cycle
 * @author Dan Cuccia
 */
public class ElapsedTime
{
	public int 				Milliseconds;
	public float 			Seconds;
	
	/**
	 * Reset values after each cycle
	 */
	public void Reset()
	{
		this.Milliseconds = 0;
		this.Seconds = 0f;
	}
}