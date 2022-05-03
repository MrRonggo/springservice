package com.tutorial.springservice.core.exception.constraintViolation;

public class ConstraintViolationException extends Exception {
    public ConstraintViolationException() {
        super("Constraint Violation!");
    }
    public ConstraintViolationException(String message) {
        super(message);
    }
}
