var dict;

$(function(){

	$("#select-source").hide();
	$("#ajax-wait-img").hide();
	
	//Data structure to keep track of the SPARQL endpoint
	dict = {
			diseasome: "http://www4.wiwiss.fu-berlin.de/diseasome/sparql",
			dailymed: "http://www4.wiwiss.fu-berlin.de/diseasome/sparql",
			drugbank: "http://www4.wiwiss.fu-berlin.de/drugbank/sparql",
			linkedct:"http://linkedct.org/snorql/index.html"
		};
	for (property in dict) {
		$('#datasources').append('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <input type="checkbox" value='+ property + ' id=' + property + ' name=' + property + ' > ' + property + ' </input><br/>');
	}
	
	//Auto grow input box if there's not enough room
	$('input').autoGrowInput({ //we don't know how long the input box should be, so expand when user wants to read to the end
	    comfortZone: 10,
	    minWidth: 200,
	    maxWidth: 2000
	});
	
	
	// Accordion
	//$("#selectors").accordion({ header: "h3", autoHeight:false });
    
	// Tabs
	$comtabs = $('#comtabs').tabs();
	$orgtabs = $('#orgtabs').tabs();


	// Dialog			
	$('#dialog').dialog({
		autoOpen: false,
		width: 600,
		buttons: {
			"Ok": function() { 
				$(this).dialog("close"); 
			}, 
			"Cancel": function() { 
				$(this).dialog("close"); 
			} 
		}
	});
	
	// Dialog Link
	$('#dialog_link').click(function(){
		$('#dialog').dialog('open');
		return false;
	});

	// Datepicker
	$('#datepicker').datepicker({
		inline: true
	});
	
	// Slider
	$('#slider').slider({
		range: true,
		values: [17, 67]
	});
	
	// Progressbar
	$("#progressbar").progressbar({
		value: 20 
	});
	
	//hover states on the static widgets
	$('#dialog_link, ul#icons li').hover(
		function() { $(this).addClass('ui-state-hover'); }, 
		function() { $(this).removeClass('ui-state-hover'); }
	);

});

$(document).ready(function() {
	
	$('#example').dataTable();
	//Display the containers for each of the data sources once the checkbox is clicked
	
	showProperties();
	$("#datasources").click(showProperties);

	//This has to be initialized first. I made a mistake by trying to initialize everytime
	//user clicks on the button
	$("#dt_container").dialog({title: "QueryMed",
		width: 1200,
		height: 700,
		autoOpen: false,
		modal: true,
		buttons: { "Print": function(){
						$(this).print();
					}
				  } 
	});

} );

