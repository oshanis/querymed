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

public class GetSelected extends HttpServlet{
	
	private static final long serialVersionUID = 2991523997146964023L;
	
	
	public void doGet(HttpServletRequest req,
            HttpServletResponse resp)
		throws IOException, ServletException{
    	
    	try {
 
            String query = "SELECT distinct ?disease " +
    		" WHERE { {?x <http://www.w3.org/2000/01/rdf-schema#label> ?disease FILTER regex(?disease, \"coronary artery disease\", \"i\"). " +
    		" ?x <http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/class> <http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseaseClass/Cardiovascular>} " +
    		" UNION " +
    		" {?x <http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/associatedGene> <http://www4.wiwiss.fu-berlin.de/diseasome/resource/genes/ABCA1>.} }";

//            ResultSet diseasomeResults = QueryExecutionFactory.sparqlService(Util.DISEASOME_ENDPOINT,diseasomeQuery).execSelect();
            
            // The following generates a page showing all the request parameters
            PrintWriter out = resp.getWriter();
            
            //Sending the response as a json type
            resp.setContentType("application/json");
            
    		QueryExecution e  = QueryExecutionFactory.sparqlService(Util.DISEASOME_ENDPOINT, query);
    		
    		ResultSet diseasomeResults = e.execSelect();

    		String r = "";
    		
//            //Send the results as a JSONobject
//            JSONObject jsonResp = new JSONObject();
//            
//	        JSONArray array = new JSONArray();
//	            
//            //Start of diseasome
//            
//            JSONObject jsonDiseasome = new JSONObject();
//            jsonDiseasome.put("source", "diseasome");
//            jsonDiseasome.put("uri", Util.DISEASOME_ENDPOINT);
//            JSONArray diseasomeVars = new JSONArray();
//            List<String> diseasomeResultVars = diseasomeResults.getResultVars();
//		     for (String val : diseasomeResultVars){
//		    	 diseasomeVars.put(val);
//		     }
//		     jsonDiseasome.put("vars", diseasomeVars);
//		     int diseasomeCount=0;
//		     JSONArray diseasomeResultArr = new JSONArray();
		     while (diseasomeResults.hasNext()){
	 			QuerySolution diseasomeSolution = diseasomeResults.next();
	 			r += diseasomeSolution.get("disease");
//	 			JSONObject j = new JSONObject();
//	            for (String val : diseasomeResultVars){
//	               	String s = diseasomeSolution.get(val).toString().replaceAll(":", "-");
//			        j.put(val,s);
//	 			}
//	            diseasomeResultArr.put(j);
//	 			diseasomeCount++;
	 		}
//	        jsonDiseasome.put("results",diseasomeResultArr);
//	        jsonDiseasome.put("count",diseasomeCount);
	        
	        //End of diseasome
            

//	        array.put(jsonDiseasome);
//	        
//	        jsonResp.put("bindings", array);
            
//            out.write("Test");
            out.write(r);
    	} 
    	catch (Exception e) {
    		System.err.println(e);
        }	
	}
}
