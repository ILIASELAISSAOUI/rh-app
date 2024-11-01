package com.service_employes.exception;


public class DepartementNotEmptyException extends RuntimeException {
    public DepartementNotEmptyException(String message) {
        super(message);
    }
}