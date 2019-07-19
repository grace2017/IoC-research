package com.qimingnan.annotation;

import com.qimingnan.interfaces.Condition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Conditional {

    Class<? extends Condition> value();
}
