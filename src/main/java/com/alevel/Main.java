package com.alevel;

import com.alevel.model.DriverLicence;
import com.alevel.model.Person;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        final DriverLicence driverLicence = DriverLicence.builder()
                .categories(new String[]{"A", "B", "C"})
                .expireDate(Instant.parse("2020-11-30T18:35:24.00Z"))
                .build();
        final Person person = Person.builder()
                .firstName("John")
                .lastName("Smith")
                .driverLicence(Optional.ofNullable(driverLicence))
                .build();

        Optional.ofNullable(person)

                .ifPresent(personOptional -> {
                    if (personOptional.getDriverLicence().isPresent()){
                    if (Instant.now().compareTo(personOptional.getDriverLicence().get().getExpireDate()) > 0) {
                        System.out.println("Date of license expired, no valid licenses");
                    } else
                        System.out.println("Active categories: " + Arrays.toString(personOptional.getDriverLicence().get().getCategories()));
                    }}
                );
    }
}
