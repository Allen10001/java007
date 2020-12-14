package com.tv.demo001.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisTest{

    private ValueOperations valueOperations;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.valueOperations = redisTemplate.opsForValue();
    }

    public void set(String s, Object o) {
        valueOperations.set(s,o);
    }

    public String get(String key){
        Object value = valueOperations.get(key);
        if(null != value){
            return value.toString();
        }
        return "";
    }

    public List<String> getMultiList(List<String> keys){
        List<String> result= valueOperations.multiGet(keys);
        return result;
    }

}