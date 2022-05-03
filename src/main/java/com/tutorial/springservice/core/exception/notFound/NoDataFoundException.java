package com.tutorial.springservice.core.exception.notFound;

public class NoDataFoundException extends Exception {
    public NoDataFoundException() {
        super("No Data Found!");
    }
    public NoDataFoundException(String message) {
        super(message);
    }
}
