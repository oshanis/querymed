package edu.mit.querymed.services; 

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.sparql.lib.org.json.JSONArray;
import com.hp.hpl.jena.sparql.lib.org.json.JSONObject;

public class RunQuery extends HttpServlet{
	
	private static final long serialVersionUID = 2991523997146964023L;
	
	
	public void doGet(HttpServletRequest req,
            HttpServletResponse resp)
		throws IOException, ServletException{
    	
    	try {
 
    		String keyword = req.getParameter("keyword");

//            String diseasomeQuery = Util.prefixes + " SELECT ?disease ?possibleDrug ?associatedGene  ?class ?degree ?size ?classDegree "+
//            	" WHERE {?x rdfs:label ?disease FILTER regex(?disease, '" + keyword  + "', 'i')."+
//            	" ?x diseasome:associatedGene ?associatedGene."+
//              	" ?x diseasome:possibleDrug ?possibleDrug."+
//              	" ?x diseasome:degree ?degree ."+
//              	" ?x diseasome:size ?size ."+
//              	" ?x diseasome:classDegree ?classDegree."+
//              	" ?x diseasome:class ?class."+
//            	" }";
//
//            String dailymedQuery = Util.prefixes + " SELECT ?name ?indication  ?precaution ?routeOfAdministration ?adverseReaction"+
//            	" WHERE {?x dailymed:indication ?indication. FILTER regex(?indication, '" + keyword +"', 'i')"+
//            	" ?s rdfs:label ?name+" +
//            	" ?x dailymed:routeOfAdministration ?routeOfAdministration." +
//              	" ?x dailymed:precaution ?precaution." +
//              	" ?x dailymed:adverseReaction ?adverseReaction." +
//            	" }";


          String diseasomeQuery = Util.prefixes + " SELECT distinct ?class ?disease WHERE {?x rdfs:label ?disease FILTER regex(?disease, '" + keyword  + "', 'i'). ?x diseasome:possibleDrug ?possibleDrug.  ?x diseasome:class ?class.}";
          String dailymedQuery = Util.prefixes + " SELECT distinct ?name ?indication ?routeOfAdministration ?precaution WHERE {?x dailymed:indication ?indication FILTER regex(?indication, '" + keyword +"', 'i') ?x rdfs:label ?name. ?x dailymed:routeOfAdministration ?routeOfAdministration. ?x dailymed:precaution ?precaution.}";

//            String diseasomeQuery = Util.prefixes + " SELECT ?disease WHERE {?x rdfs:label ?disease FILTER regex(?disease, '" + keyword  + "', 'i') }";
//            String dailymedQuery = Util.prefixes + " SELECT ?name ?indication WHERE {?x dailymed:indication ?indication FILTER regex(?indication, '" + keyword +"', 'i') ?x rdfs:label ?name}";

            ResultSet diseasomeResults = QueryExecutionFactory.sparqlService(Util.DISEASOME_ENDPOINT,diseasomeQuery).execSelect();
            ResultSet dailymedResults = QueryExecutionFactory.sparqlService(Util.DAILYMED_ENDPOINT,dailymedQuery).execSelect();

            // The following generates a page showing all the request parameters
            PrintWriter out = resp.getWriter();
            
            //Sending the response as a json type
            resp.setContentType("application/json");
            
            //Send the results as a JSONobject
            JSONObject jsonResp = new JSONObject();

            /**
             * The structure of jsonResp looks like the following: 
			 * {
			 *    "bindings":
			 *    [
			 *    	{"source" : "dailymed",
			 *    	 "uri" : "http://dailymed",
			 *    	 "vars" : ["name","indication"],
			 *    	 "count":2,
			 *    	 "results" : 
			 *    		[
			 *    			{"name": "Isosorbide (Tablet, Film Coated, Extended Release)", "indication":"Isosorbide Mononitrate ..."},
			 *    			{"name": "Altace (Capsule)", "indication":"Reduction in Risk of Myocardial Infarction..."},
			 *    		]
			 *    	}
			 *    	,
			 *    	{"source" : "diseasome",
			 *    	 "uri" : "http://diseasome",
			 *    	 "vars" : ["disease"],
			 *    	 "count":2,
			 *    	 "results" : 
			 *    		[
			 *    			{"disease": "Coronary artery disease"},
			 *    			{"disease": "Coronary artery disease, autosomal dominant, 1, 608320"}
			 *   		]
			 *    	}
			 *    ]
			 * }
			 *
             */
            
	          JSONArray array = new JSONArray();
	            
            //Start of diseasome
            
            JSONObject jsonDiseasome = new JSONObject();
            jsonDiseasome.put("source", "diseasome");
            jsonDiseasome.put("uri", Util.DISEASOME_ENDPOINT);
            JSONArray diseasomeVars = new JSONArray();
            List<String> diseasomeResultVars = diseasomeResults.getResultVars();
		     for (String val : diseasomeResultVars){
		    	 diseasomeVars.put(val);
		     }
		     jsonDiseasome.put("vars", diseasomeVars);
		     int diseasomeCount=0;
		     JSONArray diseasomeResultArr = new JSONArray();
		     while (diseasomeResults.hasNext()){
	 			QuerySolution diseasomeSolution = diseasomeResults.next();
	 			JSONObject j = new JSONObject();
	            for (String val : diseasomeResultVars){
	               	String s = diseasomeSolution.get(val).toString().replaceAll(":", "-");
			        j.put(val,s);
	 			}
	            diseasomeResultArr.put(j);
	 			diseasomeCount++;
	 		}
	        jsonDiseasome.put("results",diseasomeResultArr);
	        jsonDiseasome.put("count",diseasomeCount);
	        
	        //End of diseasome
            

            //Start of dailymed
            
            JSONObject jsonDailymed = new JSONObject();
            jsonDailymed.put("source", "dailymed");
            jsonDailymed.put("uri", Util.DAILYMED_ENDPOINT);
            JSONArray dailymedVars = new JSONArray();
            List<String> dailymedResultVars = dailymedResults.getResultVars();
		     for (String val : dailymedResultVars){
		    	 dailymedVars.put(val);
		     }
		     jsonDailymed.put("vars", dailymedVars);
		     int dailymedCount=0;
		     JSONArray dailymedResultArr = new JSONArray();
		     while (dailymedResults.hasNext()){
	 			QuerySolution dailymedSolution = dailymedResults.next();
	 			JSONObject j = new JSONObject();
	            for (String val : dailymedResultVars){
	            	String s = dailymedSolution.get(val).toString().replaceAll(":", "-");
	            	j.put(val,s);
	 			}
	            dailymedResultArr.put(j);
	 			dailymedCount++;
	 		}
	        jsonDailymed.put("results",dailymedResultArr);
	        jsonDailymed.put("count",dailymedCount);
	        
	        //End of diseasome

	        array.put(jsonDiseasome);
	        array.put(jsonDailymed);
	        
	        jsonResp.put("bindings", array);
            
            out.write(jsonResp.toString());
    	} 
    	catch (Exception e) {
    		System.err.println(e);
        }	
	}
}
