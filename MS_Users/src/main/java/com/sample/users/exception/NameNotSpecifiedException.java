package com.sample.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NameNotSpecifiedException extends ParameterNotSpecifiedException {

    public NameNotSpecifiedException() {
        super("Le nom n'a pas été spécifié.");
    }

}
