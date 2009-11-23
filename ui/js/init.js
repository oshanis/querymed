var dict;

$(function(){

	dict = {
			diseasome: "http://www4.wiwiss.fu-berlin.de/diseasome/sparql",
			dailymed: "http://www4.wiwiss.fu-berlin.de/diseasome/sparql",
			drugbank: "http://www4.wiwiss.fu-berlin.de/drugbank/sparql",
			linkedct:"http://linkedct.org/snorql/index.html"
		};
	for (property in dict) {
		$('#datasources').append('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <input type="checkbox" value='+ property + ' id=' + property + ' name=' + property + ' > ' + property + ' </input><br/>');
	}
	
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
