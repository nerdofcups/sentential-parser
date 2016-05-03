package com.att.tutorial.term.assignment1.bytearray;

import java.io.IOException;

public class Negation extends Operator {
	
	public Negation(Term output) throws IOException, InputSyntaxException {
		
		super ("~", output, getNode());
		
	}

}
