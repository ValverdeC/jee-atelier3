var currentCardId = 0;
var cards = [];

$(document ).ready(function(){

    getCards();

});

function getCards(){

	$.ajax({
		url: 'http://localhost:8080/api/cards/available',
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
            resetList();

            for(var i = 0 ; i < cards.length ; i++){
                addCardToList(cards[i]);
            }

            if(cards[currentCardId])
                fillCurrentCard(cards[currentCardId].id);
       	}
	});
}

function buyCard(){

    var user = JSON.parse(localStorage.getItem('user'));

    $.ajax({
        url: 'http://localhost:8080/api/cards/buy/'+ currentCardId +'/' + user.id,
        headers: {"Authorization": localStorage.getItem('token')},
        type : 'POST',
        success : function(resultat,status){
            $.get({
                url : "http://localhost:8080/api/users/me",
                headers: {"Authorization": localStorage.getItem('token')},
               	success : function(resultat, statut){
                    localStorage.setItem("user",JSON.stringify(resultat));
                },
            });
            getCards();
        },
        error : function(resultat,status){
            alert("Une erreur est survenue");
        }
    });
}

function fillCurrentCard(cardId){

    var card;

    for(var i = 0 ; i < cards.length ; i++){
        var c = cards[i];
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

function resetList(){
    $('#cardListId tbody').empty();
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
            <div class='hidden content'>Sell</div>\
    <div class='visible content'>\
        <i class='shop icon'></i>\
    </div>\
    </div>\
    </td>";

    $('#cardListId tbody').append('<tr>'+content+'</tr>');

};