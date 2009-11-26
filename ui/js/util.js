
/*
 * This is to return the last part of the uri string, 
 * so that the user interface will look much nicer!
 */
function getNameFromURI(uri){
	//First check if the uri is actually a uri, i.e. whether it starts with "http://"
	if (uri.search(/http/i) > -1){
		var tokens = uri.split("/");
		var lastToken = tokens[tokens.length - 1];
		var hashTokens = lastToken.split("#");
		if (hashTokens.length > 0){
			lastToken = hashTokens[hashTokens.length - 1];
		}
		return lastToken;
	}
	else {
		return uri;
	}
}


