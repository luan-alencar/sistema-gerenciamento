package com.basis.sge.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String msg){
        super(msg);
    }
}
