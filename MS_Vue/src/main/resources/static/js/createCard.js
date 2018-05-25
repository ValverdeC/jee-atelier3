$(document ).ready(function(){	
    $("#cancelButtonId").click(function(){
    	window.location = "/rooms";
    });
    
    $("#card-form").submit(function(e){
    	e.preventDefault();
    	var cardCreator = {
			name: document.getElementById("name").value,
	    	description: document.getElementById("description").value,
	    	imgUrl: document.getElementById("imgUrl").value,
	    	family: document.getElementById("family").value,
	    	affinity: document.getElementById("affinity").value,
	    	hp: document.getElementById("hp").value,
	    	energy: document.getElementById("energy").value,
	    	attack: document.getElementById("attack").value,
	    	defence: document.getElementById("defence").value,
	    	price: document.getElementById("price").value,
	    	ownerId: JSON.parse(localStorage.getItem('user')).id
		};
    	
    	
    	$.ajax({
			url: '/api/cards/card',
			type: 'POST',
			contentType: "application/json; charset=utf-8",
			dataType: "text",
			data: JSON.stringify(cardCreator),
			success: function(msg) {
				window.location = "/rooms";
			}
		});
    });
});