package com.didig.main;

import com.didig.util.AppTime;

import android.view.SurfaceHolder;

/**
 * The main thread which the engine updates game logic, and draws with
 * * 
	 * The main AppTime class is found within this Thread's run()
	 * 		AppTime will return true in it's update() when its ready to draw, 
	 * 		delta millies will be tracked by AppTime for the user to have 
	 * 		very fine-tune timing values to base logic between Draw Calls
	 * 
 * *
 * @author Dan Cuccia
 */
public class EngineThread extends Thread 
{
	// ------------------------------------------
	//			Member Variables
	// ------------------------------------------
	
	private SurfaceHolder 			mSurfaceHolder;
	private Engine 					mEngine;
	
	private boolean 				mRunning = false;
	
	
	// ------------------------------------------
	//			Initialization
	// ------------------------------------------
	
	/**
	 * Default Constructor, used by the engine
	 */
	public EngineThread(SurfaceHolder surfaceHolder, Engine engine)
	{
		mSurfaceHolder = surfaceHolder;
		mEngine = engine;
	}
	
	
	// ------------------------------------------
	//			API
	// ------------------------------------------
	
	/**
	 * The main thread logic is found here, Canvas and AppTime is created and updated in here.
	 * This is also the final try-catch of the line, any exceptions that slip through will find it's way here
	 * @throws CanvasNullException if lockCanvas returns null
	 * @throws Exception if engine is crashing for unknown reasons
	 */
	@Override
	public void run()
	{
		boolean draw = false;
		AppTime time = new AppTime( System.currentTimeMillis() );
		
		while( mRunning == true )
		{			
			draw = time.update(System.currentTimeMillis());
			
			time.beginningUpdate(System.currentTimeMillis());
			mEngine.Update(time);
			time.updateComplete(System.currentTimeMillis());
			
			if( draw == true )
			{
				try
				{
					synchronized( mSurfaceHolder )
					{
						time.beginningDraw(System.currentTimeMillis());
						//mEngine.Render(canvas);
						mEngine.getRenderer().getGLView().requestRender();
						time.drawComplete(System.currentTimeMillis());
						
						time.completeCycle();
					}
				}
				catch(Exception e)
				{
					System.out.println("EngineThread::run() is crashing for unknown reasons");
				}
			}
		}
	}
	
	
	// ------------------------------------------
	//			Mutators
	// ------------------------------------------
	
	public void setRunning(boolean value)
	{
		mRunning = value;
	}
}
