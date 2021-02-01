package com.tv.demo001.lock;

/**
 * 广义上的可重入锁指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，
 * 并且不发生死锁（前提得是同一个对象或者class），这样的锁就叫做可重入锁。ReentrantLock和synchronized都是可重入锁
 * Notice： 这个例子不是 DCL。
 */
public class LockTest001 {

    private static LockTest001 instance;
    public static final Object lock = new Object();

    public static void main(String[] args) {
        System.out.println(getInstance());
    }

    public static LockTest001 getInstance() {

        synchronized (lock) {
            if (instance == null) {
                synchronized (lock) {
                    instance = new LockTest001();
                }
            }
            return instance;
        }
    }

    @Override
    public String toString() {
        return "LockTest001{}";
    }
}
