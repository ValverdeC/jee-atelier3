$(document).ready(function(){
	
    $("#playButtonId").click(function(){
        window.location = "/rooms";
    });  
    $("#buyButtonId").click(function(){
        //alert("Buy button clicked ");
        window.location = "/search";
    });    
    $("#sellButtonId").click(function(){
        //alert("Sell button clicked ");
        window.location = "/market";
    });    
});

