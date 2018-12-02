package com.sync.singleton;

/**
 * 饿汉式:
 * 可以保证多线程下的唯一实例,无法进行懒加载
 */
public class Singleton1 {

    //实例变量
    private byte[] data = new byte[1024];

    //定义实例变量的初始化方法
    private static Singleton1 instance = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance(){
        return instance;
    }

}
