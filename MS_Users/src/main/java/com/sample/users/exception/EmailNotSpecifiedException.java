package com.sample.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotSpecifiedException extends ParameterNotSpecifiedException {

    public EmailNotSpecifiedException() {
        super("L'adresse mail n'a pas été spécifié.");
    }

}
