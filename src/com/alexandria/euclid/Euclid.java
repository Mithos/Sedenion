package com.alexandria.euclid;

public class Euclid extends REPL {
	
	private static final long serialVersionUID = -8928768898596756864L;

	public Euclid(Parser p) {
		super(p);
		this.setTitle("Euclid");
	}
	
	public static void main(String[] args){
		Euclid main = new Euclid(new JSParser());
		
	}

}
