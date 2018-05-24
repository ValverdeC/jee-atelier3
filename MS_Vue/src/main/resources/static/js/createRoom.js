$(document ).ready(function(){

    $("#cancelButtonId").click(function(){
    	window.location = "/rooms";
    });
    
    $("#roomForm").submit(function(e){
    	e.preventDefault();
    	
    	var roomCreator = {
    		estLancee: false,
    		idJoueur1: 123,
    		idCarteJoueur1: 34,
    		nbTour: document.getElementById("roomHitId").value,
			name: document.getElementById("roomNameId").value,
	    	bet: document.getElementById("roomBetId").value
		};
    	
    	$.ajax({
			url: 'http://localhost:8080/api/game/createRoom',
			type: 'POST',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(roomCreator),
			success: function(msg) {
				window.location = "/rooms";
			}
		});
    });
});