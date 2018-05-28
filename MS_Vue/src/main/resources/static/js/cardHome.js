$(document).ready(function(){
	
    $("#playButtonId").click(function(){
        window.location = "/rooms";
    });  
    $("#buyButtonId").click(function(){
        //alert("Buy button clicked ");
        window.location = "/buy";
    });    
    $("#sellButtonId").click(function(){
        //alert("Sell button clicked ");
        window.location = "/market";
    });    
    $("#addButtonId").click(function(){
        //alert("Add button clicked ");
        window.location = "/addCard";
    });  
});

