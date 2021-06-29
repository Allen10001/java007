package com.tv.demo001;

public class FieldTest {

    private String name = "aaa";

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        FieldTest001 fieldTest001 = new FieldTest001();
        System.out.println(fieldTest001.getName());
    }
}

class FieldTest001 extends FieldTest{

    private String name = "bbb";

}




