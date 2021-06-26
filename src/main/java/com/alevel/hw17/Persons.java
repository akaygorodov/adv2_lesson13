package com.alevel.hw17;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class Persons {

    private final String name;
    private final int id;
    private final long count;

    Persons(String name) {
        this.name = name;
        this.id = hashCode();
        this.count = id + name.length();
    }
}
