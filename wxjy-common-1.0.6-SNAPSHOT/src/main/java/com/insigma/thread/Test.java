package com.insigma.thread;

/**
 * Created by admin on 2018/11/16.
 */
public class Test {

    public static void main(String [] args){
        System.out.println(Thread.currentThread().getName());
        Account account=new Account("123456",1000);
        DrawThread a=new DrawThread("A",account,800);
        a.start();

        DrawThread b=new DrawThread("B",account,800);
        b.start();
    }
}
