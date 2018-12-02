package com.sync.singleton;

/**
 * 懒汉模式
 * 不能保证单例的唯一性
 *
 * 懒汉模式 + 同步模式
 * 可以保证单例的唯一
 */
//final 不允许被继承
public final class Singleton2 {

    private byte[] data = new byte[1024];

    private static Singleton2 instance = null;

    private Singleton2(){}

    public static synchronized Singleton2 getInstance(){
        if(null == instance){
            instance = new Singleton2();
        }
        return instance;
    }

}
