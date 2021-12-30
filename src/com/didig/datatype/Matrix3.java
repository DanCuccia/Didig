package com.didig.datatype;

/**
 * 3x3 float array that defines the rotational & scaling part of a matrix
 * @author Daniel Cuccia
 *
 */
public class Matrix3 
{
	public float[][] mat = new float[3][3];
	
	/**
	 * Initialize member variables to 0
	 */
	public Matrix3()
	{ }
	
	/**
	 * Create this matrix from another
	 * @param m original
	 */
	public Matrix3(Matrix3 m)
	{
		mat[0][0] = m.mat[0][0];
		mat[0][1] = m.mat[0][1];
		mat[0][2] = m.mat[0][2];
		mat[1][0] = m.mat[1][0];
		mat[1][1] = m.mat[1][1];
		mat[1][2] = m.mat[1][2];
		mat[2][0] = m.mat[2][0];
		mat[2][1] = m.mat[2][1];
		mat[2][2] = m.mat[2][2];
	}
	
	/**
	 * construct this matrix using the values from a 4x4 matrix
	 * @param m original
	 */
	public Matrix3(Matrix4 m)
	{
		mat[0][0] = m.matrix[0][0];
		mat[0][1] = m.matrix[0][1];
		mat[0][2] = m.matrix[0][2];
		
		mat[1][0] = m.matrix[1][0];
		mat[1][1] = m.matrix[1][1];
		mat[1][2] = m.matrix[1][2];
		
		mat[2][0] = m.matrix[2][0];
		mat[2][1] = m.matrix[2][1];
		mat[2][1] = m.matrix[2][1];
	}
	
	/**
	 * Index into the matrix and get a value
	 * @param row row index
	 * @param column column index
	 * @return matrix[row][column]
	 * @throws IndexOutOfBoundsException if row || column >= 3
	 */
	public float GetValue(int row, int column) throws IndexOutOfBoundsException
	{
		if(row >= 3 || column >= 3)
		{
			throw new IndexOutOfBoundsException("Matrix3.GetValue");
		}
		return mat[row][column];
	}
	
	/**
	 * get a newly allocated vector3 row of values
	 * @param r row index
	 * @return x,y,z row of matrix values
	 * @throws IndexOutOfBoundsException if r >= 3
	 */
	public Vector3 Row(int r) throws IndexOutOfBoundsException
	{
		if(r >= 3)
		{
			throw new IndexOutOfBoundsException("Matrix3.GetValue");
		}
		return new Vector3(mat[r][0], mat[r][1], mat[r][2]);
	}
	
	/**
	 * get a newly allocated vector3 column of values
	 * @param c column index
	 * @return x,y,z column of matrix values
	 * @throws IndexOutOfBoundsException if c >= 3
	 */
	public Vector3 Column(int c) throws IndexOutOfBoundsException
	{
		if(c >= 3)
		{
			throw new IndexOutOfBoundsException("Matrix3.GetValue");
		}
		return new Vector3(mat[0][c], mat[1][c], mat[2][c]);
	}
}
