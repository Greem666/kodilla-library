package com.greem.kodillalibrary.exceptions;

public class BookCopyNotRentedException extends RuntimeException {
    public BookCopyNotRentedException(String message) {
        super(message);
    }
}
