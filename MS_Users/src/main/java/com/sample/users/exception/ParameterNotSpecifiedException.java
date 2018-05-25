package com.sample.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParameterNotSpecifiedException extends Exception {

    ParameterNotSpecifiedException() {
        super("Le paramètre n'a pas été spécifié.");
    }

    ParameterNotSpecifiedException(String message) {
        super(message);
    }
}
