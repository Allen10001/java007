package com.tv.demo001.map;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * map 测试类
 *
 * @author hubo88
 * @description
 * @link
 * @date 2022/3/15 8:52 PM
 */
public class MapDemo {

    public static void main(String[] args) throws InterruptedException {
        Map<Long, String> map = new HashMap<>();
        map.put(100014237835L, "100");
        map.put(100014237836L, "110");

        long a = 100014237835L;
        System.out.println(map.get(a));
        System.out.println(map.get(Long.valueOf(a)));

        Object b = null;
        System.out.println(Strings.isNullOrEmpty(String.valueOf(b)));

        Collection<String> keys = Lists.newArrayList( "a");
        System.out.println(keys.iterator().next());

        HashMap<Long, String> hashMap = new HashMap<>();
        hashMap.put(100007197427L, "a");
        System.out.println(Optional.ofNullable(hashMap).orElseGet(HashMap::new)
            .get(100007197427L));

    }

}
