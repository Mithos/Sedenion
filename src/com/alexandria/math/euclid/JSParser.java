package com.alexandria.math.euclid;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

//TODO make it work after a slightly nicer fashion...
/**
 * A <b>MASSIVE</b> cheat. Just running it through java's javascript engine (which has access
 * to all of java's methods provided the correct packages are imported).
 * 
 * N.B. be aware of the difference between packages available to the class and engine.
 * 
 * @author James
 *
 */
public class JSParser implements com.alexandria.math.euclid.Parser {
	
	private ScriptEngineManager mgr = new ScriptEngineManager();
    private ScriptEngine engine = mgr.getEngineByName("JavaScript");
	
    
    public JSParser(){
    	
    	try {
			engine.eval("importPackage(com.alexandria.math)");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
    }
    
	public String parse(String input) {
		
		String process = "";
		
		//Matrix processing
		if(input.contains("[")){
			process = "var x = " + input.substring(input.indexOf("[")) + ";";
			
			try {engine.eval(process);} 
			catch (ScriptException e) {e.printStackTrace();}
			
			process = "var " + input.split("\\W")[0] + " = " + "new Matrix(x)";
		}
		else {
			process = input;
		}
		
		
		try {
			return String.valueOf((engine.eval(process)));
		} catch (ScriptException e) {
			e.printStackTrace();
			return "\n\n+++PINEAPPLE ERROR+++\n+++REDO FROM START+++\n\n";
		}
	}
}


