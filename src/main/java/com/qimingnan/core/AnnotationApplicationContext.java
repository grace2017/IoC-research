package com.qimingnan.core;


import com.qimingnan.annotation.*;
import com.qimingnan.beans.ConditionalDog;
import com.qimingnan.interfaces.Condition;
import com.qimingnan.interfaces.ImportSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;

/**
 *  优化：
 *      如何提供包扫描速度
 *
 */
public class AnnotationApplicationContext extends BeanFactoryImpl {

    private Class configClass;

    private Logger logger = LoggerFactory.getLogger("AnnotationApplicationContext");

    public AnnotationApplicationContext(Class appConfigClass) {
        this.configClass = appConfigClass;

        try {
            componentScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        refresh();
    }

    private void refresh() {
        
    }

    private void componentScan() throws Exception {
        // 处理@EnableShuai
        EnableShuai enableShuaiAnnot = (EnableShuai) this.configClass.getAnnotation(EnableShuai.class);
        if (null != enableShuaiAnnot) {
            // 获取该注解修饰的类、接口
            Class<? extends Annotation> thisClass = enableShuaiAnnot.annotationType();

            // 获取修饰@EnableShuai的注解@Import
            Import importAnno = thisClass.getAnnotation(Import.class);

            handleImportAnnotation(importAnno, enableShuaiAnnot.value());
        }

        // 检测是否被@Import修饰
        Import importAnno = (Import) this.configClass.getAnnotation(Import.class);

        handleImportAnnotation(importAnno, null);

        // 检测是否被@ComponentScan修饰
        ComponentScan componentScan = (ComponentScan) this.configClass.getAnnotation(ComponentScan.class);
        if (null == componentScan) {
            throw new RuntimeException("配置文件未检测到@ComponentScan");
        }

        // 获取扫描路径
        String value = componentScan.value();
        if (null == value || 0 == value.length()) {
            throw new RuntimeException("@ComponentScan的value不得为空");
        }

        // 计算出指定扫描路径的绝对路径
        String classesPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String path = value.replace('.', File.separatorChar);
        String completePath = classesPath + path;

        File file = new File(completePath);

        if (null == file && !file.exists()) {
            throw new RuntimeException("不存在的目录: " + completePath);
        }

        if (!file.isDirectory()) {
            throw new RuntimeException("不是一个目录: " + completePath);
        }

        // 开始扫描
        for (File fileItem: file.listFiles()) {
            if (fileItem.isDirectory()) {
                for (File fileItem2: fileItem.listFiles()) {
                    getAllComponent(fileItem2, classesPath);
                }
            } else if (fileItem.isFile()) {
                getAllComponent(fileItem, classesPath);
            } else {
                // 不是文件、目录
            }
        }
    }

    private void handleImportAnnotation(Import importAnno, String value) {
        Class<?>[] importClasses = importAnno.value();

        if (importClasses.length > 0) {
            for (Class<?> importClass : importClasses) {
                // 处理ImportSelector的子类
                if (ImportSelector.class.isAssignableFrom(importClass)) {
                    logger.info("ImportSelector的子类: " + importClass);

                    ImportSelector importSelector = null;

                    try {
                        importSelector = (ImportSelector) importClass.newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    String[] classNames = importSelector.selectImports(this, value);
                    if (classNames.length > 0) {
                        for (String className : classNames) {
                            // 取出className
                            String[] strings = className.split("\\.");

                            // 注册Bean
                            registerBeans(strings[strings.length - 1].toLowerCase(), className);
                        }
                    }
                } else {
                    // 注册Bean
                    registerBeans(importClass.getSimpleName().toLowerCase(), importClass.getName());
                }
            }
        }
    }

    private void getAllComponent(File fileItem, String classesPath) {
        String className = fileItem.getPath().replace(classesPath, "");
        className = className.replace(File.separatorChar, '.');
        className = className.replace(".class", "");

        // 获取Class对象
        Class clazz = null;
        try {
            clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (null == clazz) {
            System.out.println("不合法的class文件: " + fileItem);

            return;
        }

        // 如果是注解文件，pass
        if (clazz.isAnnotation()) {
            logger.info("注解文件，pass->" + fileItem);

            return;
        }

        // 获取每个对象的注解，如果被@Component修饰就存储
        if (null != clazz.getAnnotation(Component.class)) {
            // 处理@Conditional
            Conditional conditionalAnnotation;

            if (null != (conditionalAnnotation = (Conditional) clazz.getAnnotation(Conditional.class))) {
                Class<? extends Condition> value = conditionalAnnotation.value();

                try {
                    if (!value.newInstance().matches(this)) {
                        logger.info("【不装载】matches false->" + fileItem.getName());

                        return;
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            // 注册Bean
            registerBeans(fileItem.getName().replace(".class", "").toLowerCase(), className);
        }
    }
}
