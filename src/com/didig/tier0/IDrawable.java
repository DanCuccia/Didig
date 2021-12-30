package com.didig.tier0;

import javax.microedition.khronos.opengles.GL10;

import com.didig.util.AppTime;

import android.graphics.Canvas;


/**
 * The Drawing Interface which Scene Objects encapsulate
 * @author Dan Cuccia
 */
public interface IDrawable 
{
	public void OnPrepare(AppTime time);
	public void OnDraw(GL10 gl10, Canvas canvas);
}
