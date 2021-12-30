package com.didig.datatype;


/**
 * Describes location values of a single 2D object
 * @author Dan Cuccia
 */
public class World2D
{
	private boolean 		isDirty 	= true;
	private Vector2 		position 	= Vector2.Zero();
	private Vector2 		scale 		= Vector2.One();
	private float			rotation 	= 0f;
	private CenterType2D 	centerType  = CenterType2D.CENTER;
	private android.graphics.Matrix rotationMatrix = new android.graphics.Matrix();
	
	// Construction
	//============================================
	/**
	 * Default CTOR, all components 0 or 1
	 * @param type center component
	 */
	public World2D(CenterType2D type)
	{
		centerType = type;
	}
	
	/**
	 * constructs world using argument values
	 * @param type enum center position component
	 * @param position vector2 position component
	 * @param scale vector2 scale component
	 * @param rotation float rotation component
	 */
	public World2D(CenterType2D type, Vector2 position, Vector2 scale, float rotation)
	{
		centerType = type;
		this.position = position;
		this.scale = scale;
		this.rotation = rotation;
	}
	
	
	// Mutators
	//============================================
	
	/**
	 * called when GetMatrix() is called and is dirty
	 */
	private void updateMatrix()
	{
		this.rotationMatrix.reset();
		this.rotationMatrix.postRotate(rotation);
	}
	
	/**
	 * Get the rotational matrix
	 * @return android.graphics.Matrix
	 */
	public android.graphics.Matrix GetMatrix()
	{
		if(true == isDirty)
		{
			updateMatrix();
			isDirty = false;
		}
		return this.rotationMatrix;
	}
	
	/**
	 * Center value
	 */
	public CenterType2D GetCenter()
	{ return centerType; }
	/**
	 * Set Center value, isDirty will be flipped
	 */
	public void SetCenter(CenterType2D type)
	{ centerType = type; }
	/**
	 * Position value
	 */
	public Vector2 getPosition() 
	{ return position; }
	/**
	 * Set Position, isDirty will be flipped
	 */
	public void setPosition(Vector2 position) 
	{ this.position = position; }
	/**
	 * Scaling value
	 */
	public Vector2 getScale() 
	{ return scale; }
	/**
	 * Set Scaling, isDirty willb e flipped
	 */
	public void setScale(Vector2 scale) 
	{ this.scale = scale; }
	/**
	 * Rotation value
	 */
	public float getRotation()
	{ return rotation; }
	/**
	 * Set Rotation value, isDirty will be flipped
	 */
	public void setRotation(float rotation) {
		this.rotation = rotation;
		isDirty = true;
	}
	
	@Override
	public String toString()
	{
		return "{World2D: pos=\"" + position.toString() + "\" scale=\"" + scale.toString() + "\" rotation=\"" + rotation + "\"}"; 
	}
	
}
