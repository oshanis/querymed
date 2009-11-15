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
	private String endpointListURI; /*stores enpoint list*/
	private static final String DEFAULT_ENDPOINTS = "sources"; /*default endpoint list*/
	
	/*Construct a SourceManager using the default endpoint list*/
	public SourceManager() throws IOException{
		String line;
		File f = new File(".");
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();

        //Get the URLs
        URL[] urls = ((URLClassLoader)sysClassLoader).getURLs();

        for(int i=0; i< urls.length; i++)
        {
            System.out.println(urls[i].getFile());
        }       


		
        InputStream is = SourceManager.class.getResourceAsStream(DEFAULT_ENDPOINTS);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
		while((line = br.readLine()) != null){
			System.out.println(line);
			
		}
		
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
	
	/*Maim method for testing */
	public static void main(String[] args){
		try{SourceManager s = new SourceManager();} catch (IOException e) {System.err.println(e);}
		}		
	}	
