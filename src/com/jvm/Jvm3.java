package com.jvm;

import java.util.Vector;

public class Jvm3 {

    public static void main(String[] args) {

        //-Xms2m -Xmx2m -XX:+HeadDumpOnOutOfMemoryError -XX:HeadDumpPath=d:/jvm3.dump
        //堆内存溢出
        Vector v = new Vector();
        for (int i = 0; i < 5; i++) {
            v.add(new Byte[1024 * 1024 *1]);
        }
    }
}
