package com.devindi.mapper.demo.dto.simple;

import java.util.List;

/**
 * @startuml
 * class PersonDto {
 *     -String name
 *     -List<String> friends
 * }
 * @enduml
 */
public class PersonDto {

    private String name;
    private List<String> friends;

    public PersonDto() {
    }

    public PersonDto(String name, List<String> friends) {
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
