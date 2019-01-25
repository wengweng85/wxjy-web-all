package com.insigma.proxy;

import java.net.CookiePolicy;

/**
 * Created by admin on 2018/11/26.
 */
public class CookManager implements ICook {
    @Override
    public void dealWithFood() {
        System.out.println("food had been dealed with");
    }

    @Override
    public void cook() {
        System.out.println("cook food");
    }
}
