package com.greem.kodillalibrary.exceptions;

public class BookCopyAlreadyRentedByUserException extends RuntimeException {
    public BookCopyAlreadyRentedByUserException(String message) {
        super(message);
    }
}
