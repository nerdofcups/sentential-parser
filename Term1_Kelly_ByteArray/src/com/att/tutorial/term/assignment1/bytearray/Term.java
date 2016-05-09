package com.att.tutorial.term.assignment1.bytearray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public abstract class Term {
	
	String symbol;	
	String node;
	static int nodeNum = 0;
	final String indent = "     ";
	
	Term(String symbol, String node) {
		
		this.symbol = symbol;
		this.node = node;
		
	}
	
	//public methods
	
	public static Term parseTerm(InputStream stream) throws IOException, InputSyntaxException {
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			
			return parseTerm(reader);
			
		} 
	}
	
	public String toString() {
		
		return symbol;
		
	}
	
	public void toDigraph(PrintStream print) {
		
		print.println(".. graphviz::");
		print.println("");
		print.println(indent + "digraph {");
		makeDigraph(print);
		print.println(indent + "}");
		
	}
	
	private static Term parseTerm(BufferedReader reader) throws IOException, InputSyntaxException {
			
		int c = Term.skipWhitespace(reader);
			
		if (c == '(') {
			
			return Term.parseConnective(reader);
			
		} else if (c == '~') {
			
			return Term.parseNegation(reader);
			
		} else if (Character.isUpperCase(c)) {
			
			return new Sentence(c);
			
		} else {
		
			throw new InputSyntaxException(String.valueOf(c));
			
		}
		
	}
	
	//protected methods
	
	protected String getSymbol() {
		
		return symbol;
	}
	
	protected String printNode() {
		
		return node;
		
	}

	protected void makeDigraph(PrintStream print) {
		
		print.println(indent + indent + node + " [ label=\"" + symbol + "\" ]");
		
	}
	
	protected static String getNode() {
		
		String node = "node" + Integer.toString(nodeNum); 
		nodeNum = nodeNum + 1;
		
		return node;
		
	}
	

	
	//Private Methods
	
	private static Term parseNegation(BufferedReader reader) throws IOException, InputSyntaxException {
		
		Term output = Term.parseTerm(reader);
		
		return new Negation(output);
		
	}
	
	private static Term parseConnective(BufferedReader reader) throws IOException, InputSyntaxException {
		
		Term input = parseTerm(reader);
		
		int op = skipWhitespace(reader);
		
		if (op == -1) {
			
			throw new InputSyntaxException();
			
		}
		
		switch (op) {
		
			case '-':
				
				reader.mark(1);
				if (reader.read() != '>') {
					
					throw new InputSyntaxException("Malformed conditional operator.");
					
				}
				
				break;
				
			case '<':
				
				reader.mark(2);
				
				if (reader.read() != '-') {
					
					throw new InputSyntaxException("Malformed biconditional operator.");
					
				} else if (reader.read() != '>') {
					
					throw new InputSyntaxException("Malformed biconditional operator.");
					
				}
				
				break;
				
			default:
				
				break;				
		
		}
	
		Term output = Term.parseTerm(reader);
		
		int c = skipWhitespace(reader);
		
		if (c != ')') {
			
			throw new InputSyntaxException();
			
		}
		
		switch (op) {
		
			case '&':
				
				return new Conjunction(input, output);
				
			case '|':
				
				return new Disjunction(input, output);
				
			case '-':
				
				return new Conditional(input, output);
				
			case '<':
				
				return new Biconditional(input, output);
				
			default:
				
				throw new InputSyntaxException();
		
		}
		
	}
	
	 protected static int skipWhitespace(BufferedReader reader) throws IOException {

	        int c;

	        do {

	            reader.mark(1);
	            c = reader.read();

	        } while ((c != -1) && Character.isWhitespace(c));
	        
	        return c;

	    }


}
