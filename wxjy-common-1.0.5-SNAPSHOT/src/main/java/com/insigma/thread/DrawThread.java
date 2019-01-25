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
                System.out.println(getName()+"ȡǮ�ɹ��� ȡ��="+drawAmount);
                /*try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                account.setBalance( (account.getBalance()-drawAmount));
                System.out.println("\t ���Ϊ��"+account.getBalance());
            }
            else{
                System.out.println(getName()+"ȡǮʧ�ܣ��˺����㣡");
            }
        }
      System.out.println("����ͬ����������Ĵ����ܷ�ִ�У�");

    }
}
