package com.tv.demo001.bean;

import lombok.Data;

@Data
public class Person {

    public Person() {
    }

    public Person(Integer id, String firstName, String lastName, Integer birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer birthYear;

}
