package com.alevel.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Optional;

@Data
@Builder
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Person {

    private String firstName;
    private String lastName;
    private Optional<DriverLicence> driverLicence;



}

