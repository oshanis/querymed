package test;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import edu.mit.querymed.services.Util;

public class endpoint {

	String uri;
	boolean supportsREST = true;
	
	public endpoint(String uri){
		this.uri = uri;
	}

	public endpoint(String uri, boolean supportsREST){
		this.uri = uri;
		this.supportsREST = supportsREST;
	}

	public static void main(String[] args){
		
		String service = "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
		String property = "http://www.w3.org/2000/01/rdf-schema#label";
        
//		if (service == null) {
//            // The request parameter 'param' was not present in the query string
//            // e.g. http://hostname.com?a=b
//        } else if ("".equals(service)) {
//            // The request parameter 'param' was present in the query string but has no value
//            // e.g. http://hostname.com?param=&a=b
//        }

        String query = "SELECT distinct ?disease " +
        		" WHERE { {?x <http://www.w3.org/2000/01/rdf-schema#label> ?disease FILTER regex(?disease, \"coronary artery disease\", \"i\"). " +
        		" ?x <http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/class> <http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseaseClass/Cardiovascular>} " +
        		" UNION " +
        		" {?x <http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/associatedGene> <http://www4.wiwiss.fu-berlin.de/diseasome/resource/genes/ABCA1>.} }";
        
		QueryExecution e  = QueryExecutionFactory.sparqlService(Util.DISEASOME_ENDPOINT, query);
		
		ResultSet results = e.execSelect();
		
        //So, just sending as comma seperated values:
        String responseStr = "";
        
        while (results.hasNext()){
			QuerySolution s = results.next();
			responseStr += s.get("disease");
			responseStr += ",";
//			if (s.get("o").isLiteral()){
//				String dataType = s.getLiteral("o").getDatatype().getURI();
//				if (dataType.equals("http://www.w3.org/2001/XMLSchema#int")){
//					responseStr += s.getLiteral("o").getInt() + ",";	
//				}
//				
//			}
//			if (s.get("o").isAnon()) {
//				//I dunno what to do here
//				System.out.println("@@TODO: Handle anon case!");
//			}
//			if (s.get("o").isResource()){
//				responseStr += s.getResource("o").getURI() + ",";
//			}
			
		}
        System.out.println(responseStr);


	}
}
