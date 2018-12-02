package com.jvm;

/**
 * -XX:+PrintGC 使用这个参数，虚拟机启动后，只要遇到GC就会打印日志
 * -XX:+UseSerialGC 配置串行回收器
 * -XX:+PrintGCDetails 可以查看详细信息,包括各个区的情况
 * -Xms: 设置java程序启动时的初始堆大小
 * -Xmx: 设置java程序能获得的最大堆大小
 * -Xmx20m -Xms5m -XX:+PrintCommandLineFlags: 可以将隐式或者显示传给虚拟机的参数输出
 */
public class Jvm1 {

    public static void main(String[] args) {

        /**
         * -XX:+UseCompressedOops -XX:UseLargePagesIndividualAllocation
         *
         * -Xms5m -Xmx20m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
         *
         * -XX:+PrintGC -Xms5m -Xmx20m -XX:+UseSerialGC -XX:+PrintGCDetails
         */

        //查看GC信息
        System.out.println("max memory:" + Runtime.getRuntime().maxMemory());
        System.out.println("free memory:" + Runtime.getRuntime().freeMemory());
        System.out.println("total memory:" + Runtime.getRuntime().totalMemory());

        byte[] b1 = new byte[1 * 1024 * 1024];
        System.out.println("分配了1M");
        System.out.println("max memory:" + Runtime.getRuntime().maxMemory());
        System.out.println("free memory:" + Runtime.getRuntime().freeMemory());
        System.out.println("total memory:" + Runtime.getRuntime().totalMemory());

        byte[] b2 = new byte[4 * 1024 * 1024];
        System.out.println("分配了4M");
        System.out.println("max memory:" + Runtime.getRuntime().maxMemory());
        System.out.println("free memory:" + Runtime.getRuntime().freeMemory());
        System.out.println("total memory:" + Runtime.getRuntime().totalMemory());

        int a = 0x00000000fee10000;
        int b = 0x00000000fec00000;

        System.out.println("结果为:" + (a - b) / 1024);
    }
}
