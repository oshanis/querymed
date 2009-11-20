package test;

import java.util.List;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.sparql.lib.org.json.JSONArray;
import com.hp.hpl.jena.sparql.lib.org.json.JSONException;
import com.hp.hpl.jena.sparql.lib.org.json.JSONObject;


public class endpointTest {
	
	
    public static void main(String arguments[]){
    	
    	String service = "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
    	
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

		String query = prefixes + " SELECT DISTINCT ?p WHERE {?s ?p ?o}";
		
		QueryExecution e  = QueryExecutionFactory.sparqlService(service, query);
		
		ResultSet results = e.execSelect();
		
		JSONArray arr = new JSONArray();
		
		while (results.hasNext()){
			QuerySolution s = results.next();
			arr.put(s.get("p"));
		}

		System.out.println(arr.toString());
		
    }
}
