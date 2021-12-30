package com.didig.tier0;

import android.view.MotionEvent;

/**
 * Null Pattern - Default Null Grabbable Interface
 * @author Dan Cuccia
 */
public class CIGrabbableNull implements IGrabbable
{
	@Override
	public void OnPress(MotionEvent event) { }

	@Override
	public void OnMove(MotionEvent event) {	}

	@Override
	public void OnRelease(MotionEvent event) { }

}
