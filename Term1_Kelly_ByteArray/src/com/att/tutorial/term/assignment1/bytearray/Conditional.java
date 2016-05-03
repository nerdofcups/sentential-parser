package com.att.tutorial.term.assignment1.bytearray;

import java.io.IOException;

public class Conditional extends Connective {
	
	public Conditional(Term input, Term output) throws IOException, InputSyntaxException {
		
		super("->", input, output, getNode());
		
	}

}
