package com.att.tutorial.termtest.assignment1.bytearray;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.mayflowers.tt.webservice.TTHTML;

public class TTHTMLTest {

	@Test
	public void containsHTMLTest() {
		
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
				
				String html = TTHTML.getExpressionHTML(happyTest[m], StandardCharsets.UTF_8.displayName());
				assertTrue("Failure at " + m + ": not enough <br>.",contains(html, "<br>", 5));
				assertTrue("Failure at " + m + ": not enough &nbsp;.",contains(html, "&nbsp;", 5));
				
				System.out.println("HTMLContainsPass" + m);
				
			}
			
		} catch (Exception e) {
			
			fail("Exception thrown.");
			e.printStackTrace();
			
			
		} 
		
	}
	
	/**
	 * Checks that <code>match</code> occurs within <code>input</code> a minimum
	 * <code>min</code> amount of times.
	 * 
	 * @param input   The String whose contents will be tested.
	 * @param match   The String that is being searched for in the <code>input</code>.
	 * @param min     The minimum number of times that the <code>match</code> must occur within <code>input</code>.
	 * @return true   <code>input</code> contains <code>match</code> the minimum <code>min</code> amount of times.
	 * @return false  <code>input</code> does NOT contain <code>match</code> the minimum <code>min</code> amount of times.
	 */
	
	public static boolean contains(String input, String match, int min) {
		
		int counter = 0;
		int index = 0;
		
		do {
			
			index = input.indexOf(match, index);
			index++;
			counter++;
			
		} while (index > input.length());
		
		if (counter >= min) {
			
			return true;
		}
		
		return false;
		
	}

}
