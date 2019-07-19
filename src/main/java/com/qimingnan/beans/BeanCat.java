package com.qimingnan.beans;

import com.qimingnan.annotation.Component;

@Component
public class BeanCat implements Animal {


    public void eat() {
        System.out.println("BeanCat eat");
    }

    public void run() {
        System.out.println("BeanCat run");
    }
}
