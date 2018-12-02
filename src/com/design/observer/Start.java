package com.design.observer;

import java.util.concurrent.TimeUnit;

public class Start {

    public static void main(String[] args) {
        //不关心返回值
//        onVoid();

        //有返回值
        callBack();
    }

    public static void onVoid(){
        Observable observableThread = new ObservableThread<>(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("finished done.");
            return null;
        });

        observableThread.start();
    }

    public static void callBack(){
        final TaskLifeCycle<String> lifeCycle = new TaskLifeCycle<String>() {
            @Override
            public void onStart(Thread thread) {
                System.out.println("onStart");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onRunning(Thread thread) {
                System.out.println("onRunning");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }

            @Override
            public void onError(Thread thread, Exception e) {
                throw new RuntimeException("nullPointException");
            }
        };

        Observable observableThread = new ObservableThread<>(lifeCycle,()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("finished done.");
            return "Hello Observer";
        });

        observableThread.start();
    }
}
