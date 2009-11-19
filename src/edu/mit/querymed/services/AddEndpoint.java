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

import edu.mit.querymed.Endpoint;
import edu.mit.querymed.QueryMedManager;
import edu.mit.querymed.SourceManager;

public class AddEndpoint  extends HttpServlet{
	
    public void doGet(HttpServletRequest req,
            HttpServletResponse resp)
		throws IOException, ServletException{
    	
    	try {
 
    		// Get the values of the name and the uri
    		//String name = "name";
            String name = req.getParameter("name");
            String uri = req.getParameter("uri");
            
            if (name == null && uri == null) {
                // The request parameters were not present in the query string
                // e.g. http://hostname.com?a=b
            } else if ("".equals(name) && "".equals(uri)) {
                // The request parameters were present in the query string but has no value
                // e.g. http://hostname.com?name=&a=b
            }
        
            //Create an endpoint object and add it to the source manager
            Endpoint e = new Endpoint(name, uri);
            SourceManager sm = new SourceManager();
            sm.addEndpoint(e);
            
            //TODO: Assume that the state of the system is sent in a cookie.
            //The cookie says whether the system was initialized before or not.
            //If it was not initialized, we initialize the system using the QueryMedManager.
            //If it was, then we use the values that are in the QueryMedManager instance
            //For now, we create the instance and use the static variables there
            QueryMedManager qmm = new QueryMedManager(sm, null);
            
            // The following generates a page showing all the request parameters
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/plain");
            
//            MediatorTest ea = new MediatorTest(value);
// 			QueryExecution[] qes = ea.constructSelectQueries();
// 			Vector<String> v = ea.printResults(qes);
//	          for (String i: v) {
//	        	  out.println("\n"+i);
//	          }

            out.close();
    		
    	} 
    	catch (Exception e) {System.err.println(e);
        }	
	}
}
