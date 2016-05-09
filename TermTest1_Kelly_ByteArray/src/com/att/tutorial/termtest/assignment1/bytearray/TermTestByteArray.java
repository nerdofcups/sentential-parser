package com.att.tutorial.termtest.assignment1.bytearray;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import com.att.tutorial.term.assignment1.bytearray.Term;

public class TermTestByteArray {
	
		@Test
		public void parseStringTest() throws IOException {
			
			try {
				
				
				String[] happyTest = new String[13];
				happyTest[0] = "(P&Q)";
				happyTest[1] = "(P|Q)";
				happyTest[2] = "(P->Q)";
				happyTest[3] = "(P<->Q)";
				happyTest[4] = "(~P->Q)";
				happyTest[5] = "  ( P & Q ) ";
				happyTest[6] = "(P->(Q|R))";
				happyTest[7] = "((P->Q)<->(~P|Q))";
				happyTest[8] = "~P";
				happyTest[9] = "~~P";
				happyTest[10] = "  P   ";
				happyTest[11] = "(P  &  (Q  ->R))";
				happyTest[12] = "(P->~(Q|R))";
			
				for (int m = 0; m < happyTest.length; m++) {
					
					ByteArrayInputStream stream = new ByteArrayInputStream(happyTest[m].getBytes(StandardCharsets.UTF_8));
					assertEquals(happyTest[m].replaceAll("\\s", ""), Term.parseTerm(stream).toString());
					System.out.println("stringPass" + m);
					
				}
				
				for (int j=0; j < happyTest.length; j++) {
					
					ByteArrayInputStream stream2 = new ByteArrayInputStream(happyTest[j].getBytes(StandardCharsets.UTF_8));
					Term term = Term.parseTerm(stream2);
					
					//Check that term returns a Printstream.				
					ByteArrayOutputStream outputstream = new ByteArrayOutputStream();			
					PrintStream print = new PrintStream(outputstream);					
					term.toDigraph(print);
					System.out.println("digraphPass" + j);
				
				}
				
			
			} catch (Exception e){ 
			
			e.printStackTrace();
			fail("Exception thrown.");
				
			} finally {
				
			//Error Cases: Syntax
			
			String[] syntaxTest = new String[8];
			syntaxTest[0] = "(R->P&Q)";
			syntaxTest[1] = "(R->P&Q";		
			syntaxTest[2] = "(R(->P&Q";
			syntaxTest[3] = "*";
			syntaxTest[4] = "(P&|Q)";
			syntaxTest[5] = "&";
			syntaxTest[6] = "~";
			syntaxTest[7] = "(R<-S)";

			for (int i = 0; i < syntaxTest.length; i++ ){
				try {
					
					ByteArrayInputStream stream = new ByteArrayInputStream(syntaxTest[i].getBytes(StandardCharsets.UTF_8));
					Term.parseTerm(stream);
					fail("SyntaxErrorException expected at " + i + ".");
					
				} catch (Exception e) {
					
					//success
					System.out.println("exceptionPass" + i);
					
					}
				}			
			
			}
		}
		
	}
