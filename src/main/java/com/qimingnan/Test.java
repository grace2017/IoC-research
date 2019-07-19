package com.qimingnan;

import com.qimingnan.beans.Animal;
import com.qimingnan.core.JSONApplicationContext;

public class Test {

    public static void main(String[] args) {
        JSONApplicationContext context = new JSONApplicationContext("test.json");

        Animal cat = (Animal) context.getBean("cat");
        Animal dog = (Animal) context.getBean("dog");

        System.out.println(cat);
        System.out.println(dog);

        cat.eat();

        dog.run();

    }
}