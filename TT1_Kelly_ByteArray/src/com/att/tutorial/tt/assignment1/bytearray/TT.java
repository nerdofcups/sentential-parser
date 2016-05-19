package com.att.tutorial.tt.assignment1.bytearray;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import com.att.tutorial.term.assignment1.bytearray.Term;

//TODO: add javadocs stuff (needs to be compatible with DOXYGEN

public class TT implements Runnable {

	String input;
	
	public TT(String s) {
		
		input = s;
		
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
		
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
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

}
