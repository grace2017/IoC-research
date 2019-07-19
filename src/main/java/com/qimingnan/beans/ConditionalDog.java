package com.qimingnan.beans;

import com.qimingnan.annotation.Component;
import com.qimingnan.annotation.Conditional;

@Component
@Conditional(com.qimingnan.condition.ConditionalDog.class)
public class ConditionalDog implements Animal {
    public void eat() {
        System.out.println("ConditionalDog eat");
    }

    public void run() {
        System.out.println("ConditionalDog run");
    }
}
