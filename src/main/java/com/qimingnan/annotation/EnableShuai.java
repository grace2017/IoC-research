package com.qimingnan.annotation;

import com.qimingnan.importselector.AnimalImportSelector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({AnimalImportSelector.class})
public @interface EnableShuai {

    String value() default "";
}
