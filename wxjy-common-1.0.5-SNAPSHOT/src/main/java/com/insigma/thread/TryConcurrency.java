package com.insigma.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2018/11/29.
 */
public class TryConcurrency {

    public static void main(String [] args){
        new Thread(){
            public void  run(){
                enjoyMusic();
            }
        }.start();
        browseNews();
    }

    private static void browseNews(){
        for(;;){
            System.out.println("red news");
            sleep(1);
        }
    }

    private static void enjoyMusic(){
        for(;;){
            System.out.println("enjoy music");
            sleep(1);
        }
    }

    private static void sleep(int seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
