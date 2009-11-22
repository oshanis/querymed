/**
* QueryMed UI
*/

function optionclear(){
	$("#datasources > input").removeAttr("checked");
	$("#datasources :input:not(:checked)").each(function() {
		$('#selectors').find('#'+$(this).val()).remove();
	});
}



//Convenience function to get the SPARQL endpoint given the name
//Can't really read files in the local file system. So have to depend 
//on a method like this.
function getDataSourceURI(name){
	
	switch(name){
	case "diseasome":
		return "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
		break;
	case "dailymed":
		return "http://www4.wiwiss.fu-berlin.de/diseasome/sparql";
		break;
	case "drugbank":
		return "http://www4.wiwiss.fu-berlin.de/drugbank/sparql";
		break;
	case "linkedct":
		return "http://linkedct.org/snorql/index.html";
		break;
	}
}

function addSource(){
	$("#add-source").dialog({
		width: 500,
	      position: ['center', 'top'],
	      buttons: { "Ok": function() {
			var s = $('#name').val();
			$('#datasources').append('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <input type="checkbox" value="'+s+'" id="'+s+'" name="'+s+'">'+s+'</input><br/>');
			$(this).dialog("close"); 
			$(this).dialog("destroy");
	      }
	    } 
	  });
}

//This function is called when each of the data sources are clicked
//the properties of these data sources are fetched and displayed so
// that the user can input values for those, and restrict the query
function showProperties(){

	$('#datasources :checkbox:checked').each(function() {
		if ($('#selectors').find('#'+$(this).val()).length == 0){
	
		    /** SourceDiv hould look like this:
		    <div id="diseasome">
			<h3><a href="#">Diseasome</a></h3>
			<p>Search by property: <input type="textbox" id="property" value=""></p>
			</div> <!--com-->
			*/

			var option = $(this).val();
			var sourceDiv = '<div id="'+option+'">';
			sourceDiv += '<h3><a href="#">'+option+'</a></h3>';
			var propertyname = option+"_prop";
			var propertyid='#'+propertyname;
			sourceDiv += '<div id="'+propertyname+'"></div>';
			sourceDiv += '</div>';
			if ($('#selectors').find(option).length == 0){
				$('#selectors').append(sourceDiv).accordion('destroy').accordion({ header: "h3", autoHeight:false });
			}
			
			$(propertyid).append('<b>Retrieving properties from the server. Please wait...</b><br/><br/>');
			
			$.ajax({
			   url: "GetProperties",
			   processData: false,
			   data: "service="+getDataSourceURI(option),
			   success: function(msg){
					$(propertyid).children().remove();
					$(propertyid).append('<b>Add a value to the relevant properties:</b><br/><br/>');
					$(propertyid).append('<select id="propertyoptions"></select>');
					var tokens = msg.tokenize(",", " ", true);
					$.each(tokens, function(val, text) {
						$('#propertyoptions').append(
								//getNameFromURI(text) is defined in util.js
								$('<option></option>').val(text).html(getNameFromURI(text))
						);
					});
					
					$('#propertyoptions').change(function(){
						
						$(propertyid).append('&nbsp;&nbsp;&nbsp;<input id="propname" type="text" value='+$(this).val()+'/>');
						//The plugin for autogrowing the textboxes are defined in ...
						$('input#propname').autoGrowInput({
						    comfortZone: 10,
						    minWidth: 200,
						    maxWidth: 2000
						});
					});
					$('#selectors').accordion('destroy').accordion({ header: "h3", autoHeight:false });
		     	}
			 });
		}
		});

		$("#datasources :input:not(:checked)").each(function() {
			$('#selectors').find('#'+$(this).val()).remove();
		});

}


function addDialog(title, info){
	$("#container").title = title;
	$("#container").append(document.createTextNode(info));
	$("#container").dialog({title: title,
	      width: 500,
	      position: ['center', 'top'],
	      buttons: { "Ok": function() {
		$(this).dialog("close"); 
		$(this).dialog("destroy");
		$(this).empty();
	      }
	    } 
	  });

}

//@@ REMOVE THESE LATER
//OLD CODE FROM COLAB

var a; //this is the only global variable in this program
		//it denotes an assesment object

var init_called = false;

function Assessment(init_data){
	
	this.population = init_data[0];
	this.median_age = init_data[1];
	this.median_income = init_data[2];
	this.poverty_rate = init_data[3];
	this.racial_makeup = init_data[4];
	this.immigration_rate = init_data[5];
	this.language_makeup = init_data[6];
	
	this.c2 = "";
	this.o1 = "";
	this.o2 = "";
	this.o3 = "";
	this.o4 = "";
	this.o5 = "";
	this.o6 = "";
	
	
	this.isset = false;
	this.setCount = 0;
	
	this.setValues =  function(variable, value){
		switch(variable){
			case "c2": this.c2 = value; this.isset = true; this.setCount++; break;
			case "o1": this.o1 = value; this.isset = true; this.setCount++; break;
			case "o2": this.o2 = value; this.isset = true; this.setCount++; break;
			case "o3": this.o3 = value; this.isset = true; this.setCount++; break;
			case "o4": this.o4 = value; this.isset = true; this.setCount++; break;
			case "o5": this.o5 = value; this.isset = true; this.setCount++; break;
			case "o6": this.o6 = value; this.isset = true; this.setCount++; break;
		}
	}
	
	this.getValue = function(variable){
		switch(variable){
			case "c2": return this.c2; break;
			case "o1": return this.o1; break;
			case "o2": return this.o2; break;
			case "o3": return this.o3; break;
			case "o4": return this.o4; break;
			case "o5": return this.o5; break;
			case "o6": return this.o6; break;
		}	
	}
	
	
	this.getAllSetVals = function(){
		var arr = new Array();
		if (this.c2!=""){
			arr['c2'] = this.c2;
		}
		if (this.o1!=""){
			arr['o1'] = this.o1;
		}
		if (this.o2!=""){
			arr['o2'] = this.o2;
		}
		if (this.o3!=""){
			arr['o3'] = this.o3;
		}
		if (this.o5!=""){
			arr['o5'] = this.o5;
		}
		if (this.o6!=""){
			arr['o6'] = this.o6;
		}
		return arr;
	}
}


function addErrorDialog(choice){
	var msg = '<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span> No choices selected for '+choice+'!</p>';
	$("#container").html(msg);
	$("#container").dialog({ 	title: "Error",
						    	dialogClass: 'alert',
						   		buttons: {"OK": function(){$(this).dialog("close");}}
						   });

}

function getInfoBox(){
		
	var infobox = "<p><div id='infobox'><br/><h3>&nbsp;&nbsp;&nbsp;Demographic Information</h3>";
	if (a.population != "") infobox += "&nbsp;&nbsp;&nbsp;<b>Population</b> : " + a.population + "<br/>";
	if (a.median_age != "") infobox += "&nbsp;&nbsp;&nbsp;<b>Median Age</b> : " + a.median_age + "<br/>";
	if (a.median_income != "") infobox += "&nbsp;&nbsp;&nbsp;<b>Median Income</b> : " + a.median_income + "<br/>";
	if (a.poverty_rate != "") infobox += "&nbsp;&nbsp;&nbsp;<b>Poverty Rate</b> : " + a.poverty_rate + "<br/>";
	if (a.racial_makeup != "") infobox += "&nbsp;&nbsp;&nbsp;<b>Racial Makeup</b> : " + a.racial_makeup + "<br/>";
	if (a.immigration_rate != "") infobox += "&nbsp;&nbsp;&nbsp;<b>Immigration Rate</b> : " + a.immigration_rate + "<br/>";
	if (a.language_makeup != "") infobox += "&nbsp;&nbsp;&nbsp;<b>Language Makeup</b> : " + a.language_makeup + "<br/>";
	infobox += "<br/>";

	var prevousFunding = "";
	if (registerResponse("o4")){
		 prevousFunding = a.o4;
		 infobox += "<br/><h3>&nbsp;&nbsp;&nbsp;Previous Programs</h3><ul>";
		 
		 var answers = $("#o4").find("input[type=checkbox][checked]"); //not just the immediate children, but also finds the descendents
		 answers.each(function() {
		     infobox += "<li>" + $(this).val() + "</li>";	   
		   }
		   );
		 infobox += "</ul>"
	}
	infobox += "<br/></div></p><br/>";

	return infobox;
}

function addResultsDialog(title, question, data){
	
	var infobox = getInfoBox();
	$('#dt_container').html(infobox + "<h3>" + title + ":</h3><br/>" + data);
	$('#example').dataTable();
	$("#dt_container").dialog({ 	title: title,
					width: 1000,
					height: 800,
					buttons: { "Ok": function() {
									$(this).dialog("close"); 
									$(this).dialog("destroy");
									$(this).empty();
									},
								"Print": function(){
									$(this).print();
								}
							  } 
				});

	
}

$.clientCoords = function() {
     var dimensions = {width: 0, height: 0};
     if (document.documentElement) {
         dimensions.width = document.documentElement.offsetWidth;
         dimensions.height = document.documentElement.offsetHeight;
     } else if (window.innerWidth && window.innerHeight) {
         dimensions.width = window.innerWidth;
         dimensions.height = window.innerHeight;
     }
     return dimensions;
}

/**
* This function is the inializing function that populates the Assessment function.
* it needs to be called in every method that accesses this object
*/
function initialize(){
	var init_data = new Array();
	init_data[0] = $("#population").val();
	init_data[1] = $("#median_age").val();
	init_data[2] = $("#median_income").val();
	init_data[3] = $("#poverty_rate").val();
	init_data[4] = $("#racial_makeup").val();
	init_data[5] = $("#immigration_rate").val();
	init_data[6] = $("#language_makeup").val();
	
	a = new Assessment(init_data);
	init_called = true;
	return init_data;

}

/**
* Registers the reposnse in the Assessment object and returns true or false depending whether responses were selected or not 
*/
function registerResponse(question){
	var result = new Array();
	c = 0;
	var answers = $("#" + question).find("input[type=checkbox][checked]"); //not just the immediate children, but also finds the descendents
	answers.each(function() {
					result[c++] = $(this).val();	   
					}
				  );
	a.setValues(question,result);
	if (result.length < 1) return false;
	else return true;

}

function c1(){
	var init_data = initialize();
	
	addDialog("Success", "The demographic data: "+init_data + " was successfully added to the Asssessment Profile.");
}

function c2(){
	if (!init_called)
		initialize();
	if (registerResponse("c2")){
		$.ajax({
   			type: "GET",
   			url: "http://people.csail.mit.edu/oshani/colab/db/c2.php",
   			data: "c2="+a.c2,
   			success: function(data){
				addResultsDialog("Programs that fit based on Use", "c2" ,data);
   			}
 		});
	}
	else {
		addErrorDialog("C2");	
	}
}

function o1(){
	if (!init_called)
		initialize();
	if (registerResponse("o1")){
		$.ajax({
   			type: "GET",
   			url: "http://people.csail.mit.edu/oshani/colab/db/o1.php",
   			data: "o1="+a.o1,
   			success: function(data){
				addResultsDialog("Programs that fit based on the Type of Entity", "o1" ,data);
   			}
 		});
	}
	else {
		addErrorDialog("O2");	
	}
}

function o2(){
	if (!init_called)
		initialize();
	if (registerResponse("o2")){
		$.ajax({
   			type: "GET",
   			url: "http://people.csail.mit.edu/oshani/colab/db/o2.php",
   			data: "o2="+a.o2,
   			success: function(data){
				addResultsDialog("Programs that fit based on the Mission", "o2" ,data);
   			}
 		});
	}
	else {
		addErrorDialog("O2");	
	}
}

function o3(){
	if (!init_called)
		initialize();
	if (registerResponse("o3")){
		$.ajax({
   			type: "GET",
   			url: "http://people.csail.mit.edu/oshani/colab/db/o3.php",
   			data: "o3="+a.o3,
   			success: function(data){
				addResultsDialog("Programs that fit based on the Active Programs in the Organization", "o3" ,data);
   			}
 		});
	}
	else {
		addErrorDialog("O3");	
	}}

function o4(){
	if (!init_called)
		initialize();
	registerResponse("o4");

	addDialog("Success","Previous Programs Added");
}

function o5(){
	if (!init_called)
		initialize();
	if (registerResponse("o5")){
		$.ajax({
   			type: "GET",
   			url: "http://people.csail.mit.edu/oshani/colab/db/o5.php",
   			data: "o5="+a.o5,
   			success: function(data){
				addResultsDialog("Programs that fit based on the Type", "o5" ,data);
   			}
 		});
	}
	else {
		addErrorDialog("O5");	
	}	
}

function o6(){
	if (!init_called)
		initialize();
	if (registerResponse("o6")){
		$.ajax({
   			type: "GET",
   			url: "http://people.csail.mit.edu/oshani/colab/db/o6.php",
   			data: "o6="+a.o6,
   			success: function(data){
				addResultsDialog("Programs that fit based on the Eligible Entities or Sub Entities", "o6" ,data);
   			}
 		});
	}
	else {
		addErrorDialog("O1");	
	}	
}

function getAssesment(){
	if (!init_called)
		initialize();
	
	var isAnyVarSet = false;
	if (registerResponse("c2")) isAnyVarSet = true;
	if (registerResponse("o1")) isAnyVarSet = true;
	if (registerResponse("o2")) isAnyVarSet = true;
	if (registerResponse("o3")) isAnyVarSet = true;
	if (registerResponse("o5")) isAnyVarSet = true;
	if (registerResponse("o6")) isAnyVarSet = true;

	if (isAnyVarSet){
	  var tosend = "c2="+a.c2+"&o1="+a.o1+"&o2="+a.o2+"&o3="+a.o3+"&o5="+a.o5+"&o6="+a.o6;
	  var allSet = a.getAllSetVals();
	  var title = "Programs that fit based on the Choices Supplied for ";
	  var c = 1;
	  for (var i in allSet){
	    if (c++ != a.setCount)
	      title += i +" , ";
	    else
	      title += i;
	  }
	  $.ajax({
	    type: "GET",
		url: "http://people.csail.mit.edu/oshani/colab/db/all.php",
		data: tosend,
		success: function(data){
		//alert(data);
		addResultsDialog("Programs that fit", "" ,data);
	      }
	    });
	  }
	else{
	  addDialog("Error", "No response has been added");
	}
}

function c1_clear(){
	$("#c1")[0].reset();
}

function c2_clear(){
	$("#c2")[0].reset();

}

function o1_clear(){
	$("#o1")[0].reset();
}

function o2_clear(){
	$("#o2")[0].reset();
}

function o3_clear(){
	$("#o3")[0].reset();	
}

function o4_clear(){
	$("#o4")[0].reset();	
}

function o5_clear(){
	$("#o5")[0].reset();	
}

function o6_clear(){
	$("#o6")[0].reset();
}

function all_clear(){
	//Clear all the forms of their values
	c1_clear();
	c2_clear();
	o1_clear();
	o2_clear();
	o3_clear();
	o4_clear();
	o5_clear();
	o6_clear();
	
	//Reset all the variables to their default values
	init_called = false;
	initialize();
}

