package com.tutorial.springservice.core.exception.notFound;

public class NoPersonFoundException extends NoDataFoundException {
    public NoPersonFoundException() {
        super("No Person Found!");
    }
    public NoPersonFoundException(String message) {
        super(message);
    }
}
