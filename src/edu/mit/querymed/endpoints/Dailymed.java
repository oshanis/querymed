package edu.mit.querymed.endpoints;

import java.util.List;

import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.lib.org.json.JSONObject;

import edu.mit.querymed.services.Util;

//This class is a hack - don't rely on it!
public class Dailymed {

	public static String[] properties  = {
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/routeOfAdministration",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/dosage",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/sideEffect",
			"http://www.w3.org/2002/07/owl#sameAs",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/fullName",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/activeMoiety",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/name",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/precaution",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/genericMedicine",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/contraindication",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/adverseReaction",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/representedOrganization",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/description",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/clinicalPharmacology",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/supply",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/activeIngredient",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/overdosage",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/boxedWarning",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/possibleDiseaseTarget",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/inactiveIngredient",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/supplementalPatientMaterial",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/warning",
			"http://www.w3.org/2000/01/rdf-schema#label",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/genericDrug",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/indication",
			"http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
			"http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/producesDrug"};
	
	public Dailymed(){
	}
	
	public static void main(String[] args){
		
	     String query = Util.prefixes + " SELECT DISTINCT ?p WHERE {?s ?p ?o} ";
	     ResultSet results = QueryExecutionFactory.sparqlService(Util.DAILYMED_ENDPOINT,query).execSelect();
	     while (results.hasNext()){
	 			QuerySolution solution = results.next();
	 			String s = solution.get("p").toString();
	 			System.out.println(s);
	 		}

	}
}
