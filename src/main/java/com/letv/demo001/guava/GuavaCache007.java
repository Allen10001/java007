package com.letv.demo001.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.apache.curator.shaded.com.google.common.cache.CacheBuilder.newBuilder;

/**
 * cacheLoader 方式 & callable callback 方式
 *
 * guava Cache 数据移除：
 * https://blog.csdn.net/u010889616/article/details/52694294
 * 　　guava做cache时候数据的移除方式，在guava中数据的移除分为被动移除和主动移除两种。
 * 　　被动移除数据的方式，guava 默认提供了三种方式：
 * 　　1.基于大小的移除:看字面意思就知道就是按照缓存的大小来移除，如果即将到达指定的大小，那就会把不常用的键值对从cache中移除。
 * 　　定义的方式一般为 CacheBuilder.maximumSize(long)，还有一种一种可以算权重的方法，个人认为实际使用中不太用到。就这个常用的来看有几个注意点，
 * 　　　　其一，这个size指的是cache中的条目数，不是内存大小或是其他；
 * 　　　　其二，并不是完全到了指定的size系统才开始移除不常用的数据的，而是接近这个size的时候系统就会开始做移除的动作；
 * 　　　　其三，如果一个键值对已经从缓存中被移除了，你再次请求访问的时候，如果cachebuild是使用cacheloader方式的，那依然还是会从cacheloader中再取一次值，如果这样还没有，就会抛出异常
 * 　　2.基于时间的移除：guava提供了两个基于时间移除的方法
 * 　　　　expireAfterAccess(long, TimeUnit)  这个方法是根据某个键值对最后一次访问之后多少时间后移除
 * 　　　　expireAfterWrite(long, TimeUnit)  这个方法是根据某个键值对被创建或值被替换后多少时间移除
 * 　　3.基于引用的移除：
 * 　　这种移除方式主要是基于java的垃圾回收机制，根据键或者值的引用关系决定移除
 * 　　主动移除数据方式，主动移除有三种方法：
 * 　　1.单独移除用 Cache.invalidate(key)
 * 　　2.批量移除用 Cache.invalidateAll(keys)
 * 　　3.移除所有用 Cache.invalidateAll()
 * 　　如果需要在移除数据的时候有所动作还可以定义Removal Listener，但是有点需要注意的是默认Removal Listener中的行为是和移除动作同步执行的，
 * 如果需要改成异步形式，可以考虑使用RemovalListeners.asynchronous(RemovalListener, Executor)
 *
 * 测试定时回收
 *
 * 对定时回收进行测试时，不一定非得花费两秒钟去测试两秒的过期。
 * 你可以使用Ticker接口和CacheBuilder.ticker(Ticker)方法在缓存中自定义一个时间源，而不是非得用系统时钟。
 *
 *
 */
public class GuavaCache007 {

    /**
     * 1. cacheLoader
     */
    public LoadingCache<String, String> loadingCache;
    /**
     * 2. callable callback
     */
    public Cache<String, String> cache;

    public GuavaCache007() {
        /**
         * 1. cacheLoader
         */
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                System.out.println("no data in cache, get data by involve load(） in CacheLoader...");
                return "key="+key+"|value=valueOf "+key+" InCacheLoader";
            }
        };

        this.loadingCache =  CacheBuilder
                .newBuilder()
                .maximumSize(5L)
                .expireAfterAccess(2L, TimeUnit.SECONDS)
                .expireAfterWrite(2L, TimeUnit.SECONDS)
                .build(cacheLoader);

        /**
         * 2. callable callback
         * Builds a cache which does not automatically load values when keys are requested.
         */
        this.cache = CacheBuilder
                .newBuilder()
                .build();

    }

    public static void main(String[] args) throws Exception{

        /**
         * 1. cacheLoader
         */
        GuavaCache007 guavaCache007 = new GuavaCache007();
        guavaCache007.loadingCache.put("guavaKey001", "guavaValue001");
        guavaCache007.loadingCache.put("guavaKey002", "guavaValue002");
        guavaCache007.loadingCache.put("guavaKey003", "guavaValue003");

        // Thread.sleep(2000);

        System.out.println(guavaCache007.loadingCache.get("EmpytKey001"));
        System.out.println(guavaCache007.loadingCache.get("EmpytKey002"));
        System.out.println(guavaCache007.loadingCache.get("guavaKey001"));
        System.out.println(guavaCache007.loadingCache.get("guavaKey002"));
        System.out.println(guavaCache007.loadingCache.get("guavaKey003"));

        guavaCache007.loadingCache.invalidate("guavaKey001");
        System.out.println(guavaCache007.loadingCache.get("guavaKey001"));

        guavaCache007.loadingCache.invalidateAll();
        System.out.println(guavaCache007.loadingCache.get("EmpytKey001"));
        System.out.println(guavaCache007.loadingCache.get("EmpytKey002"));
        System.out.println(guavaCache007.loadingCache.get("guavaKey001"));
        System.out.println(guavaCache007.loadingCache.get("guavaKey002"));
        System.out.println(guavaCache007.loadingCache.get("guavaKey003"));

        System.out.println("---------------------------");

        /**
         * 2. callable callback
         * Builds a cache which does not automatically load values when keys are requested.
         */
        guavaCache007.getValueByCallback();



    }

    /**
     * 2. callable callback
     * @return
     * @throws Exception
     */
    public void getValueByCallback() throws Exception{

        this.cache.put("guavaKey001", "guavaValue001");
        this.cache.put("guavaKey002", "guavaValue002");
        this.cache.put("guavaKey003", "guavaValue003");

        System.out.println(this.cache.get("EmpytKey001", new Callable<String>() {
            @Override
            public String call() throws Exception {
                String curKey = "EmpytKey001";
                System.out.println("no data in cache, get data by involve load(） in CacheLoader...");
                return "key="+curKey+"|value=valueOf "+curKey+" InCacheLoader";
            }
        }));

        System.out.println(this.cache.get("EmpytKey002", new Callable<String>() {
            @Override
            public String call() throws Exception {
                String curKey = "EmpytKey002";
                System.out.println("no data in cache, get data by involve load(） in CacheLoader...");
                return "key="+curKey+"|value=valueOf "+curKey+" InCacheLoader";
            }
        }));

        System.out.println(this.cache.get("guavaKey001", new Callable<String>() {
            @Override
            public String call() throws Exception {
                String curKey = "guavaKey001";
                System.out.println("no data in cache, get data by involve load(） in CacheLoader...");
                return "key="+curKey+"|value=valueOf "+curKey+" InCacheLoader";
            }
        }));

        System.out.println(this.cache.get("guavaKey002", new Callable<String>() {
            @Override
            public String call() throws Exception {
                String curKey = "guavaKey002";
                System.out.println("no data in cache, get data by involve load(） in CacheLoader...");
                return "key="+curKey+"|value=valueOf "+curKey+" InCacheLoader";
            }
        }));

        System.out.println(this.cache.get("guavaKey003", new Callable<String>() {
            @Override
            public String call() throws Exception {
                String curKey = "guavaKey003";
                System.out.println("no data in cache, get data by involve load(） in CacheLoader...");
                return "key="+curKey+"|value=valueOf "+curKey+" InCacheLoader";
            }
        }));
    }

}
