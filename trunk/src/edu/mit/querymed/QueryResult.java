package edu.mit.querymed;

import java.util.HashMap;
import java.util.Vector;

import com.hp.hpl.jena.query.ResultSet;

/*
 * This class models the result of a query. 
 * Has provenance information such as what endpoint was called, and what parameters are in the result set.
 */
//@@@TODO: Use Query

public class QueryResult {

	private String endpoint;
	private Vector<String> params;
	private String query;
	private boolean hasMore = false;
	private Vector<ResultSet> result;
	
	public QueryResult(String endpoint, Vector<String> params, String query, Vector<ResultSet> result){
		this.endpoint = endpoint;
		this.params = params;
		this.query = query;
		this.result = result;
	}
	
	public void setHasMore(boolean yes){
		this.hasMore = yes;
	}
	
	
}
