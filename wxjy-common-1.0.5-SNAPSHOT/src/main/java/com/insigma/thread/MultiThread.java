package com.insigma.thread;

/**
 * Created by admin on 2018/11/16.
 */
public class MultiThread    {

    private int num = 200;

    public synchronized void printNum(String threadName, String tag) {
        if (tag.equals("a")) {
            num = num - 100;
            System.out.println(threadName + " tag a,set num over!");
        } else {
            num = num - 200;
            System.out.println(threadName + " tag b,set num over!");
        }
        System.out.println(threadName + " tag " + tag + ", num = " + num);
    }



}
