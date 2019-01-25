package com.insigma.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by admin on 2018/11/15.
 */
public class CallableDemo {

    static class SumTask implements Callable<Long>{


        @Override
        public Long call() throws Exception {
            long sum=0;
            for (int i=0;i<90000;i++){
                sum+=i;
            }
            return sum;
        }
    }

    public static void main(String [] a) throws Exception{
        System.out.println("Start:"+System.nanoTime());
        FutureTask<Long> futureTask=new FutureTask<Long>(new SumTask());
        Executor executor= Executors.newSingleThreadExecutor();
        executor.execute(futureTask);
        System.out.println(futureTask.get());
        System.out.println("End:" + System.nanoTime());
    }
}


