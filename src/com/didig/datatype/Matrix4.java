package com.didig.datatype;

import com.didig.exception.ArgumentNullException;

/**
 * 4x4 float array
 * @author Daniel Cuccia
 *
 */
public class Matrix4 
{
	// --------------------------------------------
	//		Member Variables
	// --------------------------------------------
	
	public float[][] matrix;
	
	
	// --------------------------------------------
	//		Initialization
	// --------------------------------------------
	
	/**
	 * Default constructor, initialize all member variables to 0
	 */
	public Matrix4()
	{
		matrix = new float[4][4];
		matrix[0][0] = matrix[0][1] = matrix[0][2] = matrix[0][3] =
		matrix[1][0] = matrix[1][1] = matrix[1][2] = matrix[1][3] =
		matrix[2][0] = matrix[2][1] = matrix[2][2] = matrix[2][3] =
		matrix[3][0] = matrix[3][1] = matrix[3][2] = matrix[3][3] = 0f;
	}
	
	/**
	 * Copy constructor, initialize all member variables to input argument,
	 * will not point back to original object
	 * @param mat Matrix to copy values from
	 */
	public Matrix4(Matrix4 mat)
	{
		for(int x = 0; x < 4; x++)
			for(int y = 0; y < 4; y++)
			{
				matrix[x][y] = mat.matrix[x][y];
			}
	}
	
	public static Matrix4 Identity()
	{
		Matrix4 mat = new Matrix4();
		mat.matrix[0][0] = 1f;
		mat.matrix[1][1] = 1f;
		mat.matrix[2][2] = 1f;
		mat.matrix[3][3] = 1f;
		return mat;
	}
	
	// --------------------------------------------
	//		API
	// --------------------------------------------
	
	public void Decompose(Vector3 position, Vector3 orientation, Vector3 scale)
	{
		if(position == null || orientation == null || scale == null)
			return;
		
		position.x = matrix[3][0];
		position.y = matrix[3][1];
		position.z = matrix[3][2];
		//do "math"
	}
	
	/**
	 * Get the Determinant of this matrix (calls Static API)
	 * @return double determinant value of this matrix
	 */
	public double Determinant()
	{
		return Matrix4.Determinant(this.matrix);
	}
	
	/**
	 * Get a newly allocated Vector3 forward direction of this matrix
	 * @return forward direction of this matrix
	 */
	public Vector3 Forward()
	{
		return new Vector3(matrix[2][0], matrix [2][1], matrix[2][2]);
	}
	
	/**
	 * Get the Vector3 forward direction without allocating new memory
	 * @param out argument will be set to the forward direction of this matrix
	 * @throws ArgumentNullException if out == null
	 */
	public void Forward(Vector3 out) throws ArgumentNullException
	{
		if(out == null)
		{
			throw new ArgumentNullException("Matrix.Forward");
		}
		out.x = matrix[2][0];
		out.y = matrix[2][1];
		out.z = matrix[2][2];
	}
	
	/**
	 * Get a newly allocated Vector3 backward direction of this matrix
	 * @return backward direction of this matrix
	 */
	public Vector3 Backward()
	{
		return new Vector3(-matrix[2][0], -matrix [2][1], -matrix[2][2]);
	}
	
	/**
	 * Get the Vector3 backward direction without allocating new memory
	 * @param out argument will be set to the backward direction of this matrix
	 * @throws ArgumentNullException if out == null
	 */
	public void Backward(Vector3 out) throws ArgumentNullException
	{
		if(out == null)
		{
			throw new ArgumentNullException("Backward");
		}
		out.x = -matrix[2][0];
		out.y = -matrix[2][1];
		out.z = -matrix[2][2];
	}
	
	/**
	 * Get a newly allocated Vector3 up direction of this matrix
	 * @return up direction of this matrix
	 */
	public Vector3 Up()
	{
		return new Vector3(matrix[1][0], matrix[1][1], matrix[1][2]);
	}
	
	/**
	 * Get the Vector3 up direction without allocating new memory
	 * @param out argument will be set to the up direction of this matrix
	 * @throws ArgumentNullException if out == null
	 */
	public void Up(Vector3 out) throws ArgumentNullException
	{
		if(out == null)
		{
			throw new ArgumentNullException("Matrix.Up");
		}
		out.x = matrix[1][0];
		out.y = matrix[1][1];
		out.z = matrix[1][2];
	}
	
	/**
	 * Get a newly allocated Vector3 down direction of this matrix
	 * @return down direction of this matrix
	 */
	public Vector3 Down()
	{
		return new Vector3(-matrix[1][0], -matrix[1][1], -matrix[1][2]);
	}
	
	/**
	 * Get the Vector3 down direction without allocating new memory
	 * @param out argument will be set to the down direction of this matrix
	 * @throws ArgumentNullException if out == null
	 */
	public void Down(Vector3 out) throws ArgumentNullException
	{
		if(out == null)
		{
			throw new ArgumentNullException("Matrix.Down");
		}
		out.x = -matrix[1][0];
		out.y = -matrix[1][1];
		out.z = -matrix[1][2];
	}
	
	/**
	 * Get a newly allocated Vector3 right direction of this matrix
	 * @return right direction of this matrix
	 */
	public Vector3 Right()
	{
		return new Vector3(matrix[0][0], matrix[0][1], matrix[0][2]);
	}
	
	/**
	 * Get the Vector3 Right direction without allocating new memory
	 * @param out argument will be set to the Right direction of this matrix
	 * @throws ArgumentNullException if out == null
	 */
	public void Right(Vector3 out) throws ArgumentNullException
	{
		if(out == null)
		{
			throw new ArgumentNullException("Matrix.Right");
		}
		out.x = matrix[0][0];
		out.y = matrix[0][1];
		out.z = matrix[0][2];
	}
	
	/**
	 * Get a newly allocated Vector3 left direction of this matrix
	 * @return left direction of this matrix
	 */
	public Vector3 Left()
	{
		return new Vector3(-matrix[0][0], -matrix[0][1], -matrix[0][2]);
	}
	
	/**
	 * Get the Vector3 left direction without allocating new memory
	 * @param out argument will be set to the left direction of this matrix
	 * @throws ArgumentNullException if out == null
	 */
	public void Left(Vector3 out) throws ArgumentNullException
	{
		if(out == null)
		{
			throw new ArgumentNullException("Matrix.Left");
		}
		out.x = -matrix[0][0];
		out.y = -matrix[0][1];
		out.z = -matrix[0][2];
	}
	
	/**
	 * Get a newly allocated Vector3 positional translation of this matrix
	 * @return
	 */
	public Vector3 GetTranslation()
	{
		return new Vector3(matrix[0][3], matrix[1][3], matrix[2][3]);
	}
	
	/**
	 * Get a Vector3 positional translation of this matrix,
	 * does not allocate new memory, input argument will be this return
	 * @param out pointer to the vector3 you wish to set to this matrice's translation value
	 * @return the input argument with changed values
	 * @throws ArgumentNullException if argument 'out' == null
	 */
	public void GetTranslation(Vector3 out) throws ArgumentNullException
	{
		if(out == null)
		{
			throw new ArgumentNullException("Matrix.GetTranslation(Vector3)");
		}
		out.x = matrix[0][3];
		out.y = matrix[1][3];
		out.z = matrix[2][3];
	}
	
	/**
	 * Set the positional translation of this matrix
	 * @param val
	 */
	public void SetTranslation(Vector3 val)
	{
		matrix[0][3] = val.x;
		matrix[1][3] = val.y;
		matrix[2][3] = val.z;
	}
	
	// --------------------------------------------
	//		Static API
	// --------------------------------------------
	
	/**
	 * Recursive Determinant algorithm
	 * @param mat 2D float array matrix
	 * @return Determinant value of the argument matrix
	 */
	public static double Determinant(float[][] mat) 
	{ 
		double result = 0;

		if(mat.length == 1)
		{ 
			result = mat[0][0];
			return result;
		} 
		else if(mat.length == 2) 
		{ 
			result = mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
			return result;
		}

		for(int i = 0; i < mat[0].length; i++)
		{
			float temp[][] = new float[mat.length - 1][mat[0].length - 1];
			for(int j = 1; j < mat.length; j++)
			{
				System.arraycopy(mat[j], 0, temp[j-1], 0, i);
				System.arraycopy(mat[j], i+1, temp[j-1], i, mat[0].length-i-1);
			}
			result += mat[0][i] * Math.pow(-1, i) * Matrix4.Determinant(temp);
		}

		return result;
	}

	public static void CreateFromYawPitchRoll(float yaw, float pitch, float roll)
	{
		
	}
	
	public static void CreateLookAt(Vector3 cameraPosition, Vector3 cameraLookat, Vector3 cameraUp, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreatePerspective(float width, float height, float nearPlane, float farPlane, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreateRotationX(float radians, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreateRotationY(float radians, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreateRotationZ(float radians, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreateFromAxisAngle(Vector3 axis, float angleRadians, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreateScale(float value, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreateScale(float x, float y, float z, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreateScale(Vector3 scaling, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreateTranslation(float x, float y, float z, Matrix4 resultMatrix)
	{
		
	}
	
	public static void CreateTranslation(Vector3 translation, Matrix4 resultMatrix)
	{
		
	}
	
	public static void Invert(Matrix4 input, Matrix4 resultMatrix)
	{
		
	}
	
	// --------------------------------------------
	//		Static Basic Math API
	// --------------------------------------------
	
	public static void Multiply(Matrix4 one, Matrix4 two, Matrix4 resultMatrix)
	{
		
	}
	
	public static void Divide(Matrix4 one, Matrix4 two, Matrix4 resultMatrix)
	{
		
	}
	
	public static void Add(Matrix4 one, Matrix4 two, Matrix4 resultMatrix)
	{
		
	}
	
	public static void Subtract(Matrix4 one, Matrix4 two, Matrix4 resultMatrix)
	{
		
	}
}
