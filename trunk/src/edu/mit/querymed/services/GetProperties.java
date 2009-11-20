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

            String prefixes = 	"PREFIX d2r: <http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#>" +
            					"PREFIX vocabClass: <http://www4.wiwiss.fu-berlin.de/drugbank/vocab/resource/class/>" +
            					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
            					"PREFIX diseasome: <http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/>" +
            					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            					"PREFIX foaf: <http://xmlns.com/foaf/0.1/>" +
            					"PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
            					"PREFIX vocabProperty: <http://www4.wiwiss.fu-berlin.de/drugbank/vocab/resource/property/>" +
            					"PREFIX db: <http://www4.wiwiss.fu-berlin.de/diseasome/resource/>" +
            					"PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/>" +
            					"PREFIX dbpedia: <http://dbpedia.org/ontology/>" +
            					"PREFIX map: <file:/C:/apps/diseasome/diseasome.n3#>"+
								"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
            
            String query = prefixes + "SELECT DISTINCT ?p WHERE {?s ?p ?o}";
            
    		QueryExecution e  = QueryExecutionFactory.sparqlService(service, query);
    		
    		ResultSet results = e.execSelect();

            // The following generates a page showing all the request parameters
            PrintWriter out = resp.getWriter();
            
            //Sending the response as a json type
            resp.setContentType("application/json");
            JSONArray arr = new JSONArray();
            while (results.hasNext()){
    			QuerySolution s = results.next();
    			arr.put(s.get("p"));
    		}
            out.write(arr.toString());
    	} 
    	catch (Exception e) {System.err.println(e);
        }	
	}
    
}
