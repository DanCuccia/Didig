package com.didig.main;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * main view
 * @author Dan Cuccia
 */
public class GLView extends GLSurfaceView
{
	// ------------------------------------------------
	//				Member Variables
	// ------------------------------------------------
	
	DidigActivity 				mProgram;
	GLRenderer					mGLRender;
	Engine						mEngine;

	// ------------------------------------------------
	//				Initialization
	// ------------------------------------------------
	
	public GLView(Context context) 
	{
		super(context);
		setEGLContextClientVersion(2);
		mProgram = (DidigActivity) context;
		
		mEngine = new Engine(context, this);
		mGLRender = new GLRenderer(context, mEngine, this);
		mEngine.setRenderer(mGLRender);
	}
	
	
	// ------------------------------------------------
	//				API
	// ------------------------------------------------
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return true;
	}

	// ------------------------------------------------
	//				Mutators
	// ------------------------------------------------
	
	/**
	 * game engine
	 */
	public Engine getEngine()
	{
		return this.mEngine;
	}
	/**
	 * Renderer
	 */
	public GLRenderer getRenderer()
	{
		return this.mGLRender;
	}
	/**
	 * Main Application Activity
	 */
	public DidigActivity getProgram()
	{
		return this.mProgram;
	}
}
