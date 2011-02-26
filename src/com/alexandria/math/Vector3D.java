package com.alexandria.math;

//TODO well, everything. Rotations, cross products. so much fun
public class Vector3D extends Vector {
	
	
	/**
	 * Default Constructor.
	 * 
	 * Creates a new 3-dimensional vector with the given x and y values.
	 * 
	 * @param x The x value of the vector
	 * @param y The y value of the vector
	 * @param z The z value of the vector
	 */
	public Vector3D(double x, double y, double z) {
		super(3, 0);
		this.content[0][0] = x;
		this.content[1][0] = y;
		this.content[2][0] = z;
	}

}
