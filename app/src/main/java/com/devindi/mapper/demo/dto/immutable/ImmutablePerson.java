package com.devindi.mapper.demo.dto.immutable;

import java.util.List;

public class ImmutablePerson {

    private final String name;
    private final List<String> friends;

//    private ImmutablePerson() {
//        name = null;
//        friends = null;
//    }

    public ImmutablePerson(String name, List<String> friends) {
        this.name = name;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public List<String> getFriends() {
        return friends;
    }
}
