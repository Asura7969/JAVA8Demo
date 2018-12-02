package com.sync.volatil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 理解 volatile 关键字不保证原子性
 * 最后打印的 i 一定不是10000
 */
public class VolatileTest {

    private static volatile int i = 0;

    private static final CountDownLatch countDown = new CountDownLatch(1);

    public static void inc(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {

        for (int j = 0; j < 10; j++) {
            new Thread(()->{
                for (int k = 0; k < 1000; k++) {
                    inc();
                }
//                countDown.countDown();
            }).start();
        }
//        countDown.await();
        TimeUnit.SECONDS.sleep(10);
        System.out.println(i);
    }
}
