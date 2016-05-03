package com.att.tutorial.term.assignment1.bytearray;

import java.io.IOException;

public class Conjunction extends Connective {
	
	public Conjunction(Term input, Term output) throws IOException, InputSyntaxException {
		
		super("&", input, output, getNode());
		
	}

}
