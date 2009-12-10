package edu.mit.querymed.endpoints;

import java.util.List;

import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.lib.org.json.JSONObject;

import edu.mit.querymed.services.Util;

//This class is a hack - don't rely on it!
public class Diseasome {

	public static String[] properties  = {
				"http://www.w3.org/2000/01/rdf-schema#label",
				"http://www.w3.org/2002/07/owl#sameAs",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/bio2rdfSymbol",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/hgncId",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/hgncIdPage",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/geneId",
				"http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/name",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/associatedGene",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/possibleDrug",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/degree",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/size",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/omimPage",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/classDegree",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/class",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/diseaseSubtypeOf",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/omim",
				"http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/chromosomalLocation"
	};
	
	public Diseasome(){
	}
	
	public static void main(String[] args){
		
//	     String query = Util.prefixes + " SELECT DISTINCT ?p WHERE {?s ?p ?o} ";
//	     ResultSet results = QueryExecutionFactory.sparqlService(Util.DISEASOME_ENDPOINT,query).execSelect();
//	     while (results.hasNext()){
//	 			QuerySolution solution = results.next();
//	 			String s = solution.get("p").toString();
//	 			System.out.println(s);
//	 		}

		for (String s : properties){
			 System.out.println(s);
		}
		
	}
}
