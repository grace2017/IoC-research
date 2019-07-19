package com.qimingnan.interfaces;

import com.qimingnan.core.AnnotationApplicationContext;

import java.lang.annotation.Annotation;

public interface ImportSelector {

    String[] selectImports(AnnotationApplicationContext applicationContext, String value);
}
