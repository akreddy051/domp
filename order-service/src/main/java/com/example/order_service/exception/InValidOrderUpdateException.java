package com.example.order_service.exception;

public class InValidOrderUpdateException extends RuntimeException {
    public InValidOrderUpdateException(String message){
        super(message);
    }
}
