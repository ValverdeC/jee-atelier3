var currentCardId;

$(document ).ready(function(){
	
    var cards = getCards();
    currentCardId = 0

    if(cards[currentCardId])
        fillCurrentCard(cards[currentCardId]);

    for(var i in cards){
        var card = cards[i];
        addCardToList(card);
    }

});

function getCards(){
    var cards = []

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
       	    return cards;
       	}
	});

}

function sellCard(){

    $.post({
        url: 'http://localhost:8080/api/cards/sell/'+ currentCardId +'/' + localStorage.getItem("userId"),
        headers: {"Authorization": localStorage.getItem('token')},
        success : function(resultat,status){
            alert("Carte vendue");
        },
        error : function(resultat,status){
            alert("Une erreur est survenue");
        }
    });
}


function fillCurrentCard(card){
    //FILL THE CURRENT CARD
    if(card){
        currentCardId = card.id;

        $('#cardFamilyImgId')[0].src= card.imgUrlFamily;
        $('#cardFamilyNameId')[0].innerText= card.familyName;
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
    <td>"+card.familyName+"</td> \
    <td>"+card.hp+"</td> \
    <td>"+card.energy+"</td> \
    <td>"+card.attack+"</td> \
    <td>"+card.defence+"</td> \
    <td>"+card.price+"$</td>\
    <td>\
        <div class='ui vertical animated button' onclick='fillCurrentCard(card)' tabindex='0'>\
            <div class='hidden content'>Sell</div>\
    <div class='visible content'>\
        <i class='shop icon'></i>\
    </div>\
    </div>\
    </td>";
    
    $('#cardListId tr:last').after('<tr>'+content+'</tr>');
    
    
};