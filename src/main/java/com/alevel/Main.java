package com.alevel;

import com.alevel.model.DriverLicence;
import com.alevel.model.Person;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

/**
 * Вывести на экран открытые водительские категории у персоны,
 * если срок водительского удостоверения не истек, в противном случае –
 * выбросить ошибку о том, что нет действующих лицензий.
 */
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

        try {
            Optional
                    .ofNullable(person)
                    .ifPresent(Main::handlePerson);
        } catch (NoValidLicenceException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void handlePerson(Person person) throws NoValidLicenceException {
        Optional<DriverLicence> licence = person.getDriverLicence();
        licence.orElseThrow(NoValidLicenceException::new);

        licence.ifPresent((lic) -> {
            Instant expireDate = lic.getExpireDate();

            if (expireDate.isBefore(Instant.now())) {
                throw new NoValidLicenceException();
            } else {
                System.out.println("Allowed categories: " + Arrays.toString(lic.getCategories()));
            }
        });
    }
}
