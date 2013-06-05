$(document).ready(function main() {
	var trollers = {trollers:[
			{name:"Nicolas",count:0},
			{name:"Benoit",count:0},
			{name:"Blandine",count:0},
			{name:"Fabien",count:0},
			{name:"Manu",count:0},
			{name:"Christophe",count:0},
			{name:"Olivier",count:0}
	]};

	var htmlTpl = document.getElementById('trollers').text
	var templateTrollers = doT.template(htmlTpl);

	$("#main").append(templateTrollers(trollers));
});