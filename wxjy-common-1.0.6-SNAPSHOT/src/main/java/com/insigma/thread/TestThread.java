package com.insigma.thread;

class SubTread extends Thread{
    @Override
    public void run() {
        for(int i=1;i<=100;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
public class TestThread {
    public static void main(String[] args) {
        SubTread st1 = new SubTread();
        st1.setName("子线程1");
        st1.start();
        Thread.currentThread().setName("==========主线程");
        for(int i=1;i<=100;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
            if(i % 10 ==0){
                Thread.currentThread().yield();
            }
        }
    }
}
