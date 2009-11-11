package edu.mit.querymed; 

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.MediatorTest;

import com.hp.hpl.jena.query.*;

public class RunQuery extends HttpServlet{
	
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

    
//	  public static void main(String arguments[]){
//	    	try {
//	    		
//	    		String service =  "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
//	    		String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?disease WHERE {?x rdfs:label ?disease FILTER regex(?disease, '^coronary artery disease$', 'i') }";
//	    		QueryExecution e  = QueryExecutionFactory.sparqlService(service, query);
//	    		
//	    		ResultSet results = e.execSelect();
//
//	    		while (results.hasNext()){
//	    			QuerySolution s = results.next();
//	    			System.out.println(s);
//	    		}
//	    		
//	    		service = "http://www4.wiwiss.fu-berlin.de/dailymed/sparql";
//	    		query = "PREFIX dailymed: <http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?name ?indication WHERE {?x dailymed:indication ?indication FILTER regex(?indication, 'coronary artery disease', 'i') ?x rdfs:label ?name}";
//	    		e  = QueryExecutionFactory.sparqlService(service, query);
//	    		
//	    		 results = e.execSelect();
//
//	    		 while (results.hasNext()){
//		    			QuerySolution s = results.next();
//		    			System.out.println(s);
//	    		 }
//
////	    		List<String> l = results.getResultVars();
////	    	
////	    		for (int i=0; i<l.size(); i++){
////	    			System.out.println(i+" : "+l.get(i));
////	    		}
//
//	    		
//	 //   			System.out.println(s.get("name"));
//	    		
//	    		
//	    	} 
//	    	catch (Exception e) {System.err.println(e);
//	        }
//	    }
}
