package edu.mit.querymed;

/**
 * 
 * This maintains the state of the querymed system
 */

public class QueryMedManager {

	private static SourceManager sm;
	private static CacheManager cm;
	
	public QueryMedManager(SourceManager sm, CacheManager cm){
		QueryMedManager.setSourceManager(sm);
		QueryMedManager.setCacheManager(cm);
	}
	
	public static SourceManager getSourceManager(){
		return sm;
	}
	
	public static CacheManager getCacheManager(){
		return cm;
	}

	public static void setSourceManager(SourceManager sm) {
		QueryMedManager.sm = sm;
	}

	public static void setCacheManager(CacheManager cm) {
		QueryMedManager.cm = cm;
	}

}
