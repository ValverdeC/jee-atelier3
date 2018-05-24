$(document ).ready(function(){
    
	getCards();
        
     $("#createRoomButtonId").click(function(){
        alert("Create Card button clicked ");
        //TO DO
    }); 
    
});

function getCards(){
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
    alert("Room selected : " +id);
}