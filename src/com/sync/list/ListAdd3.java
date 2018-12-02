package com.sync.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * t1 线程 通知 t2 执行,两个线程同时执行后面逻辑
 */
public class ListAdd3 {

    private static volatile List list = new ArrayList();

    public void add(int i ){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        final ListAdd3 add1 = new ListAdd3();
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t1 = new Thread(()->{
            try{
                for (int i = 0; i < 10; i++) {
                    add1.add(i);
                    System.out.println(Thread.currentThread().getName() + "添加一个元素");
                    Thread.sleep(500);
                    if(add1.size() == 5){
                        System.out.println("t1 已经发出通知...");
                        countDownLatch.countDown();
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        },"t1");

        Thread t2 = new Thread(()->{
            try{
                if(add1.size() != 5){
                    System.out.println("t2 进入...");
                    countDownLatch.await();
                }

                System.out.println(Thread.currentThread().getName() + " 线程收到通知:" + "add1.size() == 5 ... 停止");
                throw new RuntimeException();

            }catch (Exception e){
                e.printStackTrace();
            }

        },"t2");

        t2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();

    }
}
