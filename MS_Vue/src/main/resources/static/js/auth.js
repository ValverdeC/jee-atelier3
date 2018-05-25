$("#noAccountId").click(function() {
	window.location = "/signup";
});

$("#accountYetId").click(function() {
	window.location = "/signin";
});

$("#loginForm").submit(function(event){
    event.preventDefault();
    login(this);
});

$("#signupForm").submit(function(event){
    event.preventDefault();
    signup();
});

function login(form){
	var email = document.getElementById("email").value;
    var pwd = document.getElementById("pwd").value;
    var user = {
		email,
		password: pwd
	};
    $.ajax({
		url: '/api/users/login',
		type: 'POST',
		contentType: "application/json; charset=utf-8",
		dataType: "text",
		data : JSON.stringify(user),
		success: function(msg) {
			localStorage.setItem("token", msg);
			getMe(msg);
		},
		error: function(err) {
			console.log(err);
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
    		password: pwd
		};
        $.ajax({
			url: 'http://localhost:8080/api/users/signup',
			type: 'POST',
			contentType: "application/json; charset=utf-8",
			dataType: "text",
			data: JSON.stringify(user),
			success: function(msg) {
				localStorage.setItem("token", msg);
				getMe(msg);
			},
			error: function(err) {
				console.log(err);
			}
		});
    }
}

function getMe(token) {
	$.ajax({
		url: 'http://localhost:8080/api/users/me',
		type: 'GET',
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization', token);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(msg) {
			console.log(msg);
			localStorage.setItem("user", JSON.stringify(msg));
			window.location = "/";
		},
		error: function(err) {
			console.log(err);
		}
	});
}