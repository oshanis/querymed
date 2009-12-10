
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


function testJSON(){
	//	{
	//	"Endpoint1": {	"property1": {"value1": "AND"},
	//		        "property2": {"value 2": "OR"}
	//				 },
	//	"Endpoint2":{ "property1": {"value1": "AND"},
	//					"property2": {"value 2": "OR"}
	//				 }	
	
	
	var o = new Object();

	var endpoints = 2;
	for (var i=0; i<endpoints; i++){
		var ep = new Object();
		var endpoint = "Endpoint"+i;
		var properties = 3;
		for (var j=0; j<properties; j++){
			var property = "p"+j;
			var val = new Object();
			//there's only 1 value per property
			val["value1"] = "AND";
			ep[property] = val;
		}
		o[endpoint] = ep;
	}
	
	var t = JSON.stringify(o);
	alert(t);

//	var e1 = new Object();
//	var e2 = new Object();
//	var e1p1 = new Object();
//	var e1p2 = new Object();
//	var e2p1 = new Object();
//	var e2p2 = new Object();
//	
//	e1p1.value1="AND";
//	e1p2.value2="OR";
//	e1.property1 = e1p1;
//	e1.property2 = e1p2;
//	o.Endpoint1 = e1;
//	var newObj = o.Endpoint1;
//	var newObj2 = newObj.property1;
//	var newObj3 = newObj2.value1;
//	
//	var t = JSON.stringify(o);
//	alert(t);

}


function display(){
	
	$('#ajax-load').html('');
	var headers = ["diseases", "possibleDrug"];
	var data = [["Coronary artery disease","http://www4.wiwiss.fu-berlin.de/dailymed/resource/drugs/3560"],
	            ["Coronary artery disease","http://www4.wiwiss.fu-berlin.de/dailymed/resource/drugs/3714"],
	            ["Coronary artery disease","http://www4.wiwiss.fu-berlin.de/dailymed/resource/drugs/4266"],
	            ["Coronary artery disease in familial hypercholesterolemia, protection against, 143890","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00171"],
	            ["Coronary artery disease in familial hypercholesterolemia, protection against, 143890","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01016"],
	            ["Coronary artery disease, susceptibility to","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01076"],
	            ["Coronary artery disease, susceptibility to","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01327"],
	            ["Coronary artery disease, susceptibility to","http://www4.wiwiss.fu-berlin.de/dailymed/resource/drugs/792"],
	            ["Coronary artery disease, susceptibility to","http://www4.wiwiss.fu-berlin.de/dailymed/resource/drugs/1208"], 
	            ["Coronary artery disease, susceptibility to","http://www4.wiwiss.fu-berlin.de/dailymed/resource/drugs/2297"],
	            ["Coronary artery disease, susceptibility to","http://www4.wiwiss.fu-berlin.de/dailymed/resource/drugs/3560"],
	            ["Cerebral amyloid angiopathy","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00171"],
	            ["Cerebral amyloid angiopathy","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB0101"],
	            ["HDL cholesterol level QTL","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00171"],
	            ["HDL cholesterol level QTL","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00255"],
	            ["HDL cholesterol level QTL","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00269"],
	            ["HDL cholesterol level QTL","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00286"],
	            ["HDL cholesterol level QTL","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00294"],
	            ["Tangier disease","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00171"], 
	            ["Tangier disease","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01016"],
	            ["Cerebral amyloid angiopathy, 105150","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00171"],
	            ["Cerebral amyloid angiopathy, 105150","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01016"],
	            ["HDL deficiency, familial, 604091","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00171"],
	            ["HDL deficiency, familial, 604091","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01016"],
	            ["Tangier disease, 205400","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB00171"],
	            ["Tangier disease, 205400","http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB01016"]];

	var html = createDataTable(headers,data);
	$('#dt_container').html(html);
	$("#dt_container").dialog("open");
	//@@TODO: I dunno why the last column is always smaller than the others - try to find a fix
	$('.dataTable').dataTable();

}