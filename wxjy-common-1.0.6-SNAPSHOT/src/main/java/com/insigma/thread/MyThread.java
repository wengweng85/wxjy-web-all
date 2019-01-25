package com.insigma.thread;

/**
 * Created by admin on 2018/11/16.
 */
public class MyThread extends Thread {

    private int count = 5;

    @Override
    public synchronized  void run() {
        count--;
        System.out.println(this.currentThread().getName() + " count:" + count);
    }

}
