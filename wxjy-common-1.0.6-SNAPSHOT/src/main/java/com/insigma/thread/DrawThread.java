package com.insigma.thread;

/**
 * Created by admin on 2018/11/16.
 */
public class DrawThread extends Thread {
    private Account account;
    private double drawAmount;

    public DrawThread(String name,Account account,double drawAmount){
        super(name);
        this.account=account;
        this.drawAmount=drawAmount;
    }

    public void run(){
        System.out.println(Thread.currentThread().getName());
        synchronized (this){
            if(account.getBalance()>=drawAmount){
                System.out.println(getName()+"取钱成功！ 取出="+drawAmount);
                /*try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                account.setBalance( (account.getBalance()-drawAmount));
                System.out.println("\t 余额为："+account.getBalance());
            }
            else{
                System.out.println(getName()+"取钱失败，账号余额不足！");
            }
        }
      System.out.println("测试同步代码块后面的代码能否执行！");

    }
}
