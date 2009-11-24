
/*
 * This is to return the last part of the uri string, 
 * so that the user interface will look much nicer!
 */
function getNameFromURI(uri){
	//First check if the uri is actually a uri, i.e. whether it starts with "http://"
	if (uri.search(/http/i) > -1){
		var tokens = uri.split("/");
		if (tokens.length == 0){
			tokens = uri.split("#");
		}
		return tokens[tokens.length - 1];
	}
	else {
		return uri;
	}
}

$(document).ready(function() {
	
	$('#example').dataTable();
	//Display the containers for each of the data sources once the checkbox is clicked
	
	showProperties();
//	$(":checkbox").click(showProperties);
	$("#datasources").click(showProperties);

	
} );

