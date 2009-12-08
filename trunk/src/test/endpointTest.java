//package test;
//
//import com.hp.hpl.jena.query.*;
//import com.hp.hpl.jena.sparql.lib.org.json.JSONObject;
//
//import edu.mit.querymed.services.Util;
//
//
//public class endpointTest {
//	
//	//Default endpoints we have
//	private static final String DISEASOME_ENDPOINT = "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
//	private static final String DAILYMED_ENDPOINT = "http://www4.wiwiss.fu-berlin.de/dailymed/sparql";
//	
//    public static void main(String arguments[]){
//    	
//       	try {
//       	 
//    		String keyword = "cornonary artery disease";
//    		
//            if (keyword == null) {
//                // The request parameter 'param' was not present in the query string
//                // e.g. http://hostname.com?a=b
//            } else if ("".equals(keyword)) {
//                // The request parameter 'param' was present in the query string but has no value
//                // e.g. http://hostname.com?param=&a=b
//            }
//
//            String diseasomeQuery = Util.prefixes + " SELECT ?disease WHERE {?x rdfs:label ?disease FILTER regex(?disease, '" + keyword  + "', 'i') }";
//            String dailymedQuery = Util.prefixes + " SELECT ?name ?indication WHERE {?x dailymed:indication ?indication FILTER regex(?indication, '" + keyword +"', 'i') ?x rdfs:label ?name}";
////            String diseasomeQuery = Util.prefixes + " SELECT * WHERE {?x rdfs:label ?disease FILTER regex(?disease, '" + keyword  + "', 'i') }";
////            String dailymedQuery = Util.prefixes + " SELECT * WHERE {?x dailymed:indication ?indication FILTER regex(?indication, '" + keyword +"', 'i') ?x rdfs:label ?name}";
//
//            ResultSet diseasomeResults = QueryExecutionFactory.sparqlService(DISEASOME_ENDPOINT,diseasomeQuery).execSelect();
//            ResultSet dailymedResults = QueryExecutionFactory.sparqlService(DAILYMED_ENDPOINT,dailymedQuery).execSelect();
//
//            //Send the results as a JSONobject
//            JSONObject jsonResp = new JSONObject();
//            
//            //JSON objects the endpoints send their values in
//            JSONObject jsonDiseasome = new JSONObject();
//            JSONObject jsonDailymed = new JSONObject();
//            
//            System.out.println(diseasomeResults.hasNext());
//        	
////            while (diseasomeResults.hasNext()){
////            	QuerySolution diseasomeSolution = diseasomeResults.next();
////            	System.out.println("Test");
////            	jsonDiseasome.put("disease",diseasomeSolution.get("disease"));
////    		}
////
////            while (dailymedResults.hasNext()){
////    			QuerySolution dailymedSolution = dailymedResults.next();
////    			jsonDailymed.put("name",dailymedSolution.get("name"));
////    			jsonDailymed.put("indication",dailymedSolution.get("indication"));
////    		}
//
//         
//            //Finaly add the 2 JSONObjects to the JSONObjects that we are returning to the client
////            jsonResp.put(DISEASOME_ENDPOINT, jsonDiseasome.toString());
////            jsonResp.put(DAILYMED_ENDPOINT, jsonDailymed.toString());
////            System.out.println(jsonResp.toString());
//       	}
//    	catch (Exception e) {System.err.println(e);
//        }	
//
//    }
//}

package test;

import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.lib.org.json.JSONArray;
import com.hp.hpl.jena.sparql.lib.org.json.JSONObject;

import edu.mit.querymed.services.Util;

public class endpointTest {
	
	static String DISEASOME_ENDPOINT = "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
	static String DAILYMED_ENDPOINT = "http://www4.wiwiss.fu-berlin.de/dailymed/sparql";
	
	
	 public static void main(String[] args) {
			
         try{
			 String keyword = "coronary artery disease";
		
		     String diseasomeQuery = Util.prefixes + " SELECT ?disease WHERE {?x rdfs:label ?disease FILTER regex(?disease, '" + keyword  + "', 'i') } limit 2";
		     String dailymedQuery = Util.prefixes + " SELECT ?name ?indication WHERE {?x dailymed:indication ?indication FILTER regex(?indication, '" + keyword +"', 'i') ?x rdfs:label ?name} limit 2";
		
		     ResultSet diseasomeResults = QueryExecutionFactory.sparqlService(DISEASOME_ENDPOINT,diseasomeQuery).execSelect();
		     ResultSet dailymedResults = QueryExecutionFactory.sparqlService(DAILYMED_ENDPOINT,dailymedQuery).execSelect();

		     //Send the results as a JSONobject
		     JSONObject jsonResp = new JSONObject();
		     
	          JSONArray array = new JSONArray();
	            
	            //Start of diseasome
	            
	            JSONObject jsonDiseasome = new JSONObject();
	            jsonDiseasome.put("source", "diseasome");
	            jsonDiseasome.put("uri", DISEASOME_ENDPOINT);
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
	            jsonDailymed.put("uri", DAILYMED_ENDPOINT);
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

		        System.out.println(jsonResp.toString());
         }
         catch(Exception e){}
	 }
}



