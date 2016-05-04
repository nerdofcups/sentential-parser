package com.att.tutorial.tt.assignment1.bytearray;

// many formulas can be inputted through the command line arguments
//remove reset method
//have separate threads for each unit test
//test that a new thread is made 

import java.io.InputStream;

import com.att.tutorial.term.assignment1.bytearray.Term;

public class TT {

	public static void main(String[] args) {
		try {
			
			tt(System.in);			
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		}

	}
	
	private static void tt(InputStream stream) {
		
		try {
			
			Term term = Term.parseTerm(stream);
			System.out.println(term.toString());
			
	        System.out.println();
	        System.out.println("Graphviz");
	        System.out.println("========");
	        System.out.println();
	        term.toDigraph(System.out);
						
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
