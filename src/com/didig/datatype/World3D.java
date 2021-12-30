package com.didig.datatype;

/**
 * Composes all components to describe an object in world space
 * @author Daniel Cuccia
 */
public class World3D 
{
	public interface IWorld3DOnChange
	{
		public void onMatrixUpdateOverride(World3D w);
	}
	
	private boolean 			isDirty 			= true;
	private Vector3     		position        	= Vector3.Zero();
    private Vector3     		rotation        	= Vector3.Zero();
    private Vector3     		scale           	= Vector3.One();
    private Matrix4      		worldMatrix			= Matrix4.Identity();
    private IWorld3DOnChange 	onUpdateOverride 	= null;
    
    public World3D() { }
    
    public World3D(Vector3 position, Vector3 rotation, Vector3 scale, Matrix4 world)
    {
    	if(position != null)
            this.position = position;
        if(rotation != null)
            this.rotation = rotation;
        if(scale != null)
            this.scale = scale;
        if (world != null)
            this.worldMatrix = world;
        isDirty = true;
    }
    
    public World3D GetCopy()
    {
        return new World3D(this.position, this.rotation, this.scale, this.worldMatrix);
    }
    
    public Matrix4 GetWorldMatrix()
    {
        if (worldMatrix == null ||
            isDirty == true)
        {
            updateWorldMatrix();
        }
        return this.worldMatrix;
    }
    
    private void updateWorldMatrix()
    {
    	if(onUpdateOverride != null)
    	{
    		onUpdateOverride.onMatrixUpdateOverride(this);
    	}
    	else
    	{
    		//do "math"
    	}
    	isDirty = false;
    }
    
    public void ForceUpdate()
    {
    	this.updateWorldMatrix();
    }
    
    public void Move(Vector3 translation)
    {
    	Vector3.Add(position, position, translation);
    	isDirty = true;
    }
    
    public void Rotate(Vector3 rot)
    {
    	Vector3.Add(this.rotation, this.rotation, rot);
    	isDirty = true;
    }
    
    public void Scale(Vector3 scaling)
    {
    	Vector3.Add(scale, scale, scaling);
    	isDirty = true;
    }
    
    public void Zero()
    {
    	position.ResetToZero();
    	rotation.ResetToZero();
    	scale.ResetToOne();
    }
    
    public void SetFromWorld3D(Matrix4 world)
    {
    	world.Decompose(position, rotation, scale);
    }

//    public String ToXml()
//    {
//    	StringBuffer buf = new StringBuffer();
//    	buf.append("<World3D>");
//    	
//    	buf.append("<Position>");
//    	buf.append( position.ToXml() );
//    	buf.append("</Position>");
//    	
//    	buf.append("<Rotation>");
//    	buf.append( rotation.ToXml() );
//    	buf.append("</Rotation>");
//    	
//    	buf.append("<Scale>");
//    	buf.append( scale.ToXml() );
//    	buf.append("</Scale>");
//    	
//    	return buf.toString();
//    }
    
    //	Mutators
    //=============================================
    
    public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
		this.isDirty = true;
	}

	public Vector3 getRotation() {
		return rotation;
	}

	public void setRotation(Vector3 rotation) {
		this.rotation = rotation;
		this.isDirty = true;
	}

	public Vector3 getScale() {
		return scale;
	}

	public void setScale(Vector3 scale) {
		this.scale = scale;
		this.isDirty = true;
	}

	public Matrix4 getWorldMatrix() {
		return worldMatrix;
	}

	public void setWorldMatrix(Matrix4 worldMatrix) {
		this.worldMatrix = worldMatrix;
	}
    
}
