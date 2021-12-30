package com.didig.datatype;

import com.didig.util.*;

/**
 * Orients a cube in 3D space used for collision detection
 * this is not Axis Bound, the cube will be correctly rotated with 
 *  whatever it represents
 * @author Daniel Cuccia
 */
public class OrientedBoundingBox3D 
{
	private Vector3 mMin;
	private Vector3 mMax;
	private Vector3 mCenter;
	private Vector3 mExtents;
	private Matrix4 mWorld = Matrix4.Identity();
	
	/**
	 * Default CTOR, you must know your min and max components
	 * @param min local positional minimum component
	 * @param max local positional maximum component
	 */
	public OrientedBoundingBox3D(Vector3 min, Vector3 max)
	{
		mMin = min;
		mMax = max;
		updateFromMinMax();
	}
	
	/**
	 * update Center and extent components from current min and max
	 */
	private void updateFromMinMax()
	{
		Vector3.Add(mCenter, mMin, mMax);
		Vector3.Multiply(mCenter, mCenter, 0.5f);
		Vector3.Subtract(mExtents, mMax, mMin);
		Vector3.Multiply(mExtents, mExtents, 0.5f);
	}
	
	/**
	 * Test if this OBB is inside another OBB
	 * @param other tester
	 * @return true if inside
	 */
	public boolean Intersects(OrientedBoundingBox3D other)
	{
		Matrix4 toMe = new Matrix4();
		{
			Matrix4 inversion = new Matrix4();
			Matrix4.Invert(mWorld, inversion);
			Matrix4.Multiply(other.GetWorld(), inversion, toMe);
		}
		
		Vector3 centerOther = MyMath.Multiply(other.GetCenter(), toMe);
		Vector3 extentsOther = other.GetExtents();
		Vector3 separation = new Vector3();
		Vector3.Subtract(separation, centerOther, mCenter);
		
		Matrix3 rotations = new Matrix3(toMe);
		Matrix3 absRotations = MyMath.Abs(rotations);
		
		float r, r0, r1, r01;
		
		//--- Test case 1 - X axis
        r = Math.abs(separation.x);
        r1 = Vector3.Dot(extentsOther, absRotations.Column(0));
        r01 = mExtents.x + r1;
        if (r > r01) return false;

        //--- Test case 1 - Y axis
        r = Math.abs(separation.y);
        r1 = Vector3.Dot(extentsOther, absRotations.Column(1));
        r01 = mExtents.y + r1;
        if (r > r01) return false;

        //--- Test case 1 - Z axis
        r = Math.abs(separation.z);
        r1 = Vector3.Dot(extentsOther, absRotations.Column(2));
        r01 = mExtents.z + r1;
        if (r > r01) return false;

        //--- Test case 2 - X axis
        r = Math.abs(Vector3.Dot(rotations.Row(0), separation));
        r0 = Vector3.Dot(mExtents, absRotations.Row(0));
        r01 = r0 + extentsOther.x;
        if (r > r01) return false;

        //--- Test case 2 - Y axis
        r = Math.abs(Vector3.Dot(rotations.Row(1), separation));
        r0 = Vector3.Dot(mExtents, absRotations.Row(1));
        r01 = r0 + extentsOther.y;
        if (r > r01) return false;

        //--- Test case 2 - Z axis
        r = Math.abs(Vector3.Dot(rotations.Row(2), separation));
        r0 = Vector3.Dot(mExtents, absRotations.Row(2));
        r01 = r0 + extentsOther.z;
        if (r > r01) return false;

        //--- Test case 3 # 1
        r = Math.abs(separation.z * rotations.mat[0][1] - separation.y * rotations.mat[0][2]);
        r0 = mExtents.y * absRotations.mat[0][2] + mExtents.z * absRotations.mat[0][1];
        r1 = extentsOther.y * absRotations.mat[2][0] + extentsOther.z * absRotations.mat[1][0];
        r01 = r0 + r1;
        if (r > r01) return false;

        //--- Test case 3 # 2
        r = Math.abs(separation.z * rotations.mat[1][1] - separation.y * rotations.mat[1][2]);
        r0 = mExtents.y * absRotations.mat[1][2] + mExtents.z * absRotations.mat[1][1];
        r1 = extentsOther.x * absRotations.mat[2][0] + extentsOther.z * absRotations.mat[0][0];
        r01 = r0 + r1;
        if (r > r01) return false;

        //--- Test case 3 # 3
        r = Math.abs(separation.z * rotations.mat[2][1] - separation.y * rotations.mat[2][2]);
        r0 = mExtents.y * absRotations.mat[2][2] + mExtents.z * absRotations.mat[2][1];
        r1 = extentsOther.x * absRotations.mat[1][0] + extentsOther.y * absRotations.mat[0][0];
        r01 = r0 + r1;
        if (r > r01) return false;

        //--- Test case 3 # 4
        r = Math.abs(separation.x * rotations.mat[0][2] - separation.z * rotations.mat[0][0]);
        r0 = mExtents.x * absRotations.mat[0][2] + mExtents.z * absRotations.mat[0][0];
        r1 = extentsOther.y * absRotations.mat[2][1] + extentsOther.z * absRotations.mat[1][1];
        r01 = r0 + r1;
        if (r > r01) return false;

        //--- Test case 3 # 5
        r = Math.abs(separation.x * rotations.mat[1][2] - separation.z * rotations.mat[1][0]);
        r0 = mExtents.x * absRotations.mat[1][2] + mExtents.z * absRotations.mat[1][0];
        r1 = extentsOther.x * absRotations.mat[2][1] + extentsOther.z * absRotations.mat[0][1];
        r01 = r0 + r1;
        if (r > r01) return false;

        //--- Test case 3 # 6
        r = Math.abs(separation.x * rotations.mat[2][2] - separation.z * rotations.mat[2][0]);
        r0 = mExtents.x * absRotations.mat[2][2] + mExtents.z * absRotations.mat[2][0];
        r1 = extentsOther.x * absRotations.mat[1][1] + extentsOther.y * absRotations.mat[0][1];
        r01 = r0 + r1;
        if (r > r01) return false;

        //--- Test case 3 # 7
        r = Math.abs(separation.y * rotations.mat[0][0] - separation.x * rotations.mat[0][1]);
        r0 = mExtents.x * absRotations.mat[0][1] + mExtents.y * absRotations.mat[0][0];
        r1 = extentsOther.y * absRotations.mat[2][2] + extentsOther.z * absRotations.mat[1][2];
        r01 = r0 + r1;
        if (r > r01) return false;

        //--- Test case 3 # 8
        r = Math.abs(separation.y * rotations.mat[1][0] - separation.x * rotations.mat[1][1]);
        r0 = mExtents.x * absRotations.mat[1][1] + mExtents.y * absRotations.mat[1][0];
        r1 = extentsOther.x * absRotations.mat[2][2] + extentsOther.z * absRotations.mat[0][2];
        r01 = r0 + r1;
        if (r > r01) return false;

        //--- Test case 3 # 9
        r = Math.abs(separation.y * rotations.mat[2][0] - separation.x * rotations.mat[2][1]);
        r0 = mExtents.x * absRotations.mat[2][1] + mExtents.y * absRotations.mat[2][0];
        r1 = extentsOther.x * absRotations.mat[1][2] + extentsOther.y * absRotations.mat[0][2];
        r01 = r0 + r1;
        if (r > r01) return false;

        return true;  // No separating axis, we have intersection
	}
	
	/**
	 * Intersection test for Ray Collision
	 * @param ray the ray to test going through this OBB, position and direction
	 * @return null for no collision, if collision, Vector2.X = nearCollision Vector2.Y = farCollision
	 */
	public Vector2 Intersects(Ray ray)
	{
		Vector3 rayOrigin = ray.GetPosition();
		Vector3 rayDirection = ray.GetDirection();
		Matrix4 inverseWorld = new Matrix4();
		Matrix4.Invert(mWorld, inverseWorld);
		
		Vector3.Transform(rayOrigin, inverseWorld, rayOrigin);
		Vector3.TransformNormal(rayDirection, inverseWorld, rayDirection);
		
		Vector3 min = this.mMin;
		Vector3 max = this.mMax;
		
		float t, t1, t2;
		
		Vector2 output = new Vector2(Float.MIN_VALUE, Float.MAX_VALUE);
		
		if(rayDirection.x > -0.00001f && rayDirection.x < -0.00001f)
		{
			if(rayOrigin.x < min.x || rayOrigin.x > max.x)
				return null;
		}
		else
		{
            t = 1.0f / rayDirection.x;
            t1 = (min.x - rayOrigin.x) * t;
            t2 = (max.x - rayOrigin.x) * t;

            if (t1 > t2)
            {
                t = t1; t1 = t2; t2 = t;
            }

            if (t1 > output.x)
            {
            	output.x = t1;
            }
            if (t2 < output.y)
            {
            	output.y = t2;
            }

            if (output.x > output.y || output.y < 0.00001f)
                return null;
        }
		
		// intersect in Y 
        if (rayDirection.y > -0.00001f && rayDirection.y < -0.00001f)
        {
            if (rayOrigin.y < min.y || rayOrigin.y > max.y)
                return null;
        }
        else
        {
            t = 1.0f / rayDirection.y;
            t1 = (min.y - rayOrigin.y) * t;
            t2 = (max.y - rayOrigin.y) * t;

            if (t1 > t2)
            {
                t = t1; t1 = t2; t2 = t;
            }

            if (t1 > output.x)
            {
            	output.x = t1;
            }
            if (t2 < output.y)
            {
            	output.y = t2;
            }

            if (output.x > output.y || output.y < 0.00001f)
                return null;
        }
        
     // intersect in Z 
        if (rayDirection.z > -0.00001f && rayDirection.z < -0.00001f)
        {
            if (rayOrigin.z < min.z || rayOrigin.z > max.z)
                return null;
        }
        else
        {
            t = 1.0f / rayDirection.z;
            t1 = (min.z - rayOrigin.z) * t;
            t2 = (max.z - rayOrigin.z) * t;

            if (t1 > t2)
            {
                t = t1; t1 = t2; t2 = t;
            }

            if (t1 > output.x)
            {
            	output.x = t1;
            }
            if (t2 < output.y)
            {
            	output.y = t2;
            }
        }
        
        if (output.x > output.y || output.y < 0.00001f)
            return null;

        return output;
	}
	
	/**
	 * Update this collision by using the world matrix in which it represents
	 * @param world World Matrix, position, rotation, scale
	 */
	public void Update(Matrix4 world)
	{
		this.mWorld = world;
	}
	
	//	Mutators
	//======================================================
	
	/**
	 * Get Max - Min, will allocate a new Vector3
	 */
	public Vector3 GetDimensions()
	{
		Vector3 output = new Vector3();
		Vector3.Subtract(output, mMax, mMin);
		return output;
	}
	
	/**
	 * Get the maximum positional value
	 * @return Vector3 maximum
	 */
	public Vector3 GetMax()
	{
		return mMax;
	}
	
	/**
	 * Set Maximum component
	 * @param value Vector3 maximum
	 */
	public void SetMax(Vector3 value)
	{
		mMax = value;
		updateFromMinMax();
	}
	
	/**
	 * Get minimum component
	 * @return Vector3 minimum
	 */
	public Vector3 GetMin()
	{
		return mMin;
	}
	
	/**
	 * Set Minimum location, will update 
	 * @param value local position value
	 */
	public void SetMin(Vector3 value)
	{
		mMin = value;
		updateFromMinMax();
	}
	
	/**
	 * Get Center component
	 * @return Vector3 center
	 */
	public Vector3 GetCenter()
	{
		return mCenter;
	}
	
	/**
	 * Get Extent component
	 * @return Vector3 extents
	 */
	public Vector3 GetExtents()
	{
		return mExtents;
	}
	
	/**
	 * World Matrix getter
	 * @return Matrix 4x4 float matrix
	 */
	public Matrix4 GetWorld()
	{
		return mWorld;
	}
	
}
