package com.cash.examen.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder(toBuilder = true)
public class UserAlreadyRegisteredException extends Exception {

    public UserAlreadyRegisteredException(String message){
        super(message);
    }
}
