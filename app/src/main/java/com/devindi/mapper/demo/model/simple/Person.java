package com.devindi.mapper.demo.model.simple;

import java.util.List;

/**
 * @startuml
 * class Person {
 *     -String name
 *     -List<String> friends
 * }
 * @enduml
 */
public class Person {

    private String name;
    private List<String> friends;

    public Person(String name, List<String> friends) {
        this.name = name;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
