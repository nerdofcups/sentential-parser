package com.att.tutorial.termtest.assignment1.bytearray;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import com.att.tutorial.term.assignment1.bytearray.Term;

public class TermTestByteArray {
	
	FileInputStream filein;
	BufferedReader actual;
	BufferedReader expected;
	
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
					System.out.println("m" + m);
					
				}
			
				
				String[] digraphTest = new String[13];
				digraphTest[0] = "(P&Q)";
				digraphTest[1] = "(P|Q)";
				digraphTest[2] = "(P->Q)";
				digraphTest[3] = "(P<->Q)";
				digraphTest[4] = "(~P->Q)";
				digraphTest[5] = "  ( P & Q ) ";
				digraphTest[6] = "(P->(Q|R))";
				digraphTest[7] = "((P->Q)<->(~P|Q))";
				digraphTest[8] = "~P";
				digraphTest[9] = "~~P";
				digraphTest[10] = "  P   ";
				digraphTest[11] = "(P  &  (Q  ->R))";
				digraphTest[12] = "(P->~(Q|R))";
				
				for (int j=0; j < digraphTest.length; j++) {
					
					ByteArrayInputStream stream2 = new ByteArrayInputStream(digraphTest[j].getBytes(StandardCharsets.UTF_8));
					Term term = Term.parseTerm(stream2);
					
					//Read term digraph into a buffered reader					
					ByteArrayOutputStream outputstream = new ByteArrayOutputStream();			
					PrintStream print = new PrintStream(outputstream);					
					term.toDigraph(print);
					actual = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(outputstream.toByteArray())));
					
					//Read expected .txt file into Buffered Reader
					filein = new FileInputStream(new File("expected"+j+".txt"));
					expected = new BufferedReader(new InputStreamReader(filein));
					
					System.out.println("j" + j);
					
					//Compare Expected and Actual BufferedReader contents
					int act;
					int exp;
					
					do {
						
						actual.mark(1);
						act = actual.read();
						
						expected.mark(1);
						exp = expected.read();
						
						if (act != exp) {
							FileOutputStream fileout = new FileOutputStream(new File("outputDebug.txt"));
							PrintStream printfile = new PrintStream(fileout);
							term.toDigraph(printfile);
							fail("Equation "+j+" digraph does not match Expected Result. Check outputDebug.txt for more information.");
						}
						
					} while ((act != -1) && (exp != -1));
				
				}
				
			
			} catch (Exception e){ 
			
			e.printStackTrace();
			fail("Exception thrown.");
				
			} finally {
			
			filein.close();
			actual.close();
			expected.close();
				
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
					System.out.println("i" + i);
					
					}
				}			
			
			}
		}
		
	}
