package com.qimingnan.beans;

import com.qimingnan.annotation.Component;

@Component
public class BeanDog implements Animal {

    public void eat() {
        System.out.println("Dog Eat...");
    }

    public void run() {

    }
}
