package com.att.tutorial.term.assignment1.bytearray;

import java.io.IOException;

public class Biconditional extends Connective {
	
	public Biconditional(Term input, Term output) throws IOException, InputSyntaxException {
		
		super("<->", input, output, getNode());
		
	}

}
