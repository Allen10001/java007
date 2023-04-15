package com.tv.demo001.basictype.list;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hubo88
 * @description
 */
public class ListDemo001 {

    public static void main(String[] args) {
        List<List<Integer>> levelOrder = new LinkedList<List<Integer>>();
        levelOrder.add(0, Lists.newArrayList(1,2,3));
        levelOrder.add(0, Lists.newArrayList(2,2,3));
        levelOrder.add(0, Lists.newArrayList(3,2,3));
        levelOrder.add(0, Lists.newArrayList(4,2,3));
        levelOrder.add(0, Lists.newArrayList(5,2,3));
        levelOrder.stream().forEach(list -> System.out.println(list.get(0)));
    }

}
