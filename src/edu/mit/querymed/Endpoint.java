package edu.mit.querymed;
/*Store information about individual endpoints*/
public class Endpoint {
		private String name;
		private String URI;
		public Endpoint(String name, String URI) {
				this.name = name;
				this.URI = URI;
		}
		
		public String getName(){
			return this.name;
		}
		
		public String getURI(){
			return this.URI;
		}
}
