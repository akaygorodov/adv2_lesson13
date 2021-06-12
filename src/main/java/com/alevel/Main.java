package com.alevel;

import com.alevel.exceptions.LicenseException;
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

        Optional.ofNullable(person).ifPresent(p -> {
            DriverLicence dl = p.getDriverLicence().orElseThrow(LicenseException::new);
            if (dl.getExpireDate().isBefore(Instant.now())) { // .isBefore() possible NPE
                throw new LicenseException();
            }
            System.out.println("Active categories: " + Arrays.toString(dl.getCategories()));

        });


    }
}
