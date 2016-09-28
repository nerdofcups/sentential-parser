package org.mayflowers.tt.webservice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import com.att.tutorial.term.assignment1.bytearray.Term;

/**
 * Processes the String and Stream output from the sentential calculus
 * Term.java into an String with HTML that can be passed to a Servlet
 * and displayed on a HTML page.
 * 
 * @author Kelly Mayfield
 */
public class TTHTML {
	
	/**
	 * HTML blankspace used to create an indent.
	 * 
	 */
	private static final String indent = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	
	/**
	 * Builds a String containing a Sentential Calculus sentence without blank spaces and its
	 * associated syntax tree as a digraph containing HTML.
	 * 
	 * @param inputTerm
	 * 					A String containing a valid Sentential Calculus Sentence
	 * @param encoding TODO
	 * 
	 * @return <code>response</code>
	 * 					A String containing the original Sentenial Calculus sentence
	 * without blank space and a syntax tree digraph containing HTML
	 * 
	 * @throws          Exception
	 */
	public static String getExpressionHTML(String inputTerm, String encoding) throws Exception {
		
		String output;
		
		try (ByteArrayInputStream streamTerm = new ByteArrayInputStream(inputTerm.getBytes(encoding))) {
			
			Term term = Term.parseTerm(streamTerm);
			output = term.toString();
		
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream digraphStream = new PrintStream(out);
			term.toDigraph(digraphStream);
			
			//TODO: change input parameter to input encoding and create output encoding.
			//TODO: Add an optional parameter where the client can specify output encoding?
			//TODO: change div to pre in the html file.
			output = output.concat(new String (out.toByteArray(), StandardCharsets.UTF_8));
			
			//output = htmlFormatter(output);

			
		} catch (Exception e) {
			
			throw new Exception(e);
			
		}
		
		return output;
		
	}

	/**
	 * Injects HTML into a String containing a sentential calculus syntax tree
     * digraph. This allows customers to copy and paste the digraph straight from
     * the website and into their own documentation.  
	 * 
	 * @param
	 * 					OutputTerm a String containing a syntax tree digraph
	 * 
	 * @return
	 * 					<code>outputTerm</code> the original String with injected HTML
	 */
	private static String htmlFormatter(String outputTerm){
		
		outputTerm = outputTerm.replaceAll("\\.\\.", "<br><br>..");
		outputTerm = outputTerm.replaceAll("digraph \\{", "<br>" + indent + "digraph { <br>" + indent + indent);
		outputTerm = outputTerm.replaceAll("\\]", "] <br>" + indent + indent);
		outputTerm = outputTerm.replaceAll("\\}", "} <br>" + indent + indent);
		outputTerm = outputTerm.replaceAll("\\}$", "<br>" + indent + "}");
		return outputTerm;
		
	}

}
