package com.tv.demo001.bean;

import java.util.List;

/**
 * 节点子类.
 * https://stackoverflow.com/questions/4716040/do-subclasses-inherit-private-fields
 * However. This is different than the notion of inheritance for a class. As is the case in the java world, where there is a question of semantics the arbiter is the Java Language Specification (currently 3rd edition).
 *
 * As the JLS states (https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.2):
 *
 * Members of a class that are declared private are not inherited by subclasses of that class. Only members of a class that are declared protected or public are inherited by subclasses declared in a package other than the one in which the class is declared.
 *
 * This addresses the exact question posed by the interviewer: "do subCLASSES inherit private fields". (emphasis added by me)
 *
 * The answer is No. They do not. OBJECTS of subclasses contain private fields of their superclasses. The subclass itself has NO NOTION of private fields of its superclass.
 * @author hubo88
 * @description
 * @date 2024/5/29 11:02 AM
 */
public class ChildVertex extends Vertex{

    private List<Integer> list;

    private String name;

    public ChildVertex(Integer id, String name) {
        super();
        this.name = name;
        this.alias = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
