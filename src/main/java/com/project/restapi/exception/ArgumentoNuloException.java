package com.project.restapi.exception;

public class ArgumentoNuloException extends MyException{

    private static final long serialVersionUID = 1L;

    public ArgumentoNuloException() {
        super("Usuário null...");
    }

}
