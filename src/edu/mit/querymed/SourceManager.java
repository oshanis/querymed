package edu.mit.querymed;

import java.util.*;
/*
 * This class manages all the source endpoints
 */
public class SourceManager {
	private HashMap<String, Endpoint> allEndpoints;
	private HashMap<String, Endpoint> selectedEndpoints;
	private String endpointListURI; /*stores enpoint list*/
	private static final String DEFAULT_ENDPOINTS = "../../../../resources/sources"; /*default endpoint list*/
	
	/*Construct a SourceManager using the default endpoint list*/
	public SourceManager(){
		
		
	}
	/*Construct a SourceManager using a user specified endpoint list*/
	public SourceManager(String URI){
		
	}
	
	/*Add a new endpoint*/
	public void addEndpoint(Endpoint endpoint){
		
	}
	
	/*modify the selectedEndpoints array list to reflect new user selections*/
	public void getSelectedEndpoints(ArrayList<Endpoint> selected) {
		
	}
	
	/*Set the endpoint list according to the specified URI*/
	public void setEndpointList(String URI){
		
	}
	
	/*get the endpoint list URI*/
	public String getEndpointListURI(){
		return this.endpointListURI;
	}
	
	public Endpoint getSelectedEndpoint(String name){
		return null;
	}

	public Endpoint getEndpoint(String name){
		return null;
	}
}