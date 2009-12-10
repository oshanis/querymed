package edu.mit.querymed.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

/*
 * This servlet is used to retrieve all the possible values of any given SPARQL endpoint
 */
public class GetPropertyVals  extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,
            HttpServletResponse resp)
		throws IOException, ServletException{
    	
    	try {
 
    		String service = req.getParameter("service");
    		String property = req.getParameter("property");
            
//    		if (service == null) {
//                // The request parameter 'param' was not present in the query string
//                // e.g. http://hostname.com?a=b
//            } else if ("".equals(service)) {
//                // The request parameter 'param' was present in the query string but has no value
//                // e.g. http://hostname.com?param=&a=b
//            }

            String query = Util.prefixes + "SELECT DISTINCT ?o WHERE {?s <"+property+"> ?o}";
            
    		QueryExecution e  = QueryExecutionFactory.sparqlService(service, query);
    		
    		ResultSet results = e.execSelect();

            // The following generates a page showing all the request parameters
            PrintWriter out = resp.getWriter();
            
            //Sending the response as a json type
            resp.setContentType("text/plain");
            
            //So, just sending as comma seperated values:
            String responseStr = "";
            
            while (results.hasNext()){
    			QuerySolution s = results.next();
    			responseStr += s.get("o");
    			responseStr += ",";

//    			if (s.get("o").isLiteral()){
//    				String dataType = s.getLiteral("o").getDatatype().getURI();
//    				if (dataType.equals("http://www.w3.org/2001/XMLSchema#int")){
//    					responseStr += s.getLiteral("o").getInt() + ",";	
//    				}
//    				
//    			}
//    			if (s.get("o").isAnon()) {
//    				//I dunno what to do here
//    				System.out.println("@@TODO: Handle anon case!");
//    			}
//    			if (s.get("o").isResource()){
//    				responseStr += s.getResource("o").getURI() + ",";
//    			}
    			
    		}
            out.write(responseStr);
    	} 
    	catch (Exception e) {System.err.println(e);
        }	
	}
    
}
