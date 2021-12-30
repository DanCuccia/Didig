package com.didig.util;

import java.util.Calendar;




/**
 * Holds current date and time values of "Earth"
 * to get time fields, use the Calendar Constants (eg. EarthTime.Get(Calendar.SECOND))
 * @author Dan Cuccia
 */
class EarthTime
{
	private Calendar 		mCalendar 			= Calendar.getInstance();
	
	/**
	 * Retrieve a Real World Time from the calendar
	 * @param timeValue Calendar Time Field value eg. Calendar.SECOND
	 * @return the world value that you asked for
	 */
	public int Get(int timeValue)
	{
		return this.mCalendar.get(timeValue);
	}
}

/**
 * Encapsulates timing values for engine and objects to run off of.
 * IsRunningSlow will be flipped to true if your not hitting your target frameRate.
 * @author Dan Cuccia
 */
public class AppTime 
{
	public static final int 	TargetFramesPerSecond		= 60;
	private static final int	mMillisPerFrame				= 1000 / TargetFramesPerSecond;
	
	public long 				TotalOnTime					= 0L;
	
	public ElapsedTime 			ElapsedTime 				= new ElapsedTime();
	public EarthTime			EarthTime 					= new EarthTime();
	
	public boolean				IsRunningSlow				= false;
	
	public short 				CurrentFrameRate			= 0;
	public short 				CurrentUpdateRate			= 0;
	public short 				CurrentDrawRate				= 0;
	
	private long 				mLastUpdateTime				= 0L;
	private int 				mCurrentFrameCount 			= 0;
	private long				mCycleCounter				= 0L;
	
	/**
	 * Default Constructor
	 * @param systemMillies the current system's time
	 */
	public AppTime(long systemMillis)
	{
		this.mLastUpdateTime = systemMillis;
	}
	
	/**
	 * Update the Application Time class,
	 * this will return true when it's time for the Engine to draw
	 * @param systemMillies get this from System.currentTimeMillis()
	 * @return true if its time to update & draw, otherwise false
	 */
	public boolean update(long systemMillis)
	{
		int delta = (int) (systemMillis - mLastUpdateTime);
		TotalOnTime += delta;
		mLastUpdateTime = systemMillis;
		
		updateElapsed(delta);
		checkForTarget();
		
		return readyToDraw(delta);
	}
	
	/**
	 * will return true every "TargetFramesPerSecond" of a second
	 * @return time to draw
	 */
	private boolean readyToDraw(int deltaTime)
	{
		mCurrentFrameCount += deltaTime;
		if(mCurrentFrameCount >= mMillisPerFrame)
		{
			mCurrentFrameCount -= mMillisPerFrame;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks to see if update + draw > "mMillisPerFrame"
	 * IsRunningSlow will be toggled accordingly
	 */
	private void checkForTarget()
	{
		if(CurrentDrawRate + CurrentUpdateRate > mMillisPerFrame)
		{
			IsRunningSlow = true;
		}
		else
		{
			IsRunningSlow = false;
		}
	}
	
	/**
	 * update the elapsedTime for gameLogic to run off of
	 */
	private void updateElapsed(int deltaTime)
	{
		ElapsedTime.Milliseconds = deltaTime;
		ElapsedTime.Seconds = 1f / ElapsedTime.Milliseconds;
	}
	
	/**
	 * Call this before the engine updates, 
	 * so this class can track updating time
	 */
	public void beginningUpdate(long systemMillis)
	{
		mCycleCounter = systemMillis;
	}
	
	/**
	 * Call this after the engine updates, 
	 * so this class can track updating time,
	 * This is Absolute! - not an average
	 */
	public void updateComplete(long systemMillis)
	{
		this.CurrentUpdateRate = (short) (mCycleCounter - systemMillis);
	}
	
	/**
	 * Call this before the engine draws, 
	 * so this class can track updating time
	 */
	public void beginningDraw(long systemMillis)
	{
		mCycleCounter = systemMillis;
	}
	
	/**
	 * Call this after the engine draws, 
	 * this is so this class can track drawing time
	 * This is Absolute! - not an average
	 */
	public void drawComplete(long systemMillis)
	{
		this.CurrentDrawRate = (short) (mCycleCounter - systemMillis);
	}
	
	public void completeCycle()
	{
		this.ElapsedTime.Reset();
	}
	
}
