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
        System.out.println("�ȴ�5�룬ȷ��thread1�Ѿ�ִ����ϣ�");

        new Thread(new Runnable() {
            public void run() {
                multiThread2.printNum("thread2", "b");
            }
        }).start();
    }
}
