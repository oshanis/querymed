package test;

public class endpoint {

	String uri;
	boolean supportsREST = true;
	
	public endpoint(String uri){
		this.uri = uri;
	}

	public endpoint(String uri, boolean supportsREST){
		this.uri = uri;
		this.supportsREST = supportsREST;
	}

}
