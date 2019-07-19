package com.qimingnan.config;

import com.qimingnan.annotation.ComponentScan;
import com.qimingnan.annotation.Import;
import com.qimingnan.beans.ImportDog;
import com.qimingnan.beans.ImportDog2;

@ComponentScan("com.qimingnan")
@Import({ImportDog.class, ImportDog2.class})
public class AppConfig {
}
