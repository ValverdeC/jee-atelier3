$(document ).ready(function(){

    $("#mySearch").submit(function(event){
        event.preventDefault();
        cardSearch(document.getElementById("mySearch"));
    });
});

function cardSearch(form){
	var name = form.search.value;

	$.ajax({
		url: 'http://localhost:8080/api/cards/search/' + name,
		headers: {"Authorization": localStorage.getItem('token')},
		type: 'GET',
		//Placer info
		complete : function(resultat, statut){
            var cardsSearch = JSON.parse(resultat.responseText);

            if(cardsSearch[0])
			    fillCurrentCard(cardsSearch[0]);
       	}
	});
}

function buyCard(card){
    var user = JSON.parse(localStorage.getItem('user'));

    $.ajax({
        url: 'http://localhost:8080/api/buy/' + card.id + "/" + user.id,
        headers: {"Authorization": localStorage.getItem('token')},
        type: 'POST',
        //Placer info
        success : function(resultat, statut){
             $.get({
                url : "http://localhost:8080/api/users/me",
                headers: {"Authorization": localStorage.getItem('token')},
                type: 'GET',
                success : function(resultat, statut){
                    localStorage.setItem("user",resultat);
                },
             });
        }
    });
}

function fillCurrentCard(card){
    //FILL THE CURRENT CARD
    $('#cardFamilyImgId')[0].src=card.imgUrlFamily;
    $('#cardFamilyNameId')[0].innerText=card.familyName;
    $('#cardImgId')[0].src=card.imgUrl;
    $('#cardNameId')[0].innerText=card.name;
    $('#cardDescriptionId')[0].innerText=card.description;
    $('#cardHPId')[0].innerText=card.hp+" HP";
    $('#cardEnergyId')[0].innerText=card.energy+" Energy";
    $('#cardAttackId')[0].innerText=card.attack+" Attack";
    $('#cardDefenceId')[0].innerText=card.defence+" Defence";
};




