package com.qimingnan.interfaces;

import com.qimingnan.core.AnnotationApplicationContext;

public interface Condition {

    public boolean matches(AnnotationApplicationContext applicationContext);
}
