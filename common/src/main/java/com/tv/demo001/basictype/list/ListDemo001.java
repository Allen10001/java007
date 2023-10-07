package com.tv.demo001.basictype.list;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author hubo88
 * @description
 */
public class ListDemo001 {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        // 即时绑定
        Solution001 solution001 = injector.getInstance(Solution001.class);
        solution001.resolve();
    }
}

@Singleton
class Solution002{

    public void resolve() {
        List<List<Integer>> levelOrder = new LinkedList<List<Integer>>();
        levelOrder.add(0, Lists.newArrayList(1,2,3));
        levelOrder.add(0, Lists.newArrayList(2,2,3));
        levelOrder.add(0, Lists.newArrayList(3,2,3));
        levelOrder.add(0, Lists.newArrayList(4,2,3));
        levelOrder.add(0, Lists.newArrayList(5,2,3));
        levelOrder.stream().forEach(list -> System.out.println(list.get(0)));
    }
}

@Singleton
class Solution001{

    public void resolve() {
        String ids = "12_13_14";
        List<String> idList = Arrays.asList(ids.split("_"));
        idList.forEach(System.out::println);
    }

}
