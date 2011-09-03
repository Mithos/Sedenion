package sedenion;

public class Complex implements Extendable{


	// Immutable instance vars

	public final double re;
	public final double im;

	// Constructor
	
	public Complex(double re, double im){
		this.re = re;
		this.im = im;
	}

	// Lazy vals

	protected double abs = Double.NaN;
	protected Extendable conj = null;
	protected String toString = null;
	protected Matrix toMatrix = null;
	protected double arg = Double.NaN;

	public Complex add(Complex that){
		return new Complex(this.re + that.re, this.im + that.im);
	}
	public Complex subtract(Complex that){
		return new Complex(this.re - that.re, this.im - that.im);
	}

	public Complex multiply(Complex that){
		return new Complex(this.re*that.re-this.im*that.im, this.re*that.im+this.im*that.re);
	}

	public Extendable conj(){
		if(null == conj) conj = new Complex(re, -im);
		return conj;
	}

	public Matrix toMatrix(){
		if(null == toMatrix) toMatrix = new Matrix( new double[] {re, -im, im, re}, 2);
		return toMatrix;
	}

	public double abs(){
		if(Double.NaN == abs) abs = Math.sqrt(re*re + im*im);
		return abs; 
	}

	public Complex divide(Complex that){
		return new Complex((re*that.re + im*that.im)/(that.re*that.re + that.im*that.im), (im*that.re - re*that.im)/(that.re*that.re + that.im*that.im));
	}

	/** Raise this to the power of that */
	public Complex pow(Complex that){
		double r = Math.pow(abs(), that.re)*Math.pow(Math.E, -that.im*arg());
		double theta = that.im*Math.log(abs())+that.re*arg();
		return new Complex(r*Math.cos(theta), r*Math.sin(theta));		
	}


	public double arg(){
		if(Double.NaN == arg) arg = Math.atan2(im, re);
		return arg;
	}

	public String toString(){
		if(null == toString) toString = "" + re + " " + im + "i";
		return toString;
	}

	// Static Methods
	
	private static int signumc(double d){
		if(d == 0)
			return 1;
		else 
			return (int)Math.signum(d);
	}

	/** Returns the positive (primary) complex square root of a Complex Number (even if the number is entirely real or imaginary).
   		The other square root can be found by -sqrt(myComplex)
  	 */
  	public static Complex sqrt(Complex c)  {
		// Extract values and use common names
		double x = c.re;
		double y = c.im;
		double r = c.abs;
    		double gamma = Math.sqrt((r+x)/2);
		double delta = signumc(y)*Math.sqrt((r-x)/2);
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
		double alpha = Math.sin(a)/(Math.cos(a)+Math.cosh(b));
		double beta = Math.sin(a)/(Math.cos(a)+Math.cosh(b));
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
		double alpha = Math.sinh(a)/(Math.cosh(a)+Math.cos(b));
		double beta = Math.sin(b)/(Math.cosh(a)+Math.cos(b));
		return new Complex(alpha, beta);
	}
}