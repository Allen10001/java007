package com.tv.demo001.cache;

import static javax.cache.expiry.Duration.ONE_HOUR;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.ExpiryPolicy;
import javax.cache.spi.CachingProvider;
import org.junit.Assert;

/**
 * jsr107 cache application api 标准
 *
 * @author hubo88
 * @description
 * @date 2023/2/19 3:31 PM
 */
public class JCacheApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();

        injector.getInstance(Solution001.class).resolve();

    }
}

@Singleton
class Solution001{

    public void resolve() {
        //获取默认的cacheManager
        CachingProvider cachingProvider = Caching.getCachingProvider("org.jsr107.ri.spi.RICachingProvider");
        CacheManager cacheManager = cachingProvider.getCacheManager();

        //配置缓存
        MutableConfiguration<String, Integer> config = new MutableConfiguration<>();
        //使用值引用存储
        config.setStoreByValue(false)
            .setTypes(String.class, Integer.class)
            .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(ONE_HOUR))   //设置过期时间 使用AccessExpiryPolicy
            .setStatisticsEnabled(true);

        //创建缓存
        Cache<String, Integer> cache1 = cacheManager.createCache("simpleOptionalCache", config);

        //获取到上面创建的缓存
        Cache<String, Integer> cache = cacheManager.getCache("simpleOptionalCache",
            String.class, Integer.class);

        //使用缓存，存储数据
        String key = "key";
        Integer value1 = 1;
        cache.put("key", value1);
        Integer value2 = cache.get(key);
        Assert.assertEquals(value1, value2);

        cache.remove("key");
        Assert.assertNull(cache.get("key"));
    }
}
