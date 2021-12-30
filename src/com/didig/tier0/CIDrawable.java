package com.didig.tier0;

/**
 * Concrete IDrawable to inhereit from,
 * wraps IsDirty variable for OnPrepare() to use
 * @author Dan Cuccia
 */
public abstract class CIDrawable implements IDrawable
{
	private boolean mIsDirty = true;
	
	/**
	 * Sets the drawing interface to dirty,
	 * -Will be prepared before drawn.
	 */
	public void ToggleIsDirty()
	{
		this.mIsDirty = true;
	}
	
	/**
	 * Get the drawing interface dirty value
	 * @return drawing interface is dirty true/false
	 */
	public boolean IsDirty()
	{
		return this.mIsDirty;
	}
}
