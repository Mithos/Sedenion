package org.sedenion.complex;

/**
 * A class that represents a Complex number.
 * 
 * This class extends Number. However, it overloads many of the default Number methods
 * with ones that accept Complex arguments, for added efficiency and functionality.
 * 
 * @author james
 *
 */
public class Complex extends Number{


	// Immutable instance vars

	public final double re;
	public final double im;

	// Constructors
	
	public Complex(double re, double im){
		super(new Number(re), new Number(im));
		this.re = re;
		this.im = im;
	}
	
	// Lazy vals

	protected Complex conj = null;
	protected String toString = null;
	protected Matrix toMatrix = null;
	protected Double arg = null;
	
	public Complex add(Complex that){
		return new Complex(re + that.re, im + that.im);
	}
	
	public Complex subtract(Complex that){
		return new Complex(re - that.re, im - that.im);
	}

	public Complex multiply(Complex that){
		return new Complex(re*that.re-im*that.im, re*that.im+im*that.re);
	}
	
	public Complex divide(Complex that){
		return new Complex((re*that.re + im*that.im)/(that.re*that.re + that.im*that.im), (im*that.re - re*that.im)/(that.re*that.re + that.im*that.im));
	}

	@Override
	public Complex conjugate(){
		if(null == conj) conj = new Complex(re, -im);
		return conj;
	}

	public Matrix toMatrix(){
		if(null == toMatrix) toMatrix = new Matrix( new double[] {re, -im, im, re}, 2);
		return toMatrix;
	}


	/** Raise this to the power of that */
	public Complex pow(Complex that){
		double r = Math.pow(absolute(), that.re)*Math.pow(Math.E, -that.im*arg());
		double theta = that.im*Math.log(absolute())+that.re*arg();
		return new Complex(r*Math.cos(theta), r*Math.sin(theta));		
	}


	public double arg(){
		if(null == arg) arg = new Double(Math.atan2(im, re));
		return arg.doubleValue();
	}

	public String toString(){
		if(null == toString) toString = String.format("(%f, %f)", re, im) ;
		return toString;
	}

	// Static Methods
	
	private static int signum(double d){
		if(d == 0)
			return 1;
		else 
			return (int)Math.signum(d);
	}

	/** Returns the positive (primary) complex square root of a Complex Complex (even if the Complex is entirely real or imaginary).
   		The other square root can be found by -sqrt(myComplex)
  	 */
  	public static Complex sqrt(Complex c)  {
		// Extract values and use common names
		double x = c.re;
		double y = c.im;
		double r = c.abs;
    	double gamma = Math.sqrt((r+x)/2);
		double delta = signum(y)*Math.sqrt((r-x)/2);
		return new Complex(gamma, delta);
  	}

	public static Complex sin(Complex c){
		return new Complex(Math.sin(c.re)*Math.cosh(c.im), Math.cos(c.re)*Math.sinh(c.im));
	} 

	public static Complex cos(Complex c){
		return new Complex(Math.cos(c.re)*Math.cosh(c.im), -Math.sin(c.re)*Math.sinh(c.im));
	}

	public static Complex tan(Complex c){
		double a = 2*c.re;
		double b = 2*c.im;
		double d = Math.cos(a) + Math.cosh(b);
		double alpha = Math.sin(a) / d;
		double beta = Math.sinh(b) / d;
		return new Complex(alpha, beta);
	}

	public static Complex sinh(Complex c){
		return new Complex(Math.sinh(c.re)*Math.cos(c.im), Math.sinh(c.re)*Math.sin(c.im));
	} 

	public static Complex cosh(Complex c){
		return new Complex(Math.cosh(c.re)*Math.cos(c.im), Math.sinh(c.re)*Math.sin(c.im));
	}

	public static Complex tanh(Complex c) {
		double a = 2*c.re;
		double b = 2*c.im;
		double d = Math.cosh(a) + Math.cos(b);
		double alpha = Math.sinh(a) / d;
		double beta = Math.sin(b) / d;
		return new Complex(alpha, beta);
	}
}
