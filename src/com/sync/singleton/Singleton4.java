package com.sync.singleton;

/**
 * 静态内部类方式
 */
public class Singleton4 {

    private byte[] data = new byte[1024];

    private Singleton4(){}

    //在静态内部类中持有 Singleton4 实例,并且可被直接初始化
    private static class Holder{
        private static Singleton4 instance = new Singleton4();
    }

    public static Singleton4 getInstance(){

        return Holder.instance;
    }

}
