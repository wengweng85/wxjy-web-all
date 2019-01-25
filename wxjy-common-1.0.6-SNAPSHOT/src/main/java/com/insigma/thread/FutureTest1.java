package com.insigma.thread;

import java.util.concurrent.*;

/**
 * Created by admin on 2018/11/23.
 */
public class FutureTest1 {

    public static void main(String [] args){

        Task task1=new Task();
        FutureTask<Integer> futureTask=new FutureTask<Integer>(task1){
            // 异步任务执行完成，回调
            @Override
            protected void done() {
                try {
                    System.out.println("future.done():" + get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        };

        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(futureTask);

    }




    static class Task implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            int i=0;
            for(;i<10;i++){
                try{
                    System.out.println(Thread.currentThread().getName() + "_" + i);
                    Thread.sleep(500);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
            return i;
        }
    }
}