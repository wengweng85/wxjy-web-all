package com.insigma.thread;

/**
 * Created by wengsh on 2018/12/21.
 */
public class BootStrapClassLoader {

    public static void main(String [] args){
        System.out.println("Bootstrap:"+String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
