package com.insigma.ratelimit;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2018/11/23.
 */

    public class ListenableFutureDemo {
        public static void main(String[] args) {
            testRateLimiter();
            testListenableFuture();
        }

        /**
         * RateLimiter������JDK���ź���Semphore�����������ƶ���Դ�������ʵ��߳���
         */
        public static void testRateLimiter() {
            ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

            RateLimiter limiter = RateLimiter.create(5.0); // ÿ�벻����5�������ύ

            for (int i = 0; i < 10; i++) {
                limiter.acquire(); // ����RateLimiter, ����permits�ᱻ����

                final ListenableFuture<Integer> listenableFuture = executorService
                        .submit(new Task("is "+ i));
            }
        }

        public static void testListenableFuture() {
            ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

            final ListenableFuture<Integer> listenableFuture = executorService.submit(new Task("testListenableFuture"));


            //ͬ����ȡ���ý��
            try {
                System.out.println(listenableFuture.get());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }

            //��һ�ַ�ʽ
            listenableFuture.addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("get listenable future's result " + listenableFuture.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }, executorService);

            //�ڶ��ַ�ʽ
            Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    System.out.println("get listenable future's result with callback " + result);
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    class Task implements Callable<Integer> {
        String str;
        public Task(String str){
            this.str = str;
        }
        @Override
        public Integer call() throws Exception {
            System.out.println("call execute.." + str);
            TimeUnit.SECONDS.sleep(1);
            return 7;
        }
    }
