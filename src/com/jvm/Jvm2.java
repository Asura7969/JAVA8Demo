package com.jvm;

/**
 * 新生代配置
 * -Xmn:可以设置新生代的大小，设值一个比较打的新生代会减少老年代的大小，
 *      这个参数对系统性能以及GC行为有很大影响，新生代大小一般会设值整
 *      个堆空间的 1/3 到 1/4 左右;
 *
 * -XX:SurvivorRatio:
 *      用来设值新生代中 eden 空间和 from/to 空间的比例
 *      -XX:SurvivorRatio = eden / from = eden / to
 *
 * -XX:NewRatio:
 *      设值新生代和老年代的比例
 *      -XX:NewRatio = 老年代 / 新生代
 *
 * 基本策略:尽可能将对象预留在新生代中,减少老年代的GC次数
 */
public class Jvm2 {

    /**
     * 第一次配置    eden 2 = from 1 + to 1
     * -Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
     *
     * 第二次配置    eden 2 = from 1 + to 1
     * -Xms20m -Xmx20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
     *
     * 第三次配置
     * -XX:NewRatio = 老年代 / 新生代
     * -Xms20m -Xmx20m -XX:NewRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
     */

    public static void main(String[] args) {
        byte[] b = null;
        //连续向系统申请10MB空间
        for(int i = 0; i < 10; i ++){
            b = new byte[1 * 1024 * 1024];
        }
    }

}
