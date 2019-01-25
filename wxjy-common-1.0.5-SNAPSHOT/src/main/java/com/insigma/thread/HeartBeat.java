package com.insigma.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2018/11/15.
 */
public class HeartBeat
{
    public static void main(String [] a){
        ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(5);
        Runnable task=new Runnable() {
            @Override
            public void run() {
                System.nanoTime();
                System.out.println("HeartBeat.........................");
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(task,5,3, TimeUnit.SECONDS);

    }
}
