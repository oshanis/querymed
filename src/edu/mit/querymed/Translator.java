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
		
		for (int i = 0; i < endpoints.length; i++){
			JSONObject joProperties = new JSONObject(jo.getString(endpoints[i]));
			String[] properties = JSONObject.getNames(joProperties);			
			HashMap<String, Integer>existingVars = new HashMap<String, Integer>();
			String[] propertyVars = new String[properties.length];
			StringBuilder sbQuery = new StringBuilder();
			
			//If the query is over all properties, the property name will be the keyword "ALL."  In this case, the code inside the try loop 
			//will be executed; otherwise a JSON exception will be thrown and the code in the "catch" statement will execute.
			try { 
				joProperties.getString("ALL");
				sbQuery.append("SELECT ?ALL \n WHERE { \n ?ALLS ?ALLP ?ALL \n FILTER regex(?ALL, ");
				JSONObject joValues = new JSONObject(joProperties.getString(properties[0]));
				String[] values = JSONObject.getNames(joValues);
				sbQuery.append(values[0] + ", \"i\")\n}");
			}		
			
			//If the property is not named "ALL", the code in this catch statement will execute.  This code constructs a query that searches
			//a set of user-specified properties for a list of user-specified keywords, on the given endpoint.
			
			catch (JSONException je) {		
			sbQuery.append("SELECT ");
			
			//Append variable names--one variable per property
			for (int j = 0; j < properties.length; j++) {
				String[] tmp = properties[j].split("/");
				String[] tmp2 = tmp[tmp.length - 1].split("#");
				propertyVars[j] = tmp2[tmp2.length - 1];
				if (! (existingVars.containsKey(propertyVars[j]))) {
					sbQuery.append("?" + propertyVars[j] + " ");
					existingVars.put(propertyVars[j], j);
				}
			}
			
			//WHERE clause
			sbQuery.append("\nWHERE { \n");
			for (int j = 0; j < properties.length; j++){
				if (existingVars.get(propertyVars[j]) == j ){
					sbQuery.append("?" + propertyVars[j] + "s " + properties[j] + " ?" + propertyVars[j]);
					if (j < properties.length - 1) {
						sbQuery.append(" . \n");
					}
					else {
						sbQuery.append("\n");
					}
				}
			}

			JSONObject joValues = new JSONObject(joProperties.getString(properties[0]));
			String[] values = JSONObject.getNames(joValues);
			
			//Filter based on user-supplied values.  Append parentheses to the beginning of the WHERE statement to
			//ensure a sensible order of operations over the logical operators--otherwise, AND takes precedence over OR.
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
				sbQuery.append("regex(?" + propertyVars[j] + ", " + "\"" + values[0] + "\", " + "\"i\"" + "))");
			}
			sbQuery.append(")\n}");

		}
			Endpoint e = new Endpoint("", endpoints[i]);
			Query q = new Query(e, sbQuery.toString());
			queries.add(q);
	//		System.out.println(sbQuery.toString());
		}
	}
	
	/*return a list of Queries corresponding to the queries we must run.*/
	public ArrayList<Query> getQueries() {		
		return this.queries;
	}
	
	public static void main(String[] args){
		String s1 = "{\"Endpoint1\": {\"property11\": {\"cardiac\": \"AND\"}, \"property12\": {\"arrest\": \"OR\"}}, \"Endpoint2\": {\"property21\": {\"value21\" : \"AND\"}, \"property22\": {\"value22\": \"OR\"}}} ";
		String s2 = "{\"Endpoint1\": {\"ALL\": {\"cardiac\": \"null\"}}}";
		try {
			Translator t = new Translator(s1);
			ArrayList<Query> qs = t.getQueries();
			for (Query q : qs){
				System.out.println(q.getQuery());
			}
			
		}
		catch (JSONException e) {System.err.println(e);}
	}
}
