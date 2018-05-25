$(document).ready(function(){
	var token = localStorage.getItem('token');
	if (token == null || token == '') {
		window.location = "/signin";
	} else {
		$.ajax({
			url: 'http://localhost:8080/api/users/me',
			type: 'GET',
			beforeSend: function(xhr) {
				xhr.setRequestHeader('Authorization', token);
			},
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(msg) {
				
			},
			error: function(err) {
				window.location = "/signin";
			}
		});
	}
});