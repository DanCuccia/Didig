package com.didig.tier0;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.didig.datatype.Point;
import com.didig.datatype.Vector2;
import com.didig.drawing.Sprite;
import com.didig.main.BitmapManager;
import com.didig.util.AppTime;

/**
 * Drawing interface for billboard sprites
 * @author Daniel Cuccia
 */
public class IDrawable_Sprite extends CIDrawable 
{
	private Sprite 		drawable 		= null;
	private Bitmap 		bitmap 			= null;
	private Point 		frameSize 		= Point.One();
	private Point 		sheetSize 		= Point.One();
	private Point 		currentFrame 	= Point.Zero();
	private boolean 	isAnimating 	= true;
	private float 		fps 			= 1000/60;
	private int 		frameTime		= 0;
	
	/**
	 * Default Ctor
	 * @param sprite reference to what this is drawing
	 */
	public IDrawable_Sprite(Sprite sprite, int fileID, Point sheetSize)
	{
		this.drawable = sprite;
		bitmap = BitmapManager.GetInstance().GetBitmapByFileId(fileID);
		this.sheetSize = sheetSize;
		
	}
	
	/**
	 * Called before Draw, any final updates for drawing is found here
	 */
	@Override
	public void OnPrepare(AppTime time) 
	{
		if(true == isAnimating)
		{
			frameTime += time.ElapsedTime.Milliseconds;
			if(frameTime >= fps)
			{
				frameTime -= fps;
				currentFrame.x++;
				if(currentFrame.x >= sheetSize.x)
				{
					currentFrame.x = 0;
					currentFrame.y++;
					if(currentFrame.y >= sheetSize.y)
						currentFrame.y = 0;
				}
			}
		}
	}

	/**
	 * Draw to screen
	 */
	@Override
	public void OnDraw(GL10 gl10, Canvas canvas) 
	{
		if(canvas != null)
		{
			Vector2 pos = drawable.GetWorld().getPosition();
			canvas.drawBitmap(
					Bitmap.createBitmap( 
							bitmap,
							currentFrame.x * frameSize.x,
							currentFrame.y * frameSize.y,
							frameSize.x,
							frameSize.y,
							drawable.GetWorld().GetMatrix(),
							true ), 
					pos.x, 
					pos.y, 
					null );
		}
	}

}
