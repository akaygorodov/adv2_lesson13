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
                .expireDate(Instant.parse("2023-11-30T18:35:24.00Z"))
                .build();
        final Person person = Person.builder()
                .firstName("John")
                .lastName("Smith")
                .driverLicence(Optional.ofNullable(driverLicence))
                .build();

        try {
            Main.handlePerson(Optional.ofNullable(person));
        } catch (NoValidLicenceException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void handlePerson(Optional<Person> person) throws NoValidLicenceException {
        person
                .flatMap(Person::getDriverLicence)
                .filter(Main::filterLicence)
                .ifPresentOrElse(
                        Main::outputCategories,
                        () -> {
                            throw new NoValidLicenceException();
                        }
                );
    }

    private static boolean filterLicence(DriverLicence licence) {
        return licence.getExpireDate().isAfter(Instant.now());
    }

    private static void outputCategories(DriverLicence licence) {
        String output = Arrays.toString(licence.getCategories());
        System.out.println("Allowed categories: " + output);
    }
}
