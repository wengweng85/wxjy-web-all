package com.insigma.spring;

/**
 * Created by admin on 2018/11/16.
 */
public class Car {

    private int maxSpeed;
    private String brand;
    private double price;

    public Car(){

    }
    //���ι��췽��
    public Car(int maxSpeed,String brand, double price){
        this.maxSpeed=maxSpeed;
        this.brand=brand;
        this.price=price;
    }

    //һ��Ҫд��ע������set����
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void run(){
        System.out.println("brand:"+brand+",maxSpeed:"+maxSpeed+",price:"+price);
    }
}
