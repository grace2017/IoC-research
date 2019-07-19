package com.qimingnan.config;

import com.qimingnan.annotation.ComponentScan;
import com.qimingnan.annotation.EnableShuai;
import com.qimingnan.annotation.Import;
import com.qimingnan.beans.ImportDog;
import com.qimingnan.beans.ImportDog2;
import com.qimingnan.importselector.AnimalImportSelector2;

@ComponentScan("com.qimingnan")
@Import({ImportDog.class, ImportDog2.class, AnimalImportSelector2.class})
@EnableShuai("com.qimingnan.beans.ImportSelectorDog2")
public class AppConfig {
}
