package com.project.restapi.exception;

public class ExpirationException extends MyException {

    public ExpirationException() {
        super("Token expirado...");
    }

}
