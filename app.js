$(document).ready(function main() {
	var trollers = {trollers:[
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
	]};

	var htmlTpl = document.getElementById('trollers').text
	var templateTrollers = doT.template(htmlTpl);

	$("#main").append(templateTrollers(trollers));
});