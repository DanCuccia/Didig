package com.didig.tier0;

import android.view.MotionEvent;

/**
 * The Interface which sceneObjects encapsulate
 * @author Dan Cuccia
 */
public interface IGrabbable 
{
	public void OnPress(MotionEvent event);
	public void OnMove(MotionEvent event);
	public void OnRelease(MotionEvent event);	
}
