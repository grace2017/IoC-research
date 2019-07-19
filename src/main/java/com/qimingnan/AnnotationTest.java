package com.qimingnan;

import com.qimingnan.beans.Animal;
import com.qimingnan.beans.ImportDog;
import com.qimingnan.config.AppConfig;
import com.qimingnan.core.AnnotationApplicationContext;

public class AnnotationTest {

    public static void main(String[] args) {
        AnnotationApplicationContext context = new AnnotationApplicationContext(AppConfig.class);

        // 错误cast
//        BeanDog beandog = (BeanDog) context.getBean("beandog");

//        Animal beandog = (Animal) context.getBean("beandog");
//        Animal beancat = (Animal) context.getBean("beancat");
//
//        beandog.eat();
//        beancat.run();

        System.out.println("=============");

//        Animal conditionaldog = (Animal) context.getBean("conditionaldog");
//        if (null != conditionaldog) {
//            conditionaldog.run();
//        } else {
//            System.out.println("未注入");
//        }

        Animal importdog = (Animal) context.getBean("importdog");
        importdog.eat();
    }
}
