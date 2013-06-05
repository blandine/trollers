
function get_trollers(callback) {
   // var trollers = [];
    $.ajax({
         url: "/trollers",
         type: "GET",
        contentType:"application/json"
     }).complete(function ( data ) {
         callback(data);
         console.log("Done"+data.trollers);
     }).fail(function (jqXHR, textStatus, errorThrown ) {
        console.log("Fail");
     })
     ;
	/*return {trollers:[
			{name:"NME",count:0},
			{name:"BCA",count:0},
			{name:"BDE",count:0},
			{name:"FMO",count:0},
			{name:"EFO",count:0},
			{name:"CPR",count:0},
			{name:"OBE",count:0},
			{name:"BORE",count:0},
			{name:"EBO",count:0},
			{name:"RRO",count:0}
	]};         */
}