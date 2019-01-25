package com.insigma.thread;

/**
 * Created by admin on 2018/11/16.
 */
public class Test4 {

    public static void main(String[] args) throws InterruptedException {
        final MultiThreadStatic multiThread1 = new MultiThreadStatic();
        final MultiThreadStatic multiThread2 = new MultiThreadStatic();

        new Thread(new Runnable() {
            public void run() {
                multiThread1.printNum("thread1", "a");
            }
        }).start();

        Thread.sleep(5000);
        System.out.println("等待5秒，确保thread1已经执行完毕！");

        new Thread(new Runnable() {
            public void run() {
                multiThread2.printNum("thread2", "b");
            }
        }).start();
    }
}
