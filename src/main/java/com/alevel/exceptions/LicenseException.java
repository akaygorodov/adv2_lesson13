package com.alevel.exceptions;

public class LicenseException extends RuntimeException {
    public LicenseException(){
        super("Driver license expired or absent.");
    }
}
