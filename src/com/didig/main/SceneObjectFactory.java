package com.didig.main;

import com.didig.drawing.SceneObject;

/**
 * sprite Factory creates spritesheet driven drawables for you
 * @author Daniel Cuccia
 */
public class SceneObjectFactory 
{
	SceneObject current = null;
	
	/**
	 * Completes build stack and returns the sprite
	 * @return fully built sprite, or null if incorrect parameters found
	 */
	public SceneObject Build()
	{
		return current;
	}
}
