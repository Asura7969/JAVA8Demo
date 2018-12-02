package com.design.observer;

/**
 * 该接口暴露给调用者使用
 */
public interface Observable {
    //任务声明周期枚举
    enum Cycle{
        STARTED,RUNNING,DONE,ERROR;
    }

    //获取当前声明周期状态
    Cycle getCycle();

    //定义启动线程的方法,主要是为了屏蔽Thread的其他方法
    void start();

    //定义线程的打断方法,主要与start方法一样,也是为了屏蔽Thread的其他方法
    void interrupt();

}
