package com.sync;

public class RunThread extends Thread {

    //volatile
    private boolean isRunning = true;

    private void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void run(){
        System.out.println("进入run方法..");
        while (isRunning == true){
            boolean a = isRunning;
        }
        System.out.println("线程终止");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread rt = new RunThread();
        rt.start();
        Thread.sleep(3000);
        rt.setRunning(false);
        System.out.println("isRunning已经被设值");
        Thread.sleep(1000);
        System.out.println(rt.isRunning);
    }
}
