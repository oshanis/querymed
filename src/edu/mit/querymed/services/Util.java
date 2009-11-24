package edu.mit.querymed.services;

public class Util {

	//List of some prefixes that are useful in constructing SPARQL queries
	public static String prefixes = 	"PREFIX d2r: <http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#> " +
	"PREFIX vocabClass: <http://www4.wiwiss.fu-berlin.de/drugbank/vocab/resource/class/> " +
	"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
	"PREFIX diseasome: <http://www4.wiwiss.fu-berlin.de/diseasome/resource/diseasome/> " +
	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
	"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
	"PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
	"PREFIX vocabProperty: <http://www4.wiwiss.fu-berlin.de/drugbank/vocab/resource/property/> " +
	"PREFIX db: <http://www4.wiwiss.fu-berlin.de/diseasome/resource/> " +
	"PREFIX drugbank: <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/> " +
	"PREFIX dbpedia: <http://dbpedia.org/ontology/> " +
	"PREFIX map: <file:/C:/apps/diseasome/diseasome.n3#> "+
	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
	"PREFIX dailymed: <http://www4.wiwiss.fu-berlin.de/dailymed/resource/dailymed/>";
	
	public void addPrefix(String prefix, String uri){
		prefixes += "PREFIX " + prefix + ": <" + uri + "> ";
	}
    
}
