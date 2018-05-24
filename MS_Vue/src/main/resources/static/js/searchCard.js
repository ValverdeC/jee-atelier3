$(document ).ready(function(){
    
     fillCurrentCard("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/DC_Comics_logo.png/280px-DC_Comics_logo.png","DC comics","http://www.guinnessworldrecords.com/images/superlative/superheroes/GWR-Superheroes-SUPERMAN.svg","SUPERMAN","The origin story of Superman relates that he was born Kal-El on the planet Krypton, before being rocketed to Earth as an infant by his scientist father Jor-El, moments before Krypton's destruction. Discovered and adopted by a farm couple from Kansas, the child is raised as Clark Kent and imbued with a strong moral compass. Early in his childhood, he displays various superhuman abilities, which, upon reaching maturity, he resolves to use for the benefit of humanity through a 'Superman' identity.",50,100,17,8);
    
      $("#searchButtonId").click(function(){
        alert("Search button clicked :"+$("#searchId").val());
        //TO DO
    });  
    
});

function cardSearch(form){
	var name = form.search.value;

	$.ajax({
		url: 'http://localhost:8080/api/cards/search/' . name,
		type: 'GET',
		//Placer info
		complete : function(resultat, statut){
			var card = resultat;

			fillCurrentCard(card);
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




