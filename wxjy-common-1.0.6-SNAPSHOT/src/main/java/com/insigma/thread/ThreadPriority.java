package com.insigma.thread;

/**
 * Created by admin on 2018/11/29.
 */
public class ThreadPriority {

    public static  void main(String [] args){
        Thread t1=new Thread();
        System.out.println(t1.getPriority());
        t1.start();
    }
}
