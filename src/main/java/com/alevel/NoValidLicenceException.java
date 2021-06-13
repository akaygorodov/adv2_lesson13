package com.alevel;

public class NoValidLicenceException extends Exception {
    NoValidLicenceException() {
        super("Driver has no valid licences!");
    }
}
