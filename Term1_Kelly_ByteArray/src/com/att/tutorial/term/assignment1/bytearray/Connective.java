package com.att.tutorial.term.assignment1.bytearray;

import java.io.IOException;
import java.io.PrintStream;

public abstract class Connective extends Operator{
	
	protected Term input;
	
	protected Connective(String symbol, Term input, Term output, String node) throws IOException, InputSyntaxException {
		
		super(symbol, output, node);
		
		this.input = input;
		
	}
	
	public String toString() {
		
		return "(" + input + symbol + output + ")";
		
	}

	public void makeDigraph(PrintStream print) {
	
		print.println(indent + indent + node + " [ label=\"" + symbol + "\" ]");
		input.makeDigraph(print);
		output.makeDigraph(print);
		print.println(indent + indent + node + " -> { " + input.printNode() + " " + output.printNode() + " }");
		
	}


}
