package com.alevel;

import com.alevel.model.DriverLicence;
import com.alevel.model.Person;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws Exception {
        final DriverLicence driverLicence = DriverLicence.builder()
                .categories(new String[]{"A", "B", "C"})
                .expireDate(Instant.parse("1999-11-30T18:35:24.00Z"))
                .build();
        final Person person = Person.builder()
                .firstName("John")
                .lastName("Smith")
                .driverLicence(Optional.ofNullable(driverLicence))
                .build();

        Instant LicenceTime = Optional.ofNullable(person.getDriverLicence())
                .flatMap(Function.identity())
                .map(DriverLicence::getExpireDate)
                .orElseThrow(() -> new Exception("expireDate input Error"));
        String[] Categories = Optional.ofNullable(person.getDriverLicence())
                .flatMap(Function.identity())
                .map(DriverLicence::getCategories)
                .orElseThrow(() -> new Exception("categories input Error"));

        if (LicenceTime.isAfter(Instant.now())) {
            System.out.println(Arrays.toString(Categories));

        }else {
            throw new Exception("No Active Licence");
        }
    }
}
