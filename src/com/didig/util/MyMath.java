package com.didig.util;

import com.didig.datatype.Matrix3;
import com.didig.datatype.Vector3;
import com.didig.datatype.Vector2;
import com.didig.datatype.Matrix4;

import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Any easy access commonly used math functions are found in here
 * @author Daniel Cuccia
 */
public abstract class MyMath 
{
	/**
	 * this class is uncreatable!
	 */
	private MyMath(){}
	
	/**
	 * Test if a given motionEvent is within a rectangle
	 * @param event finger motionEvent
	 * @param rect rectangle to test
	 * @return true if the motionEvent is inside the rect
	 */
	public static boolean eventWithinRect(MotionEvent event, Rect rect)
	{
		Vector2 ePos = new Vector2(event.getX(), event.getY());
		
		if(ePos.x < rect.left || ePos.x > rect.right)
			return false;
		if(ePos.y < rect.top ||ePos.y > rect.bottom)
			return false;
		return true;
	}
	
	/**
	 * Multiply a vector by a matrix
	 * @param v vector3 vector
	 * @param m 4x4 matrix
	 * @return transformed matrix
	 */
	public static Vector3 Multiply(Vector3 v, Matrix4 m)
	{
		return new Vector3(v.x * m.matrix[0][0] + v.y * m.matrix[1][0] + v.z * m.matrix[2][0] + m.matrix[3][0],
				v.x * m.matrix[0][1] + v.y * m.matrix[1][1] + v.z * m.matrix[2][1] + m.matrix[3][1],
				v.x * m.matrix[0][2] + v.y * m.matrix[1][2] + v.z * m.matrix[2][2] + m.matrix[3][2]);
	}
	
	/**
	 * Create a new Matrix3 using Math.abs on all values
	 * @param m3 original
	 * @return new Matrix3
	 */
	public static Matrix3 Abs(Matrix3 m3)
	{
		Matrix3 absMatrix = new Matrix3();
		for(int r = 0; r < 3; r++)
			for(int c = 0; c < 3; c++)
				absMatrix.mat[r][c] = Math.abs(m3.mat[r][c]);
		return absMatrix;
	}
}
