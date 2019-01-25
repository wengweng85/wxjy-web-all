package com.insigma.thread;

/**
 * Created by admin on 2018/11/30.
 */
public class ThreadTest {

    private static class Thread1 extends  Thread{
        public void run(){
            System.out.println("进入线程:"+Thread.currentThread().getName());
        }
    }

    public static void main(String [] args){
        System.out.println("main进入线程:"+Thread.currentThread().getName());
        ThreadTest test=new ThreadTest();
        Thread1 t1=new Thread1();
        t1.start();
        /*try{*/
            t1.yield();
       /* }catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("线程"+Thread.currentThread().getName()+"继续执行");

    }
}
