package com.greem.kodillalibrary.exceptions;

public class LibraryUserNotFound extends RuntimeException {
    public LibraryUserNotFound(String message) {
        super(message);
    }
}
