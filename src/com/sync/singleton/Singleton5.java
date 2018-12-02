package com.sync.singleton;

/**
 * 枚举方式
 */

//枚举类型本身是final的,不允许被继承
public enum  Singleton5 {

    INSTANCE;

    private byte[] data = new byte[1024];

    Singleton5(){
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method() {
        //调用该方法则会主动使用 Singleton5, INSTANCE 实例化
    }

    public static Singleton5 getInstance(){
        return INSTANCE;
    }

}
