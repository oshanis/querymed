package edu.mit.querymed;

import java.util.*;
import com.hp.hpl.jena.sparql.lib.org.json.*;

/*The purpose of the  Translator is to transform user input into a list of Sparql queries and sources. */
/*Returns an arrayList of queries, specifying which query to run at which endpoint*/

public class Translator {
	private JSONObject jo;
	private ArrayList<Query> queries;
	
	public Translator(String input) throws JSONException {
		queries = new ArrayList<Query>();
		this.jo= new JSONObject(input);
		String[] endpoints = JSONObject.getNames(jo);
		//ArrayList<String> properties = new ArrayList<String>();
		//ArrayList<String> values = new ArrayList<String>();
		for (int i = 0; i < endpoints.length; i++){
			//System.out.println(endpoints[i]);
			JSONObject joProperties = new JSONObject(jo.getString(endpoints[i]));
			String[] properties = JSONObject.getNames(joProperties);
			JSONObject [] ajoValues = new JSONObject[properties.length];
			HashMap<String, Integer>existingVars = new HashMap<String, Integer>();
			String[] propertyVars = new String[properties.length];
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("SELECT ");
			for (int j = 0; j < properties.length; j++) {
				//System.out.println(properties[j]);		
				//JSONObject joValues = new JSONObject(joProperties.getString(properties[j]));
				//String[] values = JSONObject.getNames(joValues);
				String[] tmp = properties[j].split("/");
				String[] tmp2 = tmp[tmp.length - 1].split("#");
				propertyVars[j] = tmp2[tmp2.length - 1];
				if (! (existingVars.containsKey(propertyVars[j]))) {
					sbQuery.append("?" + propertyVars[j] + " ");
					existingVars.put(propertyVars[j], j);
				}
			}
			sbQuery.append("\nWHERE { \n");
			for (int j = 0; j < properties.length; j++){
				if (existingVars.get(propertyVars[j]) == j ){
				sbQuery.append("?" + propertyVars[j] + "s " + properties[j] + " ?" + propertyVars[j] + "\n");
				}
			}

			JSONObject joValues = new JSONObject(joProperties.getString(properties[0]));
			String[] values = JSONObject.getNames(joValues);
			sbQuery.append("FILTER(");
			for (int j = 0; j < properties.length - 1; j++) {	
				sbQuery.append("(");
			}
			sbQuery.append("regex(?" + propertyVars[0] + ", " + "\"" + values[0] + "\", " + "\"i\""  + ")");

			for (int j = 1; j < properties.length; j++){
				joValues = new JSONObject(joProperties.getString(properties[j]));
				values = JSONObject.getNames(joValues);
				String logicalOp  = joValues.getString(values[0]);
				if (logicalOp == "OR") {
					sbQuery.append(" || " );
				}
				else {	
					sbQuery.append( " && ");
				}
				sbQuery.append("regex(" + propertyVars[j] + ", " + "\"" + values[0] + "\", " + "\"i\"" + "))");
			}
			sbQuery.append(")\n}");
			Endpoint e = new Endpoint("", endpoints[i]);
			Query q = new Query(e, sbQuery.toString());
			queries.add(q);
			System.out.println(sbQuery.toString());
		}

	}
	/*return a list of Queries corresponding to the queries we must run.*/
	public ArrayList<Query> getQueries() {		
		return this.queries;
	}
	public static void main(String[] args){
		String s = "{\"Endpoint1\": {\"property11\": {\"value11\": \"AND\"}, \"property12\": {\"value12\": \"OR\"}}, \"Endpoint2\": {\"property21\": {\"value21\" : \"AND\"}, \"property22\": {\"value22\": \"OR\"}}} ";
		try {
			Translator t = new Translator(s);
		}
		catch (JSONException e) {System.err.println(e);}
	}
}
