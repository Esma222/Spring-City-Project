package com.esmabeydili.exception;

import org.springframework.http.HttpStatusCode;

public class IlNotFoundException extends RuntimeException{

    public  IlNotFoundException(String msg) {
        super(msg);
    }
}
