$(document ).ready(function(){

    $("#cancelButtonId").click(function(){
        alert("Cancel button clicked :");
        //TO DO
    });  
    
    /*$("#createButtonId").click(function() {
    	var room = document.getElementById("uniqueID").value;
    	
    	alert(room);
    	
		/*$.ajax({
			url: 'http://localhost:8080/api/game/createRoom/123',
			type: 'POST',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: json,
			success: function(msg) {
				alert('In Ajax');
			}
		});
    });*/ 
    
    $("#roomForm").submit(function(e){
    	e.preventDefault();
    	
    	var roomCreator = {
			name: document.getElementById("roomNameId").value,
	    	bet: document.getElementById("roomBetId").value,
	    	nbTour: document.getElementById("roomHitId").value
		};
    	
    	
    	$.ajax({
			url: 'http://localhost:8080/api/game/createRoom/123',
			type: 'POST',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(roomCreator),
			success: function(msg) {
				alert('In Ajax');
			}
		});
    });
});