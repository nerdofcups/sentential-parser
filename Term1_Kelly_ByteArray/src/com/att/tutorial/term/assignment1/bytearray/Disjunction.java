package com.att.tutorial.term.assignment1.bytearray;

import java.io.IOException;

public class Disjunction extends Connective {
	
	public Disjunction(Term input, Term output) throws IOException, InputSyntaxException {
		
		super("|", input, output,getNode());
		
		
	}

}
