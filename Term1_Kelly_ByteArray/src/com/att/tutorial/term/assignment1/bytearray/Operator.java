package com.att.tutorial.term.assignment1.bytearray;

import java.io.IOException;
import java.io.PrintStream;

public abstract class Operator extends Term {
	
	protected Term output;
	
	protected Operator(String symbol, Term output, String node) throws IOException, InputSyntaxException {
		
		super(symbol, node);
		
		this.output = output;
		
	}
	
	public String toString() {
		
		return symbol + output;
		
	}
	
	public void makeDigraph(PrintStream print) {
		
		print.println(indent + indent + node + " [ label=\"" + symbol + "\" ]");
		output.makeDigraph(print);
		print.println(indent + indent + node + " -> { " + output.printNode() + " }");
		
	}

}
