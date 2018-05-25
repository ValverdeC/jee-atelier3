package com.sample.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordNotSpecifiedException extends ParameterNotSpecifiedException {

    public PasswordNotSpecifiedException() {
        super("Le mot de passe n'a pas été spécifié.");
    }

}
