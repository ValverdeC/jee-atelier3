$(document ).ready(function(){
	
	getRooms();
        
     $("#createRoomButtonId").click(function(){
    	 window.location = "/create";
    }); 
    
});

function getRooms(){
    var cards = [];

    $.ajax({
    		url: 'http://localhost:8080/api/game/rooms',
    		type: 'GET',
    		complete : function(resultat, statut) {
    			var cards = resultat.responseJSON;
    			
    			if (cards != null) {
    				cards.forEach((card) => {
    					addRoomToList(card.id, card.name, card.idJoueur1, card.bet);
    				})
    			}
    			
    			return cards;
           	}
    });
}


function addRoomToList(id, name, user, bet){
    
    content="<td> "+name+" </td> \
                            <td> "+user+" </td> \
                            <td> "+bet+" $</td> \
                            <td> \
                                <div class='center aligned'> \
                                    <div class='ui  vertical animated button' tabindex='0' onClick='onRoomSelected("+id+")'> \
                                        <div class='hidden content'>Play</div> \
                                        <div class='visible content'> \
                                            <i class='play circle icon'></i> \
                                        </div> \
                                    </div> \
                                </div> \
                            </td>";
    
    $('#roomListId tr:last').after('<tr>'+content+'</tr>');
    
    
};

function onRoomSelected(id){
	$('.ui.basic.modal').modal('show');
	$("#modalTitle").text('Rejoindre la salle n°' + id);
	
	getCards();
};

function getCards(){
	
	var user = JSON.parse(localStorage.getItem('user'));

	$.ajax({
		url: 'http://localhost:8080/api/cards/user/' + user.id,
		headers: {"Authorization": localStorage.getItem('token')},
		type: 'GET',
		//Placer info
		success : function(resultat, statut){
			cards = resultat;
       	},
       	error : function(resultat,status) {
       	    alert("Une erreur est survenue");
       	},
       	complete : function(){
            currentCardId = 0

            for(var i = 0 ; i < cards.length ; i++){
                addCardToList(cards[i]);
            }
       	}
	});

}



function addCardToList(card){
    
    content="\
    <td> \
    <img  class='ui avatar image' src='"+card.imgUrl+"'> <span>"+card.name+" </span> \
   </td> \
    <td>"+card.description+"</td> \
    <td>"+card.family+"</td> \
    <td>"+card.hp+"</td> \
    <td>"+card.energy+"</td> \
    <td>"+card.attack+"</td> \
    <td>"+card.defence+"</td> \
    <td>"+card.price+"$</td>\
    <td>\
        <div class='ui vertical animated button' onclick='fillCurrentCard("+card.id+")' tabindex='0'>\
            <div class='hidden content'>Select</div>\
    <div class='visible content'>\
        <i class='check icon'></i>\
    </div>\
    </div>\
    </td>";
    
    //$('#cardListId tr:last').replaceWith('<tr>'+content+'</tr>');
    $('#cardListId tbody').append('<tr>'+content+'</tr>');
    
    
};