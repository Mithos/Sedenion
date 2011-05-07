package com.alexandria;

/**
 * An error thrown when an operation is attempted on two Matrices when the sizes of the two
 * does not allow it.
 * @author James McMahon <a href='mailto:james1345@googlemail.com'>{@literal <}james1345@googlemail.com{@literal >}</a>
 *
 */
public class MatrixSizeMissMatchException extends Exception {
	
	private static final long serialVersionUID = 6799388523538898562L;

	public MatrixSizeMissMatchException(){
		System.err.print("Matrix sizes incompatible.\n");
	}
}