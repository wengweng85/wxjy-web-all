package com.insigma.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by admin on 2018/11/26.
 */
public class DynamicProxyHandler implements InvocationHandler {
    Object realCookManager;

    DynamicProxyHandler(ICook realCookManager){
        this.realCookManager=realCookManager;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke start");
        System.out.println(method.getName());
        method.invoke(realCookManager,args);
        System.out.println("invokd end");
        return  null;
    }



}
