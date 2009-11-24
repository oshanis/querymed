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
import com.hp.hpl.jena.sparql.lib.org.json.JSONArray;

public class GetProperties  extends HttpServlet{

	
    /**
	 * I have no idea why this was generated! -- Oshani
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,
            HttpServletResponse resp)
		throws IOException, ServletException{
    	
    	try {
 
    		String service = req.getParameter("service");
            if (service == null) {
                // The request parameter 'param' was not present in the query string
                // e.g. http://hostname.com?a=b
            } else if ("".equals(service)) {
                // The request parameter 'param' was present in the query string but has no value
                // e.g. http://hostname.com?param=&a=b
            }

            String query = Util.prefixes + "SELECT DISTINCT ?p WHERE {?s ?p ?o}";
            
    		QueryExecution e  = QueryExecutionFactory.sparqlService(service, query);
    		
    		ResultSet results = e.execSelect();

            // The following generates a page showing all the request parameters
            PrintWriter out = resp.getWriter();
            
            //Sending the response as a json type
            resp.setContentType("application/json");
            
            //A JSON array seems too much work!
            //JSONArray arr = new JSONArray();
            
            //So, just sending as comma seperated values:
            String responseStr = "";
            
            while (results.hasNext()){
    			QuerySolution s = results.next();
    			responseStr += s.get("p") + ",";
    		//	arr.put(s.get("p"));
    		}
            out.write(responseStr);
            //out.write(arr.toString());
    	} 
    	catch (Exception e) {System.err.println(e);
        }	
	}
    
}
