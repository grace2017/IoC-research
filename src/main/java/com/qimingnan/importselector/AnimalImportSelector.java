package com.qimingnan.importselector;

import com.qimingnan.beans.ImportSelectorDog1;
import com.qimingnan.beans.ImportSelectorDog2;
import com.qimingnan.core.AnnotationApplicationContext;
import com.qimingnan.interfaces.ImportSelector;

public class AnimalImportSelector implements ImportSelector {
    public String[] selectImports(AnnotationApplicationContext applicationContext, String value) {
        if (0 != value.length()) {
            return new String[]{value};
        }

        return new String[]{ImportSelectorDog1.class.getName(), ImportSelectorDog2.class.getName()};
    }
}
