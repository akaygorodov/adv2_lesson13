package com.alevel;

import com.alevel.model.DriverLicence;
import com.alevel.model.Person;

import java.time.Instant;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        final DriverLicence driverLicence = DriverLicence.builder()
                .categories(new String[]{"A", "B", "C"})
                .expireDate(Instant.parse("2022-11-30T18:35:24.00Z"))
                .build();
        final Person person = Person.builder()
                .firstName("John")
                .lastName("Smith")
                .driverLicence(Optional.ofNullable(driverLicence))
                .build();
        Optional.ofNullable(person).ifPresent(value -> {
            try {
                soutCategories(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        })
        // you code here
        ;
    }

    private static void soutCategories(Person person) throws Exception {

        if (isNotExpired(person.getDriverLicence().get().getExpireDate())) {
            soutCategoriesStringArray(person);
        } else {
            throw new Exception("DriverLicence is expired");
        }

    }

    private static void soutCategoriesStringArray(Person person) {
        String[] categoriesArray = person.getDriverLicence().get().getCategories();
        System.out.println("The person " + person.getFirstName() + " " + person.getLastName() + "has next categories:");
        for (int i = 0; i < categoriesArray.length; i++) {
            System.out.print(categoriesArray[i] + ", ");
        }
    }


    private static boolean isNotExpired(Instant expireDate) {
        return expireDate.isAfter(Instant.now());
    }
}
