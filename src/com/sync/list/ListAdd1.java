package com.sync.list;

import java.util.ArrayList;
import java.util.List;

public class ListAdd1 {

    private static volatile List list = new ArrayList();

    public void add(int i ){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        final ListAdd1 add1 = new ListAdd1();

        Thread t1 = new Thread(()->{
            try{
                for (int i = 0; i < 10; i++) {
                    add1.add(i);
                    System.out.println(Thread.currentThread().getName() + "添加一个元素");
                    Thread.sleep(500);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        },"t1");

        Thread t2 = new Thread(()->{
            try{
                while (true){
                    if(add1.size() == 5){
                        System.out.println(Thread.currentThread().getName() + " 线程收到通知:" + "add1.size() == 5 ... 停止");
                        throw new RuntimeException();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        },"t2");

        t1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

    }
}
