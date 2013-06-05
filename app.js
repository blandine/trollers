$(document).ready(function main() {
	var trollers = get_trollers(function(trollers){
        var htmlTpl = document.getElementById('trollers').text;
        var templateTrollers = doT.template(htmlTpl);

        $("#main").append(templateTrollers(trollers));

        $(".troll").on("click", function(e) {
            var target = $(e.currentTarget);
            var name = target.find(".troll-name").html();
            //console.log("click sur ",name);


        });
    });


});