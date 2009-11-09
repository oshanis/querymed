package edu.mit.querymed;

import java.util.List;

import com.hp.hpl.jena.query.*;


public class endpointTest {
	
	
    public static void main(String arguments[]){
    	
    	try {

    		String service = "http://demo.openlinksw.com/sparql";
    		String query = "PREFIX foaf:  <http://xmlns.com/foaf/0.1/> SELECT ?person ?name FROM <http://www.w3.org/People/Berners-Lee/card> WHERE { ?person foaf:name ?name .}";
    		QueryExecution e  = QueryExecutionFactory.sparqlService(service, query);
    		
    		ResultSet results = e.execSelect();
    		
    		

//    		List<String> l = results.getResultVars();
//    	
//    		for (int i=0; i<l.size(); i++){
//    			System.out.println(i+" : "+l.get(i));
//    		}

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
