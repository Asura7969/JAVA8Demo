package com.design.observer;

public interface TaskLifeCycle<T> {

    //任务启动时会触发 onStart 方法
    void onStart(Thread thread);

    //任务正在运行时会触发 onRunning 方法
    void onRunning(Thread thread);

    //任务结束时会触发结束方法,其中 result 时任务结束时的执行结果
    void onFinish(Thread thread,T result);

    //任务执行报错时会触发 onError 方法
    void onError(Thread thread,Exception e);

    /**
     * 声明周期实现接口空实现（Adapter）
     * @param <T>
     */
    class EmptyLifeCycle<T> implements TaskLifeCycle<T> {

        @Override
        public void onStart(Thread thread) {
            //do nothing
        }

        @Override
        public void onRunning(Thread thread) {
            //do nothing
        }

        @Override
        public void onFinish(Thread thread, T result) {
            //do nothing
        }

        @Override
        public void onError(Thread thread, Exception e) {
            //do nothing
        }
    }
}
