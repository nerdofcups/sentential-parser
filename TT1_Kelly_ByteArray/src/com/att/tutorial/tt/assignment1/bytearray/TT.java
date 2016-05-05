package com.att.tutorial.tt.assignment1.bytearray;

import java.io.ByteArrayInputStream;

// many formulas can be inputted through the command line arguments
//remove reset method
//have separate threads for each unit test
//test that a new thread is made 

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.att.tutorial.term.assignment1.bytearray.Term;

public class TT implements Runnable {

	String inputArgs;
	
	public TT(String s) {
		
		inputArgs = s;
		
	}
	
	public static void main(String[] args) {
		
		try {
			
			for (String s: args) {
			
				Runnable r = new TT(s);
				new Thread(r).start();
				
			}
			
		} catch (Exception e ) {
			
			e.printStackTrace();
			
		}

	}
	
	@Override
	public void run() {
		
		ByteArrayInputStream stream = new ByteArrayInputStream(inputArgs.getBytes(StandardCharsets.UTF_8));
		tt(stream);
		
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

	//@Override
	//public void run() {
		// TODO Auto-generated method stub
		
	//}

}
