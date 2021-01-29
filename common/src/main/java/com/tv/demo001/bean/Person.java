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
    // bad case, boolean  类型不要is开头，生成的set和get方法不符合规范
    @Deprecated
    private Boolean isA;
    private String firstName;
    private String lastName;
    private Integer birthYear;

    public Boolean getA() {
        return isA;
    }

    public void setA(Boolean a) {
        isA = a;
    }

    public static void main(String[] args) {
        System.out.println(NumEnum.一.name());
    }


}
