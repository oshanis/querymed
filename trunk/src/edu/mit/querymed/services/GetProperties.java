package edu.mit.querymed.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.MediatorTest;

import com.hp.hpl.jena.query.QueryExecution;

public class GetProperties  extends HttpServlet{
	
    public void doGet(HttpServletRequest req,
            HttpServletResponse resp)
		throws IOException, ServletException{
    	
    	try {
 
    		// Get the value of a request parameter; the name is case-sensitive
            String name = "param";
            String value = req.getParameter(name);
            if (value == null) {
                // The request parameter 'param' was not present in the query string
                // e.g. http://hostname.com?a=b
            } else if ("".equals(value)) {
                // The request parameter 'param' was present in the query string but has no value
                // e.g. http://hostname.com?param=&a=b
            }
        
            // The following generates a page showing all the request parameters
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/plain");
        
            value = req.getParameter(name);
            
            MediatorTest ea = new MediatorTest(value);
 			QueryExecution[] qes = ea.constructSelectQueries();
 			Vector<String> v = ea.printResults(qes);
	          for (String i: v) {
	        	  out.println("\n"+i);
	          }

            out.close();
    		
    	} 
    	catch (Exception e) {System.err.println(e);
        }	
	}
}
