package com.insigma.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2018/11/23.
 */
public class RateDemo1 {

    public static void main(String [] args){
        RateLimiter ratelimiter=RateLimiter.create(0.5);

        List<Runnable> tasks=new ArrayList<Runnable>();
        for (int i=0;i<10;i++){
            tasks.add(new UserRequest(i));
        }

        ExecutorService executorService= Executors.newCachedThreadPool();
        for(Runnable runnable:tasks){
            System.out.println("µÈ´ýÊ±¼ä£º" + ratelimiter.acquire());
            executorService.submit(runnable);
        }
    }

    private static class UserRequest implements  Runnable{

        private int id;
        public UserRequest(int id){
            this.id=id;
        }
        @Override
        public void run() {
            System.out.println(id);
        }
    }

}
