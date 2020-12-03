package com.letv.demo001;

import com.letv.demo001.redis.RedisTest;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTestTest {

    @Autowired
    private RedisTest redisTest;

    @Test
    public void set() {
        redisTest.set("slogan","hello redis");
    }

    @Test
    public void get(){
        String value = redisTest.get("slogan");
        System.out.println(value);
    }

    @Test
    public void getMultiList(){
        List<String> keys = Lists.newArrayList();
        keys.add("slogan");
        keys.add("label");
        List<String> res = redisTest.getMultiList(keys);
        Iterator<String> iterator = res.iterator();
        while(iterator.hasNext()){
            String item = iterator.next();
            if(StringUtils.isBlank(item)){
                iterator.remove();
            }
        }
        res.forEach(item->System.out.print(item));
    }
}