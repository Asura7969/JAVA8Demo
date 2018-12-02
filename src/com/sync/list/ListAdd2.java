package com.sync.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * t1 线程 通知 t2 执行, t1 执行完后 t2 才执行
 */
public class ListAdd2 {

    private static volatile List list = new ArrayList();

    public void add(int i ){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        final ListAdd2 add1 = new ListAdd2();
        final Object lock = new Object();
        Thread t1 = new Thread(()->{
            try{
                synchronized (lock){
                    for (int i = 0; i < 10; i++) {
                        add1.add(i);
                        System.out.println(Thread.currentThread().getName() + "添加一个元素");
                        TimeUnit.MILLISECONDS.sleep(500);
                        if(add1.size() == 5){
                            System.out.println("t1 已经发出通知...");
                            lock.notify();
                        }
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        },"t1");

        Thread t2 = new Thread(()->{
            try{
                synchronized (lock){
                    if(add1.size() != 5){
                        System.out.println("t2 进入...");
                        lock.wait();
                    }

                    System.out.println(Thread.currentThread().getName() + " 线程收到通知:" + "add1.size() == 5 ... 停止");
                    throw new RuntimeException();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        },"t2");

        t2.start();

        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();

    }
}
