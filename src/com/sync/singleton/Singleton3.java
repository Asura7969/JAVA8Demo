package com.sync.singleton;


import java.net.Socket;
import java.sql.Connection;

/**
 * Double-Check
 * 满足懒加载,满足唯一性,高效
 */
public class Singleton3 {

    private byte[] data = new byte[1024];

    /**
     * 此处添加 volatile 关键字,可防止类成员变量的实例化 conn 和 socket 发生在 instance 实例化之后,
     * 这一切均是由JVM指令重排序导致
     */
    private static Singleton3 instance = null;

    Connection conn;

    Socket socket;

    private Singleton3(Connection conn,Socket socket){
        this.conn = conn;
        this.socket = socket;
    }

    public static Singleton3 getInstance(Connection conn,Socket socket){
        if (null == instance){
            synchronized (Singleton3.class){
                if (null == instance){
                    instance = new Singleton3(conn,socket);
                }
            }
        }
        return instance;
    }
}
