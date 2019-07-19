package com.qimingnan;

import com.qimingnan.beans.Animal;
import com.qimingnan.beans.BeanDog;
import com.qimingnan.config.AppConfig;
import com.qimingnan.core.AnnotationApplicationContext;

public class AnnotationTest {

    public static void main(String[] args) {
        AnnotationApplicationContext context = new AnnotationApplicationContext(AppConfig.class);

        // 错误cast
//        BeanDog beandog = (BeanDog) context.getBean("beandog");

        Animal beandog = (Animal) context.getBean("beandog");
        Animal beancat = (Animal) context.getBean("beancat");

        beandog.eat();
        beancat.run();
    }
}
