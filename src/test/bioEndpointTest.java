package test;

import java.util.List;
import com.hp.hpl.jena.query.*;

public class bioEndpointTest {
	  public static void main(String arguments[]){
	    	try {
	    		System.out.println("hello");
	    		String service = "http://www4.wiwiss.fu-berlin.de/dailymed/sparql";
	    		String query = "SELECT ?name ?indication WHERE {?x dailymed:indication ?indication FILTER regex(?indication, 'coronary artery disease', 'i') ?x rdfs:label ?name}";
	    		QueryExecution e  = QueryExecutionFactory.sparqlService(service, query);
	    		System.out.println("hello2");
//	    		System.out.println("hello");
//	    		String service =  "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
//	    		String query = "SELECT ?disease WHERE {?x rdfs:label ?disease FILTER regex(?disease, '^coronary artery disease$', 'i') }";
//	    		QueryExecution e  = QueryExecutionFactory.sparqlService(service, query);
//	    		System.out.println("hello2");

	    		ResultSet results = e.execSelect();
	    		

//	    		List<String> l = results.getResultVars();
//	    	
//	    		for (int i=0; i<l.size(); i++){
//	    			System.out.println(i+" : "+l.get(i));
//	    		}

	    		while (results.hasNext()){
	    			QuerySolution s = results.next();
	    			System.out.println(s);

	 //   			System.out.println(s.get("name"));
	    		}
	    		
	    	} 
	    	catch (Exception e) {
	        }
	    }
}
