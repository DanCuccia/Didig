package com.didig.main;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;


/**
 * singleton object to manage bitmaps,
 * ensures only 1 image is ever loaded at once
 * @author Dan Cuccia
 */
public class BitmapManager 
{
	/**
	 * the actual bitmap image,
	 * filepath is used as identifier
	 * @author Dan Cuccia
	 */
	private class BitmapInstance
	{
		private Bitmap mBitmap = null;
		private int mIdentifier = -1;
		private int mInstanceCount = 0;
		
		public BitmapInstance(Bitmap b, int id)
		{
			this.mBitmap = b;
			this.mIdentifier = id;
		}
		
		public Bitmap GetBitmap()
		{ return mBitmap; }
		
		public int GetInstanceCount()
		{ return mInstanceCount; }
		
		public void IncrementCount()
		{ mInstanceCount++; }
		
		/**@return true is count is <= 0*/
		public boolean DecrementCount()
		{ 
			mInstanceCount--;
			if(mInstanceCount <= 0)
				return true;
			else return false;
		}
	}
	
	//	Singleton Pattern
	//===============================================
	private static BitmapManager mInstance = null;
	private BitmapManager(){}
	public static BitmapManager GetInstance()
	{
		if(mInstance == null)
			mInstance = new BitmapManager();
		return mInstance;
	}
	//===============================================
	
	private Resources mResources = null;
	private SparseArray<BitmapInstance> mBitmapList = new SparseArray<BitmapInstance>();
	
	/**
	 * the android Resources object must be set externally
	 * @param res android resources object
	 */
	public void InitializeResources(Resources res)
	{
		mResources = res;
	}
	
	/**
	 * Get a bitmap by filepath
	 * @param filepath filepath from root
	 * @return loaded Bitmap
	 */
	public Bitmap GetBitmapByFileId(int file)
	{
		BitmapInstance res = contains(file);
		if(res == null)
		{
			Bitmap b = BitmapFactory.decodeResource(mResources, file);
			res = new BitmapInstance(b, file);
			res.IncrementCount();
			mBitmapList.put(file, res);
		}
		return res.GetBitmap();
	}
	
	/**
	 * Find the instance of a given bitmap, and increment it
	 * @param bitmap identifier
	 */
	public void IncrementByBitmap(Bitmap bitmap)
	{
		BitmapInstance b = contains(bitmap);
		if(b != null)
			b.IncrementCount();
	}
	
	/**
	 * Find the instance of a given bitmap, and decrement it
	 * if instanceCount is 0, it will be destroyed from memory
	 * @param bitmap bitmap identifier
	 */
	public void DecrementInstance(Bitmap bitmap)
	{
		BitmapInstance b = contains(bitmap);
		if(b != null)
		{
			if( b.DecrementCount() )
			{
				mBitmapList.remove(b.mIdentifier);
				b = null;
			}
		}
	}
	
	/**
	 * If a bitmap has already been created, 
	 * returns it's instance, or null
	 * @param file identifier
	 * @return bitmapInstance out of hashmap
	 */
	private BitmapInstance contains(int file)
	{
		return mBitmapList.get(file);
	}
	
	/**
	 * Iterate the map looking at bitmatInstance values
	 * return the BitmapInstance value or null
	 * @param bitmap Bitmap identifier to look for
	 * @return BitmapInstance from list
	 */
	private BitmapInstance contains(Bitmap bitmap)
	{
		for(int i = 0; i < mBitmapList.size(); i++)
		{
			if(mBitmapList.get(i).GetBitmap() == bitmap)
				return mBitmapList.get(i);
		}
		return null;
	}
	
	/**
	 * Iterate the list, removing any with an count of 0
	 */
	public void Verify()
	{
		for(int i = 0; i < mBitmapList.size(); i++)
		{
			if(mBitmapList.get(i).GetInstanceCount() <= 0)
			{
				mBitmapList.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Clear the list of loaded bitmap images
	 * Warning!! any objects with remaining references will keep bitmaps in memory!
	 */
	public void Clear()
	{
		mBitmapList.clear();
	}
}
