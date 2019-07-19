package com.qimingnan.condition;

import com.qimingnan.core.AnnotationApplicationContext;
import com.qimingnan.interfaces.Condition;

public class ConditionalDog implements Condition {
    public boolean matches(AnnotationApplicationContext applicationContext) {
        return false;
    }
}
