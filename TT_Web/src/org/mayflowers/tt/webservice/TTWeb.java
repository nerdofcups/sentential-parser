package org.mayflowers.tt.webservice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TTWeb
 */
@WebServlet("/TTWeb")
public class TTWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TTWeb() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      
	 * Takes a String "term" parameter value and returns a well-formed HTML
	 * representation of a sentential calculus equation and digraph.
	 * 
	 * Passing a null value or an empty String will cause a {@link ServletException}
	 * to be thrown
	 *      
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//TODO: remove this line when encoding nullpointerexception issue is resolved.
		//String encoding = null;
		
		try {
			
			String inputTerm = request.getParameter("term");
			
			if (inputTerm == null) {
				
				throw new ServletException("ServletException: missing term parameter.");
				
			}
			
			if (inputTerm.length() < 1) {
				
				throw new ServletException("ServletException: term cannot be bound to empty String.");
				
			}
			
			String encoding = request.getCharacterEncoding();
			response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
			response.getWriter().append(TTHTML.getExpressionHTML(inputTerm, encoding));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			//TODO: Reconsider content-type of response.
			//TODO: remove "encoding" from the append below.
			String encoding = request.getCharacterEncoding();
			response.getWriter().append("Error: Please enter valid equation." + encoding);
			
		}
		
	}
}
