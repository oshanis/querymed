
/*
 * This is to return the last part of the uri string, 
 * so that the user interface will look much nicer!
 */
function getNameFromURI(uri){
	var tokens = uri.split("/");
	if (tokens.length == 0){
		tokens = uri.split("#");
	}
	return tokens[tokens.length - 1];
}