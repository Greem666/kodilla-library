package com.greem.kodillalibrary.exceptions;

public class NoAvailableBookCopiesException extends RuntimeException {
    public NoAvailableBookCopiesException(String message) {
        super(message);
    }
}
