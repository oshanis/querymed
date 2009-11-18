package edu.mit.querymed;

import java.util.*;
import java.io.*;
import java.net.*;

/*
 * This class manages all the source endpoints
 */
public class SourceManager {
	private HashMap<String, Endpoint> allEndpoints;
	private HashMap<String, Endpoint> selectedEndpoints;  /*stores endpoints for current user query.  Maps URI to endpoint */
	private String endpointListURI; /*stores endpoint list*/
	private static final String DEFAULT_ENDPOINTS = "sources"; /*default endpoint list*/
	
	/*Construct a SourceManager using the default endpoint list*/
	public SourceManager() throws IOException{
		this.allEndpoints = new HashMap<String, Endpoint>();
		this.selectedEndpoints = new HashMap<String, Endpoint>();
		this.endpointListURI = DEFAULT_ENDPOINTS;
		String line;
		File f = new File(".");
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();

        InputStream is = SourceManager.class.getResourceAsStream(DEFAULT_ENDPOINTS);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
		while((line = br.readLine()) != null){
			String[] parts = line.split("\t");
			Endpoint e = new Endpoint(parts[0], parts[1]);
			allEndpoints.put(parts[1], e);
			selectedEndpoints.put(parts[1], e);
			System.out.println(line);
		}
		
	}
	
	/*Construct a SourceManager using a user specified endpoint list*/
	public SourceManager(String URI) throws FileNotFoundException, IOException {
		this.allEndpoints = new HashMap<String, Endpoint>();
		this.selectedEndpoints = new HashMap<String, Endpoint>();
		this.endpointListURI = URI;
		String line;
		File f = new File(URI);
		
        BufferedReader br= new BufferedReader(new FileReader(f));
		while((line = br.readLine()) != null){
			String[] parts = line.split("\t");
			Endpoint e = new Endpoint(parts[0], parts[1]);
			allEndpoints.put(parts[1], e);
			selectedEndpoints.put(parts[1], e);
			System.out.println(line);
		}
       
       
	}
	
	/*Add a new endpoint*/
	public void addEndpoint(Endpoint endpoint){
		allEndpoints.put(endpoint.getURI(), endpoint);
	
	}
	
	/*modify the selectedEndpoints array list to reflect new user selections*/
	public void getSelectedEndpoints(ArrayList<Endpoint> selected) {
		selectedEndpoints = new HashMap<String, Endpoint>();
		for (Endpoint e : selected) {
			selectedEndpoints.put(e.getURI(), e);
		}
	}
	
	/*Set the endpoint list according to the specified URI*/
	public void setEndpointList(String URI){
		endpointListURI = URI;
	}
	
	/*get the endpoint list URI*/
	public String getEndpointListURI(){
		return this.endpointListURI;
	}
	
	public HashMap<String, Endpoint> getAllEndpointsMap() {
		return allEndpoints;
	}
	
	public HashMap<String,Endpoint> getSelectedEndpointsMap(){
		return selectedEndpoints;
	}
	/*Maim method for testing */
	public static void main(String[] args){
		try{SourceManager s = new SourceManager("../querymed/resources/sources");} catch (Exception e) {System.err.println(e);}
		}		
	}	
