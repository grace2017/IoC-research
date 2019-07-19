package com.qimingnan.importselector;

import com.qimingnan.beans.ImportSelectorDog3;
import com.qimingnan.beans.ImportSelectorDog4;
import com.qimingnan.core.AnnotationApplicationContext;
import com.qimingnan.interfaces.ImportSelector;

import java.lang.annotation.Annotation;

public class AnimalImportSelector2 implements ImportSelector {
    public String[] selectImports(AnnotationApplicationContext applicationContext, String value) {
        return new String[]{ImportSelectorDog3.class.getName(), ImportSelectorDog4.class.getName()};
    }
}
