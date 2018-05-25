
$("#loginForm").submit(function(event){
    event.preventDefault();
    login(this);
});

$("#signupForm").submit(function(event){
    event.preventDefault();
    signup();
});

function login(form){
    var email = form.email.value;
    var pwd = form.pwd.value;

    $.post({
        url: "/api/users/login",
        data : {
            "email" : email,
            "password" : pwd
        },
        error: function(res,err){
            console.error(res);console.error(err);
        },
        success: function(resultat, status){
            localStorage.setItem("token",response.body.token);
            localStorage.setItem("userId",response.body.userId);
            alert("Logged in !");
            //stocker token
        }
    });
}

function signup() {
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var pwd = document.getElementById("pwd1").value;
    var pwd2 = document.getElementById("pwd2").value;

    if(pwd == pwd2){
    	var user = {
    		name,
    		email,
    		pwd
		};
        $.ajax({
			url: 'http://localhost:8080/api/users/signup',
			type: 'POST',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(user),
			success: function(msg) {
				localStorage.setItem("token",response.body.token);
                    		localStorage.setItem("userId",response.body.userId);
				console.log("success")
				window.location = "/";
			}
		});
    }

}