/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

package com.yxxy.c_004;

import java.util.concurrent.*;

public class T {

	private static int count = 100;
	
	public synchronized static void m() { //这里等同于synchronized(yxxy.c_004.T.class)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() throws InterruptedException {
		synchronized(T.class) { //考虑一下这里写synchronized(this)是否可以？
			if(count == 0){
				return;
			}
			count --;

			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		while (true) {
			threadPool.submit(()->{
				try {
					T.mm();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});

		}

	}

}
