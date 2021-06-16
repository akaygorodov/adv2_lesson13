package com.alevel;

import com.alevel.model.DriverLicence;
import com.alevel.model.Person;
import lombok.SneakyThrows;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    @SneakyThrows
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

        List<DriverLicence> personOptional = Optional.ofNullable(person)
                .stream()
                .flatMap(personOpt -> personOpt.getDriverLicence().stream())
                .filter(expireDate -> Instant.now().isBefore(expireDate.getExpireDate()))
                .collect(Collectors.toList());
        if (personOptional.isEmpty()) {
            throw new Exception("Date of license expired, no valid licenses");
        } else System.out.println(personOptional);
    }
}
