package com.didig.main;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Map.Entry;

import javax.microedition.khronos.opengles.GL10;

import com.didig.drawing.SceneObject;
import com.didig.tier0.Scene;
import com.didig.util.AppTime;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.Toast;

/**
 * This is the single-entry point for all game components.
 * @author Dan Cuccia
 */
public class Engine
{
	// ------------------------------------------
	//			Member Variables
	// ------------------------------------------
	
	private DidigActivity 					mProgram;
	
	private ArrayList<Scene> 				mSceneList		= new ArrayList<Scene>();
	
	private EngineThread					mEThread;
	
	private GLRenderer						mGLRender;
	
	
	// ------------------------------------------
	//			Initialization Logic
	// ------------------------------------------
	
	/**
	 * Default Constructor
	 */
	public Engine(Context context, GLView view)
	{
		this.mEThread = new EngineThread(view.getHolder(), this);
		this.mProgram = (DidigActivity) context;
	}

	public void Initialize() 
	{
		try
		{
			mEThread.setRunning(true);
			mEThread.start();
		}
		catch(IllegalThreadStateException e)
		{
			System.out.println("Engine::surfaceCreated() : unable to start Thread E = " + e);
			Toast.makeText(getProgram(), "Engine::surfaceCreated() : unable to start EThread : Contact Developer\n" + e, Toast.LENGTH_LONG).show();
		}
	}
	
	
	// ------------------------------------------
	//			API
	// ------------------------------------------
	
	/**
	 * Update and Animate the Master Scene List
	 */
	public void Update(AppTime time)
	{
		try
		{
			for(Scene scene : mSceneList)
			{
				for(Entry<Short, ArrayList<SceneObject>> sceneList : scene.getSceneMap().entrySet())
				{
					for( SceneObject obj : sceneList.getValue() )
					{
						if(obj.Updatable)
						{
							obj.UpdateLogic.OnAnimate(time);
							obj.UpdateLogic.OnUpdate(time);
						}
					}
				}
			}
		}
		catch(ConcurrentModificationException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	/**
	 * Draw the master scene list,
	 * each scene it's own 2D/3D calls
	 * @param canvas where 2D scenes are drawn
	 */
	public void Render(AppTime time, GL10 gl10, Canvas canvas)
	{
		try
		{
			for(Scene scene : mSceneList)
			{
				for(Entry<Short, ArrayList<SceneObject>> sceneList : scene.getSceneMap().entrySet())
				{
					for( SceneObject obj : sceneList.getValue() )
					{
						if(obj.Drawable)
						{
							obj.DrawLogic.OnPrepare(time);
							obj.DrawLogic.OnDraw(gl10, canvas);
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	// ------------------------------------------
	//			Mutators
	// ------------------------------------------
	
	/**
	 * Returns the Activity which this engine belongs to 
	 */
	public DidigActivity getProgram()
	{
		return this.mProgram;
	}
	
	/**
	 * Add a scene to the engine's master list
	 */
	public void RegisterScene(Scene scene)
	{
		if(scene != null)
			this.mSceneList.add(scene);
	}
	
	/**
	 * Get the Master Scene list from the engine 
	 */
	public ArrayList<Scene> getSceneList()
	{
		return this.mSceneList;
	}
	
	/**
	 * Get the main OpenGL Renderer
	 */
	public GLRenderer getRenderer()
	{
		return this.mGLRender;
	}
	
	/**
	 * This must be Initialized after both Engine and GLRenderer has been constructed
	 */
	public void setRenderer(GLRenderer value)
	{
		this.mGLRender = value;
	}
}
