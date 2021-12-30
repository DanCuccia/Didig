package com.didig.tier0;

import javax.microedition.khronos.opengles.GL10;

import com.didig.util.AppTime;

import android.graphics.Canvas;


/**
 * Null Pattern - Default Null Drawable Interface
 * @author Dan Cuccia
 */
public class CIDrawableNull implements IDrawable
{
	@Override
	public void OnPrepare(AppTime time) { }

	@Override
	public void OnDraw(GL10 gl10, Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
}
