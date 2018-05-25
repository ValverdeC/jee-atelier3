package com.sample.users.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParameterNotSpecifiedExceptionTest {

    @Test
    public void testConstructor() {
        Exception exception = new ParameterNotSpecifiedException();
        assertEquals("Le paramètre n'a pas été spécifié.", exception.getMessage());

        exception = new ParameterNotSpecifiedException("Le nom n'a pas été spécifié.");
        assertEquals("Le nom n'a pas été spécifié.", exception.getMessage());
    }

}