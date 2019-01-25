package com.insigma.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by admin on 2018/11/23.
 */
public class FutureTest {

    public static class Task implements Callable<String>{

        @Override
        public String call() {
            System.out.println("ah");
            return "something";
        }

    }
    public static void main(String [] a) throws ExecutionException,InterruptedException{
        List<Future<String>> results=new ArrayList<Future<String>>();
        ExecutorService es= Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            results.add(es.submit(new Task()));
        }
        for (Future<String> result:results){
            System.out.println(result.get());
        }
    }
}
