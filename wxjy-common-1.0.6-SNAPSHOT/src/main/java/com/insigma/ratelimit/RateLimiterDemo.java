package com.insigma.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by admin on 2018/11/23.
 */
public class RateLimiterDemo {

    public static void main(String [] a){
        testNoRateLimiter();
        testWithRateLimiter();
    }

    public static void testNoRateLimiter() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }
    public static void testWithRateLimiter(){
        Long start=System.currentTimeMillis();
        RateLimiter limiter=RateLimiter.create(10.0);
        for(int i=0;i<100;i++){
            limiter.acquire();
            System.out.println("call execute..."+i);
        }
        Long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
