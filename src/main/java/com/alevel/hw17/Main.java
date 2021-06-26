package com.alevel.hw17;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Persons> persons = List.of(new Persons("Jonny"),
                new Persons("Mike"),
                new Persons("Jany"),
                new Persons("Joan"));
        persons.forEach(System.out::println);

        List<String> names = persons.stream()
                .map(Persons::getName)
                .collect(Collectors.toList());
        System.out.println(names);

        System.out.println(persons.stream().max(Comparator.comparing(Persons::getId)));

        long sumCountsOfPersonNamesContainsChar = persons.stream()
                .filter(person -> person.getName().contains("a"))
                .mapToLong(Persons::getCount)
                .sum();
        System.out.println(sumCountsOfPersonNamesContainsChar);

        Map<Integer, String> personsMap = persons.stream()
                .collect(Collectors.toMap(Persons::getId, Persons::getName));
        System.out.println(personsMap);
    }
}
