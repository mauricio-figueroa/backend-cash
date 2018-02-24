package com.cash.examen.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAlreadyRegisteredException extends Exception {

    public UserAlreadyRegisteredException(String message){
        super(message);
    }
}
