package com.sample.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailAlreadyUsedException extends Exception {

    public EmailAlreadyUsedException() {
        super("Cette adresse mail est déjà utilisée");
    }
}
