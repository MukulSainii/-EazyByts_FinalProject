package com.fitness.exception;


public class InvalidEmailException extends Throwable{

    public InvalidEmailException(String message){
        super(message);
    }

    public InvalidEmailException(String message, Throwable e){
        super(message, e);
    }
}

