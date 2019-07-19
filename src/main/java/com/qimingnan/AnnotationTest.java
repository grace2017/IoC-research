package com.qimingnan;

import com.qimingnan.beans.Animal;
import com.qimingnan.beans.ImportSelectorDog2;
import com.qimingnan.config.AppConfig;
import com.qimingnan.core.AnnotationApplicationContext;
import com.qimingnan.importselector.AnimalImportSelector2;
import com.qimingnan.interfaces.ImportSelector;

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


//        Animal importdog = (Animal) context.getBean("importdog");
//        importdog.eat();

        // 测试ImportSelector
        Animal importselectordog3 = (Animal) context.getBean("importselectordog3");
        importselectordog3.run();

        // 测试@EnableShuai，空指针，因为@EnableShuai没有注入这个类
//        Animal importselectordog1 = (Animal) context.getBean("importselectordog1");
//        importselectordog1.run();

        Animal importselectordog2 = (Animal) context.getBean("importselectordog2");
        importselectordog2.run();
    }
}
