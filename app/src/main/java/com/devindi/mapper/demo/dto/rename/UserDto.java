package com.devindi.mapper.demo.dto.rename;

import java.util.List;

public class UserDto {

    private String name;
    private List<String> linked;

    public UserDto() {
    }

    public UserDto(String name, List<String> linked) {
        this.name = name;
        this.linked = linked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLinked() {
        return linked;
    }

    public void setLinked(List<String> linked) {
        this.linked = linked;
    }
}
