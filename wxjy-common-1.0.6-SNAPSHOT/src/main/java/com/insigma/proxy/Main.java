package com.insigma.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by admin on 2018/11/26.
 */
public class Main {

    public static void main(String [] a){
        CookManager cookManager=new CookManager();
        DynamicProxyHandler dynamicProxyHandler=new DynamicProxyHandler(cookManager);
        ICook iCook=(ICook)Proxy.newProxyInstance(dynamicProxyHandler.getClass().getClassLoader(),cookManager.getClass().getInterfaces(),dynamicProxyHandler);
        //打印一下代理类的类名
        System.out.println(iCook.getClass().getName());
        iCook.dealWithFood();
        iCook.cook();
    }
}
