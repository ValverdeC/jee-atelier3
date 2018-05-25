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

	$.ajax({
		url: 'http://localhost:8080/api/cards',
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

            if(cards[currentCardId])
                fillCurrentCard(cards[currentCardId].id);
       	}
	});

}

function fillCurrentCard(cardId){

    var card;

    for(var i = 0 ; i < cards.length ; i++){
        var c = cards[i];
        console.log(c);
        if(c.id == cardId){
            card = c;
        }
    }

    //FILL THE CURRENT CARD
    if(card){
        currentCardId = card.id;

        $('#cardFamilyImgId')[0].src= card.imgUrlFamily;
        $('#cardFamilyNameId')[0].innerText= card.family;
        $('#cardImgId')[0].src=card.imgUrl;
        $('#cardNameId')[0].innerText=card.name;
        $('#cardDescriptionId')[0].innerText=card.description;
        $('#cardHPId')[0].innerText=card.hp+" HP";
        $('#cardEnergyId')[0].innerText=card.energy+" Energy";
        $('#cardAttackId')[0].innerText=card.attack+" Attack";
        $('#cardDefenceId')[0].innerText=card.defence+" Defence";
        $('#cardPriceId')[0].innerText=card.price+" $";
    }
};


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
    
    $('#cardListId tr:last').replaceWith('<tr>'+content+'</tr>');
    
    
};