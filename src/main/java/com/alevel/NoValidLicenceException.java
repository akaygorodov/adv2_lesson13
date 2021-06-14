package com.alevel;

public class NoValidLicenceException extends RuntimeException {
    NoValidLicenceException() {
        super("Driver has no valid licences!");
    }
}
