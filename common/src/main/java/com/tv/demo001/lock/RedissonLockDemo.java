package com.tv.demo001.lock;

import java.io.File;
import java.io.IOException;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * redisson
 *
 * @author hubo88
 * @description
 * @date 2022/11/6 8:23 PM
 */
public class RedissonLockDemo {

    public static void main(String[] args) {

    }


}

class Solution {

    public void solution() throws IOException, InterruptedException {
        // 1. Create config object
        Config config = new Config();
        config.useClusterServers()
            // use "rediss://" for SSL connection
            .addNodeAddress("redis://127.0.0.1:7181");

        // or read config from file
        config = Config.fromYAML(new File("config-file.yaml"));

        // 2. Create Redisson instance

        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("lock");
        // 源码研究入口
        lock.lock();
        try{
            Thread.sleep(300000);
        }finally {
            lock.unlock();
        }

    }

}
