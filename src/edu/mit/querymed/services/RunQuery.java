package edu.mit.querymed.services; 

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.sparql.lib.org.json.JSONObject;

public class RunQuery extends HttpServlet{
	
	private static final long serialVersionUID = 2991523997146964023L;
	
	//Default endpoints we have
	private static final String DISEASOME_ENDPOINT = "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
	private static final String DAILYMED_ENDPOINT = "http://www4.wiwiss.fu-berlin.de/dailymed/sparql";
	
	public void doGet(HttpServletRequest req,
            HttpServletResponse resp)
		throws IOException, ServletException{
    	
    	try {
 
    		String keyword = req.getParameter("keyword");
    		
            if (keyword == null) {
                // The request parameter 'param' was not present in the query string
                // e.g. http://hostname.com?a=b
            } else if ("".equals(keyword)) {
                // The request parameter 'param' was present in the query string but has no value
                // e.g. http://hostname.com?param=&a=b
            }

            String diseasomeQuery = Util.prefixes + " SELECT ?disease WHERE {?x rdfs:label ?disease FILTER regex(?disease, '" + keyword  + "', 'i') }";
            String dailymedQuery = Util.prefixes + " SELECT ?name ?indication WHERE {?x dailymed:indication ?indication FILTER regex(?indication, '" + keyword +"', 'i') ?x rdfs:label ?name}";

            ResultSet diseasomeResults = QueryExecutionFactory.sparqlService(DISEASOME_ENDPOINT,diseasomeQuery).execSelect();
            ResultSet dailymedResults = QueryExecutionFactory.sparqlService(DAILYMED_ENDPOINT,dailymedQuery).execSelect();

            // The following generates a page showing all the request parameters
            PrintWriter out = resp.getWriter();
            
            //Sending the response as a json type
            resp.setContentType("application/json");
            
            //Send the results as a JSONobject
            JSONObject jsonResp = new JSONObject();
            
            //JSON objects the endpoints send their values in
            JSONObject jsonDiseasome = new JSONObject();
            JSONObject jsonDailymed = new JSONObject();
	       	
            int dieseasomeCount=0;
	        
            while (diseasomeResults.hasNext()){
	 			QuerySolution diseasomeSolution = diseasomeResults.next();
	 			jsonDiseasome.put("disease"+(dieseasomeCount++),diseasomeSolution.get("disease"));
	 		}
	
	        int dailymedCount=0;
	        while (dailymedResults.hasNext()){
	 			QuerySolution dailymedSolution = dailymedResults.next();
	 			jsonDailymed.put("name"+(dailymedCount++),dailymedSolution.get("name"));
	 			jsonDailymed.put("indication"+(dailymedCount++),dailymedSolution.get("indication"));
	        }
	         
	         //Finaly add the 2 JSONObjects to the JSONObjects that we are returning to the client
         	jsonResp.put(DISEASOME_ENDPOINT, jsonDiseasome);
            jsonResp.put(DAILYMED_ENDPOINT, jsonDailymed);
            out.write(jsonResp.toString());
    	} 
    	catch (Exception e) {
    		System.err.println(e);
        }	
	}
}
