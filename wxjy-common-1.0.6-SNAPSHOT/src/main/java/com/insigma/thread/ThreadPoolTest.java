package com.insigma.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2018/11/15.
 */
public class ThreadPoolTest {

    public static void main(String [] a) throws InterruptedException{
        ExecutorService threadPool= Executors.newCachedThreadPool();
        for (int i = 1; i <= 3; i ++) {
            final  int task = i;   //10������
            TimeUnit.SECONDS.sleep(10);
            threadPool.execute(new Runnable() {    //����һ��Runnableʵ��
                public void run() {
                    System.out.println("�߳����֣� " + Thread.currentThread().getName() +  "  ������Ϊ�� "+task);
                }
            });
        }
    }
}
