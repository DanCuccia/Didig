package com.didig.main;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLRenderer implements GLSurfaceView.Renderer
{
	// ------------------------------------------
	//			Member Variables
	// ------------------------------------------
	
	private DidigActivity 					mProgram;
	private Engine							mEngine;
	private GLView 							mView;
	
	
	// ------------------------------------------
	//			Initialization
	// ------------------------------------------
	
	public GLRenderer(Context context, Engine engine, GLView view)
	{
		mProgram = (DidigActivity) context;
		mEngine = engine;
		mView = view;
	}
	
	
	// ------------------------------------------
	//				API
	// ------------------------------------------
	
	@Override
	public void onDrawFrame(GL10 gl) 
	{	
		mEngine.Render(null, gl, null); //FIX!!!
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) 
	{	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{	}
	
	
	// ------------------------------------------
	//				Mutators
	// ------------------------------------------
	
	public DidigActivity getProgram()
	{
		return this.mProgram;
	}
	
	public GLView getGLView()
	{
		return this.mView;
	}

}
