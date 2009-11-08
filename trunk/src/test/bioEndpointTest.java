package test; 

import java.util.List;
import com.hp.hpl.jena.query.*;

public class bioEndpointTest {
	  public static void main(String arguments[]){
	    	try {
	    		
	    		String service =  "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
	    		String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?disease WHERE {?x rdfs:label ?disease FILTER regex(?disease, '^coronary artery disease$', 'i') }";
	    		QueryExecution e  = QueryExecutionFactory.sparqlService(service, query);
	    		
	    		ResultSet results = e.execSelect();

	    		while (results.hasNext()){
	    			QuerySolution s = results.next();
	    			System.out.println(s);
	    		}
	    		
	    		service = "http://www4.wiwiss.fu-berlin.de/dailymed/sparql";
	    		query = "PREFIX dailymed: <http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?name ?indication WHERE {?x dailymed:indication ?indication FILTER regex(?indication, 'coronary artery disease', 'i') ?x rdfs:label ?name}";
	    		e  = QueryExecutionFactory.sparqlService(service, query);
	    		
	    		 results = e.execSelect();

	    		 while (results.hasNext()){
		    			QuerySolution s = results.next();
		    			System.out.println(s);
	    		 }

//	    		List<String> l = results.getResultVars();
//	    	
//	    		for (int i=0; i<l.size(); i++){
//	    			System.out.println(i+" : "+l.get(i));
//	    		}

	    		
	 //   			System.out.println(s.get("name"));
	    		
	    		
	    	} 
	    	catch (Exception e) {System.err.println(e);
	        }
	    }
}
