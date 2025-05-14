package com.tv.demo001.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点父类
 *
 * @author hubo88
 * @description
 * @date 2024/5/29 11:01 AM
 */
public class Vertex {

    protected ArrayList<Integer> list;

    private Integer id;
    protected String alias;
    private String alias001;

    public Vertex() {
    }

    public Vertex(Integer id) {
        this.id = id;
    }


}
