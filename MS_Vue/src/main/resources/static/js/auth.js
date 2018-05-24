
$("#loginForm").submit(function(event){
    event.preventDefault();
    login(this);
});

$("#signupForm").submit(function(event){
    event.preventDefault();
    signup(this);
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
            alert("Logged in !");
            //stocker token
        }
    });
}

function signup(form){

    if(form){
        var name = form.name.value;
        var email = form.email.value;
        var pwd = form.pwd.value;
        var pwd2 = form.pwdConfirm.value;

        if(pwd == pwd2){
            $.post({
                url: "/api/users/signup",
                data : {
                    "name" : name,
                    "email" : email,
                    "password" : pwd
                },
                success: function(resultat, status){
                    alert("Signed up !");
                    //stocker token
                }
            });
        }
    }

}