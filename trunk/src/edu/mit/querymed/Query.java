package edu.mit.querymed;

/*This class models queries that are to be run*/

public class Query {
	private Endpoint source;
	private String sparqlQuery;
	
	public Query(Endpoint endpoint, String sparqlQuery){
		this.source = endpoint;
		this.sparqlQuery = sparqlQuery;
	}
	
	public Endpoint  getEndpoint() {
		return this.source;
	}
	
	public String getQuery(){
		return this.sparqlQuery;
	}
}
